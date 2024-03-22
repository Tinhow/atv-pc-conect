package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;



public interface BancoServiceIF extends Remote {
    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    void cadastrarConta(String conta, double saldoInicial) throws RemoteException;
    boolean pesquisarConta(String conta) throws RemoteException;
    boolean removerConta(String conta) throws RemoteException;
    void adicionarConta(Conta conta) throws RemoteException;
    List<Conta> listarContas() throws RemoteException;
}
