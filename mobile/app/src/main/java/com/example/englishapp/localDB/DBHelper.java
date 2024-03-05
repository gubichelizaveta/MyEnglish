package com.example.englishapp.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    private static final int SCHEMA = 1;
    private static final String DATABASE_NAME = "coursework";
    private static final String USER_TABLE = "users";
    private static final String MODULE_TABLE = "module";
    private static final String FAVORITE_TABLE = "favorite";
    private static final String TEST_TABLE = "test";
    private static final String RESULT_TABLE = "result";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                "UserName TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "CONSTRAINT Users_pkey PRIMARY KEY (UserName)," +
                "CONSTRAINT Users_UserName_key UNIQUE (UserName)" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS Modules (" +
                "Title TEXT NOT NULL PRIMARY KEY" +
                ");"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS ModuleUser (" +
                "FavoriteModulesTitle TEXT NOT NULL, " +
                "UsersName TEXT NOT NULL, " +
                //"FOREIGN KEY (FavoriteModulesTitle) REFERENCES Modules (Title) ON UPDATE NO ACTION ON DELETE NO ACTION, " +
                "FOREIGN KEY (UsersName) REFERENCES Users (UserName) ON UPDATE NO ACTION ON DELETE NO ACTION" +
                ");"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS Tests (" +
                "IdTest INTEGER NOT NULL," +
                "ModuleTitle TEXT," +
                "CONSTRAINT Tests_pkey PRIMARY KEY (IdTest)" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS TestResults (" +
                "IdResult INTEGER NOT NULL," +
                "TestId INTEGER NOT NULL," +
                "UsersName TEXT NOT NULL," +
                "Mark INTEGER NOT NULL," +
                "CONSTRAINT TestResults_TestId_fkey FOREIGN KEY (TestId) REFERENCES Tests(IdTest) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION," +
                "CONSTRAINT TestResults_UsersName_fkey FOREIGN KEY (UsersName) REFERENCES Users(UserName) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION" +
                ");"
        );

    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + "Users");
        db.execSQL("drop table if exists " + "Tests");
        db.execSQL("drop table if exists " + "TestResults");
        db.execSQL("drop table if exists " + "Modules");
        db.execSQL("drop table if exists " + "ModuleUser");
        onCreate(db);
    }
}