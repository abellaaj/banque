package fr.ides.gestion.banque.model;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import fr.ides.gestion.banque.entities.Compte;
import fr.ides.gestion.banque.entities.Operation;

public class BanqueForm {
	@NotEmpty
	@Size(min=3, max=10)
	private String code;
	private Compte compte;
	private String typeCompte;
	private String exception;
	private List<Operation> operations;
	private String typeOperation;
	@DecimalMin(value="50")
	private double montant=50;
	@NotEmpty
	@Size(min=3, max=10)
	private String code2="XXX";
	private String action;
	private int page=1;
	private int nbrePages;
	private int nbreLignes=5;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public String getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public String getTypeOperation() {
		return typeOperation;
	}
	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNbrePages() {
		return nbrePages;
	}
	public void setNbrePages(int nbrePages) {
		this.nbrePages = nbrePages;
	}
	public int getNbreLignes() {
		return nbreLignes;
	}
	public void setNbreLignes(int nbreLignes) {
		this.nbreLignes = nbreLignes;
	}
	
}