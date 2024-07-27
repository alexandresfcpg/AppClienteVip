package app.daazi.aluno.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.FancyAlertDialog;

import app.daazi.aluno.appclientevip.R;
import app.daazi.aluno.appclientevip.api.AppUtil;
import app.daazi.aluno.appclientevip.controller.ClienteController;
import app.daazi.aluno.appclientevip.model.Cliente;

public class LoginActivity extends AppCompatActivity {

    Cliente clienteFake;

    private SharedPreferences preferences;

    TextView txtRecuperarSenha, txtLerPolitica;
    EditText editEmail, editSenha;
    CheckBox ckLembrar;
    Button btnAcessar, btnSejaVip;

    boolean isFormularioOK, isLembrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFormulario();

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOK = validarFormulario()) {

                    if (validarDadosDoUsuario()) {

                        salvarSharedPreferences();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        return;

                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique os dados...", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnSejaVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, ClienteVipActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });

        txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Carregando tela de recuperação de senha...",
                        Toast.LENGTH_LONG).show();

            }
        });

        txtLerPolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FancyAlertDialog.Builder
                        .with(LoginActivity.this)
                        .setTitle("Política de Privacidade e Termos de Uso")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                        .setMessage("Do you really want to Exit ?")
                        .setNegativeBtnText("Discordo")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                        .setPositiveBtnText("Concordo")
                        .setNegativeBtnBackground(Color.parseColor("#4ECA25"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                        .isCancellable(true)
                        .setIcon(R.mipmap.ic_launcher_round, View.VISIBLE)
                        .onPositiveClicked(dialog -> Toast.makeText(LoginActivity.this, "Obrigado, seja bem-vindo e conclua o seu cadastro", Toast.LENGTH_SHORT).show())
                        .onNegativeClicked(dialog -> Toast.makeText(LoginActivity.this, "Lamentamos, mas é necessário concordar com a política de privacidade e os termos de uso", Toast.LENGTH_SHORT).show())
                        .build()
                        .show();
            }
        });
    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editEmail.getText().toString())) {
            editEmail.setError("*");
            editEmail.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editSenha.getText().toString())) {
            editSenha.setError("*");
            editSenha.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    private void initFormulario() {

        txtRecuperarSenha = findViewById(R.id.txtRecuperarSenha);
        txtLerPolitica = findViewById(R.id.txtLerPolitica);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        ckLembrar = findViewById(R.id.ckLembrar);
        btnAcessar = findViewById(R.id.btnAcessar);
        btnSejaVip = findViewById(R.id.btnSejaVip);

        isFormularioOK = false;

        clienteFake = ClienteController.getClienteFake();

        restaurarSharedPreferences();
    }

    public void lembrarSenha(View view) {

        isLembrarSenha = ckLembrar.isChecked();
    }

    public boolean validarDadosDoUsuario() {

        return ClienteController.validarDadosDoCliente(clienteFake, editEmail.getText().toString(), editSenha.getText().toString());
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putBoolean("loginAutomatico", isLembrarSenha);
        dados.putString("emailCliente", editEmail.getText().toString());
        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }
}