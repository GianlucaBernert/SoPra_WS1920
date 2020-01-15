package de.hdm.SoPra_WS1920.server;


import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Vector;

import com.google.gwt.dev.util.collect.HashSet;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.SoPra_WS1920.server.db.BusinessObjectMapper;
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
import de.hdm.SoPra_WS1920.shared.SurveyManagement;
import de.hdm.SoPra_WS1920.shared.bo.BusinessObject;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
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
 * @author GianlucaBernert
 */
public class SurveyManagementImpl extends RemoteServiceServlet implements SurveyManagement{

	/*
	 * Variablen der Klasse SurveyManagementImpl
	 */
	private static final long serialVersionUID = 1L;
	private CinemaMapper cMapper = null;
	private GroupMapper gMapper = null;
	private MovieMapper mMapper = null;
	private PersonMapper pMapper = null;
	private ScreeningMapper scMapper = null;
	private SurveyEntryMapper seMapper = null;
	private SurveyMapper sMapper = null;
	private VoteMapper vMapper = null;
	private OwnershipMapper oMapper = null;
	private MembershipMapper meMapper = null;
	private BusinessObjectMapper boMapper = null;
	private CinemaAdministrationImpl Admin = null;
	
    /**
     * Default constructor
     */
    public SurveyManagementImpl() throws IllegalArgumentException {
    	
    }
    
    /*
     * Initialisierungsmethode, die alle Mapper initialisiert
     * @throws IllegalArgumentException
     */
    public void init() throws IllegalArgumentException {
    	this.cMapper = CinemaMapper.cinemaMapper();
    	this.gMapper = GroupMapper.groupMapper();
    	this.mMapper = MovieMapper.moviemapper();
    	this.pMapper = PersonMapper.personMapper();
    	this.scMapper = ScreeningMapper.screeningMapper();
    	this.seMapper = SurveyEntryMapper.surveyEntryMapper();
    	this.sMapper = SurveyMapper.surveyMapper();
    	this.vMapper = VoteMapper.voteMapper();
    	this.oMapper = OwnershipMapper.ownershipMapper();
    	this.meMapper = MembershipMapper.membershipMapper();
    	this.boMapper = BusinessObjectMapper.businessObjectMapper();
    	this.Admin = new CinemaAdministrationImpl();
    }
    
    /**
     * Methode um ein BusinessObject zu erstellen
     * @throws IllegalArgumentException
     * @return BusinessObject bo
     */
    public BusinessObject createBusinessObject() throws IllegalArgumentException {
    	BusinessObject bo = new BusinessObject();
    	Timestamp time = new Timestamp(System.currentTimeMillis());
    	bo.setCreationTimestamp(time);
    	this.boMapper.insertBusinessObject(bo);
    	return bo;
    }
    
    /**
     * Methode um ein BusinessObject zu löschen
     * @param BusinessObject bo
     */
    public void deleteBusinessObject(BusinessObject bo) {
    	this.boMapper.deleteBusinessObject(bo);
    }
    
    /*
     * Methode um eine Ownership zu erstellen
     * @param int pFK
     * @throws IllegalArgumentException
     * @return Ownership os
     */
    public Ownership createOwnership(int pFK) throws IllegalArgumentException {
    	BusinessObject bo = this.createBusinessObject();
    	Ownership os = new Ownership();
    	os.setId(bo.getId());
    	os.setCreationTimestamp(bo.getCreationTimestamp());
    	os.setPersonFK(pFK);
    	this.oMapper.insertOwnership(os);
    	return os;
    }

    /*
     * Methode um eine Ownership zu löschen
     * @param Ownership os
     */
    public void deleteOwnership(Ownership os) {
    	this.deleteBusinessObject(this.boMapper.findBusinessObjectByID(os.getId()));
    	this.oMapper.deleteOwnership(os);
    }
    
    /**
     * Methode um eine Person zu erstellen
     * @param String firstName 
     * @param String lastName 
     * @param String email 
     * @throws IllegalArgumentException
     * @return Person p
     */
    public Person createPerson(String firstName, String lastName, String eMail) throws IllegalArgumentException {
    	BusinessObject bo = this.createBusinessObject();
        Person p = new Person();
        p.setId(bo.getId());
        p.setFirstname(firstName);
        p.setLastname(lastName);
        p.setEMail(eMail);
        p.setCreationTimestamp(bo.getCreationTimestamp());
        this.pMapper.insertPerson(p);
        return p;
    }
    
    /**
     * Methode um eine Person zu bearbeiten
     * @param Person p 
     */
    public void editPerson(Person p) {
    	this.pMapper.updatePerson(p);
    }
    
    /**
     * Methode um eine Person zu löschen
     * @param Person p
     */
    public void deletePerson(Person p) {
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
        
        Vector<Screening> scOfPerson = this.scMapper.findScreeningByPersonFK(p.getId());
        if (scOfPerson != null) {
        	for (Screening sc : scOfPerson) {
        		Admin.deleteScreening(sc);
        		System.out.println("Screening ok");
        	}
        }
        
        Vector<Cinema> cOfPerson = this.cMapper.findCinemaByPersonFK(p.getId());
        if (cOfPerson != null) {
        	for (Cinema c : cOfPerson) {
        		Admin.deleteCinema(c);
        		System.out.println("Cinema ok");
        	}
        }
        
        Vector<Movie> mOfPerson = this.mMapper.findMovieByPersonFK(p.getId());
        if (mOfPerson != null) {
        	for (Movie m : mOfPerson) {
        		Admin.deleteMovie(m);
        		System.out.println("Movie ok");
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
        
        this.deleteBusinessObject(bo);
        
        this.pMapper.deletePerson(p);
    }
    
    /**
     * Methode um eine Person anhand der ID zu finden
     * @param int id 
     * @return Person
     */
    public Person getPersonById(int id) {
    	return this.pMapper.findPersonByID(id);
    }

    /**
     * Methode um eine Person anhand des Vornamens zu finden
     * @param String firstName
     * @return Vector<Person>
     */
    public Vector<Person> getPersonByFirstname(String firstName) {
        return this.pMapper.findPersonByFirstname(firstName);
    }

    /**
     * Methode um eine Person anhand des Nachnamens zu finden
     * @param String lastName
     * @return Vector<Person>
     */
    public Vector<Person> getPersonByLastname(String lastName) {
        return this.pMapper.findPersonByLastname(lastName);
    }

    /**
     * Methode um eine Person anhand der E-Mail adresse zu finden
     * @param String eMail
     * @return Person
     */
    public Person getPersonByEmail(String eMail) {
        return this.pMapper.findPersonByEmail(eMail);
    }

    /**
     * Methode um eine Gruppe zu erstellen
     * @param String name
     * @param int pFK
     * @throws IllegalArgumentException
     * @return Group g
     */
    public Group createGroup(String name, int pFK) throws IllegalArgumentException {
    	Ownership os = this.createOwnership(pFK);
        Group g = new Group();
        g.setId(os.getId());
        g.setName(name);
        g.setCreationTimestamp(os.getCreationTimestamp());
        this.gMapper.insertGroup(g);
        return g;
       
    }
    /**
     * Methode um eine Gruppe zu bearbeiten
     * @param Group g
     */
    public void editGroup(Group g) {
        this.gMapper.updateGroup(g);
    }
    
    /**
     * Methode um eine Mitgliedschaft zu erstellen 
     * @param Group g
     * @param Person p
     * @throws IllegalArgumentException
     * @return Membership m
     */
    public Membership createMembership(Group g, Person p) throws IllegalArgumentException {
    	Membership m = new Membership();
    	m.setGroupFK(g.getId());
    	m.setPersonFK(p.getId());
        this.meMapper.insertMembership(m);
        return m;
     }
    
    /**
     * Methode um eine Mitgliedschaft zu löschen
     * @param Group g
     * @param Person p
     */
    public void deleteMembership(int gFK, int pFK) {    	
        this.meMapper.deleteMembership(gFK, pFK);
    }
    
    /**
     * Methode um eine Gruppe zu löschen
     * @param Group g 
     */
    public void deleteGroup(Group g) {
    	Ownership os = this.oMapper.findOwnershipByID(g.getId());
    	
    	Vector<Survey> sOfGroups = this.sMapper.findSurveyByGroupFK(g.getId());
        if (sOfGroups != null) {
        	for (Survey s : sOfGroups) {
        		this.deleteSurvey(s);
        	}
        }
        
        Vector<Membership> mOfGroup = this.meMapper.findMembershipByGroupFK(g.getId());
        if (mOfGroup != null) {
        	for (Membership me : mOfGroup) {
        		this.deleteMembership(me.getGroupFK(), me.getPersonFK());
        	}
        }
        
        this.deleteOwnership(os);    	
    	this.gMapper.deleteGroup(g);
    }
    
    /**
     * Methode um eine Gruppe anhand der ID zu finden
     * @param int id
     * @return Group
     */
    public Group getGroupById(int id) {
    	return this.gMapper.findGroupByID(id);
    }
    
    /**
     * Methode um eine Gruppe anhand des Namens zu finden
     * @param String name
     * @return Vector<Group>	
     */
    public Vector<Group> getGroupByName(String name) {
        return this.gMapper.findGroupByName(name);
    }
    
    /**
     * Methode um eine Gruppe anhand des PersonFK zu finden
     * @param int pFK
     * @return Vector<Group>
     */
    public Vector<Group> getGroupByPersonFK(int pFK) {
    	return this.gMapper.findGroupByPersonFK(pFK);
    }

    /** 
     * Methode um eine Umfrage zu erstellen
     * @param int gFK
     * @param int pFK
     * @param Timestamp startDate
     * @param Timestamp endDate 
     * @throws IllegalArgumentException
     * @return Survey s
     */
    public Survey createSurvey(int gFK, int pFK, Timestamp startDate, Timestamp endDate) throws IllegalArgumentException {
    	Ownership os = this.createOwnership(pFK);
        Survey s = new Survey();
        s.setId(os.getId());
        s.setGroupFK(gFK);
        s.setStartDate(startDate);
        s.setEndDate(endDate);
        s.setCreationTimestamp(os.getCreationTimestamp());
        this.sMapper.insertSurvey(s);
        return s;
    }
    
    /**
     * Methode um eine Umfrage zu bearbeiten
     * @param Survey s
     * @return Survey
     */
    public void editSurvey(Survey s) {
        this.sMapper.updateSurvey(s);
    }
    
    /**
     * Methode um eine Umfrage zu löschen
     * @param Survey s
     */
    public void deleteSurvey(Survey s) {
    	Ownership os = this.oMapper.findOwnershipByID(s.getId());
        
        Vector<SurveyEntry> seOfSurvey = this.getSurveyEntryBySurveyFK(s.getId());
        if (seOfSurvey != null) {
        	for (SurveyEntry se : seOfSurvey) {
        		this.deleteSurveyEntry(se);
        	}
        }
        
        this.deleteOwnership(os);
        
        this.sMapper.deleteSurvey(s);
    }
    
    /**
     * Methode um eine Umfrage anhand der ID zu finden
     * @param int id
     * @return Survey
     */
    public Survey getSurveyById(int id) {
        return this.sMapper.findSurveyByID(id);
    }
    
    /** 
     * Methode um eine Umfrage anhand des Start Datums zu finden
     * @param Timestamp startDate
     * @return Vector<Survey>
     */
    public Vector<Survey> getSurveyByStartDate(Timestamp startDate) {
        return this.sMapper.findSurveyByEndDate(startDate);
    }

    /**
     * Methode um eine Umfrage anhand des ENd Datums zu finden
     * @param Timestamp endDate
     * @return Vector<Survey>
     */
    public Vector<Survey> getSurveyByEndDate(Timestamp endDate) {
        return this.sMapper.findSurveyByEndDate(endDate);
    }
    
    /**
     * Methode um eine Umfrage anhand des PersonFKs zu finden
     * @param int pFK
     * @return Vector<Survey>
     */
    public Vector<Survey> getSurveyByPersonFK(int pFK) {
    	return this.sMapper.findSurveyByPersonFK(pFK);
    }
    
    /**
     * Methode um eine Umfrage anhand des GrouFKs zu finden
     * @param int gFK
     * @return Vector<Survey>
     */
    public Vector<Survey> getSurveyByGroupFK(int gFK) {
    	return this.sMapper.findSurveyByGroupFK(gFK);
    }
    
    /**
     * Methode um einen Umfrageeintrag zu erstellen
     * @param int scFK
     * @param int sFK
     * @param int pFK
     * @throws IllegalArgumentException
     * @return SurveyEntry se
     */
    public SurveyEntry createSurveyEntry(int scFK, int sFK, int pFK) throws IllegalArgumentException {
    	Ownership os = this.createOwnership(pFK);
        SurveyEntry se = new SurveyEntry();
        se.setId(os.getId());
        se.setScreeningFK(scFK);
        se.setSurveyFK(sFK);
        se.setCreationTimestamp(os.getCreationTimestamp());
        this.seMapper.insertSurveyEntry(se);
        return se;
    }
    
    /**
     * Methode um einen Umfrageeintrag zu bearbeiten
     * @param SurveyEntry se
     */
    public void editSurveyEntry(SurveyEntry se) {
        this.seMapper.updateSurveyEntry(se);
    }
    
    /**
     * MEthode um einen Umfrageeintrag zu löschen
     * @param SurveyEntry se
     */
    public void deleteSurveyEntry(SurveyEntry se)  {
    	Ownership os = this.oMapper.findOwnershipByID(se.getId());
    	
    	Vector<Vote> vOfSurveyEntry = this.vMapper.findVoteBySurveyEntryFK(se.getId());
        if (vOfSurveyEntry != null) {
        	for (Vote v : vOfSurveyEntry) {
        		this.deleteVote(v);
        	}
        }
        
        this.deleteOwnership(os);
        
        this.seMapper.deleteSurveyEntry(se);
    }
    
    /**
     * Methode um einen Umfrageeintrag anhand der ID zu finden
     * @param int id
     * @return SurveyEntry
     */
    public SurveyEntry getSurveyEntryById(int id) {
        return this.seMapper.findSurveyEntryByID(id);
    }
    
    /**
     * Methode um einen Umfrageeintrag anhand des SurveyFKs zu finden
     * @param int sFK 
     * @return Vector<SurveyEntry>
     */
    public Vector<SurveyEntry> getSurveyEntryBySurveyFK(int sFK){
    	return this.seMapper.findSurveyEntryBySurveyFK(sFK);
    }

    /**
     * Methode um ein Vote zu erstellen
     * @param int vw 
     * @param int seFK
     * @param int pFK
     * @throws IllegalArgumentException
     * @return Vote v
     */
    public Vote createVote(int vw, int seFK, int pFK) throws IllegalArgumentException {
    	Ownership os = this.createOwnership(pFK);
        Vote v = new Vote();
        v.setId(os.getId());
        v.setVotingWeight(vw);
        v.setSurveyEntryFK(seFK);
        v.setCreationTimestamp(os.getCreationTimestamp());
        this.vMapper.insertVote(v);
        return v;
    }

    /**
     * Methode um ein Vote zu bearbeiten
     * @param Vote v
     */
    public void editVote(Vote v) {
        this.vMapper.updateVote(v);
    }

    /**
     * Methode um ein Vote zu löschen
     * @param Vote v
     */
    public void deleteVote(Vote v) {
    	Ownership os = this.oMapper.findOwnershipByID(v.getId());
    	this.deleteOwnership(os);
    	this.vMapper.deleteVote(v);
    }

    /**
     * Methode um ein Vote anhand der ID zu finden
     * @param int id
     * @return Vote 
     */
    public Vote getVoteById(int id) {
        return this.vMapper.findVoteByID(id);
    }

    /**
     * Methode um ein Vote anhand seiner Gewichtung zu finden
     * @param int vw
     * @return Vector<Vote>
     */
    public Vector<Vote> getVoteByVotingWeight(int vw) {
        return this.vMapper.findVoteByVotingWeight(vw);
    }

    /**
     * Methode um ein Vote anhand des PersonFK zu finden
     * @param int pFK
     * @return Vector<Vote>
     */
    public Vector<Vote> getVoteByPersonFK(int pFK) {
    	return this.vMapper.findVoteByPersonFK(pFK);
    }
    
    /**
     * Methode um ein Vote anhand des SurveyEntryFKs zu finden
     * @param int seFK 
     * @return Vector<Vote>
     */
    public Vector<Vote> getVoteBySurveyEntryFK(int seFK){
    	return this.vMapper.findVoteBySurveyEntryFK(seFK);
    }
    
    /**
     * Methode um die Anzahl der Votes zu Zählen
     * @param SurveyEntry se 
     * @return int v.size();
     */
    public int countVotes(SurveyEntry se) {
        Vector<Vote> v = this.vMapper.findVoteBySurveyEntryFK(se.getId());
        return v.size();
    }
    
    /**
     * Methode um die Anzahl der Gruppenmitglieder zu Zählen
     * @param GroupFK gFK
     * @return int m.size();
     */
    public int countGroupMembers(int gFK) {
    	Vector <Membership> m = this.meMapper.findMembershipByGroupFK(gFK);
    	return m.size();
    }

    /**
     * Methode um den Film einer Umfrage zur�ckzugeben
     * @param int sFK
     * @return Movie m;
     */
    public Movie getMoviebySurveyFK(int sFK) {
    	Vector<SurveyEntry> se = this.getSurveyEntryBySurveyFK(sFK);
    	SurveyEntry see = se.get(1);
    	Screening sc = Admin.getScreeningById(see.getScreeningFK());
    	Movie m = Admin.getMovieById(sc.getMovieFK());
    	return m;
    }
    
    /**
     * Methode um alle Personen einer Umfrage zur�ckzugeben, die bereits abgestimmt haben
     * @param int sFK
     * @return ;
     */
//    public int countvotedPersons(int sFK) {
//    	Vector<SurveyEntry> se = this.getSurveyEntryBySurveyFK(sFK);
//    	for(SurveyEntry see: se) {
//    		Vector<Vote> v = this.getVoteBySurveyEntryFK(see.getId());
//    		
//    	}
//    }
    
    /*
     * Methode um eine Person zu aktualisieren
     * @param Person p
     * @return Person p
     */
    
	public Person updatePerson(Person p) {
		this.pMapper.updatePerson(p);
		return p;
	}

	
    /*
     * Methode um eine Gruppe zu aktualisieren
     * @param Group g
     * @return Grou g
     */
	public Group updateGroup(Group g) {
		this.gMapper.updateGroup(g);
		return g;
	}

	
	/*
	 * Methode um eine Umfrage zu aktualisieren
	 * @param Survey s
	 * @return Survey s
	 */
	public Survey updateSurvey(Survey s) {
		this.sMapper.updateSurvey(s);
		return s;
	}


	/*
	 * Methode um ein Vote zu aktualisieren
	 * @param Vote v
	 * @return Vote v
	 */
	public Vote updateVote(Vote v) {
		this.vMapper.updateVote(v);
		return v;
	}

	
	/*
	 * Methode um einen Umfrageeintrag zu aktualisieren
	 * @param SurveyEntry se
	 * @return SurveyEntry se
	 */
	public SurveyEntry updateSurveyEntry(SurveyEntry se) {
		this.seMapper.updateSurveyEntry(se);
		return se;
	}
	
	public Vector<Movie> getMoviesByName(String name) throws IllegalArgumentException {
		
		return this.mMapper.findMovieByName(name);
		
	}
	
	public Vector<Movie> getMoviesByGenre(String genre) throws IllegalArgumentException {
		
	
		return this.mMapper.findMovieByGenre(genre);
	}
	
	public Vector<Movie> searchMovie(String text){
		
		HashSet<Movie> hs = new HashSet<Movie>();
		Vector <Movie> movies = new Vector<Movie>();
		String s = text;
		hs.addAll(this.getMoviesByName(s));
		hs.addAll(this.getMoviesByGenre(s));
		
		Iterator<Movie> it = hs.iterator();
			while(it.hasNext()) {
				movies.add(it.next());
			}
			
			return movies;
	
	}
	
	public Vector<Group> searchGroup(String text){
		
		HashSet<Group> hs = new HashSet<Group>();
		Vector <Group> groups = new Vector<Group>();
		String s = text;
		hs.addAll(this.getGroupByName(s));
		
		Iterator<Group> it = hs.iterator();
			while(it.hasNext()) {
				groups.add(it.next());
			}
			
			return groups;
			
	}
	
	public Vector<Survey> searchSurvey(Timestamp time){
		
		HashSet<Survey> hs = new HashSet<Survey>();
		Vector<Survey> surveys = new Vector<Survey>();
		Timestamp t = time;
		hs.addAll(this.getSurveyByEndDate(t));
		
		Iterator<Survey> it = hs.iterator();
			while(it.hasNext()) {
				surveys.add(it.next());
			}
			
			return surveys;
		
	}
	
	@Override
	public Vector <Person> searchPerson(String text){
		
		HashSet<Person> hs = new HashSet<Person>();
		Vector<Person> persons = new Vector<Person>();
		String s = text;
		hs.add(this.getPersonByEmail(s));
		
		Iterator<Person> it = hs.iterator();
			while(it.hasNext()) {
				persons.add(it.next());
			}
			
			return persons;
	}


	
}