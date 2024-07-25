package app.daazi.aluno.appclientevip.controller;

import app.daazi.aluno.appclientevip.model.Cliente;

public class ClienteController {

    public static boolean validarDadosDoCliente(Cliente cliente, String email, String senha) {

        boolean retorno = ((cliente.getEmail().equals(email)) && (cliente.getSenha().equals(senha)));

        return retorno;
    }

    public static Cliente getClienteFake() {

        Cliente fake = new Cliente();
        fake.setPrimeiroNome("Alexandre");
        fake.setSobreNome("Augusto");
        fake.setEmail("alexandre@gmail.com");
        fake.setSenha("123456");
        fake.setPessoaFisica(true);

        return fake;
    }
}
