package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Loja {
	private String nomeLoja;
	private Double valor;
	private Conta conta;
	List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	Lock lock = new ReentrantLock();
	private Integer funcionarioContador = 0;

	public Loja(String nomeLoja,Double valorDouble, Conta conta) {
		this.nomeLoja=nomeLoja;
		valor = valorDouble;
		this.conta = conta;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor= valor;
	}
	public Conta getConta() {
		return conta;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}
	public void addFuncionario(Funcionario funcionario) {
		funcionarios.add(funcionario);
	}
	public void pagarFuncinario() {
		
		while(conta.getSaldo() > 1400) {
			System.out.println(getNomeLoja()+" "+(conta.getSaldo() >1400.));
			lock.lock();
			try {
			
				System.out.println(getNomeLoja()+" atualmente tem atualmete: " + conta.getSaldo());
				System.out.println( funcionarios.get(funcionarioContador).getNomeFuncionario() +" tem atualmente: " + funcionarios.get(funcionarioContador).getContaReceber().getSaldo());
				conta.getBanco().transacao(conta,1400.0, funcionarios.get(funcionarioContador).getContaReceber());
				System.out.println( funcionarios.get(funcionarioContador).getNomeFuncionario() +" recebeu 1.400 de "+ nomeLoja +" e tem atualmente: " + funcionarios.get(funcionarioContador).getContaReceber().getSaldo());
				System.out.println(getNomeLoja()+" atualmente tem atualmete: " + conta.getSaldo());
				funcionarioContador++;
				if (funcionarioContador > 1) {
					funcionarioContador = 0;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}

	

}