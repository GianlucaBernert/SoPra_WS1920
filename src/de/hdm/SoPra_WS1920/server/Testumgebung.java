package de.hdm.SoPra_WS1920.server;

public class Testumgebung {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		/*
		 * Mit dieser Klasse werden s�mtliche Methoden aus der Impl getestet um die volle Funktionalit�t
		 * der Methoden zu pr�fen. Sobald eine Methode von allen Usern erfolgreich getestet wurde,
		 * bitte in der GitHub Issue Liste abhaken.
		 */
		
		CinemaAdministrationImpl implAdmin = new CinemaAdministrationImpl();
		implAdmin.init();
		
		SurveyManagementImpl implSurveyManagement = new SurveyManagementImpl();
		implSurveyManagement.init();
		
		//implAdmin.createMovie("Wonderwoman2", "Action", "ndjfns", 1);
		
		System.out.println(implSurveyManagement.createSurveyEntry(44,68 , 5));
		
		
		//implSurveyManagement.createPerson("Yesin","Soufi", "ys018@hdm-stuttgart.de");
		
//		implAdmin.createPerson("Yesin", "Soufi", "ys018@hdm-stuttgart.de");
//		System.out.println(implSurveyManagement.getAllPersons());
//		System.out.println(implSurveyManagement.getAllPersons().get(0).getEMail());
//		Movie m = implAdmin.getMoviesByName("Wonderwoman").get(0);
//		m.setId(7);
//		
//		SurveyEntry see = implSurveyManagement.getSurveyEntryBySurveyFK(33).get(0);
//		Screening sc = implAdmin.getScreeningById(see.getScreeningFK());
//		Movie movie = implAdmin.getMovieById(sc.getMovieFK());
//		System.out.println(see.getId() + " " + sc.getId()+ " "+ movie.getId());
//		implAdmin.createPerson("Sebastian", "Hermann", "sh267@hdm-stuttgart.de");
//		implAdmin.createPerson("Yesin", "Soufi", "yesin.soufi@hdm-stuttgart.de");
//		implAdmin.createPerson("Matthias", "Kling", "matthias.kling@hdm-stuttgart.de");
//		java.sql.Date ds = new java.sql.Date(119, 5, 5);
//		java.sql.Date de = new java.sql.Date(121, 5, 5);
//		
//		System.out.println(implAdmin.getScreeningsforSurveyCreation(m, "Rottweil", ds, de));
//		
//		Vector<Screening> s = implAdmin.getScreeningByMovieFK(7);
		/*
		 * Erstellen einer Person
		 * implSurveyManagement.createPerson(1, "Yesin", "Soufi", "ys018@hdm-stuttgart.de", true, "2019-11-22 16:47,08.128");
		 */
		
		
		/*
		 	Update eines Kinos
		 	Cinema c = implAdmin.getCinemaById(1);
			System.out.println(c.getName());
			c.setName("Metropol");
			c.setPostCode(0711);
			c.setStreetNo("34");
			impl.updateCinema(c);
			System.out.println(c.getName());
			System.out.println(c.getPostCode());
			System.out.println(c.getStreetNo());	
			*/
		
		/*	
		 	L�schen einer Person
		 	Person p = new Person();
			p.setPersonId(1);
			implSurveyManagement.deletePerson(p);
		 */
		 
		
		

	}

}
