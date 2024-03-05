require('dotenv').config()
const express = require("express");
const pool= require('./db');
const cors = require("cors");
const bcrypt = require('bcrypt');
const fs = require('fs');
const path = require('path');
const bodyParser = require('body-parser');
const { error } = require('console');



const PORT = process.env.PORT || 5000;

const app = express();

app.use(cors());
app.use(express.json());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', (req,res) =>{
   res.end(fs.readFileSync('./views/login.html'));
});

app.get('/statistic',(req,res)=>{
  res.sendFile(__dirname + '/views/page1.html');
})
app.get('/modules',(req,res)=>{
  res.sendFile(__dirname + '/views/page2.html');
})
app.get('/cards',(req,res)=>{
  res.sendFile(__dirname + '/views/page3.html');
})
app.get('/EditModules',(req,res)=>{
  res.sendFile(__dirname + '/views/page4.html');
})
app.get('/Exit',(req,res)=>{
  res.sendFile(__dirname + '/views/login.html');
})
app.post('/modules/add', async (req, res) => {
  try {
    const { Title } = req.body;
    const newModule = await pool.query(
      'INSERT INTO public."Modules" ("Title") VALUES($1)',
      [Title]
    );
    const msg = Title;
    res.send(`
      <html>
        <head>
          <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.6/dist/sweetalert2.min.css">
        </head>
        <body>
          <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.6/dist/sweetalert2.min.js"></script>
          <script>
            window.onload = function() {
              Swal.fire({title: 'Модуль ${msg} добавлен! ',icon: 'success',timer: 1000, showConfirmButton: false});
            };
            window.setTimeout(function() {
              window.location.href = '/modules';
            }, 1000); 
          </script>
        </body>
      </html>
    `);
  } catch (err) {
    console.error(err.message);
    res.send(`<script>window.location.href = '/modules'; alert("ошибка добавления модуля"); </script>`);
  }
});

app.post('/cards/add', async (req,res)=>{
  try{
    const {Word,Picture,Translation,Transcription,ModuleTitle} = req.body;
    await pool.query(
      'INSERT INTO public."Cards" ("Word", "Picture", "Translation", "Transcription", "ModuleTitle") VALUES($1,$2,$3,$4,$5)',
      [Word, Picture, Translation, Transcription, ModuleTitle]
    );
    res.send(`
      <html>
        <head>
          <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.6/dist/sweetalert2.min.css">
        </head>
        <body>
          <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.6/dist/sweetalert2.min.js"></script>
          <script>
            window.onload = function() {
              Swal.fire({title: 'Добавлена новая карточка! ',icon: 'success',timer: 1000, showConfirmButton: false});
            };
            window.setTimeout(function() {
              window.location.href = '/cards';
            }, 1000); // Redirect after 3 seconds
          </script>
        </body>
      </html>
    `);
  }
  catch(err) {
    console.error(err.message);
    res.send(`<script>window.location.href = '/cards'; alert("ошибка добавления карточки"); </script>`);
  }
});

app.get("/allModules", async (req, res) => {
  try {
    const allModules = await pool.query('SELECT * FROM public."Modules"');
    res.status(200).json(allModules.rows);
  } catch (err) {
    console.error(err.message);
  }
});

app.delete('/allModules/:moduleTitle', async (req, res) => {
  try {
    const { moduleTitle } = req.params;
    const deleteTodo = await pool.query('DELETE FROM public."Modules" WHERE "Title" = $1', [
      moduleTitle
    ]);
    res.json("Module was deleted!");
  } catch (err) {
    console.log(err.message);
  }
});

app.put("/modules/:moduleTitle/:newModuleTitle", async (req, res) => {
  try {
    const { moduleTitle, newModuleTitle } = req.params;
    const update = await pool.query(
      'UPDATE public."Modules" SET "Title" = $1 WHERE "Title" = $2',
      [newModuleTitle, moduleTitle]
    );
    res.json("Module was updated!");
  } catch (err) {
    console.error(err.message);
  }
});

app.post('/auth', async (req, res) => {
    try {
      const name = req.body.name;
      const password = req.body.password;
      const admin = await pool.query(
        'SELECT * from public."Users" WHERE "UserName" = $1 AND "Password" = $2', 
         [name, password]
        );
        if (admin.rows.length > 0) {
          res.sendFile(__dirname + '/views/adminPanel.html');
        } else {
          res.sendFile(__dirname + '/views/errorLogin.html');       
        }
    } catch (err) {
      console.error(err.message);
      res.status(500).send('Server Error');
    }
  });

//------API---------
// app.get('/users/:UserName/:Password', async (req, res) => {
//   const {UserName, password} = req.params;
//   const user = await pool.query('SELECT * FROM public."Users" WHERE "UserName" = $1', [UserName], (error, results) => {
//     if (error) {
//       throw error;
//     }
//     else{
//       const decryptedPassword = await bcrypt.compare(password, user.Password);

//       // Создание нового объекта с логином и разхэшированным паролем
//       const userObj = {
//         UserName: user.UserName,
//         Password: decryptedPassword
//       };
  
//       res.status(200).json(userObj);
//     }
//     //res.status(200).json(results.rows[0]);
//   });
// });
app.get('/users/:UserName/:Password', async (req, res) => {
  const { UserName, Password } = req.params;
  
  try {
    const userResult = await pool.query('SELECT * FROM public."Users" WHERE "UserName" = $1', [UserName]);
    const user = userResult.rows[0];
    
    if (!user) {
      res.status(404).json({ error: 'User not found' });
      return;
    }
  
    const decryptedPassword = await bcrypt.compare(Password, user.Password);
  
    res.status(200).json(decryptedPassword);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

//
app.get('/users',(req,res)=>{
  pool.query('SELECT * FROM public."Users"', (error, results) => {
    if (error) {
      throw error;
    }
    res.status(200).json(results.rows);
  });
})

app.post('/user/add',async (req,res)=>{
  try {
    let { UserName, Password } = req.body;
    
    Password = await bcrypt.hash(Password, 10);
   
    const newModule = await pool.query(
      'INSERT INTO public."Users" ("UserName","Password","isAdmin") VALUES($1,$2,$3)',
      [UserName,Password,false]
    );
    res.status(200).json('UserAddDone');
  } catch (error) {
    res.status(500).send({ error: error.message });
  }
});

app.get('/getAllModules',(req,res)=>{
  pool.query('SELECT * FROM public."Modules"', (error, results) => {
    if (error) {
      throw error;
    }
    res.status(200).json(results.rows);
  });
})

app.get('/getCards/:ModuleTitle',(req,res)=>{
  const module = req.params.ModuleTitle;
  pool.query('SELECT * FROM public."Cards" WHERE "ModuleTitle" = $1', [module], (error, results) => {
    if (error) {
      throw error;
    }
    res.status(200).json(results.rows);
  });
})
app.get('/usersModule/:UserName', (req, res) => {
  const user = req.params.UserName;
  pool.query('select * from public."ModuleUser" Where "UsersName" = $1', [user], (error, results) => {
    if (error) {
      throw error;
    }
    res.status(200).json(results.rows);
  });
});


app.post('/favoriteModule/add',async (req,res)=>{
  try {
    const { UsersName, FavoriteModulesTitle } = req.body;
    const newValue = await pool.query(
      'INSERT INTO public."ModuleUser" VALUES($1,$2)',
      [FavoriteModulesTitle,UsersName]
    );
    res.status(200).json('Done');
  } catch (error) {
    res.status(500).send({ error: error.message });
  }
})
app.delete('/deleteFavorite/:ModuleTitle/:UserName', async (req, res) => {
  try {
    const {UserName, ModuleTitle } = req.params;
    const deleteTodo = await pool.query('DELETE FROM public."ModuleUser" WHERE "FavoriteModulesTitle" = $1 and "UsersName"=$2', 
    [ModuleTitle,UserName]
    );
    res.status(200).json("Module was deleted!");
  } catch (err) {
    console.log(err.message);
  }
});

app.get('/getModulesTests/:ModuleTitle',async (req,res)=>{
  try{
    const module =req.params.ModuleTitle;
    const tests = await pool.query('SELECT "TestId", "QuestionText", "CorrectAnswer", "AnswerVariant1", "AnswerVariant2", "AnswerVariant3" FROM public."Questions"  INNER JOIN public."Tests" ON "TestId" = "IdTest" WHERE "ModuleTitle" =$1',[module]);
    res.status(200).send(tests.rows);
  }
  catch(err) {
    console.log(err.message);
  }
})
app.post('/TestResultAdd',async (req,res)=>{
  try {
    const { TestID,UsersName,Mark} = req.body;
    const newValue = await pool.query(
      'INSERT INTO public."TestResults" ("TestId", "UsersName", "Mark") VALUES($1,$2,$3)',
      [TestID,UsersName,Mark]
    );
    res.status(200).json('Done');
  } catch (error) {
    res.status(500).send({ error: error.message });
  }
})
app.get('/UsersResult/:UserName/:ModuleTitle', async (req,res)=>{
  try {
    const { UserName,ModuleTitle} = req.params;
    const newValue = await pool.query(
      'SELECT TR."Mark" FROM public."TestResults" TR INNER JOIN public."Tests" T ON TR."TestId" = T."IdTest" WHERE T."ModuleTitle" =$2 AND TR."UsersName" =$1',
      [UserName,ModuleTitle]
    );
    res.status(200).json(newValue.rows);
  } catch (error) {
    res.status(500).send({ error: error.message });
  }
})


const start = async () => {
    try {
        app.listen(PORT,()=>console.log(`Server started on port ${PORT}`));
    }
    catch(e) {
        console.log(e);
    }
}

start();