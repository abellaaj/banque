package fr.ides.gestion.banque.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.ides.gestion.banque.entities.Client;
import fr.ides.gestion.banque.entities.Compte;
import fr.ides.gestion.banque.entities.Operation;

/**
 * 
 * @author abellaaj
 *
 */
//@Repository("banqueDao")
public class BanqueDaoImpl implements IbanqueDao {
	
	@PersistenceContext
	private EntityManager em;
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	public Compte addCompte(Compte cp, Long codeClient) {
		Client client = em.find(Client.class, codeClient);
		cp.setClient(client);
		em.persist(cp);
		return cp;
	}

	public Operation addOperation(Operation op, String codeCpte) {
		Compte compte = consulterCompte(codeCpte);
		op.setCompte(compte);
		em.persist(op);
		return op;
	}

	public Compte consulterCompte(String codeCpte) {
		Compte compte = em.find(Compte.class, codeCpte);
		if (compte==null){
			throw new RuntimeException("Compte Introuvable");
		}
		return compte;
	}

	public List<Operation> consulterOperations(String codeCompte) {
		Query req=em.createQuery("select o from Operation o where o.compte.codeCompte=:codCp");
		req.setParameter("codCp", codeCompte);
		return req.getResultList();
	}

	public Client consulterClient(Long codeCli) {
		Client client = em.find(Client.class, codeCli);
		if (client==null){
			throw new RuntimeException("Client Introuvable");
		}
		return client;
	}

	public List<Client> consulterClients(String motCle) {
		Query req=em.createQuery("select c from Client c where c.nomClient like :nomCli");
		req.setParameter("nomCli", "%"+motCle+"%");
		return req.getResultList();
	}

	public List<Compte> getComptesByClient(Long codeCli) {
		Query req=em.createQuery("select c from Compte where c.client.codeClient=:cdCli");
		req.setParameter("cdCli", codeCli);
		return req.getResultList();
	}

}
