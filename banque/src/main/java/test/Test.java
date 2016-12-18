package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.ides.gestion.banque.entities.Client;
import fr.ides.gestion.banque.metier.IbanqueMetier;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IbanqueMetier m = (IbanqueMetier) cp.getBean("banqueMetier");
		m.addClient(new Client("test", "testAdresse"));

	}

}
