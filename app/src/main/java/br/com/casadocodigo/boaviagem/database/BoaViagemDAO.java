package br.com.casadocodigo.boaviagem.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.casadocodigo.boaviagem.model.Viagem;

/**
 * Created by adilson on 04/07/2016.
 */
public class BoaViagemDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public BoaViagemDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb(){
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return db;
    }


    public List<Viagem> listarViagens(){
        Cursor cursor = getDb().query(DatabaseHelper.Viagem.TABELA, DatabaseHelper.Viagem.COLUNAS,
                null, null, null, null, null);
        List<Viagem> viagens = new ArrayList<>();
        while(cursor.moveToNext()){
            Viagem viagem = criarViagem(cursor);
            viagens.add(viagem);
        }
        cursor.close();
        return viagens;
    }

    private Viagem criarViagem(Cursor cursor) {
        Viagem viagem = new Viagem();
        String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
        viagem.setDescricao(descricao);
    }

    public void close(){
        helper.close();
    }
}
