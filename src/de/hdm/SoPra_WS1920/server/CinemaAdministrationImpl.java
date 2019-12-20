package de.hdm.SoPra_WS1920.server;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.SoPra_WS1920.server.db.CinemaChainMapper;
import de.hdm.SoPra_WS1920.server.db.CinemaMapper;
import de.hdm.SoPra_WS1920.server.db.MovieMapper;
import de.hdm.SoPra_WS1920.server.db.OwnershipMapper;
import de.hdm.SoPra_WS1920.server.db.PersonMapper;
import de.hdm.SoPra_WS1920.server.db.ScreeningMapper;
import de.hdm.SoPra_WS1920.server.db.SurveyEntryMapper;
import de.hdm.SoPra_WS1920.server.db.VoteMapper;
import de.hdm.SoPra_WS1920.shared.CinemaAdministration;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Ownership;
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

    private ScreeningMapper scMapper = null;

    public PersonMapper pMapper = null;
    
    public SurveyEntryMapper seMapper = null;
    
    public VoteMapper vMapper = null;
    
    private CinemaChainMapper ccMapper = null;
    
    private OwnershipMapper oMapper = null;
    
    /**
     * Initialisierung
     */
    
    public void init() {
    	
    	this.cMapper = CinemaMapper.cinemaMapper();
    	this.mMapper = MovieMapper.moviemapper();
    	this.scMapper = ScreeningMapper.screeningMapper();
    	this.pMapper = PersonMapper.personMapper();
    	this.seMapper = SurveyEntryMapper.surveyEntryMapper();
    	this.vMapper = VoteMapper.voteMapper();
    	this.ccMapper = CinemaChainMapper.cinemaChainMapper();
    	this.oMapper = OwnershipMapper.ownershipMapper();
    	
    }
    
    
    

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param postCode 
     * @return
     */
    @Override
    public Cinema createCinema(String name, String cityName, String street, String streetNr, String postCode, int personFK, Timestamp creationTimestamp) {
    		
    	
        	Cinema c = new Cinema();
        	
        	c.setName(name);
        	c.setCity(cityName);
        	c.setStreet(street);
        	c.setPostCode(postCode);
        	c.setId(1);
        	c.setCreationTimestamp(creationTimestamp);
        	
        	this.cMapper.insertCinema(c);
        	
        	this.createOwnership(personFK, creationTimestamp, c.getId());
        	
        	return c;
        	
        
    }
    
    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    @Override
    public Movie createMovie(String name, String genre, String description, int personFK, Timestamp creationTimestamp) {
        
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
		
		this.mMapper.insertMovie(m);
		
		this.createOwnership(personFK, creationTimestamp, m.getId());
		
		return  m;
			
		}
    	
        
    }

    /**
     * @param screeningDateTime 
     * @param cinemaFK 
     * @param movieFK 
     * @return
     */
    @Override
    public Screening createScreening(Date screeningDateTime, int cinemaFK, int movieFK, int personFK, Timestamp creationTimestamp) {
        
    	Screening sc = new Screening();
    	
    	sc.setCinemaFK(cinemaFK);
    	sc.setMovieFK(movieFK);
    	sc.setScreeningDateTime(screeningDateTime);
    	sc.setId(1);
    	sc.setCreationTimestamp(creationTimestamp);
    	
        this.scMapper.insertScreening(sc);
        
        this.createOwnership(personFK, creationTimestamp, sc.getId());
        
        return sc;
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
        this.deleteOwnership(this.findOwnership(cinema.getId()));
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
       this.deleteOwnership(this.findOwnership(movie.getId()));
    }

    /**
     * @param screening 
     * @return
     */
    @Override
    public void deleteScreening(Screening screening) {
        
    	Vector<SurveyEntry> ses = this.getSurveyEntryByScreeningFK(screening.getId());
    	
    	if (ses != null) {
    		for (SurveyEntry se : ses) {
    			this.deleteSurveyEntry(se);
    		}
    	}
    	
        this.scMapper.deleteScreening(screening);
        this.deleteOwnership(this.findOwnership(screening.getId()));
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
        
        return this.scMapper.findScreeningByID(id);
    }

    /**
     * @param cinemaFK 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByCinemaFK(int cinemaFK) {
        
        return this.scMapper.findScreeningByCinemaFK(cinemaFK);
    }

    /**
     * @param movieFK 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByMovieFK(int movieFK) {
        
        return this.scMapper.findScreeningByMovieFK(movieFK);
    }

    /**
     * @param screeningDateTime 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByScreeningDateTime(Timestamp screeningDateTime) {
        
        return this.scMapper.findScreeningByScreeningDateTime(screeningDateTime);
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
		
    	return this.scMapper.updateScreening(screening);
     
    }

    /**
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @return
     */
    @Override
    public Person createPerson(String firstName, String lastName, String eMail, Timestamp creationTimestamp) {
        
    	Person p = new Person();
    	
    	p.setEMail(eMail);
    	p.setFirstname(firstName);
    	p.setLastname(lastName);
    	p.setId(1);
    	p.setCreationTimestamp(creationTimestamp);
    	
        return this.pMapper.insertPerson(p);
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
    public Vector<Person> getPersonByFirstName(String firstName) {
        
        return this.pMapper.findPersonByFirstname(firstName);
    }

    /**
     * @param lastName 
     * @return
     */
    @Override
    public Vector<Person> getPersonByLastName(String lastName) {
        
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
    	this.deleteOwnership(this.findOwnership(vote.getId()));
        
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
    
    @Override
    public Ownership createOwnership(int personFK, Timestamp creationTimestamp, int id ) {
    	
    	Ownership o = new Ownership();
    	o.setId(id);
    	o.setPersonFK(personFK);
    	o.setCreationTimestamp(creationTimestamp);
    	
    	
    	return this.oMapper.insertOwnership(o);
    }
    
    @Override
    public void deleteOwnership(Ownership ownership) {
    	this.oMapper.deleteOwnership(ownership);
    }
    
    @Override
    public Ownership findOwnership(int id) {
    	return this.oMapper.findOwnershipByID(id);
    }
    
    @Override
    public Vector <Ownership> getOwnershipsbyPersonFK(int personFK){
    	return this.oMapper.findOwnershipByPersonFK(personFK);
    }
    
    @Override
    public CinemaChain createCinemaChain(Cinema c , String name, Timestamp creationTimestamp,int personFK) {
    	
    	CinemaChain cc = new CinemaChain();
    	
    	cc.setId(1);
    	cc.setName(name);
    	cc.setCreationTimestamp(creationTimestamp);
    	
    	
    	this.ccMapper.insertCinemaChain(cc);
    	
    	c.setId(cc.getId());
    	this.updateCinema(c);
    	
    	this.createOwnership(personFK, creationTimestamp, cc.getId());
    	
    	return cc;
    	
    }
    
    // Methode updateCinemaId hierzu muss bei einem Vector von ausgew�hlten Kino Objekten die CinemaChain ID geupdatet werden.
    
    @Override
    public CinemaChain updateCinemaChain(CinemaChain cc) {
    	return this.ccMapper.updateCinemaChain(cc);
    }
    
    @Override
    public void deleteCinemaChain(CinemaChain cc) {
    	this.ccMapper.deleteCinemaChain(cc);
    	this.deleteOwnership(this.findOwnership(cc.getId()));
    	// L�schen der CinemaChainID in den Cinema Objekten notwenig!
    }
    
    @Override
    public CinemaChain findCinemaChainByName(String name) {
    	return this.ccMapper.findCinemaChainByName(name);
    }
    
    @Override
    public Vector <CinemaChain> findCinemaChainByPersonFK(int personFK){
    	return this.ccMapper.findCinemaChainByPersonFK(personFK);
    }
    
    @Override
    public CinemaChain findCinemaChainById(int id) {
    	return this.ccMapper.findCinemaChainByID(id);
    }
    
    @Override
    public Vector <Cinema> findCinemasByPersonFK(int personFK){
    	return this.cMapper.findCinemaByPersonFK(personFK);
    }
    
    /**
     * Methode zum Aufrufen aller zugeh�rigen Cinema Objekte einer CinemaChain
     * @param cc
     * @return
     */
    @Override
    public Vector <Cinema> findCinemasByCinemaChainID(CinemaChain cc){
    	return this.ccMapper.findCinemasByCinemaChainID(cc);
    }
    
}