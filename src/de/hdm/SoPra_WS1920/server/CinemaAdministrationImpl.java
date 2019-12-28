package de.hdm.SoPra_WS1920.server;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.SoPra_WS1920.server.db.BusinessObjectMapper;
import de.hdm.SoPra_WS1920.server.db.CinemaChainMapper;
import de.hdm.SoPra_WS1920.server.db.CinemaMapper;
import de.hdm.SoPra_WS1920.server.db.GroupMapper;
import de.hdm.SoPra_WS1920.server.db.MovieMapper;
import de.hdm.SoPra_WS1920.server.db.OwnershipMapper;
import de.hdm.SoPra_WS1920.server.db.PersonMapper;
import de.hdm.SoPra_WS1920.server.db.ScreeningMapper;
import de.hdm.SoPra_WS1920.server.db.SurveyEntryMapper;
import de.hdm.SoPra_WS1920.server.db.SurveyMapper;
import de.hdm.SoPra_WS1920.server.db.VoteMapper;
import de.hdm.SoPra_WS1920.shared.CinemaAdministration;
import de.hdm.SoPra_WS1920.shared.bo.BusinessObject;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Ownership;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
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
    
    private BusinessObjectMapper boMapper = null;
    
    private GroupMapper gMapper = null;
    
    private SurveyMapper sMapper = null;
    
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
    	this.boMapper = BusinessObjectMapper.businessObjectMapper();
    	this.gMapper = GroupMapper.groupMapper();
    	this.sMapper = SurveyMapper.surveyMapper();
    	
    }
    
    
    

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param postCode 
     * @return
     */
    @Override
    public Cinema createCinema(String name, String cityName, String street, String streetNr, String zipCode, int ccFK, int personFK) {
    		
    		Ownership o = this.createOwnership(personFK);
    	
        	Cinema c = new Cinema();
        	
        	c.setName(name);
        	c.setCity(cityName);
        	c.setStreet(street);
        	c.setZipCode(zipCode);
        	c.setCinemaChainFK(ccFK);
        	c.setId(o.getId());
        	
        	this.cMapper.insertCinema(c);
        	
        	return c;
        	
        
    }
    
    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    @Override
    public Movie createMovie(String name, String genre, String description, int personFK) {
			
		Ownership o = this.createOwnership(personFK);	
			
		Movie m	= new Movie();
		
		m.setName(name);
		m.setGenre(genre);
		m.setDescription(description);
		m.setId(o.getId());
		
		this.mMapper.insertMovie(m);
		
		
		
		return  m;
			
		}
    	
  

    /**
     * @param screeningDateTime 
     * @param cinemaFK 
     * @param movieFK 
     * @return
     */
    @Override

    public Screening createScreening(Date screeningDate, Time screeningTime, int cinemaFK, int movieFK, int personFK) {

        
    	
    	Ownership o = this.createOwnership(personFK);
    	
    	Screening sc = new Screening();
    	
    	sc.setCinemaFK(cinemaFK);
    	sc.setMovieFK(movieFK);
    	sc.setScreeningDate(screeningDate);
    	sc.setScreeningTime(screeningTime);
    	sc.setId(o.getId());

    	return this.scMapper.insertScreening(sc);
        
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
    	//Wenn cc Id -> 
    	
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
    public Vector<Screening> getScreeningByScreeningDate(Date screeningDate) {
        
        return this.scMapper.findScreeningByScreeningDate(screeningDate);
    }
    
    @Override
    public Vector<Screening> getScreeningByScreeningTime(Time screeningTime){
    	
    	return this.scMapper.findScreeningByScreeningTime(screeningTime);
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
    public Person createPerson(String firstName, String lastName, String eMail) {
        
    	BusinessObject bo = this.createBusinessObject();
    	
    	Person p = new Person();
    	
    	p.setEMail(eMail);
    	p.setFirstname(firstName);
    	p.setLastname(lastName);
    	p.setId(bo.getId());
    	
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
    	this.deleteBusinessObject(this.boMapper.findBusinessObjectByID(surveyEntry.getId()));
        
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
    public Ownership createOwnership(int personFK) {
    	
    	BusinessObject bo = this.createBusinessObject();
    	

    	Ownership o = new Ownership();
    	o.setId(bo.getId());
    	o.setPersonFK(personFK);
    	
    	return this.oMapper.insertOwnership(o);
    }
    
    @Override
    public void deleteOwnership(Ownership ownership) {
    	this.oMapper.deleteOwnership(ownership);
    	this.deleteBusinessObject(this.boMapper.findBusinessObjectByID(ownership.getId()));
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
    public CinemaChain createCinemaChain(String name, int personFK) {
    	
    	
    	Ownership o = this.createOwnership(personFK);
    	
    	CinemaChain cc = new CinemaChain();
    	
    	cc.setId(o.getId());
    	cc.setName(name);
    	
    	cc = this.ccMapper.insertCinemaChain(cc);
    	
    	//c.setId(cc.getId());
    	//this.updateCinema(c);
    	
    	
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
    public Vector<CinemaChain> findCinemaChainByName(String name) {
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
    public Vector <Cinema> findCinemasByCinemaChainFK(CinemaChain cc){
    	return this.cMapper.findCinemaByCinemaChainFK(cc.getId());
    }
    
    @Override
    public BusinessObject createBusinessObject(){
    	BusinessObject bo = new BusinessObject();
    	Timestamp time = new Timestamp(System.currentTimeMillis());
    	bo.setCreationTimestamp(time);
    	this.boMapper.insertBusinessObject(bo);
    	return bo;
    }
    
    @Override
    public void deleteBusinessObject(BusinessObject bo) {
    	this.boMapper.deleteBusinessObject(bo);
    }
    
    @Override
    public void deletePerson(Person p) {
        Vector<Vote> vOfPerson = this.vMapper.findVoteByPersonFK(p.getId());
        if (vOfPerson != null) {
        	for (Vote v : vOfPerson) {
        		this.vMapper.deleteVote(v);
        	}
        }
        
        Vector<Survey> sOfPerson = this.sMapper.findSurveyByPersonFK(p.getId());
        if (sOfPerson != null) {
        	for (Survey s : sOfPerson) {
        		this.sMapper.deleteSurvey(s);
        	}
        }
        
        Vector<Group> gOfPerson = this.gMapper.findGroupByPersonFK(p.getId());
        if (gOfPerson != null) {
        	for (Group g : gOfPerson) {
        		this.gMapper.deleteGroup(g);
        	}
        }
        
        Vector<Screening> scOfPerson = this.scMapper.findScreeningByPersonFK(p.getId());
        if (scOfPerson != null) {
        	for (Screening sc : scOfPerson) {
        		this.scMapper.deleteScreening(sc);
        	}
        }
        
        Vector<Cinema> cOfPerson = this.cMapper.findCinemaByPersonFK(p.getId());
        if (cOfPerson != null) {
        	for (Cinema c : cOfPerson) {
        		this.cMapper.deleteCinema(c);
        	}
        }
        
        Vector<Movie> mOfPerson = this.mMapper.findMovieByPersonFK(p.getId());
        if (mOfPerson != null) {
        	for (Movie m : mOfPerson) {
        		this.mMapper.deleteMovie(m);
        	}
        }
        
        Vector<Ownership> osOfPerson = this.oMapper.findOwnershipByPersonFK(p.getId());
        if (osOfPerson != null) {
        	for (Ownership os : osOfPerson) {
        		this.oMapper.deleteOwnership(os);
        	}
        }
        
        this.deleteBusinessObject(this.boMapper.findBusinessObjectByID(p.getId()));
        
        this.pMapper.deletePerson(p);
    }
    
}