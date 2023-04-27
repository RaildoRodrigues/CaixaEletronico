package com.raildo.caixaeletronico;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String codigoBanco;
    private String nomeBanco;
    private int contadorDeContas;
    static private int contadorDeBancos = 0;
    private List<Conta> contas;


    public String getName(){
        return nomeBanco;
    }

    public Banco(String nome){
        this.nomeBanco = nome;
        this.codigoBanco = String.format("%04d", contadorDeBancos);
        this.contadorDeContas = 0;
        this.contas = new ArrayList<>();
        contadorDeBancos += 1;
    }

    public void listarContas(){
        if (contas.isEmpty()){
            System.out.println("Não há contas neste banco");
            return;
        }
        System.out.println("Contas do Banco " + nomeBanco + ":");
        for(Conta conta : contas) {
            System.out.println(conta);
        }
    }

    public Conta getConta(int index){
        return contas.get(index);
    }


    public Conta criarConta(String novoCliente){
        contadorDeContas++;
        Conta nova_conta = new Conta(novoCliente, contadorDeContas, this.codigoBanco);
        contas.add(nova_conta);
        return nova_conta;
    }


}
