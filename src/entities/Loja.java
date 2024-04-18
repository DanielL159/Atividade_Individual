package entities;

import java.util.ArrayList;
import java.util.List;

public class Loja {
	private String nomeLoja;
	private Double valor;
	private Conta conta;
	List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public Loja(String nomeLoja,Double valorDouble, Conta conta) {
		this.nomeLoja=nomeLoja;
		valor = valorDouble;
		this.conta = conta;
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
		
		if (conta.getSaldo() > 2800.0) {
			
			try {
			
				for (Funcionario funcionario: funcionarios) {
					System.out.println("Loja atualmente tem atualmete: " + conta.getSaldo());
					System.out.println(funcionario.getNomeFuncionario() +" tem atualmente " + funcionario.getContaReceber().getSaldo());
					conta.getBanco().transacao(conta,1400.0, funcionarios.get(0).getContaReceber());
					System.out.println( funcionario.getNomeFuncionario() +" recebeu 1.400 de "+ nomeLoja +" e tem atualmente " + funcionario.getContaReceber().getSaldo());
					System.out.println("Loja atualmente tem atualmete: " + conta.getSaldo());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
