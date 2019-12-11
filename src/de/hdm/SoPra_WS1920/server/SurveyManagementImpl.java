package de.hdm.SoPra_WS1920.server;


import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
	 * 
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
	
    /**
     * Default constructor
     */
    public SurveyManagementImpl() throws IllegalArgumentException {
    	
    }
    
    /*
     * 
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
    }

    /**
     * @param int id 
     * @param String firstname 
     * @param String lastname 
     * @param String email 
     * @param Timestamp creationTimestamp
     * @return Person p
     */
    public Person createPerson(int id, String firstname, String lastname, String eMail, Timestamp creationTimestamp) throws IllegalArgumentException {
        Person p = new Person();
        p.setId(id);
        p.setFirstname(firstname);
        p.setLastname(lastname);
        p.setEMail(eMail);
        p.setCreationTimestamp(creationTimestamp);
        this.pMapper.insertPerson(p);
        return p;
    }
    
    /**
     * @param Person p 
     */
    public void editPerson(Person p) {
    	this.pMapper.updatePerson(p);
    }
    
    /**
     * @param Person p
     */
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
        
        Vector<Ownership> oOfPerson = this.oMapper.findOwnershipByPersonFK(p.getId());
        if (oOfPerson != null) {
        	for (Ownership o : oOfPerson) {
        		this.oMapper.deleteOwnership(o);
        	}
        }
        
        this.deletePerson(this.pMapper.findPersonByID(p.getId()));
        
        this.pMapper.deletePerson(p);
    }
    
    /**
     * @param int id 
     * @return
     */
    public Person getPersonById(int id) {
    	return this.pMapper.findPersonByID(id);
    }

    /**
     * @param String 
     * @return
     */
    public Vector<Person> getPersonByFirstname(String firstname) {
        return this.pMapper.findPersonByFirstname(firstname);
    }

    /**
     * @param String 
     * @return
     */
    public Vector<Person> getPersonByLastname(String lastname) {
        return this.pMapper.findPersonByLastname(lastname);
    }

    /**
     * @param String 
     * @return
     */
    public Person getPersonByEmail(String email) {
        return this.pMapper.findPersonByEmail(email);
    }

    /**
     * @param int id 
     * @param String name
     * @param int PersonFK
     * @param Timestamp creationTimestamp 
     * @return Group g
     */
    public Group createGroup(int id, String name, int PersonFK, Timestamp creationTimestamp) throws IllegalArgumentException {
        Group g = new Group();
        g.setId(id);
        g.setName(name);
        g.setPersonFK(PersonFK);
        g.setCreationTimestamp(creationTimestamp);
        this.gMapper.insertGroup(g);
        Ownership o = new Ownership();
        o.setId(id);
        o.setPersonFK(PersonFK);
        this.oMapper.insertOwnership(o);
        return g;
       
    }
    /**
     * @param Group g
     */
    public void editGroup(Group g) {
        this.gMapper.updateGroup(g);
    }
    
    /**
     * @param Group g
     * @param Person p
     */
    public void createMembership(Group g, Person p) {
    	Membership m = new Membership();
    	m.setGroup(g);
    	m.setPerson(p);
        this.meMapper.insertMembership(g, p);
    }
    
    /**
     * @param Group g
     * @param Person p
     */
    public void deleteMembership(Group g, Person p) {
    	
    	//this.deleteMembership(g, p);
    	
        this.meMapper.deleteMembership(g, p);
    }
    
    /**
     * @param group 
     * @return
     */
    public void deleteGroup(Group g) {
    	
    	Vector<Survey> sOfGroups = this.sMapper.findSurveyByGroupFK(g.getId());
        if (sOfGroups != null) {
        	for (Survey s : sOfGroups) {
        		this.sMapper.deleteSurvey(s);
        	}
        }
        
    	this.deleteGroup(this.gMapper.findGroupByID(g.getId()));
    	
    	this.gMapper.deleteGroup(g);
    }
    
    /**
     * @param id 
     * @return
     */
    public Group getGroupById(int id) {
    	return this.gMapper.findGroupByID(id);
    }
    
    /**
     * @param String 
     * @return
     */
    public Vector<Group> getGroupByName(String name) {
        return this.gMapper.findGroupByName(name);
    }
    
    /**
     * @param personFK 
     * @return
     */
    public Vector<Group> getGroupByPersonFK(int personFK) {
    	return this.gMapper.findGroupByPersonFK(personFK);
    }

    /**
     * @param int id 
     * @param int GroupFK
     * @param int PersonFK
     * @param Timestamp startDate
     * @param Timestamp endDate 
     * @param Timestamp creationTimestamp
     * @return Survey s
     */
    public Survey createSurvey(int id, int GroupFK, int PersonFK, Timestamp startDate, Timestamp endDate, Timestamp creationTimestamp) throws IllegalArgumentException {
        Survey s = new Survey();
        s.setId(id);
        s.setGroupFK(GroupFK);
        s.setPersonFK(PersonFK);
        s.setStartDate(startDate);
        s.setEndDate(endDate);
        s.setCreationTimestamp(creationTimestamp);
        this.sMapper.insertSurvey(s);
        return s;
    }
    
    /**
     * @param Survey s
     */
    public void editSurvey(Survey s) {
        this.sMapper.updateSurvey(s);
    }
    
    /**
     * @param survey 
     * @return
     */
    public void deleteSurvey(Survey s) {
        
        Vector<SurveyEntry> seOfSurvey = this.getSurveyEntryBySurveyFK(s.getId());
        if (seOfSurvey != null) {
        	for (SurveyEntry se : seOfSurvey) {
        		this.deleteSurveyEntry(se);
        	}
        }
        
        this.deleteSurvey(this.sMapper.findSurveyByID(s.getId()));
        
        this.sMapper.deleteSurvey(s);
    }
    
    /**
     * @param int 
     * @return
     */
    public Survey getSurveyById(int id) {
        return this.sMapper.findSurveyByID(id);
    }
    
    /**
     * @param Datetime 
     * @return
     */
    public Vector<Survey> getSurveyByStartDate(Timestamp startDate) {
        return this.sMapper.findSurveyByEndDate(startDate);
    }

    /**
     * @param Datetime 
     * @return
     */
    public Vector<Survey> getSurveyByEndDate(Timestamp endDate) {
        return this.sMapper.findSurveyByEndDate(endDate);
    }
    
    /**
     * @param personFK 
     * @return
     */
    public Vector<Survey> getSurveyByPersonFK(int personFK) {
    	return this.sMapper.findSurveyByPersonFK(personFK);
    }
    
    /**
     * @param groupFK 
     * @return
     */
    public Vector<Survey> getSurveyByGroupFK(int groupFK) {
    	return this.sMapper.findSurveyByGroupFK(groupFK);
    }
    
    /**
     * @param int id
     * @param int screeningFK
     * @param in surveyFK
     * @param Timestamp creationTimestamp
     * @return SurveyEntry se
     */
    public SurveyEntry createSurveyEntry(int id, int screeningFK, int surveyFK, Timestamp creationTimestamp) throws IllegalArgumentException {
        SurveyEntry se = new SurveyEntry();
        se.setId(id);
        se.setScreeningFK(screeningFK);
        se.setSurveyFK(surveyFK);
        se.setCreationTimestamp(creationTimestamp);
        this.seMapper.insertSurveyEntry(se);
        return se;
    }
    
    /**
     * @param SurveyEntry se
     */
    public void editSurvey(SurveyEntry se) {
        this.seMapper.updateSurveyEntry(se);
    }
    
    /**
     * @param surveyentry 
     * @return
     */
    public void deleteSurveyEntry(SurveyEntry se)  {
    	Vector<Vote> vOfSurveyEntry = this.vMapper.findVoteBySurveyEntryFK(se.getId());
        if (vOfSurveyEntry != null) {
        	for (Vote v : vOfSurveyEntry) {
        		this.vMapper.deleteVote(v);
        	}
        }
        
        this.deleteSurveyEntry(this.seMapper.findSurveyEntryByID(se.getId()));
        
        this.seMapper.deleteSurveyEntry(se);
    }
    
    /**
     * @param int 
     * @return
     */
    public SurveyEntry getSurveyEntryById(int id) {
        return this.seMapper.findSurveyEntryByID(id);
    }
    
    /**
     * @param surveyFK 
     * @return
     */
    public Vector<SurveyEntry> getSurveyEntryBySurveyFK(int surveyFK){
    	return this.seMapper.findSurveyEntryBySurveyFK(surveyFK);
    }

    /**
     * @param int id 
     * @param int votingWeight 
     * @param int SurveyEntryFK
     * @param int PersonFK
     * @param Timestamp creationTimestamp
     * @return Vote v
     */
    public Vote createVote(int id, int votingWeight, int SurveyEntryFK, int PersonFK, Timestamp creationTimestamp) throws IllegalArgumentException {
        Vote v = new Vote();
        v.setId(id);
        v.setVotingWeight(votingWeight);
        v.setSurveyEntryFK(SurveyEntryFK);
        v.setPersonFK(PersonFK);
        v.setCreationTimestamp(creationTimestamp);
        this.vMapper.insertVote(v);
        return v;
    }

    /**
     * @param Vote v
     */
    public void editVote(Vote v) {
        this.vMapper.updateVote(v);
    }

    /**
     * @param vote 
     * @return
     */
    public void deleteVote(Vote v) {
        
    	this.deleteVote(this.vMapper.findVoteByID(v.getId()));
    	
    	this.vMapper.deleteVote(v);
    }

    /**
     * @param int 
     * @return
     */
    public Vote getVoteById(int id) {
        return this.vMapper.findVoteByID(id);
    }

    /**
     * @param int 
     * @return
     */
    public Vector<Vote> getVoteByVotingWeight(int vw) {
        return this.vMapper.findVoteByVotingWeight(vw);
    }

    /**
     * @param personFK 
     * @return
     */
    public Vector<Vote> getVoteByPersonFK(int personFK) {
    	return this.vMapper.findVoteByPersonFK(personFK);
    }
    
    /**
     * @param surveyEntryFK 
     * @return
     */
    public Vector<Vote> getVoteBySurveyEntryFK(int surveyEntryFK){
    	return this.vMapper.findVoteBySurveyEntryFK(surveyEntryFK);
    }
    
    /**
     * @param surveyentry 
     * @return
     */
    public int countVotes(SurveyEntry se) {
        Vector<Vote> v = this.vMapper.findVoteBySurveyEntryFK(se.getId());
        return v.size();
    }
}