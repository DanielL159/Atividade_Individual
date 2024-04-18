package entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente extends Thread {
	private String clienteName;
	private Conta conta;
	private Loja loja;
	private Lock lock = new ReentrantLock();
	
	
	public Cliente(String clienteName ,Conta conta, Loja loja) {
		this.clienteName = clienteName;
		this.conta = conta;
		this.loja = loja;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			while (conta.getSaldo()> 0) {
				compras();
			}
			
		} finally {
			lock.unlock();
		}
		
	}

	public void compras() {
		
		if (conta.getSaldo() <= 0 || loja.getValor() > conta.getSaldo()) {
			System.out.println("Voce nao possue saldo suficiente");
		} else {
			try {
				
				System.out.println( clienteName +" com saldo; " + conta.getSaldo());
				conta.getBanco().transacao(conta, loja.getValor(), loja.getConta());
				
				System.out.println(clienteName +" na loja "+loja.getNomeLoja()  +" com saldo atual pos-compra de: " + conta.getSaldo());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public Conta getConta() {
		return conta;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public String getclienteName() {
		return clienteName;
	}

	public void setclienteName(String clienteName) {
		this.clienteName = clienteName;
	}
	

}
