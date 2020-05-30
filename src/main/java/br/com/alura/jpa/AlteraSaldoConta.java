package br.com.alura.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class AlteraSaldoConta {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Conta conta = em.find(Conta.class, 1L);

		em.getTransaction().begin();
		conta.setSaldo(20.0);
		em.getTransaction().commit();
		em.close();
	}


}
