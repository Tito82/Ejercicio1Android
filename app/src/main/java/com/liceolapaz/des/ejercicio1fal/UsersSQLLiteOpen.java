package com.liceolapaz.des.ejercicio1fal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


    public class UsersSQLLiteOpen extends SQLiteOpenHelper {
        String consultaCreacionTabla = "CREATE TABLE IF NOT EXISTS usuarios " +
                "(email TEXT PRIMARY KEY," +
                "password TEXT NOT NULL," +
                "idioma TEXT CHECK(idioma IN('ES','GL','EN')) ," +
                "edad INTEGER NOT NULL," +
                "nombre TEXT NOT NULL)";
        public UsersSQLLiteOpen(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
            super(contexto, nombre, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(consultaCreacionTabla);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS usuarios");
            db.execSQL(consultaCreacionTabla);
        }
    }




