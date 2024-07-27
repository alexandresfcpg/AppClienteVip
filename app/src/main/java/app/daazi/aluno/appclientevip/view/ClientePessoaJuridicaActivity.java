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
import app.daazi.aluno.appclientevip.model.ClientePJ;

public class ClientePessoaJuridicaActivity extends AppCompatActivity {

    Cliente novoVip;
    ClientePJ novoClientePJ;

    private SharedPreferences preferences;

    EditText editCnpj, editRazaoSocial, editDataAbertura;
    CheckBox ckSimplesNacional, ckMei;
    Button btnSalvarContinuar, btnVoltar, btnCancelar;

    boolean isFormularioOK, isSimplesNacional, isMei;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_juridica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOK = validarFormulario()) {

                    novoClientePJ.setCnpj(editCnpj.getText().toString());
                    novoClientePJ.setRazaoSocial(editRazaoSocial.getText().toString());
                    novoClientePJ.setDataAbertura(editDataAbertura.getText().toString());
                    novoClientePJ.setSimplesNacional(isSimplesNacional);
                    novoClientePJ.setMei(isMei);

                    salvarSharedPreferences();

                    Intent intent = new Intent(ClientePessoaJuridicaActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClientePessoaJuridicaActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public void simplesNacional(View view){

        isSimplesNacional = ckSimplesNacional.isChecked();

    }

    public void mei(View view){

        isMei = ckMei.isChecked();

    }
    private void initFormulario() {

        editCnpj = findViewById(R.id.editCnpj);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editDataAbertura = findViewById(R.id.editDataAbertura);
        ckSimplesNacional = findViewById(R.id.ckSimplesNacional);
        ckMei = findViewById(R.id.ckMei);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);

        novoClientePJ = new ClientePJ();

        novoVip = new Cliente();

        restaurarSharedPreferences();
    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCnpj.getText().toString())) {
            editCnpj.setError("*");
            editCnpj.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editRazaoSocial.getText().toString())) {
            editRazaoSocial.setError("*");
            editRazaoSocial.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editDataAbertura.getText().toString())) {
            editDataAbertura.setError("*");
            editDataAbertura.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

    }


}