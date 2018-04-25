package br.usjt.desmob.paises;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.usjt.desmob.paises.PaisContract.PaisEntry.*;

/**
 * Created by Vinicius Maciel on 14/4/2018.
 * RA 816117960
 */

public class PaisDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Paises.db";
    public static final String SQL_CREATE_PAIS =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    _ID + " INTEGER PRIMARY KEY,"+
                    COLUMN_NAME_NOME + " TEXT," +
                    COLUMN_NAME_REGIAO + " TEXT," +
                    COLUMN_NAME_CAPITAL + " TEXT," +
                    COLUMN_NAME_BANDEIRA + " TEXT," +
                    COLUMN_NAME_SUBREGIAO + " TEXT," +
                    COLUMN_NAME_LONGITUDE + " DOUBLE," +
                    COLUMN_NAME_LATITUDE + " DOUBLE," +
                    COLUMN_NAME_DEMONIMO + " TEXT," +
                    COLUMN_NAME_AREA + " INT," +
                    COLUMN_NAME_POPULACAO + " INT," +
                    COLUMN_NAME_GINI + " DOUBLE," +
                    COLUMN_NAME_FRONTEIRAS + " TEXT," +
                    COLUMN_NAME_FUSOS + " TEXT," +
                    COLUMN_NAME_DOMINIOS + " TEXT," +
                    COLUMN_NAME_MOEDAS + " TEXT," +
                    COLUMN_NAME_IDIOMAS + " TEXT," +
                    COLUMN_NAME_CODIGO3 + " TEXT)";

    public static final String SQL_DROP_PAIS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public PaisDbHelper(Context contexto){
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PAIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_PAIS);
        db.execSQL(SQL_CREATE_PAIS);
    }
}
