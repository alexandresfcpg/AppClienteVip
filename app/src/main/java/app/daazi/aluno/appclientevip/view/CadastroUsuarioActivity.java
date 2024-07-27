package app.daazi.aluno.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import app.daazi.aluno.appclientevip.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    Button btnCadastro;
    EditText editNome, editEmail, editSenhaA, editSenhaB;
    CheckBox ckTermo;
    boolean isFormularioOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        initFormulario();

        btnCadastro.setOnClickListener((v -> {

            isFormularioOK = true;

            if(TextUtils.isEmpty(editNome.getText().toString())){
                editNome.setError("*");
                editNome.requestFocus();
                isFormularioOK = false;
            }
            if(TextUtils.isEmpty(editEmail.getText().toString())){
                editEmail.setError("*");
                editEmail.requestFocus();
                isFormularioOK = false;
            }
            if(TextUtils.isEmpty(editSenhaA.getText().toString())){
                editSenhaA.setError("*");
                editSenhaA.requestFocus();
                isFormularioOK = false;
            }
            if(TextUtils.isEmpty(editSenhaB.getText().toString())){
                editSenhaB.setError("*");
                editSenhaB.requestFocus();
                isFormularioOK = false;
            }
            if(!ckTermo.isChecked()){
                isFormularioOK = false;
            }

            if (isFormularioOK){

                if (!validarSenha()){

                    editSenhaA.setError(("*"));
                    editSenhaB.setError(("*"));
                    editSenhaA.requestFocus();

                    Toast.makeText(getApplicationContext(), "As senhas digitadas não conferem...",
                            Toast.LENGTH_LONG).show();
                }
            }

        }));
    }

    private boolean validarSenha() {

        return false;
    }

    private void initFormulario() {

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenhaA);
        editSenhaB = findViewById(R.id.editSenhaB);
        ckTermo = findViewById(R.id.ckTermo);

        isFormularioOK = false;
    }
}