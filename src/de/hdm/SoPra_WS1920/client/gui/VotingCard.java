package de.hdm.SoPra_WS1920.client.gui;

import java.awt.Checkbox;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.CancelClickHandler;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.CreateSurveyClickHandler;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.DeleteSurveyClickHandler;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.GetGroupOfSurveyCallback;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.GetMovieByNameCallback;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.GetScreeningsCallback;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.GetSurveyEntriesCallback;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.ScreeningRow;
import de.hdm.SoPra_WS1920.client.gui.SurveyCardEdit.UpdateSurveyClickHandler;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

/*
 * Formular, um für eine Umfrage abzustimmen.
 * 
 * @author SebastianHerrmann
 */
public class VotingCard extends DialogBox {
	
	Survey surveyToShow;
	
	FlowPanel formWrapper;
	Label cardDescription;
	Image cancelIcon;	
	Label selectedMovie;
	Label selectedGroup;
	Label selectedCity;
	Label selectedPeriod;
	Label screeningLabel;
	FlowPanel surveyEntrySelection;
	Button saveVoting;
	
	Vector<SurveyEntryRow> surveyEntryVector;
	Vector<SurveyEntry> surveyEntries;
	Person person;
	
	SurveyContent surveyContent;
	SurveyCard parentCard;
	SurveyManagementAsync surveyManagement;
	
	/*
	 * Konstruktor der Klasse VotingCard
	 * @param parentCard, surveyToShow
	 */
	public VotingCard(SurveyCard parentCard, Survey surveyToShow) {
		this.surveyToShow = surveyToShow;
		this.parentCard = parentCard;
	}
	
	public void onLoad() {
		super.onLoad();
	
		this.clear();
		this.setStyleName("EditCard");
		
		person = new Person();
		person.setEMail(Cookies.getCookie("gmail"));
		person.setFirstname(Cookies.getCookie("firstName"));
		person.setLastname(Cookies.getCookie("lastName"));
		person.setId(Integer.parseInt(Cookies.getCookie("userId")));
		
		surveyManagement = ClientsideSettings.getSurveyManagement();
		
//		surveyEntries = new Vector<SurveyEntry>();
		formWrapper = new FlowPanel();
		formWrapper.setStyleName("DialogBoxWrapper");
		cardDescription = new Label("Vote");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		selectedMovie = new Label("Movie: "+ surveyToShow.getMovieName());
		selectedMovie.setStyleName("TextBoxLabel");
		selectedGroup = new Label ();
		selectedGroup.setStyleName("TextBoxLabel");
		selectedCity = new Label ("City: "+ surveyToShow.getSelectedCity());
		selectedCity.setStyleName("TextBoxLabel");
		selectedPeriod = new Label ("Screening Period: "+ surveyToShow.getStartDate().toString()+" - "+surveyToShow.getEndDate().toString());
		selectedPeriod.setStyleName("TextBoxLabel");
		surveyEntrySelection = new FlowPanel();
		surveyEntrySelection.setStyleName("SurveyEntrySelection");

		screeningLabel = new Label("Screenings:");
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(selectedMovie);
		formWrapper.add(selectedGroup);
		formWrapper.add(selectedCity);
		formWrapper.add(selectedPeriod);
		formWrapper.add(surveyEntrySelection);
		
		saveVoting = new Button("Save");
		saveVoting.setStyleName("SaveButton");
		saveVoting.addClickHandler(new SaveSurveyClickHandler(this));
		
		surveyManagement.getSurveyEntryBySurveyFK(surveyToShow.getId(), new GetSurveyEntriesCallback());					
		surveyManagement.getGroupById(surveyToShow.getGroupFK(), new GetGroupOfSurveyCallback());

//		saveVoting.addClickHandler(new UpdateSurveyClickHandler(this));
		if(surveyToShow.getStatus()==1) {	
			formWrapper.add(saveVoting);
		}else {
			cardDescription.setText("Results");
		}
		
		this.add(formWrapper);
	}
	
	/*
	 * ClickHandler, der die Survey nachdem alle Votes getätigt sind, abspeichert.
	 */
	class SaveSurveyClickHandler implements ClickHandler{
		VotingCard votingCard;
		public SaveSurveyClickHandler(VotingCard votingCard) {
			// TODO Auto-generated constructor stub
			this.votingCard=votingCard;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			for(SurveyEntryRow surveyEntryRow: surveyEntryVector) {
				
				if(surveyEntryRow.voteOfPerson.getVotingWeight()==0 && surveyEntryRow.newVoteOfPerson.getVotingWeight()!=0) {
					surveyManagement.createVote(surveyEntryRow.newVoteOfPerson.getVotingWeight(), surveyEntryRow.surveyEntry.getId(), person.getId(), new CreateVoteCallback());
				}else if(surveyEntryRow.voteOfPerson.getVotingWeight()!=0 && surveyEntryRow.newVoteOfPerson.getVotingWeight()!=0) {
					if(surveyEntryRow.newVoteOfPerson.getVotingWeight()!=surveyEntryRow.voteOfPerson.getVotingWeight()) {
						surveyManagement.updateVote(surveyEntryRow.newVoteOfPerson, new UpdateVoteCallback());
					}
				}else if(surveyEntryRow.voteOfPerson.getVotingWeight()!=0 && surveyEntryRow.newVoteOfPerson.getVotingWeight()==0) {
					surveyManagement.deleteVote(surveyEntryRow.voteOfPerson, new DeleteVoteCallback());
				}
			}
			votingCard.hide();
			parentCard.showSurveyCardView(surveyToShow);
			parentCard.surveyContent.showSurveys();
			
		}
		
	}
	
	
	/*
	 * Callback, der die Methode zum Erstellen eines Votes aufruft.
	 */
	class CreateVoteCallback implements AsyncCallback<Vote>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vote result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*
	 * Callback, der die Methode zum Bearbeiten eines Votes aufruft.
	 */
	class UpdateVoteCallback implements AsyncCallback<Vote>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vote result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*
	 * Callback, der die Methode zum Löschen eines Votes aufruft.
	 */
	class DeleteVoteCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*
	 * ClickHandler zum Schließen der VotingCard.
	 */
	class CancelClickHandler implements ClickHandler{
		VotingCard votingCard;
		public CancelClickHandler(VotingCard votingCard) {
			// TODO Auto-generated constructor stub
			this.votingCard=votingCard;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			votingCard.hide();
		}
		
	}
	
	/*
	 * Callback, der die Gruppe einer Survey angibt.
	 */
	class GetGroupOfSurveyCallback implements AsyncCallback<Group>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Group result) {
			// TODO Auto-generated method stub
			selectedGroup.setText("Group: "+result.getName());
		}
		
	}
	
	/*
	 * Zeile, die als Umfrageeintrag zum Abstimmen angezeigt wird.
	 */
	class SurveyEntryRow extends FlowPanel{
		
		SurveyEntry surveyEntry;
		Cinema cinemaOfSurveyEntry;
		Screening screeningOfSurveyEntry;
		
		Label surveyEntryDescription;
		Button upVoteButton;
		Button downVoteButton;
		
		Vector<Vote> allVotes;	
		Vector<Vote> upVotes;
		
		int upVoteCounter;
		Vector<Vote> downVotes;
		int downVoteCounter;
		
		Vote voteOfPerson;
		Vote newVoteOfPerson;
		
		public SurveyEntryRow(SurveyEntry surveyEntry) {
			this.surveyEntry=surveyEntry;
		}
		
		//Three Voting-Weights: 1: Positive Vote, 0: Neutral Vote, -1: Negative Vote
		
		public void onLoad() {
			super.onLoad();
			this.setStyleName("SurveyEntryRow");
			voteOfPerson = new Vote();
			voteOfPerson.setVotingWeight(0);
			newVoteOfPerson = new Vote();
			newVoteOfPerson.setVotingWeight(0);
			
			surveyManagement.getCinemaByScreeningFK(surveyEntry.getScreeningFK(), new GetCinemaOfSurveyEntryCallback());
			surveyManagement.getVoteBySurveyEntryFK(surveyEntry.getId(), new GetVoteBySurveyEntryCallback());
			surveyEntryDescription = new Label();
			surveyEntryDescription.setStyleName("SurveyRowLabel");
			
			upVoteButton = new Button();
			upVoteButton.setStyleName("UpVoteButton");
			downVoteButton = new Button();
			downVoteButton.setStyleName("DownVoteButton");
			if(surveyToShow.getStatus()==1) {
				upVoteButton.addClickHandler(new UpVoteClickHandler());
				downVoteButton.addClickHandler(new DownVoteClickHandler());	
			}
		
			this.add(surveyEntryDescription);
			this.add(downVoteButton);
			this.add(upVoteButton);
			
						
		}
		
		/*
		 * ClickHandler, der für einen Umfrageeintrag abstimmen lässt.
		 */
		class UpVoteClickHandler implements ClickHandler{
			
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(newVoteOfPerson.getVotingWeight()==1) {
					newVoteOfPerson.setVotingWeight(0);
					upVoteCounter--;
					upVoteButton.setText(Integer.toString(upVoteCounter)+"+");
					
					upVoteButton.setStyleName("UpVoteButton");
				
				}else if(newVoteOfPerson.getVotingWeight()==-1) {
					newVoteOfPerson.setVotingWeight(1);
					
					upVoteCounter++;
					downVoteCounter--;
					downVoteButton.setText(Integer.toString(downVoteCounter)+"-");			
					upVoteButton.setText(Integer.toString(upVoteCounter)+"+");
					
					downVoteButton.setStyleName("DownVoteButton");
					upVoteButton.setStyleName("UpVoteButton VoteSelection");
				
				}else {
					newVoteOfPerson.setPersonFK(person.getId());
					newVoteOfPerson.setSurveyEntryFK(surveyEntry.getId());
					newVoteOfPerson.setVotingWeight(1);
					upVoteCounter++;
					upVoteButton.setText(Integer.toString(upVoteCounter)+"+");
					
					upVoteButton.setStyleName("UpVoteButton VoteSelection");
				
				}

			}
			
		}
		
		
		/*
		 * ClickHandler, der gegen einen Umfrageeintrag abstimmen lässt.
		 */
		class DownVoteClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(newVoteOfPerson.getVotingWeight()==1) {
					newVoteOfPerson.setVotingWeight(-1);
					upVoteCounter--;
					downVoteCounter++;
					upVoteButton.setText(Integer.toString(upVoteCounter)+"+");	
					downVoteButton.setText(Integer.toString(downVoteCounter)+"-");
					
					downVoteButton.setStyleName("DownVoteButton VoteSelection");
					upVoteButton.setStyleName("UpVoteButton");
					

				}else if(newVoteOfPerson.getVotingWeight()==-1) {
					newVoteOfPerson.setVotingWeight(0);
					downVoteCounter--;
					downVoteButton.setText(Integer.toString(downVoteCounter)+"-");
					downVoteButton.setStyleName("DownVoteButton");
					
				}else {
					newVoteOfPerson.setPersonFK(person.getId());
					newVoteOfPerson.setSurveyEntryFK(surveyEntry.getId());
					newVoteOfPerson.setVotingWeight(-1);
					downVoteCounter++;
					downVoteButton.setText(Integer.toString(downVoteCounter)+"-");
					downVoteButton.setStyleName("DownVoteButton VoteSelection");
				
				}
			}
			
		}
		
		/*
		 * Callback, der das Kino zu einem Umfrageeintrag ausgibt.
		 */
		class GetCinemaOfSurveyEntryCallback implements AsyncCallback<Cinema>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Cinema result) {
				// TODO Auto-generated method stub
				cinemaOfSurveyEntry = result;
				surveyManagement.getScreeningById(surveyEntry.getScreeningFK(), new GetScreeningOfSurveyEntryCallback());
			}
			
		}
		/*
		 * Callback, zum Anzeigen einer Spielzeit zu einem Umfrageeintrag.
		 */
		class GetScreeningOfSurveyEntryCallback implements AsyncCallback<Screening>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Screening result) {
				// TODO Auto-generated method stub
				screeningOfSurveyEntry = result;
				surveyEntryDescription.setText(cinemaOfSurveyEntry.getName() + ", "+ screeningOfSurveyEntry.getScreeningDate().toString() + ", "+ screeningOfSurveyEntry.getScreeningTime());
			}
			
		}
		
		/*
		 * Callback, um die Stimmen zu Umfrageeinträgen ausgeben zu lassen.
		 */
		class GetVoteBySurveyEntryCallback implements AsyncCallback<Vector<Vote>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Vote> result) {
				// TODO Auto-generated method stub
				Vector<Vote> allVotes = result;
				upVotes = new Vector<Vote>();
				downVotes = new Vector<Vote>();
				for(Vote v: allVotes) {
					if(v.getPersonFK()==person.getId()) {
						voteOfPerson.setId(v.getId());
						voteOfPerson.setVotingWeight(v.getVotingWeight());
						voteOfPerson.setPersonFK(v.getPersonFK());
						voteOfPerson.setSurveyEntryFK(v.getSurveyEntryFK());
						newVoteOfPerson.setId(v.getId());
						newVoteOfPerson.setVotingWeight(v.getVotingWeight());
						newVoteOfPerson.setPersonFK(v.getPersonFK());
						newVoteOfPerson.setSurveyEntryFK(v.getSurveyEntryFK());
						
						if(v.getVotingWeight()==1) {
							upVoteButton.setStyleName("UpVoteButton VoteSelection");
						}else if(v.getVotingWeight()==-1) {
							downVoteButton.setStyleName("DownVoteButton VoteSelection");
						}
					}
				}

				for(Vote v: result) {
					if(v.getVotingWeight()==1) {
						upVotes.add(v);
					}else{
						downVotes.add(v);
					}
				}
				upVoteCounter = upVotes.size();
				downVoteCounter = downVotes.size();
				upVoteButton.setText(Integer.toString(upVoteCounter)+"+");
				downVoteButton.setText(Integer.toString(downVoteCounter)+"-");
			}
			
		}
		
	}
	
	/*
	 * Callback zum Zurückgeben von Umfrageeinträgen.
	 */
	class GetSurveyEntriesCallback implements AsyncCallback<Vector<SurveyEntry>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<SurveyEntry> result) {
			// TODO Auto-generated method stub
			surveyEntryVector = new Vector<SurveyEntryRow>();
			for(SurveyEntry sE: result) {
				SurveyEntryRow surveyEntryRow = new SurveyEntryRow(sE);
				surveyEntrySelection.add(surveyEntryRow);
				//Survey Entry Selection Vector
				surveyEntryVector.add(surveyEntryRow);
				
			}
		} 
		
	}
}