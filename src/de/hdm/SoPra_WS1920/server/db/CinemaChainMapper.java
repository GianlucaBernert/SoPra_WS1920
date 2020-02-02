package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;

/**
 * Mapper-Klasse, die <code>CinemaChain</code>-Objekte auf relationale Datenbank abbildet.
 * Anhand von den Methoden k�nnen Objekte gesucht, erzeugt, bearbeitet und gel�scht werden. 
 * Objekte k�nnen in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
 * 
 * @author shila
 */

public class CinemaChainMapper {
	
/**
 * Die Klasse CinemaChainMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
 * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal f�r 
 * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert. 
 */
	
	private static CinemaChainMapper cinemaChainMapper = null;
	
/**
 * Gesch�tzter Konstruktor, der verhindert, dass mit dem new-Operator
 * neue Instanzen der Klasse erstellt werden.
 */
	
	protected CinemaChainMapper() {
		
	}
	
/**
 * Folgende statische Methode sichert die Singleton-Eigenschaft.
 * Es wird daf�r gesorgt, dass nur eine einzige Instanz von
 * <code>CinemaChainMapper</code> existiert.
 * SurveyMapper wird durch den Aufruf dieser statischen Methode instanziiert, 
 * nicht durch den new-Operator.
 * Aufruf der Methode durch: <code>CinemaChainMapper.cinemaChainMapper()</code>
 * 
 * @return <code>CinemaChainMapper</code>-Objekt
 */
	 
		public static CinemaChainMapper cinemaChainMapper() {
			if(cinemaChainMapper == null) {
				cinemaChainMapper = new CinemaChainMapper();
			}
			
			return cinemaChainMapper;
		}
		
/**
 * @param id (Prim�rschl�ssel-Attribut)
 * @return CinemaChain-Objekt, das dem �bergebenen Schl�ssel entspricht, null
 * bei nicht vorhandenem DB-Tupel.
 */
		
		public CinemaChain findCinemaChainByID(int id) {
			Connection con = DBConnection.connection();
			
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM cinemachain WHERE id= " + id);
				
				if(rs.next()) {
					
					CinemaChain cc = new CinemaChain();
					cc.setName(rs.getString("name"));
					cc.setId(rs.getInt("id"));
					
					return cc;
				}
				
			}
			catch(SQLException e2) {
				e2.printStackTrace();
			}
			return null;
		}
	
		/**
	     * Einf�gen eines <code>CinemaChain</code>-Objekts in die DB.
	     * Pr�fung und ggf. Korrektur des Prim�rschl�ssels
	     * @param cc, das zu speichernde Objekt
	     * @return das �bergebene Objekt, mit ggf. korrigierter <code>id</code>.
	     */
		
		public CinemaChain insertCinemaChain(CinemaChain cc) {
			Connection con = DBConnection.connection();
			
			try {
				Statement stmt = con.createStatement();
				
				
					stmt.executeUpdate("INSERT INTO cinemachain(id, name)"
						+ "VALUES ('"
						+ cc.getId()
						+ "','"
						+ cc.getName() + "')");
				
			}
			catch(SQLException e2) {
				
				e2.printStackTrace();
			}
			return cc;
		}
	
		/**
	     * Ein Objekt wird wiederholt in die DB geschrieben.
	     * 
	     * @param cc, das Objekt, das in die DB geschrieben werden soll
	     * @return das Objekt, das als Parameter �bergeben wird -> cc
	     */
	    public CinemaChain updateCinemaChain(CinemaChain cc) {
	        Connection con = DBConnection.connection();
	        
	        try {
	        	con.setAutoCommit(true);
	        	Statement stmt = con.createStatement();
	        	
	        	stmt.executeUpdate("UPDATE cinemachain SET name= '" + cc.getName()
	        	+ "'WHERE id=" + cc.getId());
	        	con.setAutoCommit(false);
	        	
	        }
	        catch(SQLException e2) {
	        e2.printStackTrace();
	       
	    }
	        return cc;
	    }
	    
	    /**
	     * L�schen von Daten eines <code>CinemaChain</code>-Objekts aus der Datenbank
	     * @param cc, das zu l�schende Objekt 
	     */
	    public void deleteCinemaChain(CinemaChain cc) {
	    	Connection con = DBConnection.connection();
	    	
	    	try {
	    		Statement stmt = con.createStatement();
	    		
	    		stmt.executeUpdate("DELETE FROM cinemachain WHERE id= " + cc.getId());
	    		
	    	}
	    	catch(SQLException e2) {
	    		e2.printStackTrace();
	    	}
	        
	        
	    }
	    
	    /**
	     * Auslesen der CinemaChain-Objekte mit gegebenem Namen
	     * @param name
	     * 
	     * @return Vektor mit CinemaChain-Objekten
	     */
	    public Vector<CinemaChain> findCinemaChainByName(String name) {
	        Connection con = DBConnection.connection();
	        Vector<CinemaChain> result = new Vector<CinemaChain>();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT * FROM cinemachain WHERE name= '" + name + "'");
	        	//F�r jeden Eintrag im Suchergebnis wird ein CinemaChain-Objekt erstellt
	        	while(rs.next()) {
	        		CinemaChain cc = new CinemaChain();
	        		cc.setName(rs.getString("name"));
	        		cc.setId(rs.getInt("id"));
	        		
	        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
	        		result.addElement(cc);
	        	}
	        }
	        	catch(SQLException e2) {
	        		e2.printStackTrace();
	        	}
	        	//R�ckgabe des Ergebnisvektors
	        	return result;
	        }
	    
	    /**
	     * L�schen einer Kinokette anhand des Namens
	     * @param name
	     */
	    public void deleteCinemaChainByName(String name) {
	    	Connection con = DBConnection.connection();
	    	
	    	try {
	    		Statement stmt = con.createStatement();
	    		stmt.executeUpdate("DELETE FROM cinemachain" + "WHERE cinemachain.name='" + name + "'");
	    	}
	    	catch(SQLException e2) {
	    		e2.printStackTrace();
	    	}
	    }
	    
	    /**
	     * Methode, die einen Vektor mit allen erstellen CinemaChain Objekten einer Person zur�ckgibt
	     * @param personFK 
	     * @return Vektor mit Kinoketten-Objekten eines Erstellers/Besitzers
	     */
	    public Vector<CinemaChain> findCinemaChainByPersonFK(int personFK) {
	        Connection con = DBConnection.connection();
	        Vector<CinemaChain> result = new Vector<CinemaChain>();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	
	        	ResultSet rs = stmt.executeQuery("SELECT cinemachain.name, cinemachain.id FROM cinemachain INNER JOIN popcorns.businessownership ON cinemachain.id = businessownership.id AND businessownership.personFK= '" + personFK+ "'");
	        	
	        	//F�r jeden Eintrag im Suchergebnis wird ein CinemaChain-Objekt zugeordnet
	        	while(rs.next()) {
	        		CinemaChain cc = new CinemaChain();
	        		cc.setName(rs.getString("name"));
	        		cc.setId(rs.getInt("id"));
	        		
	        	        		
	        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
	        		result.addElement(cc);
	        	}
	        }
	        catch(SQLException e2) {
	        	e2.printStackTrace();
	        }
	        return result;
	        
	    }
	    

}
