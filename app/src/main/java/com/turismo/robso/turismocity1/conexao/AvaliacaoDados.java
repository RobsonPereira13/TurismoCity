package com.turismo.robso.turismocity1.conexao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.turismo.robso.turismocity1.basicas.Avaliacao;
import com.turismo.robso.turismocity1.basicas.Usuario;

/**
 * Created by GabrielDev on 09/06/2018.
 */

public class AvaliacaoDados extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TurismoCity1.db";
    private static final int DATABASE_VERSION = 1;

    public static final String AVALIACAO_TABELA_NOME = "avaliacao";
    public static final String AVALIACAO_TABLELA_NOME2="usuario";

    public static final String AVALIACAO_COLUNA_ID = "idAvalicao";
    public static final String AVALIACAO_COLUNA_PONTUACAO = "pontos";
    public static final String AVALIACAO_COLUNA_MESSAGE = "msgAvali";
    public static final String USUARIO_COLUNA_ID = "id";


    public AvaliacaoDados(Context context) {

        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + AVALIACAO_TABELA_NOME +
                        "(" + AVALIACAO_COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        AVALIACAO_COLUNA_PONTUACAO+"INTEGER,"+
                        AVALIACAO_COLUNA_MESSAGE+" TEXT,"+
                        USUARIO_COLUNA_ID+"INTEGER,"+
                        "FOREIGN KEY ("+USUARIO_COLUNA_ID+") REFERENCES "+AVALIACAO_TABLELA_NOME2+"("+USUARIO_COLUNA_ID+
                        "));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AVALIACAO_TABELA_NOME);
        onCreate(db);
    }

    public boolean insert(Avaliacao avaliacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AVALIACAO_COLUNA_PONTUACAO,avaliacao.pontos);
        contentValues.put(AVALIACAO_COLUNA_MESSAGE,avaliacao.msgAvali);
        contentValues.put(USUARIO_COLUNA_ID, String.valueOf(avaliacao.usuario));

        db.insert(AVALIACAO_TABELA_NOME, null, contentValues);
        return true;
    }



    public boolean update(Avaliacao avaliacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AVALIACAO_COLUNA_PONTUACAO,avaliacao.pontos);
        contentValues.put(AVALIACAO_COLUNA_MESSAGE,avaliacao.msgAvali);
        contentValues.put(USUARIO_COLUNA_ID, String.valueOf(avaliacao.usuario));

        db.update(AVALIACAO_TABELA_NOME, contentValues, AVALIACAO_COLUNA_ID + " = ? ", new String[] { Integer.toString(avaliacao.idAvali)} );
        return true;
    }

}
