package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>MovieMapper</code> werden Movie-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden k�nnen Movie-Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden.
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
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgez�hlt, die
	 * in allen Mapper-Klassen existieren.
	 */	
  
    
    /**
     * Methode, die ein Movie Objekt anhand der ID zur�ckgibt
     * @param int movieID 
     * @return movie m
     */
    public Movie findMovieByID(int movieID) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM movie " + "WHERE id= " + movieID);
			
			if(rs.next()) {
				Movie m = new Movie();
				m.setName(rs.getString("name"));
				m.setGenre(rs.getString("genre"));
				m.setDescription(rs.getString("description"));
				m.setId(rs.getInt("id"));
				return m;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    
    /**
	 * Methode, die das Anlegen eines Movie-Objekts erm�glicht
	 * @param movie
	 * @return movie
	 */
    
    
    public Movie insertMovie(Movie movie) {
    	Connection con = DBConnection.connection();

    	try {
    		
    		Statement stm = con.createStatement();

			stm.executeUpdate("INSERT INTO movie (id, name, genre, description) VALUES ('"
								+movie.getId()
								+"', '"+movie.getName()
								+"', '"+movie.getGenre()
								+"', '"+movie.getDescription()
								+"')");
			
    	}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}
        return movie;
    }

    /**
	 * Methode, die das Updaten eines Movie-Objekts in der Datenbank erm�glicht	
	 * @param movie
	 * @return movie
	 */
    
    public Movie updateMovie(Movie movie) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE movie Set name='"+movie.getName()
    				+"', genre='"+movie.getGenre()
    				+"', description='"+movie.getDescription()
    				+"' Where id="+movie.getId());
    		con.setAutoCommit(true);
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
        return movie;
    }

    /**
	 * Methode, die das Loeschen eines Movie-Objekts aus der Datenbank erm�glicht
	 * @param movie
	 */
    
    public void deleteMovie(Movie movie) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			
			stm1.executeUpdate("Delete from movie Where id = "+movie.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

    
    /* Ende: Standard-Mapper-Methoden
 	 * ================================================================================================
 	 * Beginn: Foreign Key-Mapper-Methoden
 	 */
    
    /**
     * Methode, die alle Movie Objekte zur�ckgibt, die ein User erstellt hat
     * @param int personFK
     * @return Vektor movie
     */
    public Vector<Movie> findMovieByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		Vector<Movie> result = new Vector<Movie>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT movie.id, movie.name, movie.genre, movie.description FROM movie "
					+ "INNER JOIN popcorns.businessownership "
					+ "ON businessownership.id = movie.id "
					+ "AND businessownership.personFK ='" + personFK+"'");
		
			while (rs.next()) {
				Movie m = new Movie();
				m.setName(rs.getString("name"));
				m.setGenre(rs.getString("genre"));
				m.setDescription(rs.getString("description"));
				m.setId(rs.getInt("id"));
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
       /** Umsetzung �berhaupt n�tig? Movies einer Person sollten in der Regel nicht gel�scht werden
        return null;
    }
    
    
     /* Ende:  Foreign Key-Mapper-Methoden
     * ================================================================================================
     * Beginn: Spezifische Business Object Methoden
	 */	
    	
    /** 
     * Methode, die alle Movies in der Datenbank zur�ckgibt
     * @return Vektor movie
     */
    // Find-All Methode zum Abrufen aller Movie-Objekte
    public Vector<Movie> findAll() {
        Connection con = DBConnection.connection();
        Vector<Movie> result = new Vector<Movie>();

        try {
          Statement stmt = con.createStatement();

          ResultSet rs = stmt.executeQuery("SELECT * FROM movie "
              + " ORDER BY id");

          while (rs.next()) {
            Movie m = new Movie();
            m.setId(rs.getInt("id"));
            m.setName(rs.getString("name"));
            m.setGenre(rs.getString("genre"));
            m.setDescription(rs.getString("description"));
            m.setId(rs.getInt("id"));

            // Hinzuf�gen des neuen Objekts zum Ergebnisvektor
            result.addElement(m);
          }
        }
        catch (SQLException e2) {
          e2.printStackTrace();
        }

        // Ergebnisvektor zur�ckgeben
        return result;
      }
    
    
    /**
     * Methode, die Movie Objekte anhand des Namens zur�ckgibt
     * @param name 
     * @return Vektor movie
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
				m.setId(rs.getInt("id"));
				result.add(m);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    /**
     * Methode, die ein Movie Objekt anhand des Namens l�scht
     * @param name 
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
     * Methode, die Movie Objekte anhand des Genres zur�ckgibt.
     * @param genre 
     * @return Vektor movie
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
				m.setId(rs.getInt("id"));
				result.add(m);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    

}