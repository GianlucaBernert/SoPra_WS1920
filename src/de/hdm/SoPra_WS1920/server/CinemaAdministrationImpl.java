package de.hdm.SoPra_WS1920.server;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.SoPra_WS1920.server.db.BusinessObjectMapper;
import de.hdm.SoPra_WS1920.server.db.CinemaChainMapper;
import de.hdm.SoPra_WS1920.server.db.CinemaMapper;
import de.hdm.SoPra_WS1920.server.db.GroupMapper;
import de.hdm.SoPra_WS1920.server.db.MembershipMapper;
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
import de.hdm.SoPra_WS1920.shared.bo.Membership;
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
    public CinemaAdministrationImpl() throws IllegalArgumentException {
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
    
    private MembershipMapper mmMapper = null;
    
    
    
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
    	this.mmMapper = MembershipMapper.membershipMapper();
    	
    }
    
    
    
    /**
     * Methode zum Erstellen eines person Objects.
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @return
     */
    @Override
    public Person createPerson(String firstName, String lastName, String eMail) throws IllegalArgumentException {
        
    	BusinessObject bo = this.createBusinessObject();
    	
    	Person p = new Person();
    	
    	p.setEMail(eMail);
    	p.setFirstname(firstName);
    	p.setLastname(lastName);
    	p.setId(bo.getId());
    	
        return this.pMapper.insertPerson(p);
    }
    

    /**
     * Methode zur Suche eines Person Objects nach dessen id.
     * @param id 
     * @return
     */
    @Override
    public Person getPersonById(int id) throws IllegalArgumentException {
        
        return this.pMapper.findPersonByID(id);
    }
    

    /**
     * Methode zur Suche eines Person Objects nach dessen Vornamen.
     * @param firstName 
     * @return
     */
    @Override
    public Vector<Person> getPersonByFirstName(String firstName) throws IllegalArgumentException {
        
        return this.pMapper.findPersonByFirstname(firstName);
    }
    
    
    /**
     * Methode zur Suche eines Person Objects nach dessen Nachnamen.
     * @param lastName 
     * @return
     */
    @Override
    public Vector<Person> getPersonByLastName(String lastName) throws IllegalArgumentException {
        
        return this.pMapper.findPersonByLastname(lastName);
    }

    /**
     * Methode zur Suche eines Person Objects nach dessen eMail.
     * @param eMail 
     * @return
     */
    @Override
    public Person getPersonByEMail(String eMail) throws IllegalArgumentException {
        
        return this.pMapper.findPersonByEmail(eMail);
    }



    /**
     * Methode zur  Aktualisierung eines Person Objects.
     * @param person 
     * @return
     */
    @Override
    public Person updatePerson(Person person) throws IllegalArgumentException {
        
        return this.pMapper.updatePerson(person);
    }
    
    
    /**
     * Methode zum L�schen von Personen Objekten und allen zugeh�rigen Businessobjekten
     * @param p
     */
    
    @Override
    public void deletePerson(Person p) throws IllegalArgumentException{
    	Vector<Vote> vOfPerson = this.vMapper.findVoteByPersonFK(p.getId());
        if (vOfPerson != null) {
        	for (Vote v : vOfPerson) {
        		this.deleteVote(v);
        		System.out.println("Vote ok");
        	}
        }
        
        Vector<Survey> sOfPerson = this.sMapper.findSurveyByPersonFK(p.getId());
        if (sOfPerson != null) {
        	for (Survey s : sOfPerson) {
        		this.deleteSurvey(s);
        		System.out.println("Survey ok");
        	}
        }
        
        Vector<Group> gOfPerson = this.gMapper.findGroupByPersonFK(p.getId());
        if (gOfPerson != null) {
        	for (Group g : gOfPerson) {
        		this.deleteGroup(g);
        		System.out.println("Group ok");
        	}
        }
        
        Vector<Membership> mOfGroup = this.mmMapper.findMembershipByPersonFK(p.getId());
        if (mOfGroup != null) {
        	for (Membership me : mOfGroup) {
        		this.deleteMembership(me.getGroupFK(), me.getPersonFK());
        	}
        }
        
        Vector<Screening> scOfPerson = this.scMapper.findScreeningByPersonFK(p.getId());
        if (scOfPerson != null) {
        	for (Screening sc : scOfPerson) {
        		this.deleteScreening(sc);
        		System.out.println("Screening ok");
        	}
        }
        
        Vector<Cinema> cOfPerson = this.cMapper.findCinemaByPersonFK(p.getId());
        if (cOfPerson != null) {
        	for (Cinema c : cOfPerson) {
        		this.deleteCinema(c);
        		System.out.println("Cinema ok");
        	}
        }
        
        Vector<Movie> mOfPerson = this.mMapper.findMovieByPersonFK(p.getId());
        if (mOfPerson != null) {
        	for (Movie m : mOfPerson) {
        		this.deleteMovie(m);
        		System.out.println("Movie ok");
        	}
        }
        
        Vector<CinemaChain> ccOfPerson = this.ccMapper.findCinemaChainByPersonFK(p.getId());
        if (ccOfPerson != null) {
        	for (CinemaChain cc : ccOfPerson) {
        		this.ccMapper.deleteCinemaChain(cc);
        		System.out.println("CinemaChain ok");
        	}
        }
        
        Vector<Ownership> osOfPerson = this.oMapper.findOwnershipByPersonFK(p.getId());
        if (osOfPerson != null) {
        	for (Ownership os : osOfPerson) {
        		this.deleteOwnership(os);
        		System.out.println("Ownership ok");
        	}
        }
        
        BusinessObject bo = this.boMapper.findBusinessObjectByID(p.getId());
        this.pMapper.deletePerson(p);
        this.deleteBusinessObject(bo);
        
       
    }
    
    
    
    /**
     * Methode zum Erstellen von Kino Objekten.
     * @param name 
     * @param cityName 
     * @param street 
     * @param postCode 
     * @return
     */
    @Override
    public Cinema createCinema(String name, String cityName, String street, String streetNr, String zipCode, int ccFK, int personFK) 
    		throws IllegalArgumentException{ 
    	
    		Ownership o = this.createOwnership(personFK);
    	
        	Cinema c = new Cinema();
        	
        	c.setName(name);
        	c.setCity(cityName);
        	c.setStreet(street);
        	c.setStreetNo(streetNr);
        	c.setZipCode(zipCode);
        	c.setId(o.getId());
        	c.setCinemaChainFK(ccFK);
        		
        	
        	this.cMapper.insertCinema(c);
        	
        	return c;
        	
        
    }
    

    /**
     * Methode zur Aktualisierung eines Kino Objekts.
     * @param cinema 
     * @return
     */
    @Override
    public Cinema updateCinema(Cinema cinema) throws IllegalArgumentException{
        
        return this.cMapper.updateCinema(cinema);
    }

    

    /**
     * Methode zum L�schen eines KinoObjects und zugeh�rigen BusinessObjects.
     * @param cinema 
     * @return
     */
    @Override
    public void deleteCinema(Cinema cinema) throws IllegalArgumentException {
    	
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
        this.deleteOwnership(this.getOwnership(cinema.getId()));
    }


    /**
     * Methode zum Aufrufen eines Kino Objekts anhand dessen Id
     * @param id 
     * @return
     */
    @Override
    public Cinema getCinemaById(int id) throws IllegalArgumentException{
        
    	
        return this.cMapper.findCinemaByID(id);
    }

    /**
     * Methode zum Aufrufen eines Kino Objekts anhand dessen Namen.
     * @param name 
     * @return
     */
    @Override
    public Vector<Cinema> getCinemaByName(String name) {
        
        return this.cMapper.findCinemaByName(name);
    }

    /**
     * Methode zum Aufrufen von Kino Objekten anhand deren Stadt.
     * @param cityName 
     * @return
     */
    @Override
    public Vector<Cinema> getCinemaByCity(String cityName) throws IllegalArgumentException {
        
        return this.cMapper.findCinemaByCity(cityName);
    }
    
    
    /**
     * Methode zum Aufrufen aller Kinos einer Person.
     * @param personFK
     * @return
     */
    @Override
    public Vector <Cinema> getCinemasByPersonFK(int personFK) throws IllegalArgumentException{
    	return this.cMapper.findCinemaByPersonFK(personFK);
    }
    
    
    /**
     * Methode zum Aufrufen aller zugeh�rigen Cinema Objekte einer CinemaChain
     * @param cc
     * @return
     */
    @Override
    public Vector <Cinema> getCinemasByCinemaChainFK(CinemaChain cc) throws IllegalArgumentException{
    	return this.cMapper.findCinemaByCinemaChainFK(cc.getId());
    }

    public Cinema getCinemaByScreeningFK(int screeningFK) {
    	Screening s = this.getScreeningById(screeningFK);
    	return this.getCinemaById(s.getCinemaFK());
    }
    
    
    /**
     * Methode zur Erstellung eines Movie Objects
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    @Override
    public Movie createMovie(String name, String genre, String description, int personFK) throws IllegalArgumentException {
			
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
     * Methode zum L�schen eines Kino Objects.
     * @param movie 
     * @return
     */
    @Override
    public void deleteMovie(Movie movie) throws IllegalArgumentException {
        
    	Vector<Screening> screenings = this.getScreeningByMovieFK(movie.getId());
    	
    	Vector<Survey> surveys = this.sMapper.findMovieNameOfSurvey(movie.getName());
    	
    	if(screenings != null) {
    		for(Screening s : screenings) {
    			this.deleteScreening(s);
    		}
    	}
    	if(movie != null) {
    		for(Survey s : surveys) {
    			this.deleteSurvey(s);
    		}
    	}
    	
       this.mMapper.deleteMovie(movie); 
       this.deleteOwnership(this.getOwnership(movie.getId()));
    }
    
    
    /**
     * Methode zum Aufrufen aller MOvie Objects
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Vector <Movie> getAllMovies() throws IllegalArgumentException{
    	
    	return this.mMapper.findAll();
    }
    
    
    /**
     * Methode zum Aufrufen eines Movies nach dessen Id
     * @param id 
     * @return
     */
    @Override
    public Movie getMovieById(int id) throws IllegalArgumentException {
        
        return this.mMapper.findMovieByID(id);
    }

    /**
     * Methode zum Aufrufen von MOvies nach deren Namen.
     * @param name 
     * @return
     */
    @Override
    public Vector<Movie> getMoviesByName(String name) throws IllegalArgumentException{
       
        return this.mMapper.findMovieByName(name);
    }

    /**
     * Methode zum Aufrufen von Movies nach Genre.
     * @param genre 
     * @return
     */
    @Override
    public Vector<Movie> getMovieByGenre(String genre) throws IllegalArgumentException {
        
        return this.mMapper.findMovieByGenre(genre);
    }
    

    /**
     * Methode zur Aktualisierung eines Movie Objects.
     * @param movie 
     * @return
     */
    @Override
    public Movie updateMovie(Movie movie) throws IllegalArgumentException{
        
        return this.mMapper.updateMovie(movie);
    }
    
    
    
    

    /**
     * Methode zur Erstellung eines Screenig Objects.
     * @param screeningDateTime 
     * @param cinemaFK 
     * @param movieFK 
     * @return
     */
    @Override

    public Screening createScreening(Date screeningDate, Time screeningTime, int cinemaFK, int movieFK, int personFK)
    		throws IllegalArgumentException{

        
    	
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
     * Methode zum L�schen eines Screening Objects und dazugeh�rigen Business Objects.
     * @param screening 
     * @return
     */
    @Override
    public void deleteScreening(Screening screening) throws IllegalArgumentException {
        
    	Vector<SurveyEntry> ses = this.getSurveyEntryByScreeningFK(screening.getId());
    	
    	if (ses != null) {
    		for (SurveyEntry se : ses) {
    			this.deleteSurveyEntry(se);
    		}
    	}
    	
        this.scMapper.deleteScreening(screening);
        this.deleteOwnership(this.getOwnership(screening.getId()));
    }

  
    /**
     * Methode zum Aufrufen eines Screening Objects anhand dessen Id.
     * @param id 
     * @return
     */
    @Override
    public Screening getScreeningById(int id) throws IllegalArgumentException {
        
        return this.scMapper.findScreeningByID(id);
    }

    /**
     * Methode zum Aufrufen von Screening Objects die f�r die Erstellung von Umfragen ben�tigt werden.
     * @param cinemaFK 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningsforSurveyCreation(Movie movie, String city, Date startDate, Date endDate) throws IllegalArgumentException{
    	
    	return this.scMapper.findScreeningForSurveyCreation(startDate, endDate, movie.getId(), city);
    }
    
    
    /**
     * Methode zum Aufrufen von Screening Objects anhand des cinemaFK.
     * @param cinemaFK 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByCinemaFK(int cinemaFK) throws IllegalArgumentException {
        
        return this.scMapper.findScreeningByCinemaFK(cinemaFK);
    }

    /**
     * Methode zum Aufrufen von Screening Objects anhand des movieFK.
     * @param movieFK 
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByMovieFK(int movieFK) throws IllegalArgumentException {
        
        return this.scMapper.findScreeningByMovieFK(movieFK);
    }

    /**
     * Methode zum Aufrufen von Screening Objects anhand des Datums.
     * @param screeningDate
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByScreeningDate(Date screeningDate) throws IllegalArgumentException {
        
        return this.scMapper.findScreeningByScreeningDate(screeningDate);
    }
    
    /**
     * Methode zum Aufrufen von Screening Objects anhand der Zeit und dem Datum der Vorstellung.
     * @param screeningDate
     * @param screeningTime
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByScreeningDateTime(Date screeningDate, Time screeningTime) throws IllegalArgumentException{
    	
    	return this.scMapper.findScreeningByScreeningDateTime(screeningDate, screeningTime);
    }
    
    /**
     * Methode zum Aufrufen von Screening Objects anhand der Uhrzeit der Vorstellung.
     * @param screeningTime
     * @return
     */
    @Override
    public Vector<Screening> getScreeningByScreeningTime(Time screeningTime) throws IllegalArgumentException{
    	
    	return this.scMapper.findScreeningByScreeningTime(screeningTime);
    }

    /**
     * Methode zur Aktualisierung eines Schreening Objects.
     * @param screening
     * @return
     */
    @Override
    public Screening updateScreening(Screening screening) throws IllegalArgumentException {
		
    	return this.scMapper.updateScreening(screening);
     
    }
    
    /**
     * Methode zum Aufrufen aller Screening Objects einer Person
     */
    @Override
    public Vector <Screening> getScreeningsByPersonFK(int personFK){
    	
    	return this.scMapper.findScreeningByPersonFK(personFK);
    }
    
    /**
     * Methode zur Erstellung eines CinemaChain Objects.
     */
    
    @Override
    public CinemaChain createCinemaChain(String name, int personFK) throws IllegalArgumentException {
    	
    	
    	Ownership o = this.createOwnership(personFK);
    	
    	CinemaChain cc = new CinemaChain();
    	
    	cc.setId(o.getId());
    	cc.setName(name);
    	
    	cc = this.ccMapper.insertCinemaChain(cc);

    	return cc;
    	
    }
    
    /**
     * Methode updateCinemaId hierzu muss bei einem Vector von ausgew�hlten Kino Objekten die CinemaChain ID geupdatet werden.
     */
    
    @Override
    public CinemaChain updateCinemaChain(CinemaChain cc) throws IllegalArgumentException{
    	return this.ccMapper.updateCinemaChain(cc);
    }
    
    
    /**
     * Methode zum L�schen einer CinemaChain und zur�cksetzen der ccId in den betroffenen Cinema Objects
     */
    @Override
    public void deleteCinemaChain(CinemaChain cc) throws IllegalArgumentException {
    	Vector <Cinema> vc = this.getCinemasByCinemaChainFK(cc);
    	if(vc != null) {
    		for (Cinema c : vc) {
    			this.deleteCinema(c);
    		}
    	}
    	this.ccMapper.deleteCinemaChain(cc);
    	this.deleteOwnership(this.getOwnership(cc.getId()));

    }
    
    
    /**
     * Methode zum Suchen eines CinemaChain Objects nach dessen Namen.
     */
    @Override
    public Vector<CinemaChain> getCinemaChainByName(String name) throws IllegalArgumentException {
    	return this.ccMapper.findCinemaChainByName(name);
    }
    
    
    /**
     * Methode zum Aufrufen von CinemaChain Objects anhand eines PersonFK.
     */
    @Override
    public Vector <CinemaChain> getCinemaChainByPersonFK(int personFK) throws IllegalArgumentException{
    	return this.ccMapper.findCinemaChainByPersonFK(personFK);
    }
    
   
    /**
    * Methode zum Aufrufen eines CinemaChain Objects nach dessen Id. 
    */
    @Override
    public CinemaChain getCinemaChainById(int id) throws IllegalArgumentException {
    return this.ccMapper.findCinemaChainByID(id);
    }
   
   

    /**
    * Methode zum aufrufen von Vote Objects anhand eines Umfrageeintrags (SurveyEntry)
    */
    @Override
    public Vector<Vote> getVotesBySurveyEntryFK(int surveyEntryFK) throws IllegalArgumentException{
   	
   	return this.vMapper.findVoteBySurveyEntryFK(surveyEntryFK);
    }
       

    /**
     * Methode zum L�schen eines Vote Objects
     * @param vote 
     * @return
     */
    @Override
    public void deleteVote(Vote vote) throws IllegalArgumentException{
        
    	this.vMapper.deleteVote(vote);
    	this.deleteOwnership(this.getOwnership(vote.getId()));
        
    }

    /**
     * Methode zum L�schen eines SurveyEntry Objects,
     * @param surveyEntry 
     * @return
     */
    @Override
    public void deleteSurveyEntry(SurveyEntry surveyEntry) throws IllegalArgumentException{
        
    	Vector<Vote> votes = this.getVotesBySurveyEntryFK(surveyEntry.getId());
    	
    	if(votes != null) {
    		for(Vote v : votes) {
    			this.deleteVote(v);
    		}
    	}
    	
    	this.seMapper.deleteSurveyEntry(surveyEntry);
    	this.deleteBusinessObject(this.boMapper.findBusinessObjectByID(surveyEntry.getId()));
        
    }
   
    
    /**
     * Methode zum Aufrufen eines SurveyEntrys anhand des ScreeningFK
     */
    @Override
    public Vector<SurveyEntry> getSurveyEntryByScreeningFK(int screeningFK) throws IllegalArgumentException{
    	
    	return this.seMapper.findSurveyEntryByScreeningFK(screeningFK);
    }
    
    
    /**
     * Methode zur Erstellung eines Ownership Objects
     * @param personFK
     * @return
     */
    @Override
    public Ownership createOwnership(int personFK) throws IllegalArgumentException{
    	
    	BusinessObject bo = this.createBusinessObject();
    	

    	Ownership o = new Ownership();
    	o.setId(bo.getId());
    	o.setPersonFK(personFK);
    	
    	return this.oMapper.insertOwnership(o);
    }
    
    
    /**
     * Methode zum L�schen eines Ownership Objects.
     * @param ownership
     */
    @Override
    public void deleteOwnership(Ownership ownership) throws IllegalArgumentException{
    	this.oMapper.deleteOwnership(ownership);
    	this.deleteBusinessObject(this.boMapper.findBusinessObjectByID(ownership.getId()));
    }
    
    
    /**
     * Methode zum Aufrufen eines Ownership Objects anhand dessen ID.
     */
    @Override
    public Ownership getOwnership(int id) throws IllegalArgumentException {
    	return this.oMapper.findOwnershipByID(id);
    }
    
    
    /**
     * Methode zum Aufrufen von OwnershipObjects anhand deren personFK
     */
    @Override
    public Vector <Ownership> getOwnershipsbyPersonFK(int personFK) throws IllegalArgumentException{
    	return this.oMapper.findOwnershipByPersonFK(personFK);
    }
    
    
    /**
     * Methode zur Erstellung eines BusinessObjects.
     */
    @Override
    public BusinessObject createBusinessObject() throws IllegalArgumentException{
    	BusinessObject bo = new BusinessObject();
    	Timestamp time = new Timestamp(System.currentTimeMillis());
    	bo.setCreationTimestamp(time);
    	this.boMapper.insertBusinessObject(bo);
    	return bo;
    }
    
    
    /**
     * Methode zum L�schen von BusinessObjects.
     */
    @Override
    public void deleteBusinessObject(BusinessObject bo) throws IllegalArgumentException {
    	this.boMapper.deleteBusinessObject(bo);
    }


    /**
     * Methode zum Suchen nach MovieObjekten
     */
	@Override
	public Vector<Movie> searchMovie(String text) {
		// TODO Auto-generated method stub
		HashSet<Movie> hs = new HashSet<Movie>();
		Vector<Movie> movies = new Vector<Movie>();
		String s = text;
		hs.addAll(this.getMoviesByName(s));
		hs.addAll(this.getMovieByGenre(s));
		
		Iterator<Movie> it = hs.iterator();
	     while(it.hasNext()){
	        movies.add(it.next());
	     }
		
		return movies;

	}
	
	/**
	 * Methode zum Suchen nach KinoObjekten
	 */
	public Vector<Cinema> searchCinema(int personFk, String text) {
		// TODO Auto-generated method stub
		HashSet<Cinema> hs = new HashSet<Cinema>();
		Vector<Cinema> cinemas = new Vector<Cinema>();
		Vector<CinemaChain> cinemaChainsOfPerson = this.getCinemaChainByPersonFK(personFk);
		
		for(CinemaChain cH: cinemaChainsOfPerson) {
			if(cH.getName().equals(text)) {
				hs.addAll(this.getCinemasByCinemaChainFK(cH));
			}
		}
		for(Cinema c:this.getCinemasByPersonFK(personFk)) {
			if(c.getName().equals(text)){
				hs.add(c);
			}else if(c.getCity().equals(text)) {
				hs.add(c);
			}
		}
		
		Iterator<Cinema> it = hs.iterator();
	     while(it.hasNext()){
	        cinemas.add(it.next());
	     }
		
		return cinemas;
	}


	/**
	 * methode zum Suchen nach Kinoketten
	 */
	@Override
	public Vector<CinemaChain> searchCinemaChain(int personFk,String text) {
		// TODO Auto-generated method stub
		HashSet<CinemaChain> hs = new HashSet<CinemaChain>();
		Vector<CinemaChain> cinemaChains = new Vector<CinemaChain>();
		String s = text;
		hs.addAll(this.getCinemaChainByName(s));
		
		Iterator<CinemaChain> it = hs.iterator();
	     while(it.hasNext()){
	        cinemaChains.add(it.next());
	     }
		
		return cinemaChains;
	}


	/**
	 * Methode zum Suchen nach Screeningobjekten
	 */
	@Override
	public Vector<Screening> searchScreening(int personFk, String text) {
		// TODO Auto-generated method stub
		HashSet<Screening> hs = new HashSet<Screening>();
		Vector<Screening> screenings = new Vector<Screening>();
		Vector<Cinema> cinemas = new Vector<Cinema>();
		Vector<Movie> movies = new Vector<Movie>();
		
		for(Screening s:this.getScreeningsByPersonFK(personFk)) {
			cinemas.add(this.getCinemaById(s.getCinemaFK()));
			movies.add(this.getMovieById(s.getMovieFK()));
		}
		
		for(Cinema c: cinemas) {
			if(c.getName().equals(text)) {
				hs.addAll(this.getScreeningByCinemaFK(c.getId()));
			}
		}
		
		for(Movie m: movies) {
			if(m.getName().equals(text)) {
				hs.addAll(this.getScreeningByMovieFK(m.getId()));
			}
		}
		
		Iterator<Screening> it = hs.iterator();
	     while(it.hasNext()){
	        screenings.add(it.next());
	     }
		
		return screenings;
	}
	
	/**
	 * Methode zum l�schen von Membershipobjekten
	 */
	@Override
	  public void deleteMembership(int gFK, int pFK) {    	
	        this.mmMapper.deleteMembership(gFK, pFK);
	    }
	
	 /**
     * Methode um eine Umfrage zu löschen
     * @param Survey s
     */
	@Override
    public void deleteSurvey(Survey s) {
    	Ownership os = this.oMapper.findOwnershipByID(s.getId());
        
        Vector<SurveyEntry> seOfSurvey = this.getSurveyEntryBySurveyFK(s.getId());
        if (seOfSurvey != null) {
        	for (SurveyEntry se : seOfSurvey) {
        		this.deleteSurveyEntry(se);
        	}
        }
        this.sMapper.deleteSurvey(s);
        
        this.deleteOwnership(os);
        
    }
	
	 /**
     * Methode um einen Umfrageeintrag anhand des SurveyFKs zu finden
     * @param int sFK 
     * @return Vector<SurveyEntry>
     */
	@Override
    public Vector<SurveyEntry> getSurveyEntryBySurveyFK(int sFK){
    	return this.seMapper.findSurveyEntryBySurveyFK(sFK);
    }
	
	/**
     * Methode um eine Gruppe zu löschen
     * @param Group g 
     */
	@Override
    public void deleteGroup(Group g) {
    	Ownership os = this.oMapper.findOwnershipByID(g.getId());
    	
    	Vector<Survey> sOfGroups = this.sMapper.findSurveyByGroupFK(g.getId());
        if (sOfGroups != null) {
        	for (Survey s : sOfGroups) {
        		this.deleteSurvey(s);
        	}
        }
        
        Vector<Membership> mOfGroup = this.mmMapper.findMembershipByGroupFK(g.getId());
        if (mOfGroup != null) {
        	for (Membership me : mOfGroup) {
        		this.deleteMembership(me.getGroupFK(), me.getPersonFK());
        	}
        }
        	
    	this.gMapper.deleteGroup(g);
    	this.deleteOwnership(os);    
    }
        
}