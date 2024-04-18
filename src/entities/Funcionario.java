package entities;

public class Funcionario extends Thread {
	private String nomeFuncionario;
	private Conta contaReceber ;
	private Conta investir;
	
	public Funcionario(String nomeFuncionario,Conta contaReceber,Conta investir) {
		this.nomeFuncionario = nomeFuncionario;
		this.contaReceber = contaReceber;
		this.investir=investir;
	}
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
             contaReceber.getBanco().transacao(contaReceber,contaReceber.getSaldo() * 0.2, investir);
        }
	}

	public Conta getContaReceber() {
		
		return contaReceber;
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

	public void setContaReceber(Conta contaReceber) {
		this.contaReceber = contaReceber;
	}
	
}
