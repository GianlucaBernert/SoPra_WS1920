package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;
import java.sql.*;


import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>MovieMapper</code> werden Movie-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden können Movie-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
 * 
 */
public class MovieMapper {

	/**
     * Durch einem sogeannten <b>Singleton<b> kann die Klasse MovieMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
     */
    private static MovieMapper movieMapper = null;

    /**
     * Ein gesch?tzter Konstruktor der weitere Instanzierungen von MovieMapper Objekten verhindert.
     */
    protected MovieMapper() {
 
    }

    /**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * Sie daf?r sorgt, dass nur eine einzige Instanz von <code>MovieMapper</code> existiert.
	 * @return Sie gibt den MovieMapper zur?ck.
	 */
    
    public static MovieMapper moviemapper() {
        if (movieMapper == null) {
        	movieMapper = new MovieMapper();
        }
        return movieMapper;
    }

    /**
     * @param Movie
     *           Prim?rschl?sselattribut (->DB)
	 * @return Movie-Objekt, das dem ?bergebenen Schl?ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 */
    
    
    /*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 * 
	 */	
    public Movie findMovieByID(int movieID) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM movie " + "WHERE bo_id= " + movieID);
			
			if(rs.next()) {
				Movie m = new Movie();
				m.setName(rs.getString("name"));
				m.setGenre(rs.getString("genre"));
				m.setDescription(rs.getString("description"));
				return m;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    
    /**
	 * Methode, die das Anlegen eines Movie-Objekts ermöglicht
	 * @param person
	 */
    
    
    public Movie insertMovie(Movie movie) {
    	Connection con = DBConnection.connection();

    	try {
		
    		Statement stm1 = con.createStatement();
    		Statement stm2 = con.createStatement();
    		Statement stm3 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO businessobject (bo_id, creationTimeStamp) VALUES ('"
								+movie.getId()
								+"', '"+movie.getCreationTimestamp()
								+"')");
    		stm2.executeUpdate("INSERT INTO businessownership (bo_id, creationTimeStamp, PersonFK) VALUES ('"
    							+ movie.getId()
    							+ "', '"+movie.getCreationTimestamp()
    							+ "', '"+movie.getPersonFK()
    							+"')");
			stm3.executeUpdate("INSERT INTO movie (bo_id, name, genre, description, creationTimeStamp) VALUES ('"
								+movie.getId()
								+"', '"+movie.getName()
								+"', '"+movie.getGenre()
								+"', '"+movie.getDescription()
								+"', '"+movie.getCreationTimestamp()
								+"')");
			
		}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}
        return movie;
    }

    /**
	 * Methode, die das Updaten eines Movie-Objekts in der Datenbank ermöglicht	
	 * @param person
	 */
    
    public Movie updateMovie(Movie movie) {
    	Connection con = DBConnection.connection();

    	try {
    	
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE movie Set name='"+movie.getName()
    				+"', genre='"+movie.getGenre()
    				+"', description='"+movie.getDescription()
    				+"' Where bo_id="+movie.getId());
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
        return null;
    }

    /**
	 * Methode, die das Loeschen eines Movie-Objekts aus der Datenbank ermöglicht
	 * @param person
	 */
    public void deleteMovie(Movie movie) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			Statement stm2 = con.createStatement();
			Statement stm3 = con.createStatement();
			
			stm1.executeUpdate("Delete from movie Where bo_id = "+movie.getId());
			stm2.executeUpdate("Delete from businessownership Where bo_id = "+movie.getId());
			stm3.executeUpdate("Delete from businessobject Where bo_id = "+movie.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

    
    /* Ende: Standard-Mapper-Methoden
 	 * ================================================================================================
 	 * Beginn: Foreign Key-Mapper-Methoden
 	 */
    /**
     * @param id 
     * @return
     */
    public Vector<Movie> findMovieByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		Vector<Movie> result = new Vector<Movie>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT movie.bo_id, movie.name, movie.genre, movie.description FROM movie\n" + 
					"INNER JOIN popcorns.businessownership\n" + 
					"ON businessownership.bo_id = movie.bo_id\n" + 
					"AND businessownership.personFK = " + personFK);
		
			while (rs.next()) {
				Movie m = new Movie();
				m.setName(rs.getString("name"));
				m.setGenre(rs.getString("genre"));
				m.setDescription(rs.getString("description"));
				result.add(m);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    /**
     * @param person 
     * @return
     */
    /*public void deleteMovieByPersonFK(Person person) {
       /** Umsetzung überhaupt nötig? Movies einer Person sollten in der Regel nicht gelöscht werden
        return null;
    }
    
    
     /* Ende:  Foreign Key-Mapper-Methoden
     * ================================================================================================
     * Beginn: Spezifische Business Object Methoden
	 */	
    	
    	
    /**
     * @param name 
     * @return
     */
    public Vector<Movie> findMovieByName(String name) {
    	Connection con = DBConnection.connection();
		Vector<Movie> result = new Vector<Movie>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM movie Where name='" +name+"'");
		
			while (rs.next()) {
				Movie m = new Movie();
				m.setName(rs.getString("name"));
				m.setGenre(rs.getString("genre"));
				m.setDescription(rs.getString("description"));
				result.add(m);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    /**
     * @param name 
     * @return
     */
    public void deleteMovieByName(String name) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stm1 = con.createStatement();
			
			stm1.executeUpdate("Delete from movie Where name='" +name+"'");
				
						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}        
    }

    /**
     * @param genre 
     * @return
     */
    public Vector<Movie> findMovieByGenre(String genre) {
    	Connection con = DBConnection.connection();
		Vector<Movie> result = new Vector<Movie>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM movie Where genre='" +genre+"'");
		
			while (rs.next()) {
				Movie m = new Movie();
				m.setName(rs.getString("name"));
				m.setGenre(rs.getString("genre"));
				m.setDescription(rs.getString("description"));
				result.add(m);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    

}