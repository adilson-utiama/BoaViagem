package br.com.casadocodigo.boaviagem.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
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

        List<Viagem> viagens = new ArrayList<>();

        Cursor cursor = getDb().query(DatabaseHelper.Viagem.TABELA, DatabaseHelper.Viagem.COLUNAS,
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Viagem viagem = criarViagem(cursor);
            viagens.add(viagem);
        }
        cursor.close();
        return viagens;
    }

    private Viagem criarViagem(Cursor cursor) {
        Viagem viagem;

        Long id = cursor.getLong(cursor.getColumnIndex("_id"));
        String destino = cursor.getString(cursor.getColumnIndex("destino"));
        int tipoViagem = cursor.getInt(cursor.getColumnIndex("tipo_viagem"));
        Date data_chegada = new Date(cursor.getLong(cursor.getColumnIndex("data_chegada")));
        Date data_saida = new Date(cursor.getLong(cursor.getColumnIndex("data_saida")));
        double orcamento = cursor.getDouble(cursor.getColumnIndex("orcamento"));
        int quantidade_pessoas = cursor.getInt(cursor.getColumnIndex("quantidade_pessoas"));

        viagem = new Viagem(
                id, destino, tipoViagem, data_chegada, data_saida, orcamento, quantidade_pessoas
        );

        return viagem;

    }

    public void close(){
        helper.close();
    }
}
