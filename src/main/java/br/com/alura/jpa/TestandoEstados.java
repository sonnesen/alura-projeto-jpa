package br.com.alura.jpa;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class TestandoEstados {

	public static void main(String[] args) {
		Random random = new Random();
		int[] ints = random.ints(4, 1000, 10000).toArray();

		// Transient
		Conta conta = new Conta();
		conta.setTitular("Tony Ramos");
		conta.setNumero(ints[0]);
		conta.setAgencia(ints[1]);
		conta.setSaldo(100.0);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		// Transient -> Managed
		em.persist(conta);

		// Managed -> Removed
		em.remove(conta);
		em.getTransaction().commit();

		em.close();
	}

}
