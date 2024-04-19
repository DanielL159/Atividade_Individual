package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente extends Thread {
    private String clienteName;
    private Conta conta;
    private List<Loja> lojas;
    private Lock lock = new ReentrantLock();
    private int currentLojaIndex = 0;
    
    public Cliente(String clienteName, Conta conta, List<Loja> lojas) {
        this.clienteName = clienteName;
        this.conta = conta;
        this.lojas = lojas;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (conta.getSaldo() > 0) {
                compras();
                changeLoja();
            }
        } finally {
            lock.unlock();
        }
    }

    public void compras() {
        try {
            System.out.println(clienteName + " com saldo: " + conta.getSaldo());
            conta.getBanco().transacao(conta, lojas.get(currentLojaIndex).getValor(), lojas.get(currentLojaIndex).getConta());
            System.out.println(clienteName +" " + lojas.get(currentLojaIndex).getNomeLoja() + " com saldo atual pos-compra de: " + conta.getSaldo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeLoja() {
        currentLojaIndex = (currentLojaIndex + 1) % lojas.size(); 
    }

    public Conta getConta() {
        return conta;
    }

    public Loja getLoja() {
        return lojas.get(currentLojaIndex);
    }

    public void setLoja(Loja loja) {
        int index = lojas.indexOf(loja);
        if (index != -1) {
            currentLojaIndex = index;
        } else {
            System.out.println("Loja não encontrada.");
        }
    }

    public String getclienteName() {
        return clienteName;
    }

    public void setclienteName(String clienteName) {
        this.clienteName = clienteName;
    }
}
