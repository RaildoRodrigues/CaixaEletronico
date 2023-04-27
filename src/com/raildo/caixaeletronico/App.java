package com.raildo.caixaeletronico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static List<Banco> bancos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Criar bancos de teste
        criarBanco("Banco do Brasil");
        criarBanco("Nubank");
        criarBanco("PicPay");


        System.out.println("Bem vindo ao Caixa Eletronico");
        while (true){
            System.out.println("O que deseja fazer?");
            System.out.println("Digite o numero do banco ou C: CriarBanco S: Sair");
            listarBancos();
            String opcao = scanner.nextLine();
            if(opcao.equalsIgnoreCase("C")){
                System.out.println("Criar banco, digite o nome do novo banco");
                String novoBanco = scanner.nextLine();
                criarBanco(novoBanco);
                System.out.println("O banco " + novoBanco + " foi criado com sucesso");
            } else if (opcao.equalsIgnoreCase("S")) {
                System.exit(0);
            }
            else {
                try {
                    int numeroBanco = Integer.parseInt(opcao);
                    if (numeroBanco < bancos.size()){
                        menuBanco(bancos.get(numeroBanco));
                    }
                    else {
                        System.out.println("Banco Invalido");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Opcao Invalida");
                }

            }


        }

    }


    static void menuBanco(Banco banco){
        while (true){
            System.out.println("Acessando " + banco.getName());
            System.out.println("Digite o numero da conta ou C: CriarConta L: Listar contas, V: Voltar");
            banco.listarContas();
            String numeroConta = scanner.nextLine();
            if(numeroConta.equalsIgnoreCase("C")){
                System.out.println("Digite o nome do cliente");
                String novoCliente = scanner.nextLine();
                Conta novaConta = banco.criarConta(novoCliente);
                System.out.println("Nova conta criada " + novaConta);
            } else if(numeroConta.equalsIgnoreCase("L")){
                banco.listarContas();
            } else if (numeroConta.equalsIgnoreCase("V")) {
                break;
            }else {
                try {
                    int acessarConta = Integer.parseInt(numeroConta);
                    menuConta(banco.getConta(acessarConta - 1));

                }
                catch (NumberFormatException e){
                    System.out.println("Opcao Invalida");

                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Conta Inexistente");
                }
            }
        }

    }


    static void menuConta(Conta conta){
        System.out.println("Voce esta na conta " + conta);
        while (true) {
            System.out.println("S: Sacar, D: Depositar, E: Extrato, V: Voltar");
            String opcao = scanner.nextLine();
            if(opcao.equalsIgnoreCase("S")){
                System.out.println("digite o valor do saque");
                String valueString = scanner.nextLine();
                try {
                    double value = Double.parseDouble(valueString);
                    conta.Sacar(value);
                }
                catch (NumberFormatException e){
                    System.out.println("valor invalido");
                }

            } else if (opcao.equalsIgnoreCase("D")) {
                System.out.println("digite o valor do deposito");
                String valueString = scanner.nextLine();
                try {
                    double value = Double.parseDouble(valueString);
                    conta.depositar(value);
                }
                catch (NumberFormatException e){
                    System.out.println("valor invalido");
                }
            } else if (opcao.equalsIgnoreCase("E")) {
                conta.verExtrato();
            } else if (opcao.equalsIgnoreCase("V")) {
                break;
            }
            else {
                System.out.println("Opcao invalida");
            }
        }


    }

    static void criarBanco(String nomeBanco){
        bancos.add(new Banco(nomeBanco));

    }

    static void listarBancos(){
        for(int i = 0; i < bancos.size(); i++){
            String tracos = "-------------------------------------";
            String linha = tracos.substring(0, tracos.length() - bancos.get(i).getName().length()) + bancos.get(i).getName();
            System.out.println(i + linha);
        }
    }


}
