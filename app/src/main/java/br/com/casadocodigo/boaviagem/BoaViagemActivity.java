package br.com.casadocodigo.boaviagem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class BoaViagemActivity extends AppCompatActivity {

    private static final String MANTER_CONECTADO = "manter_conectado";
    private CheckBox manterConectado;

    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.usuario_id);
        senha = (EditText) findViewById(R.id.senha);

        manterConectado = (CheckBox) findViewById(R.id.manterConectado);

        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        boolean conectado = preferencias.getBoolean(MANTER_CONECTADO, false);
        if(conectado){
            startActivity(new Intent(this, DashboardActivity.class));
        }
    }

    public void entrarOnClick(View v){
        String usuarioInformado = usuario.getText().toString();
        String senhaInformada = senha.getText().toString();

        if("leitor".equals(usuarioInformado) && "123".equals(senhaInformada)){

            SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putBoolean(MANTER_CONECTADO, manterConectado.isChecked());
            editor.commit();

            startActivity(new Intent(this, DashboardActivity.class));
        }else{
            String mensagemErro = getString(R.string.erro_autenticacao);
            Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show();
        }
    }
}
