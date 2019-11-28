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
				p.setIsAdmin(rs.getBoolean("isAdmin"));
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
    
    public void insert(Person person) {
    	Connection con = DBConnection.connection();

    	try {
		
    		Statement stm1 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO businessobject (bo_id, creationTimeStamp) VALUES ('"
    							+ person.getId()
    							+ "', '"+person.getCreationTimestamp()
    							+"#)");
			Statement stm2 = con.createStatement();
			
			stm2.executeUpdate("INSERT INTO person (bo_id, firstname, lastname, eMail, isAdmin, creationTimeStamp) VALUES ('"
								+person.getId()
								+"*, '"+person.getFirstname()
								+"', '"+person.getLastname()
								+"', '"+person.getEMail()
								+"', '"+person.getIsAdmin()
								+"', '"+person.getCreationTimestamp()
								+"')");
			
		}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}

    }

	/**
	 * Methode, die das Updaten eines Person-Objekts in der Datenbank ermöglicht	
	 * @param person
	 */
    
    public Person updatePerson(Person person) {
    	Connection con = DBConnection.connection();

    	try {
    	
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE person Set firstname='"+person.getFirstname()
    				+"', lastname='"+person.getLastname()
    				+"', eMail='"+person.getEMail()
    				+"', isAdmin='"+person.getEMail()
    				+"' Where id="+person.getId());
    		
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
    	return person;
    }

    /**
	 * Methode, die das Loeschen eines User-Objekts aus der Datenbank ermöglicht
	 * @param person
	 */
    public void deletePerson(Person person) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			stm1.executeUpdate("Delete from person Where ('bo_id' = '1')");
			Statement stm2 = con.createStatement();
			stm2.executeUpdate("Delte from businessobject Where ('bo_id' = '1')");
			
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
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM membership WHERE groupFK= " + groupFK);
		
			while (rs.next()) {
				Person p = new Person();
				p.setfirstname(rs.getString("firstname"));
				p.setlastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				p.setIsAdmin(rs.getString("isAdmin"));
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
    public vector<Person> findPersonByFirstname(String firstname) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where firstname='" + firstname+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setfirstname(rs.getString("firstname"));
				p.setlastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				p.setIsAdmin(rs.getString("isAdmin"));
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
    public vector<Person> findPersonByLastname(String lastname) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where lastname='" + lastname+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setfirstname(rs.getString("firstname"));
				p.setlastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				p.setIsAdmin(rs.getString("isAdmin"));
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
    public Person findPersonByEmail(String email) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where eMail='" + email+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setfirstname(rs.getString("firstname"));
				p.setlastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				p.setIsAdmin(rs.getString("isAdmin"));
				result.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }
    
    
    /**
     * @param isAdmin 
     * @return
     */
    public vector<Person> findPersonByIsAdmin(boolean isAdmin) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where isAdmin='" + isAdmin+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setfirstname(rs.getString("firstname"));
				p.setlastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				p.setIsAdmin(rs.getString("isAdmin"));
				result.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }
    



}