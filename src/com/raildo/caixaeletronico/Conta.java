package com.raildo.caixaeletronico;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String codigoConta;
    private String codigoBanco;
    private String clientName;
    private double saldo;
    private List<String> extrato;

    public Conta(String name, int accountNumber, String bankNumber) {
        this.clientName = name;
        if(name.length() > 12){
            this.clientName = name.substring(0,12);
        }
        this.codigoConta = String.format("%05d", accountNumber);
        this.codigoBanco = bankNumber;
        this.extrato = new ArrayList<>();
    }

    public void verSaldo(){
        System.out.println("Seu saldo é de: R$ " + saldo);
    }
    public void Sacar(double value){
        if(value <= saldo){
            saldo -= value;
            System.out.println("Você Sacou R$ " + value);
            adicionarLinhaExtrato("D: -R$ " + value + "S: R$ " + saldo);
        }
        else {
            System.out.println("Você não tem saldo suficiente para o saque");
        }

        verSaldo();
    }

    public void depositar(double value){
        saldo += value;
        adicionarLinhaExtrato("C: +R$ " + value + "S: R$ " + saldo);
        System.out.println("Você depositou R$ " + value + " na sua conta");
        verSaldo();
    }

    public void verExtrato(){
        for (String linha: extrato) {
            System.out.println(linha);
        }
    }


    private void adicionarLinhaExtrato(String linha){
        String tracos = "_____________________________";
        String subTracos = tracos.substring(0, tracos.length() - linha.length()) + linha;
        extrato.add(subTracos);
    }


    @Override
    public String toString() {
        return  "Cliente: " + clientName + " Agencia: " + codigoBanco + "/Conta:" + codigoConta;
    }


}



