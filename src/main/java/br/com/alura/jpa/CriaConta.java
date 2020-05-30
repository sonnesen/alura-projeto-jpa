package br.com.alura.jpa;

import java.util.Random;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaConta {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Random random = new Random();
		int[] ints = random.ints(2, 1000, 10000).toArray();

		Conta conta = new Conta();
		conta.setTitular("José da Silva");
		conta.setNumero(ints[0]);
		conta.setAgencia(ints[1]);

		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();

		em.close();
	}

}
