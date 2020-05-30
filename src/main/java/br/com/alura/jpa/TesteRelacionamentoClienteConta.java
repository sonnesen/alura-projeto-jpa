package br.com.alura.jpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacaoEnum;

public class TesteRelacionamentoClienteConta {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setId(2L);

		Categoria categoria1 = new Categoria("Viagem");
		Categoria categoria2 = new Categoria("Negócios");

		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(LocalDateTime.now());
		movimentacao1.setDescricao("Viagem à SP");
		movimentacao1.setValor(new BigDecimal(300.0));
		movimentacao1.setTipoMovimentacao(TipoMovimentacaoEnum.SAIDA);
		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));
		movimentacao1.setConta(conta);

		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setData(LocalDateTime.now());
		movimentacao2.setDescricao("Viagem ao RJ");
		movimentacao2.setValor(new BigDecimal(400.0));
		movimentacao2.setTipoMovimentacao(TipoMovimentacaoEnum.SAIDA);
		movimentacao2.setConta(conta);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(categoria1);
		em.persist(categoria2);
		em.persist(movimentacao1);
		em.persist(movimentacao2);
		em.getTransaction().commit();
		em.close();
	}

}
