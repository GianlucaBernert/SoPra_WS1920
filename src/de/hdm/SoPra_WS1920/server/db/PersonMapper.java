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
     * Methode, die einer Person anhand einer ID zurückgibt.
     * @param int personID 
     * @return Person p
     */
    public Person findPersonByID(int personID) {
	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE id= " + personID);
			
			if(rs.next()) {
				Person p = new Person();
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setEMail(rs.getString("eMail"));
				p.setId(rs.getInt("id"));
				return p;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null ;	
	}	
	
    
	/**
	 * Methode, die das Anlegen eines Person-Objekts ermöglicht
	 * @param person
	 * @return person p
	 */
    
    public Person insertPerson(Person person) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stm = con.createStatement();

			stm.executeUpdate("INSERT INTO person (id, firstname, lastname, eMail) VALUES ('"
								+person.getId()
								+"', '"+person.getFirstname()
								+"', '"+person.getLastname()
								+"', '"+person.getEMail()
								+"')");
		con.setAutoCommit(true);
    	}
			catch(SQLException exc) {
				exc.printStackTrace();
			}
    	return person;
    }

	/**
	 * Methode, die das Updaten eines Person-Objekts in der Datenbank ermöglicht	
	 * @param person
	 * @return person
	 */
    
    public Person updatePerson(Person person) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE person Set firstname='"+person.getFirstname()
    				+"', lastname='"+person.getLastname()
    				+"', eMail='"+person.getEMail()
    				+"' Where id="+person.getId());
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
			stm1.executeUpdate("Delete from person Where id= " +person.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

    
    /* Ende: Standard-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Foreign Key-Mapper-Methoden
	 */
    /**
     * Methode, die alle Personen einer Gruppe zurückgibt
     * @param group 
     * @return Vektor Person
     */
    public Vector <Person> findPersonByGroupFK(int groupFK) {
    	Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT person.id, person.firstname, person.email, person.lastname FROM popcorns.person "
					+ "INNER JOIN membership ON membership.groupFK ='" +groupFK+"'");
		
			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
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
     * Methode, die Personen anhand des Vornamen zurückgibt
     * @param firstname 
     * @return Vektor Person
     */
    public Vector<Person> findPersonByFirstname(String firstname) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where firstname='" + firstname+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
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
     * Methode, die Personen anahnd des Nachnamen zurückgibt.
     * @param lastname 
     * @return Vektor Person
     */
    public Vector<Person> findPersonByLastname(String lastname) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where lastname='" + lastname+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
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
     * Methode, die eine Person anhand der Email zurückgibt.
     * @param email 
     * @return Person p
     */
   public Person findPersonByEmail(String mail) {
    	Connection con= DBConnection.connection();	
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE email='" + mail+"'");
			
			if(rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
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
     * Methode, die Personen welche den Status isAdmin erfüllen zurückgibt
     * @param isAdmin 
     * @return Vektor Person
     */
    public Vector<Person> findPersonByIsAdmin(boolean isAdmin) {
    	Connection con= DBConnection.connection();	
    	Vector<Person> result = new Vector<Person>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person Where isAdmin='" + isAdmin+"'");		
			while(rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
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
	 * Methode, die alle Personen zurückgibt, die in der Datenbank gespeichert sind.
	 * @return Vektor Person
	 */
    public Vector<Person> findAll() {
        Connection con = DBConnection.connection();
        Vector<Person> result = new Vector<Person>();

        try {
          Statement stmt = con.createStatement();

          ResultSet rs = stmt.executeQuery("SELECT * FROM person "
              + " ORDER BY id");

          while (rs.next()) {
            Person p = new Person();
            p.setId(rs.getInt("id"));
            p.setFirstname(rs.getString("firstname"));
            p.setLastname(rs.getString("lastname"));
            p.setEMail(rs.getString("email"));

            //  Hinzufügen des neuen Objekts zum Ergebnisvektor
            result.addElement(p);
          }
        }
        catch (SQLException e2) {
          e2.printStackTrace();
        }

        // Ergebnisvektor zurückgeben
        return result;
      }


}