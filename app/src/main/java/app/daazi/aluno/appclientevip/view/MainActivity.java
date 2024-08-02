package app.daazi.aluno.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;

import app.daazi.aluno.appclientevip.R;
import app.daazi.aluno.appclientevip.api.AppUtil;
import app.daazi.aluno.appclientevip.model.Cliente;
import app.daazi.aluno.appclientevip.model.ClientePF;
import app.daazi.aluno.appclientevip.model.ClientePJ;

public class MainActivity extends AppCompatActivity {

    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFormulario();
    }

    private void initFormulario() {

        cliente = new Cliente();
        clientePF = new ClientePF();
        clientePJ = new ClientePJ();

        restaurarSharedPreferences();


    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "NULO"));
        cliente.setSobreNome(preferences.getString("sobreNome", "NULO"));
        cliente.setEmail(preferences.getString("email", "NULO"));
        cliente.setSenha(preferences.getString("senha", "NULO"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));

        clientePF.setCpf(preferences.getString("cpf", "NULO"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "NULO"));

        clientePJ.setCnpj(preferences.getString("cnpj", "NULO"));
        clientePJ.setRazaoSocial(preferences.getString("razaoSocial", "NULO"));
        clientePJ.setDataAbertura(preferences.getString("dataAbertura", "NULO"));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
        clientePJ.setMei(preferences.getBoolean("mei", false));

    }

    public void meusDados(View view) {

        /**
         * <boolean name="pessoaFisica" value="false" />
         *     <string name="emailCliente">alexandresfcpg@yahoo.com.br</string>
         *     <string name="primeiroNome">Alexandre</string>
         *     <boolean name="simplesNacional" value="true" />
         *     <boolean name="loginAutomatico" value="true" />
         *     <string name="cnpj">11222333000155</string>
         *     <string name="sobreNome">Augusto</string>
         *     <string name="dataAbertura">01/08/2024</string>
         *     <boolean name="mei" value="false" />
         *     <string name="senha">12345</string>
         *     <string name="cpf">28772021861</string>
         *     <string name="razaoSocial">Alex ME</string>
         *     <string name="email">alexandresfcpg@yahoo.com.br</string>
         *     <string name="nomeCompleto">Alexandre Augusto da Silva Henriques Ribeiro </string>
         */

        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE ***");
        Log.i(AppUtil.LOG_APP, "ID: "+cliente.getId());
        Log.i(AppUtil.LOG_APP, "Primeiro nome: "+cliente.getPrimeiroNome());
        Log.i(AppUtil.LOG_APP, "Sobrenome: "+cliente.getSobreNome());
        Log.i(AppUtil.LOG_APP, "E-mail: "+cliente.getEmail());
        Log.i(AppUtil.LOG_APP, "Senha: "+cliente.getSenha());
        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PF ***");
        Log.i(AppUtil.LOG_APP, "CPF: "+clientePF.getCpf());
        Log.i(AppUtil.LOG_APP, "Nome completo: "+clientePF.getNomeCompleto());

        if(!cliente.isPessoaFisica()){
            Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PJ ***");
            Log.i(AppUtil.LOG_APP, "CNPJ: "+ clientePJ.getCnpj());
            Log.i(AppUtil.LOG_APP, "Razão Social: "+ clientePJ.getRazaoSocial());
            Log.i(AppUtil.LOG_APP, "Data de abertura: "+ clientePJ.getDataAbertura());
            Log.i(AppUtil.LOG_APP, "Simples Nacional: "+ clientePJ.isSimplesNacional());
            Log.i(AppUtil.LOG_APP, "MEI: "+ clientePJ.isMei());
        }
    }

    public void atualizarMeusDados(View view) {
    }

    public void excluirMinhaConta(View view) {
    }

    public void consultarClientesVip(View view) {
    }

    public void sairDoAplicativo(View view) {

        FancyAlertDialog.Builder
                .with(MainActivity.this)
                .setTitle("SAIR DO APLICATIVO")
                .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                .setMessage("Confirma a sua saída do aplicativo ?")
                .setNegativeBtnText("RETORNAR")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#4ECA25"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                .isCancellable(true)
                .setAnimation(Animation.POP)
                .setIcon(R.mipmap.ic_launcher_round, View.VISIBLE)
                .onPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        Toast.makeText(MainActivity.this, cliente.getPrimeiroNome() + " , volte sempre e obrigado!", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                })
                .onNegativeClicked(dialog -> Toast.makeText(MainActivity.this, cliente.getPrimeiroNome() + " , obrigado por continuar a usar o nosso aplicativo!", Toast.LENGTH_SHORT).show())
                .build()
                .show();
    }
}