package entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {
	private Double saldo;
	private Banco banco;
	private Cliente cliente;
	private Lock lock = new ReentrantLock();
	
	public Conta( Double saldo,Banco banco) {
		
		this.saldo = saldo;
		this.banco = banco;
	}

	public void adicionarSaldo(Double saldo) {
		lock.lock();
		try {
			this.saldo += saldo;
		} finally {
			lock.unlock();
		}
		
	}
	public void debitarSaldo(Double saldo) {
		lock.lock();
		try {
			this.saldo -= saldo;
		} finally {
			lock.unlock();
		}
		
	}
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Banco getBanco() {
		return banco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	


}