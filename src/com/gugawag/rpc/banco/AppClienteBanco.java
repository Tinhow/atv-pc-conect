package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // Procura o serviço no RMI Registry local. Perceba que o cliente não conhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            menu();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    // Chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    // Chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da conta a ser cadastrada:");
                    String novaConta = entrada.next();
                    System.out.println("Digite o saldo inicial da conta:");
                    double saldoInicial = entrada.nextDouble();
                    banco.cadastrarConta(novaConta, saldoInicial);
                    System.out.println("Conta cadastrada com sucesso.");
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta a ser pesquisada:");
                    String contaPesquisada = entrada.next();
                    if (banco.pesquisarConta(contaPesquisada)) {
                        System.out.println("Conta encontrada.");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta a ser removida:");
                    String contaRemovida = entrada.next();
                    if (banco.removerConta(contaRemovida)) {
                        System.out.println("Conta removida com sucesso.");
                    } else {
                        System.out.println("Conta não encontrada ou não pode ser removida.");
                    }
                    break;
                }
                case 6: {
                    System.out.println("Digite o número da nova conta:");
                    String novaContaNumero = entrada.next();
                    System.out.println("Digite o saldo inicial da nova conta:");
                    double novoSaldo = entrada.nextDouble();
                    Conta novaConta = new Conta(novaContaNumero, novoSaldo);
                    banco.adicionarConta(novaConta);
                    System.out.println("Nova conta adicionada com sucesso.");
                    break;
                }
                case 7: {
                    System.out.println("Lista de Contas:");
                    List<Conta> listaContas = banco.listarContas();
                    for (Conta conta : listaContas) {
                        System.out.println("Número: " + conta.getNumero() + ", Saldo: " + conta.getSaldo());
                    }
                    break;
                }
            }
        } while (opcao != 9);
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("\n=== Walter da Silva Filho ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }
}
