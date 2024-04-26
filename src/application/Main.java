package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Banco;
import entities.Cliente;
import entities.Conta;
import entities.Funcionario;
import entities.Loja;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Banco bradesco = new Banco();
		List<Loja> lojas = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			lojas.add(new Loja("Loja " + (i + 1), 200.00 / (i + 1), new Conta(0.0, bradesco)));

			for (int j = 0; j < 2; j++) {
				Funcionario funcionario = new Funcionario("Funcionario " + (i * 2 + j + 1), new Conta(0.0, bradesco),
						new Conta(0.0, bradesco));
				lojas.get(i).addFuncionario(funcionario);
			}
		}

		for (int i = 0; i < 5; i++) {
			Cliente cliente = new Cliente("Cliente " + (i + 1), new Conta(1000.0, bradesco), lojas);
			cliente.start();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Loja loja : lojas) {
			loja.pagarFuncinario();
			for (Funcionario funcionario : loja.getFuncionarios()) {
				funcionario.start();
			}
		}

	}

}