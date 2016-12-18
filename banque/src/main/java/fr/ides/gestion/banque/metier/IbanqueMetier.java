package fr.ides.gestion.banque.metier;

import java.util.List;

import fr.ides.gestion.banque.entities.Client;
import fr.ides.gestion.banque.entities.Compte;
import fr.ides.gestion.banque.entities.Operation;

/**
 * 
 * @author abellaaj
 *
 */
public interface IbanqueMetier {
	
	public Client addClient(Client c);
	public Compte addCompte(Compte cp, Long codeClient);
	public void versement(String codeCpte, double montant);
	public void retrait(String codeCpte, double montant);
	public void virement(String codeCpte1, String codeCpte2, double montant);
	public Compte consulterCompte(String codeCpte);
	public List<Operation>consulterOperations(String codeCompte);
	public Client consulterClient(Long codeCli);
	public List<Client > consulterClients(String motCle);
	public List<Compte> getComptesByClient(Long codeCli);

}
