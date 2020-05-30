package br.com.alura.jpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacaoEnum;

public class TesteRelacionamento {

	public static void main(String[] args) {
		Random random = new Random();
		int[] ints = random.ints(4, 1000, 10000).toArray();

		// Transient
		Conta conta = new Conta();
		conta.setTitular("Anderson Silva");
		conta.setNumero(ints[0]);
		conta.setAgencia(ints[1]);
		conta.setSaldo(1000.0);

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(LocalDateTime.now());
		movimentacao.setDescricao("Churrasco");
		movimentacao.setValor(new BigDecimal(200.0));
		movimentacao.setTipoMovimentacao(TipoMovimentacaoEnum.SAIDA);
		movimentacao.setConta(conta);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(conta);
		em.persist(movimentacao);
		em.getTransaction().commit();
		em.close();
	}

}
