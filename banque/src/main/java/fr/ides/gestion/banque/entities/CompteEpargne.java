package fr.ides.gestion.banque.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author abellaaj
 *
 */
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7242749781920490852L;
	private double taux;
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	public CompteEpargne() {
		super();
	}
	public CompteEpargne(String codeCompte, Date dateCreation, double solde, double taux) {
		super(codeCompte, dateCreation, solde);
		this.taux=taux;
	}
	

}
