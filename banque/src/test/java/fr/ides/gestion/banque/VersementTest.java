package fr.ides.gestion.banque;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.ides.gestion.banque.entities.Compte;
import fr.ides.gestion.banque.metier.IbanqueMetier;

/**
 * 
 * @author DELL
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class VersementTest extends CommonTest{

	@Autowired
	IbanqueMetier banqueMetier;
	
	
	@Test
	public void testVersement(){
		Compte compte1 = banqueMetier.consulterCompte("compte1028_1");
		banqueMetier.versement("compte1028_1", 500);
		Compte compte2 = banqueMetier.consulterCompte("compte1028_1");
		double[] l1= {compte1.getSolde()+500};
		double[] l2= {compte2.getSolde()};
		assertArrayEquals(l1, l2, 0);
	}
	
	
	
	@BeforeClass
	 public static void beforeTest() {
		InputStream inputStreamXML;
		try{
		dbUnitConnection=getConnection();
		FlatXmlDataSetBuilder xmlDSBuilder = new FlatXmlDataSetBuilder();
	      xmlDSBuilder.setCaseSensitiveTableNames(false);
	      inputStreamXML = new FileInputStream("src/test/resources/dataSet/Client2.xml");
	      dataSet = xmlDSBuilder.build(inputStreamXML);
	      //DatabaseOperation.DELETE.execute(dbUnitConnection, dataSet);
	      DatabaseOperation.UPDATE.execute(dbUnitConnection, dataSet);
	    } catch (DatabaseUnitException e) {
	      e.printStackTrace();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	// j'ai commenté l'afterClass pour pouvoir visualiser le résultat dans la base de donnée
	//@AfterClass
	public static void afterTest() {
		try {
			DatabaseOperation.DELETE.execute(dbUnitConnection, dataSet);
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
