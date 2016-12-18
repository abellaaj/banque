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
@DiscriminatorValue("CC")
public class CompteCourant extends Compte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9162952621344533154L;
	private double decouvert;
	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public CompteCourant() {
		super();
	}
	public CompteCourant(String codeCompte, Date dateCreation, double solde, double decouvert) {
		super(codeCompte, dateCreation, solde);
		this.decouvert = decouvert;
	}
	

}
