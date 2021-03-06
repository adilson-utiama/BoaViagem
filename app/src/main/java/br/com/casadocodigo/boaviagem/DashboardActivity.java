package br.com.casadocodigo.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static br.com.casadocodigo.boaviagem.R.menu.*;

/**
 * Created by adilson on 01/05/2016.
 */
public class DashboardActivity extends AppCompatActivity {

    public static final String MODO_VIAGEM = "modo_viagem";
    public static final String VIAGEM_ID = "viagem_id";
    public static final String VIAGEM_DESTINO = "viagem_destino";
    public static final String MODO_SELECIONAR_VIAGEM = "modo_selecionar_viagem";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public void selecionarOpcao(View view){
        //Com base na view que foi clicada iremos tomar a acao correta
        switch(view.getId()){
            case R.id.nova_viagem:
                startActivity(new Intent(this, ViagemActivity.class));
                break;
            case R.id.novo_gasto:
                SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
                boolean modoViagem = preferencias.getBoolean(MODO_VIAGEM, false);

                if(modoViagem){
                    //obter o id da viagem atual
                    int viagemAtual = 1;
                    String destino = "São Paulo";
                    Intent intent = new Intent(this, GastoActivity.class);
                    intent.putExtra(VIAGEM_ID, viagemAtual);
                    intent.putExtra(VIAGEM_DESTINO, destino);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this, ViagemListActivity.class);
                    intent.putExtra(MODO_SELECIONAR_VIAGEM, true);
                    startActivityForResult(intent, 0);
                }
                break;

            case R.id.minhas_viagens:
                startActivity(new Intent(this, ViagemListActivity.class));
                break;
            case R.id.configuracoes:
                startActivity(new Intent(this, ConfiguracoesActivity.class));
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            int id = data.getExtras().getInt(VIAGEM_ID);
            String destino = data.getExtras().getString(VIAGEM_DESTINO);

            Intent intent = new Intent(this, GastoActivity.class);
            intent.putExtra(VIAGEM_ID, id);
            intent.putExtra(VIAGEM_DESTINO, destino);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, getString(R.string.erro_selecionar_viagem),
                    Toast.LENGTH_SHORT).show();
        }
    }



}
