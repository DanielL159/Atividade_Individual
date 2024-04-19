package entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Funcionario extends Thread {
	private String nomeFuncionario;
	private Conta contaSalario;
	private Conta investir;
	private Lock lock = new ReentrantLock();

	public Funcionario(String nomeFuncionario, Conta contaSalario, Conta investir) {
		this.nomeFuncionario = nomeFuncionario;
		this.contaSalario = contaSalario;
		this.investir = investir;
	}

	@Override
	public void run() {

		while (!Thread.interrupted() && contaSalario.getSaldo() == 1400.0) {
			lock.lock();
			try {
				System.out.println(getNomeFuncionario() + " investiu : " + (contaSalario.getSaldo() * 0.2));
				contaSalario.getBanco().transacao(contaSalario, contaSalario.getSaldo() * 0.2, investir);
				System.out.println(getNomeFuncionario() + " conta de investimento :" + investir.getSaldo()
						+ " conta conrrente: " + contaSalario.getSaldo());

			} finally {
				lock.unlock();
			}
		}
	}

	public Conta getContaReceber() {

		return contaSalario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Conta getInvestir() {
		return investir;
	}

	public void setInvestir(Conta investir) {
		this.investir = investir;
	}

	public void setContaReceber(Conta contaSalario) {
		this.contaSalario = contaSalario;
	}

}