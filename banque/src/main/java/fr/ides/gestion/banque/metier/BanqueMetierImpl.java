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
@Service("banqueMetier")
public class BanqueMetierImpl implements IbanqueMetier{
	
	@Autowired
	private IbanqueDao banqueDao;
	
	public void setDao(IbanqueDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public Client addClient(Client c) {
		return banqueDao.addClient(c);
	}

	public Compte addCompte(Compte cp, Long codeClient) {
		return banqueDao.addCompte(cp, codeClient);
	}

	public void versement(String codeCpte, double montant) {
		Versement versement = new Versement(new Date(), montant);
		banqueDao.addOperation(versement, codeCpte);
		Compte compte = banqueDao.consulterCompte(codeCpte);
		compte.setSolde(compte.getSolde()+montant);
	}

	public void retrait(String codeCpte, double montant) {
		Retrait retrait = new Retrait(new Date(), montant);
		banqueDao.addOperation(retrait, codeCpte);
		Compte compte = banqueDao.consulterCompte(codeCpte);
		compte.setSolde(compte.getSolde()-montant);
	}

	public void virement(String codeCpte1, String codeCpte2, double montant) {
		retrait(codeCpte1, montant);
		versement(codeCpte2, montant);
		
	}

	public Compte consulterCompte(String codeCpte) {
		return banqueDao.consulterCompte(codeCpte);
	}

	public List<Operation> consulterOperations(String codeCompte, int position, int nbOperation) {
		return banqueDao.consulterOperations(codeCompte, position, nbOperation);
	}

	public Client consulterClient(Long codeCli) {
		return banqueDao.consulterClient(codeCli);
	}

	public List<Client> consulterClients(String motCle) {
		return banqueDao.consulterClients(motCle);
	}

	public List<Compte> getComptesByClient(Long codeCli) {
		return banqueDao.getComptesByClient(codeCli);
	}

	public long getNombreOperations(String codeCompte) {
		return banqueDao.getNombreOperations(codeCompte);
	}
}
