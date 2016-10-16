package br.com.saojudas.maven.projetointegrado.util;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.model.Acesso;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class PopulaAcesso {
	private static DateFormat dateFormat;
	private static Date date;

	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();	
		
		Usuario usuario1 = manager.find(Usuario.class,1);
		Usuario usuario2 = manager.find(Usuario.class,2);
		Usuario usuario3 = manager.find(Usuario.class,3);
		Usuario usuario4 = manager.find(Usuario.class,4);
		Usuario usuario5 = manager.find(Usuario.class,5);
		Usuario usuario6 = manager.find(Usuario.class,6);
		Usuario usuario7 = manager.find(Usuario.class,7);
		Usuario usuario8 = manager.find(Usuario.class,8);
		Usuario usuario9 = manager.find(Usuario.class,9);
		Usuario usuario10 = manager.find(Usuario.class,10);
		Usuario usuario11 = manager.find(Usuario.class,11);
		Usuario usuario12 = manager.find(Usuario.class,12);

		Date date = new Date();
		Acesso acesso1 = new Acesso(date,usuario1);
		acesso1.setSaida(date);
		
		Acesso acesso2 = new Acesso(date,usuario2);
		acesso2.setSaida(date);
		
		Acesso acesso3 = new Acesso(date,usuario3);
		acesso3.setSaida(date);
		
		Acesso acesso4 = new Acesso(date,usuario4);
		acesso4.setSaida(date);

		Acesso acesso5 = new Acesso(date,usuario5);
		acesso5.setSaida(date);
		
		Acesso acesso6 = new Acesso(date,usuario6);
		acesso6.setSaida(date);
		
		Acesso acesso7 = new Acesso(date,usuario7);
		acesso7.setSaida(date);
		
		Acesso acesso8 = new Acesso(date,usuario8);
		acesso8.setSaida(date);
		
		Acesso acesso9 = new Acesso(date,usuario9);
		acesso9.setSaida(date);
		
		Acesso acesso10 = new Acesso(date,usuario10);
		acesso10.setSaida(date);
		
		Acesso acesso11 = new Acesso(date,usuario11);
		acesso11.setSaida(date);
		
		Acesso acesso12 = new Acesso(date,usuario12);
		acesso12.setSaida(date);
		
		manager.persist(acesso1);
		manager.persist(acesso2);
		manager.persist(acesso3);
		manager.persist(acesso4);
		manager.persist(acesso5);
		manager.persist(acesso6);
		manager.persist(acesso7);
		manager.persist(acesso8);
		manager.persist(acesso9);
		manager.persist(acesso10);
		manager.persist(acesso11);
		manager.persist(acesso12);
		
		manager.getTransaction().commit();

		manager.close();
	}
}