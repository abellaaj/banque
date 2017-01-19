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
@DiscriminatorValue(value="1")
public class Retrait extends Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5253748221110863770L;

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double montant) {
		super(dateOperation, montant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Retrait";
	}
}
