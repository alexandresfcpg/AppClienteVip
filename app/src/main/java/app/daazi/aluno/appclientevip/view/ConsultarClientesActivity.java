package app.daazi.aluno.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.daazi.aluno.appclientevip.R;
import app.daazi.aluno.appclientevip.api.ClienteAdapter;
import app.daazi.aluno.appclientevip.model.Cliente;

public class ConsultarClientesActivity extends AppCompatActivity {

    List<Cliente> clientes;
    ClienteAdapter adapter;
    Cliente obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clientes);

        clientes = new ArrayList<>();

        for (int i = 0; i < 50; i++){

            obj = new Cliente();
            obj.setPrimeiroNome("Cliente "+i);
            obj.setPessoaFisica(i % 2==0);

            clientes.add(obj);

        }

        adapter = new ClienteAdapter(clientes, getApplicationContext());
    }
}