package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;
    private List<Conta> contas;


    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);

        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

    @Override
    public void cadastrarConta(String conta, double saldoInicial) throws RemoteException {
        saldoContas.put(conta, saldoInicial);
    }

    @Override
    public boolean pesquisarConta(String conta) throws RemoteException {
        return saldoContas.containsKey(conta);
    }

    @Override
    public boolean removerConta(String conta) throws RemoteException {
        return saldoContas.remove(conta) != null;
    }
    @Override
    public void adicionarConta(Conta conta) throws RemoteException {
        contas.add(conta);
    }

    @Override
    public List<Conta> listarContas() throws RemoteException {
        return contas;
    }
}
