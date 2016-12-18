package fr.ides.gestion.banque.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.ides.gestion.banque.dao.IbanqueDao;
import fr.ides.gestion.banque.entities.Client;
import fr.ides.gestion.banque.entities.Compte;
import fr.ides.gestion.banque.entities.Operation;
import fr.ides.gestion.banque.entities.Retrait;
import fr.ides.gestion.banque.entities.Versement;

/**
 * 
 * @author DELL
 *
 */
@Transactional
//@Service("banqueMetier")
public class BanqueMetierImpl implements IbanqueMetier{
	
	@Autowired
	private IbanqueDao dao;
	
	public void setDao(IbanqueDao dao) {
		this.dao = dao;
	}

	public Client addClient(Client c) {
		return dao.addClient(c);
	}

	public Compte addCompte(Compte cp, Long codeClient) {
		return dao.addCompte(cp, codeClient);
	}

	public void versement(String codeCpte, double montant) {
		Versement versement = new Versement(new Date(), montant);
		dao.addOperation(versement, codeCpte);
		Compte compte = dao.consulterCompte(codeCpte);
		compte.setSolde(compte.getSolde()+montant);
	}

	public void retrait(String codeCpte, double montant) {
		Retrait retrait = new Retrait(new Date(), montant);
		dao.addOperation(retrait, codeCpte);
		Compte compte = dao.consulterCompte(codeCpte);
		compte.setSolde(compte.getSolde()-montant);
	}

	public void virement(String codeCpte1, String codeCpte2, double montant) {
		retrait(codeCpte1, montant);
		versement(codeCpte2, montant);
		
	}

	public Compte consulterCompte(String codeCpte) {
		return dao.consulterCompte(codeCpte);
	}

	public List<Operation> consulterOperations(String codeCompte) {
		return dao.consulterOperations(codeCompte);
	}

	public Client consulterClient(Long codeCli) {
		return dao.consulterClient(codeCli);
	}

	public List<Client> consulterClients(String motCle) {
		return dao.consulterClients(motCle);
	}

	public List<Compte> getComptesByClient(Long codeCli) {
		return dao.getComptesByClient(codeCli);
	}
}
