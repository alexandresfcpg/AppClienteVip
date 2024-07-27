package app.daazi.aluno.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import app.daazi.aluno.appclientevip.R;
import app.daazi.aluno.appclientevip.api.AppUtil;
import app.daazi.aluno.appclientevip.model.Cliente;

public class ClienteVipActivity extends AppCompatActivity {

    Cliente novoVip;
    private SharedPreferences preferences;

    EditText editPrimeiroNome, editSobrenome;
    CheckBox ckPessoaFisica;
    Button btnSalvarContinuar, btnCancelar;

    boolean isFormularioOK, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_vip);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOK = validarFormulario()) {

                    novoVip.setPrimeiroNome(editPrimeiroNome.getText().toString());
                    novoVip.setSobreNome(editSobrenome.getText().toString());
                    novoVip.setPessoaFisica(isPessoaFisica);

                    salvarSharedPreferences();

                    if(isPessoaFisica){

                        Intent intent = new Intent(ClienteVipActivity.this, ClientePessoaFisicaActivity.class);
                        startActivity(intent);

                    }else{

                        Intent intent = new Intent(ClienteVipActivity.this, ClientePessoaJuridicaActivity.class);
                        startActivity(intent);

                    }
                }

            }
        });

    }

    private void initFormulario() {

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobrenome);
        ckPessoaFisica = findViewById(R.id.ckPessoaFisica);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);

        isFormularioOK = false;

        novoVip = new Cliente();

        restaurarSharedPreferences();
    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editPrimeiroNome.getText().toString())) {
            editPrimeiroNome.setError("*");
            editPrimeiroNome.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editSobrenome.getText().toString())) {
            editSobrenome.setError("*");
            editSobrenome.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    public void pessoaFisica(View view){

        isPessoaFisica = ckPessoaFisica.isChecked();

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("primeiroNome", novoVip.getPrimeiroNome());
        dados.putString("sobreNome", novoVip.getSobreNome());
        dados.putBoolean("pessoaFisica", novoVip.isPessoaFisica());
        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

    }
}