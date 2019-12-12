package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;
import java.sql.*;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Person;



/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>PersonMapper</code> werden Person-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden können Person-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
 * 
 */
public class PersonMapper {


    /**
     * Durch einem sogeannten <b>Singleton<b> kann die Klasse PersonMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
     */
    private static PersonMapper personMapper = null;

    /**
     * Ein gesch?tzter Konstruktor der weitere Instanzierungen von UserMapper Objekten verhindert.
     */
    protected PersonMapper() {
      
    }

    /**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * Sie daf?r sorgt, dass nur eine einzige Instanz von <code>UserMapper</code> existiert.
	 * @return Sie gibt den UserMapper zur?ck.
	 */
    
    public static PersonMapper personMapper() {
    	if (personMapper == null) {
			personMapper = new PersonMapper();
		} 
		
		return personMapper;
 
    }

    /**
     * @param person 
     *           Prim?rschl?sselattribut (->DB)
	 * @return Kunden-Objekt, das dem ?bergebenen Schl?ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 */
    
    

	/*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 */	
     
    /**
     * @param personID 
     * @return
     */
    public Person findPersonByID(int personID) {
	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM person " + "WHERE bo_id= " + personID);
			
			if(rs.next()) {
				Person p = new Person();
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				return p;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null ;	
	}	
	
    
	/**
	 * Methode, die das Anlegen eines User-Objekts ermöglicht
	 * @param person
	 */
    
    public Person insertPerson(Person person) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stmt = con.createStatement();
    	
    	    ResultSet rs = stmt.executeQuery("SELECT MAX(bo_id) AS maxid "
    	          + "FROM businessobject ");

    	      if (rs.next()) {
    	     
    	        person.setId(rs.getInt("maxid") + 1);
    	        
    		Statement stm1 = con.createStatement();
    		Statement stm2 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO businessobject (bo_id, creationTimeStamp) VALUES ('"
    							+ person.getId()
    							+ "', '"+person.getCreationTimestamp()
    							+"')");
			stm2.executeUpdate("INSERT INTO person (bo_id, firstname, lastname, eMail) VALUES ('"
								+person.getId()
								+"', '"+person.getFirstname()
								+"', '"+person.getLastname()
								+"', '"+person.getEMail()
								+"')");
		}con.setAutoCommit(true);
    	}
			catch(SQLException exc) {
				exc.printStackTrace();
			}
    	return person;
    }

	/**
	 * Methode, die das Updaten eines Person-Objekts in der Datenbank ermöglicht	
	 * @param person
	 */
    
    public Person updatePerson(Person person) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE person Set firstname='"+person.getFirstname()
    				+"', lastname='"+person.getLastname()
    				+"', eMail='"+person.getEMail()
    				+"' Where bo_id="+person.getId());
    		con.setAutoCommit(true);
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
    	return person;
    }

    /**
	 * Methode, die das Loeschen eines Person-Objekts aus der Datenbank ermöglicht
	 * @param person
	 */
    public void deletePerson(Person person) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			Statement stm2 = con.createStatement();
			stm1.executeUpdate("Delete from person Where ('bo_id' =" +person.getId());
			stm2.executeUpdate("Delete from businessobject Where ('bo_id' =" +person.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

    
    /* Ende: Standard-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Foreign Key-Mapper-Methoden
	 */
    /**
     * @param group 
     * @return
     */
    public Vector <Person> findPersonByGroupFK(int groupFK) {
    	Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT person.bo_id, person.firstname, person.email, person.lastname, person.isAdmin" 
					+ "FROM person INNER JOIN membership" + 
					"ON membership.groupFK =" +groupFK);
		
			while (rs.next()) {
				Person p = new Person();
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				result.add(p);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }
    /* Ende:  Foreign Key-Mapper-Methoden
     * ================================================================================================
     * Beginn: Spezifische Business Object Methoden
	 */	
	
    
    
    /**
     * @param firstname 
     * @return
     */
    public Vector<Person> findPersonByFirstname(String firstname) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where firstname='" + firstname+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				result.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }

    /**
     * @param lastname 
     * @return
     */
    public Vector<Person> findPersonByLastname(String lastname) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where lastname='" + lastname+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				result.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }

    /**
     * @param email 
     * @return
     */
   public Person findPersonByEmail(String mail) {
    	Connection con= DBConnection.connection();	
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM person " + "WHERE eMail= " + mail);
			
			if(rs.next()) {
				Person p = new Person();
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				return p;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null ;	
	}	
    
    
    /**
     * @param isAdmin 
     * @return
     */
    public Vector<Person> findPersonByIsAdmin(boolean isAdmin) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where isAdmin='" + isAdmin+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				result.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }
    



}