package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	private List<Conta> contas = new ArrayList<>();
	private Lock lock= new ReentrantLock();;
	private Condition enviando = lock.newCondition();
	private Boolean recebendo = false;
	

	public List<Conta> getContas() {
		return contas;
	}

	public Banco() {
	}

	public Banco(Conta conta) {
		contas.add(conta);
	}

	public void transacao(Conta origem, Double valor,Conta destino) {
		lock.lock();
		try {
			if (!recebendo) {
				origem.debitarSaldo(valor);
				destino.adicionarSaldo(valor);
			}else {
				try {
					recebendo = true;
					enviando.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} finally {
			enviando.signalAll();
			
			
			this.lock.unlock();
		}
		
		
		
	}


}