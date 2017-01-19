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
@DiscriminatorValue("2")
public class Versement extends Operation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8436114705844488319L;

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versement(Date dateOperation, double montant) {
		super(dateOperation, montant);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
	return "Versement";
	}
	
	

}
