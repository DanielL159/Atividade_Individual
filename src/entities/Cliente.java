package entities;

public class Cliente extends Thread {
	private Conta conta;
	private Loja loja;
	
	public Cliente(Conta conta,Loja loja) {
		this.conta = conta;
		this.loja=loja;
	}
	@Override
	public void run() {
		
	}
	public void compras() {
		
		this.Banco.remove();
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	
	
}
