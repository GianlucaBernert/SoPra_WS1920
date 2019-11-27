package de.hdm.SoPra_WS1920.server;

import java.util.*;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.SoPra_WS1920.server.db.CinemaMapper;
import de.hdm.SoPra_WS1920.server.db.MovieMapper;
import de.hdm.SoPra_WS1920.server.db.PersonMapper;
import de.hdm.SoPra_WS1920.server.db.ScreeningMapper;
import de.hdm.SoPra_WS1920.shared.CinemaAdministration;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

/**
 * @author MatthiasKling
 */
@SuppressWarnings("serial")
public class CinemaAdministrationImpl extends RemoteServiceServlet implements CinemaAdministration {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public CinemaAdministrationImpl() {
    }

    /**
     * Referenzen auf Mapperklassen
     */
    private CinemaMapper cMapper = null;

    private MovieMapper mMapper = null;

    private ScreeningMapper sMapper = null;

    public PersonMapper pMapper = null;
    
    /**
     * Initialisierung
     */
    
    public void init() {
    	
    	this.cMapper = CinemaMapper.cineMapper();
    	this.mMapper = MovieMapper.moviemapper();
    	this.sMapper = ScreeningMapper.screeningMapper();
    	this.pMapper = PersonMapper.personMapper();
    }
    
    
    

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param postCode 
     * @return
     */
    @Override
    public Cinema createCinema(String name, String cityName, String street, String streetNr, String postCode, int PersonFK) {

        	Cinema c = new Cinema();
        	
        	c.setName(name);
        	c.setCity(cityName);
        	c.setStreet(street);
        	c.setPostCode(postCode);
        	c.setId(1);
        	c.setPersonFK(PersonFK);
        	
        	return this.cMapper.insertCinema(c);
        	
        
    }
    
    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    @Override
    public Movie createMovie(String name, String genre, String description, int PersonFK) {
        
    	Vector <Movie> m1 = new Vector <Movie>();
    	m1 = mMapper.findMovieByName(name);
		
		if (m1 != null) {
			return null;
		}
		
		else {
			
		Movie m	= new Movie();
		
		m.setName(name);
		m.setGenre(genre);
		m.setDescription(description);
		m.setId(1);
		m.setPersonFK(PersonFK);
		
		return this.mMapper.insertMovie(m);
			
		}
    	
        
    }

    /**
     * @param screeningDateTime 
     * @param cinemaFK 
     * @param movieFK 
     * @return
     */
    @Override
    public Screening createScreening(DateTime screeningDateTime, int cinemaFK, int movieFK) {
        
    	Screening s = new Screening();
    	
    	s.setCinemaFK(cinemaFK);
    	s.setMovieFK(movieFK);
    	/**
    	 * TO DO: Date + Time anstelle von DateTime siehe bo screening
    	 */
        return null;
    } 

    /**
     * @param cinema 
     * @return
     */
    @Override
    public Void deleteCinema(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    @Override
    public Void deleteMovie(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    @Override
    public Void deleteScreening(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Cinema getCinemaById(int id) {
        
    	
        return this.cMapper.findCinemaByID(id);
    }

    /**
     * @param name 
     * @return
     */
    @Override
    public Vector<Cinema> getCinemaByName(String name) {
        
        return this.cMapper.findCinemaByName(name);
    }

    /**
     * @param cityName 
     * @return
     */
    @Override
    public Vector<Cinema> getCinemaByCity(String cityName) {
        
        return this.cMapper.findCinemaByCity(cityName);
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Movie getMovieById(int id) {
        
        return this.mMapper.findMovieByID(id);
    }

    /**
     * @param name 
     * @return
     */
    @Override
    public Vector<Movie> getMoviesByName(String name) {
       
        return this.mMapper.findMovieByName(name);
    }

    /**
     * @param genre 
     * @return
     */
    @Override
    public Vector<Movie> getMovieByGenre(String genre) {
        
        return this.mMapper.findMovieByGenre(genre);
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Screening getScreeningById(int id) {
        
        return this.sMapper.findScreeningByID(id);
    }

    /**
     * @param cinemaFK 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByCinemaFK(int cinemaFK) {
        
        return this.sMapper.findScreeningByCinemaFK(cinemaFK);
    }

    /**
     * @param movieFK 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByMovieFK(int movieFK) {
        
        return this.sMapper.findScreeningByMovieFK(movieFK);
    }

    /**
     * @param screeningDateTime 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByScreeningDateTime(DateTime screeningDateTime) {
        
        return this.sMapper.findScreeningByScreeningDateTime(screeningDateTime);
    }

    /**
     * @param cinema 
     * @return
     */
    @Override
    public Cinema updateCinema(Cinema cinema) {
        
        return this.cMapper.updateCinema(cinema);
    }

    /**
     * @param movie 
     * @return
     */
    @Override
    public Movie updateMovie(Movie movie) {
        
        return this.mMapper.updateMovie(movie);
    }

    /**
     * @param screening
     */
    @Override
    public Screening updateScreening(Screening screening) {
		
    	return this.sMapper.updateScreening(screening);
     
    }

    /**
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @return
     */
    @Override
    public Person createPerson(String firstName, String lastName, String eMail, boolean isAdmin) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Person getPersonById(int id) {
        
        return this.pMapper.findPersonByID(id);
    }

    /**
     * @param firstName 
     * @return
     */
    @Override
    public Person getPersonByFirstName(String firstName) {
        
        return this.pMapper.findPersonByFirstname(firstname);
    }

    /**
     * @param lastName 
     * @return
     */
    @Override
    public Person getPersonByLastName(String lastName) {
        
        return this.pMapper.findPersonByLastname(lastname);
    }

    /**
     * @param eMail 
     * @return
     */
    @Override
    public Person getPersonByEMail(String eMail) {
        
        return this.pMapper.findPersonByEmail(email);
    }

    /**
     * @param person 
     * @return
     */
    @Override
    public Void deletePerson(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    @Override
    public Person updatePerson(Person person) {
        
        return this.pMapper.updatePerson(person);
    }

}