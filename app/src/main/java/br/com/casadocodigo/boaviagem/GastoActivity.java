package br.com.casadocodigo.boaviagem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import br.com.casadocodigo.boaviagem.database.BoaViagemDAO;
import br.com.casadocodigo.boaviagem.database.DatabaseHelper;

/**
 * Created by adilson on 04/05/2016.
 */
public class GastoActivity extends AppCompatActivity {

    private int ano, mes, dia;
    private Button dataGasto;
    private Spinner categoria;

    private BoaViagemDAO dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gasto);


        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataGasto = (Button) findViewById(R.id.data);
        String data = dia + "/" + (mes+1) + "/" + ano;
        dataGasto.setText(data);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categoria_gasto, android.R.layout.simple_spinner_item
        );
        this.categoria = (Spinner) findViewById(R.id.categoria);
        this.categoria.setAdapter(adapter);

        TextView destino = (TextView) findViewById(R.id.destino);
        destino.setText("Destino Viagem");
    }

    public void selecionarData(View view){
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(R.id.data == id){
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            String data = dia + "/" + (mes+1) + "/" + ano;
            dataGasto.setText(data);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.remover_gasto:
                //remover gasto do banco
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void registrarGasto(View view){
        dao = new BoaViagemDAO(this);
        ContentValues values = getValores();
        Toast.makeText(this, values.toString(), Toast.LENGTH_LONG).show();

    }

    private ContentValues getValores(){
        ContentValues values = new ContentValues();
        Spinner categoria = (Spinner) findViewById(R.id.categoria);
        String categoriaSelecionada = (String) categoria.getSelectedItem();
        EditText valor = (EditText) findViewById(R.id.valor);
        String valorText = valor.getText().toString();
        Button data = (Button) findViewById(R.id.data);
        String dataGasto = data.getText().toString();
        EditText descricaoGasto = (EditText) findViewById(R.id.descricao);
        String descricao = descricaoGasto.getText().toString();
        EditText localGasto = (EditText) findViewById(R.id.local);
        String local = localGasto.getText().toString();

        values.put(DatabaseHelper.Gasto.CATEGORIA, categoriaSelecionada);
        values.put(DatabaseHelper.Gasto.VALOR, valorText);
        values.put(DatabaseHelper.Gasto.DATA, dataGasto);
        values.put(DatabaseHelper.Gasto.DESCRICAO, descricao);
        values.put(DatabaseHelper.Gasto.LOCAL, local);

        return values;

    }

}
