package br.com.alura.jpa;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaContaComSaldo {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Random random = new Random();
		int[] ints = random.ints(4, 1000, 10000).toArray();

		Conta conta = new Conta();
		conta.setTitular("Maria Joaquina");
		conta.setNumero(ints[0]);
		conta.setAgencia(ints[1]);
		conta.setSaldo(100.0);

		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();

		em.close();

		conta.setSaldo(500.0);

		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		em2.merge(conta);
		em2.getTransaction().commit();
	}

}
