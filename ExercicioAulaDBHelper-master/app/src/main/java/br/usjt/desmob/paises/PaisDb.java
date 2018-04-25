package br.usjt.desmob.paises;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;

import java.util.ArrayList;


import static br.usjt.desmob.paises.PaisContract.PaisEntry.*;

/**
 * Created by Vinicius Maciel on 14/4/2018.
 * RA 816117960
 */

public class PaisDb {
    PaisDbHelper dbHelper;

    public PaisDb(Context contexto){
        dbHelper = new PaisDbHelper(contexto);
    }

    public void inserePaises(Pais[] paises){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete( TABLE_NAME, null, null);

        for(Pais pais:paises){
            ContentValues values = new ContentValues();
            values.put( COLUMN_NAME_NOME, pais.getNome());
            values.put( COLUMN_NAME_REGIAO, pais.getRegiao());
            values.put( COLUMN_NAME_CAPITAL, pais.getCapital());
            values.put( COLUMN_NAME_BANDEIRA, pais.getBandeira());
            values.put( COLUMN_NAME_CODIGO3, pais.getCodigo3());
            values.put( COLUMN_NAME_DEMONIMO, pais.getDemonimo());
            values.put( COLUMN_NAME_SUBREGIAO, pais.getSubRegiao());
            values.put( COLUMN_NAME_POPULACAO, pais.getPopulacao());
            values.put( COLUMN_NAME_GINI, pais.getGini());
            values.put( COLUMN_NAME_IDIOMAS, String.valueOf( pais.getIdiomas() ) );
            values.put( COLUMN_NAME_FRONTEIRAS, String.valueOf( pais.getFronteiras() ) );
            values.put( COLUMN_NAME_FUSOS, String.valueOf( pais.getFusos() ) );
            values.put( COLUMN_NAME_MOEDAS, String.valueOf( pais.getMoedas() ) );
            values.put( COLUMN_NAME_DOMINIOS, String.valueOf( pais.getDominios() ) );
            values.put( COLUMN_NAME_LATITUDE, pais.getLatitude());
            values.put( COLUMN_NAME_LONGITUDE, pais.getLongitude());
            values.put( COLUMN_NAME_AREA, pais.getArea());

            db.insert( TABLE_NAME, null, values);
        }
    }

    public Pais[] selecionaPaises(){
        ArrayList<Pais> paises = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = { COLUMN_NAME_NOME,
                COLUMN_NAME_REGIAO,
                COLUMN_NAME_CAPITAL,
                COLUMN_NAME_BANDEIRA,
                COLUMN_NAME_CODIGO3,
                COLUMN_NAME_LONGITUDE ,
                COLUMN_NAME_LATITUDE,
                COLUMN_NAME_GINI,
                COLUMN_NAME_SUBREGIAO,
                COLUMN_NAME_POPULACAO,
                COLUMN_NAME_DEMONIMO,
                COLUMN_NAME_MOEDAS,
                COLUMN_NAME_IDIOMAS,
                COLUMN_NAME_FUSOS,
                COLUMN_NAME_FRONTEIRAS,
                COLUMN_NAME_DOMINIOS,
                COLUMN_NAME_AREA};
        String ordem = COLUMN_NAME_NOME;

        Cursor c = db.query( TABLE_NAME, colunas, null, null,
                ordem, null, null);
        while(c.moveToNext()) {
            Pais pais = new Pais();
            pais.setNome(c.getString(c.getColumnIndex( COLUMN_NAME_NOME)));
            pais.setRegiao(c.getString(c.getColumnIndex( COLUMN_NAME_REGIAO)));
            pais.setCapital(c.getString(c.getColumnIndex( COLUMN_NAME_CAPITAL)));
            pais.setBandeira(c.getString(c.getColumnIndex( COLUMN_NAME_BANDEIRA)));
            pais.setCodigo3(c.getString(c.getColumnIndex( COLUMN_NAME_CODIGO3)));
            pais.setLongitude(c.getDouble(c.getColumnIndex( COLUMN_NAME_LONGITUDE ) ));
            pais.setLatitude(c.getDouble(c.getColumnIndex( COLUMN_NAME_LATITUDE ) ));
            pais.setGini(c.getDouble(c.getColumnIndex( COLUMN_NAME_GINI ) ));
            pais.setSubRegiao(c.getString(c.getColumnIndex( COLUMN_NAME_SUBREGIAO)));
            pais.setPopulacao(c.getInt(c.getColumnIndex( COLUMN_NAME_POPULACAO ) ));
            pais.setDemonimo(c.getString(c.getColumnIndex( COLUMN_NAME_DEMONIMO)));
            /* pais.setMoedas(c.getString(c.getColumnIndex( COLUMN_NAME_MOEDAS ) ));
           pais.setFronteiras(c.getString(c.getColumnIndex( COLUMN_NAME_FRONTEIRAS ) ));
            pais.setFusos(c.getString(c.getColumnIndex( COLUMN_NAME_FUSOS)));
            pais.setDominios(c.getString(c.getColumnIndex( COLUMN_NAME_DOMINIOS ) ));
            pais.setIdiomas(c.getString(c.getColumnIndex( COLUMN_NAME_IDIOMAS)));*/
            pais.setArea(c.getInt(c.getColumnIndex( COLUMN_NAME_AREA ) ));

            paises.add(pais);
        }
        c.close();
        if(paises.size()> 0) {
            return paises.toArray(new Pais[0]);
        } else {
            return new Pais[0];
        }

        }
    }

