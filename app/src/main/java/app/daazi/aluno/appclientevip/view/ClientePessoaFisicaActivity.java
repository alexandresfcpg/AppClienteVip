package app.daazi.aluno.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.FancyAlertDialog;

import app.daazi.aluno.appclientevip.R;
import app.daazi.aluno.appclientevip.api.AppUtil;
import app.daazi.aluno.appclientevip.model.Cliente;
import app.daazi.aluno.appclientevip.model.ClientePF;

public class ClientePessoaFisicaActivity extends AppCompatActivity {

    Cliente novoVip;
    ClientePF novoClientePF;

    private SharedPreferences preferences;

    EditText editCpf, editNomeCompleto;
    Button btnSalvarContinuar, btnVoltar, btnCancelar;

    boolean isFormularioOK, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_fisica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOK = validarFormulario()) {

                    novoClientePF.setCpf(editCpf.getText().toString());
                    novoClientePF.setNomeCompleto(editNomeCompleto.getText().toString());

                    salvarSharedPreferences();

                    Intent intent;

                    if (isPessoaFisica)
                        intent = new Intent(ClientePessoaFisicaActivity.this, CredencialDeAcessoActivity.class);

                    else
                        intent = new Intent(ClientePessoaFisicaActivity.this, ClientePessoaJuridicaActivity.class);

                    startActivity(intent);
                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClientePessoaFisicaActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FancyAlertDialog.Builder
                        .with(ClientePessoaFisicaActivity.this)
                        .setTitle("Confirma o cancelamento?")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                        .setMessage("Deseja realmente cancelar?")
                        .setNegativeBtnText("NÃO")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                        .setPositiveBtnText("SIM")
                        .setNegativeBtnBackground(Color.parseColor("#4ECA25"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                        .isCancellable(true)
                        .setIcon(R.mipmap.ic_launcher_round, View.VISIBLE)
                        .onPositiveClicked(dialog -> Toast.makeText(getApplicationContext(), "Cancelado com sucesso!", Toast.LENGTH_SHORT).show())
                        .onNegativeClicked(dialog -> Toast.makeText(getApplicationContext(), "Continue o seu cadastro!", Toast.LENGTH_SHORT).show())
                        .build()
                        .show();
            }
        });
    }

    private void initFormulario() {

        editCpf = findViewById(R.id.editCpf);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);

        novoClientePF = new ClientePF();

        novoVip = new Cliente();

        restaurarSharedPreferences();
    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCpf.getText().toString())) {
            editCpf.setError("*");
            editCpf.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
            editNomeCompleto.setError("*");
            editNomeCompleto.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("cpf", editCpf.getText().toString());
        dados.putString("nomeCompleto", editNomeCompleto.getText().toString());
        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);

    }
}