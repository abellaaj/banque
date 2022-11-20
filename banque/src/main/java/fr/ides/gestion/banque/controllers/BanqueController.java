package fr.ides.gestion.banque.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.ides.gestion.banque.entities.Compte;
import fr.ides.gestion.banque.entities.Operation;
import fr.ides.gestion.banque.metier.IbanqueMetier;
import fr.ides.gestion.banque.model.BanqueForm;

/**
 * 
 * @author abellaaj
 *
 */
@Controller
public class BanqueController {
	
	@Autowired
	IbanqueMetier banqueMetier;
	//test from branche
	// test from branch test
	
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("banqueForm", new BanqueForm());
		return "banque";
	}
	
	@RequestMapping(value="/chargerCompte")
	public String charger(@Valid BanqueForm bf, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()){
			return "banque";
		}
		try {
			chargerCompte(bf);
			chargerOperations(bf);
		} catch (Exception e) {
			bf.setException(e.getMessage());
		}
		
		model.addAttribute("banqueForm", bf);
		return "banque";
	}
	
	@RequestMapping(value="/saveOperation")
	public String saveOp(@Valid BanqueForm bf, BindingResult bindingResult){
		try {
			chargerCompte(bf);
		if (!bindingResult.hasErrors()){
			if(bf.getAction()!=null){
				if (bf.getTypeOperation().equals("VIR")){
					banqueMetier.virement(bf.getCode(), bf.getCode2(), bf.getMontant());
				}else if(bf.getTypeOperation().equals("VER")){
					banqueMetier.versement(bf.getCode(), bf.getMontant());
				}else{
					banqueMetier.retrait(bf.getCode(), bf.getMontant());
				}
			}
		}
			
		} catch (Exception e) {
			bf.setException(e.getMessage());
		}
		chargerOperations(bf);
		return "banque";
	}
	
	public void chargerCompte( BanqueForm bf){
		Compte compte=banqueMetier.consulterCompte(bf.getCode());
		bf.setTypeCompte(compte.getClass().getSimpleName());
		bf.setCompte(compte);
	}
	
	public void chargerOperations(BanqueForm bf){
		int pos= bf.getNbreLignes()*(bf.getPage()-1);
		List<Operation> operations = banqueMetier.consulterOperations(bf.getCode(),pos,bf.getNbreLignes());
		bf.setOperations(operations);
		int nbrePages= (int)(banqueMetier.getNombreOperations(bf.getCode())/bf.getNbreLignes()) +1;
		bf.setNbrePages(nbrePages);
	}

}
