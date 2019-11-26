package de.hdm.SoPra_WS1920.server;

import java.sql.Time;
import java.util.*;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.SoPra_WS1920.server.db.CinemaMapper;
import de.hdm.SoPra_WS1920.server.db.MovieMapper;
import de.hdm.SoPra_WS1920.server.db.PersonMapper;
import de.hdm.SoPra_WS1920.server.db.ScreeningMapper;
import de.hdm.SoPra_WS1920.server.db.SurveyEntryMapper;
import de.hdm.SoPra_WS1920.server.db.VoteMapper;
import de.hdm.SoPra_WS1920.shared.CinemaAdministration;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

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
    
    public SurveyEntryMapper seMapper = null;
    
    public VoteMapper vMapper = null;
    
    /**
     * Initialisierung
     */
    
    public void init() {
    	
    	this.cMapper = CinemaMapper.cineMapper();
    	this.mMapper = MovieMapper.moviemapper();
    	this.sMapper = ScreeningMapper.screeningMapper();
    	this.pMapper = PersonMapper.personMapper();
    	this.seMapper = SurveyEntryMapper.surveyEntryMapper();
    	this.vMapper = VoteMapper.voteMapper();
    	
    }
    
    
    

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param postCode 
     * @return
     */
    @Override
    public Cinema createCinema(String name, String cityName, String street, String streetNr, String postCode, int personFK) {

        	Cinema c = new Cinema();
        	
        	c.setName(name);
        	c.setCity(cityName);
        	c.setStreet(street);
        	c.setPostCode(postCode);
//        	TO DO: Postcode in Klasse Cinema zu typ String ändern
        	c.setId(1);
        	c.setPersonFK(personFK);
        	
        	return this.cMapper.insertCinema(c);
        	
        
    }
    
    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    @Override
    public Movie createMovie(String name, String genre, String description, int personFK) {
        
    	Vector <Movie> m1 = new Vector <Movie>();
    	m1 = mMapper.findMovieByName(name);
		
		if (m1 != null) {
			return null;
		}
		
		else {
			
//		TO DO/DISCUSS: braucht movie einen Person FK? Probleme beim löschen der Person!	
			
		Movie m	= new Movie();
		
		m.setName(name);
		m.setGenre(genre);
		m.setDescription(description);
		m.setId(1);
		m.setPersonFK(personFK);
		
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
    public Screening createScreening(Date date, Time time, int cinemaFK, int movieFK, int personFK) {
        
    	Screening s = new Screening();
    	
    	s.setCinemaFK(cinemaFK);
    	s.setMovieFK(movieFK);
    	s.setDate(date);
    	s.setTime(time);
    	s.setId(1);
    	s.setPersonFK(personFK);
    	
        return null;
    } 

    /**
     * @param cinema 
     * @return
     */
    @Override
    public void deleteCinema(Cinema cinema) {
    	
    	//Loeschen aller Screenings eines Kinos
        
    	Vector<Screening> s = this.getScreeningByCinemaFK(cinema.getId());
    	
    	if (s != null) {
    		for(Screening s1 : s) {
    			this.deleteScreening(s1);
    		}
    	}
    	
    	//Loeschen des Cinema Object
        this.cMapper.deleteCinema(cinema);
    }

    /**
     * @param movie 
     * @return
     */
    @Override
    public void deleteMovie(Movie movie) {
        
    	Vector<Screening> screenings = this.getScreeningByMovieFK(movie.getId());
    	
    	if(screenings != null) {
    		for(Screening s : screenings) {
    			this.deleteScreening(s);
    		}
    	}
    	
       this.mMapper.deleteMovie(movie); 
    }

    /**
     * @param screening 
     * @return
     */
    @Override
    public void deleteScreening(Screening screening) {
        
    	Vector<SurveyEntry> ses = this.getSurveyEntryByScreening(screening.getId());
    	
    	if (ses != null) {
    		for (SurveyEntry se : ses) {
    			this.deleteSurveyEntry(se);
    		}
    	}
    	
        this.sMapper.deleteScreening(screening);
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
    public Person createPerson(String firstName, String lastName, String eMail, int isAdmin) {
        
    	Person p = new Person();
    	
    	p.setEMail(eMail);
    	p.setFirstname(firstName);
    	p.setLastname(lastName);
    	p.setIsAdmin(isAdmin);
    	p.setId(1);
    	
        return pMapper.insertPerson(p);
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
        
        return this.pMapper.findPersonByFirstname(firstName);
    }

    /**
     * @param lastName 
     * @return
     */
    @Override
    public Person getPersonByLastName(String lastName) {
        
        return this.pMapper.findPersonByLastname(lastName);
    }

    /**
     * @param eMail 
     * @return
     */
    @Override
    public Person getPersonByEMail(String eMail) {
        
        return this.pMapper.findPersonByEmail(eMail);
    }

    /**
     * @param person 
     * @return
     */
    @Override
    public void deletePerson(Person person) {
        // TODO implement here
        
    }

    /**
     * @param person 
     * @return
     */
    @Override
    public Person updatePerson(Person person) {
        
        return this.pMapper.updatePerson(person);
    }
    
    /**
     * @param vote 
     * @return
     */
    @Override
    public void deleteVote(Vote vote) {
        
    	this.vMapper.deleteVote(vote);
        
    }

    /**
     * @param surveyentry 
     * @return
     */
    @Override
    public void deleteSurveyEntry(SurveyEntry surveyEntry) {
        
    	Vector<Vote> votes = this.getVotesBySurveyEntryFK(surveyEntry.getId());
    	
    	if(votes != null) {
    		for(Vote v : votes) {
    			this.deleteVote(v);
    		}
    	}
    	
    	this.seMapper.deleteSurveyEntry(surveyEntry);
        
    }
    
    
    @Override
    public Vector<Vote> getVotesBySurveyEntryFK(int surveyEntryFK){
    	
    	return this.vMapper.findVoteBySurveyEntryFK(surveyEntryFK);
    }
    
    
    @Override
    public Vector<SurveyEntry> getSurveyEntryByScreeningFK(int screeningFK){
    	
    	return this.seMapper.findSurveyEntryByScreeningFK(screeningFK);
    }

}