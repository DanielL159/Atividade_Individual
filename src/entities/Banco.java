package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	private List<Conta> contas = new ArrayList<>();
	private Lock lock;
	private Double valorTransacao;
	private Condition enviando;
	private Condition recebendo;
	
	
	public List<Conta> getContas(){
		return contas;
	}
	public Banco(Conta conta) {
		contas.add(conta);
		this.lock = new ReentrantLock();
	}
	
	public void pagar(Double valor) {
		this.lock.lock();
		
		try {
			valorTransacao = valor;
		} finally {
			
		}
	}
	
	public void receber() {
		
	}
}
