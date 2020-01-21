package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;
import java.sql.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

public class SurveyCardEdit extends DialogBox {
	
	FlowPanel formWrapper;
	SurveyCard parentCard;
	Survey surveyToShow;
	
	//CreateSurvey 1/2
	
	Label cardDescription1;
	Label movieLabel;
	Label groupLabel;
	Label cityLabel;
	Label startDateLabel;
	Label endDateLabel;
	
	MultiWordSuggestOracle allMovies;
	SuggestBox movieSuggestBox;
	
	ListBox allGroups;
	
	TextBox cityTextBox;
	
	DateBox startDateBox;
	DateBox endDateBox;
	
	Button invisibleButton;
	Button addScreenings; 
	Image cancelIcon;
	
	Group group;
	Movie movie;
	Person person;
	String city;
	java.sql.Date startDate;
	java.sql.Date endDate;
	
	
	//CreateSurvey 2/2 
	
	Label cardDescription2;
	Label selectedMovie;
	Label selectedGroup;
	Label selectedCity;
	Label selectedPeriod;
	
	
	Label cinemaFilter;
	ListBox availableCinemas;
	
	Label dateFilter;
	DateBox filterDateBox;
	
	FlowPanel screeningSelection;
	CheckBox screeningToSelect;
	
	Vector <ScreeningRow> screeningRowVector;
	Vector <Screening> sv;
	Vector <Screening> screeningVector;
	Vector <SurveyEntry> surveyEntryVector;
	
	Label showSelected;
	Label deleteSurveyLabel;
	Button saveSurvey;
	Survey survey;
	
	Cinema cinema;
	
	SurveyManagementHeader header;
	SurveyContent content;
	SurveyManagementAsync surveyManagement;
	CinemaAdministrationAsync cinemaAdministration;
	
	
	
	public SurveyCardEdit(SurveyCard surveyCard, Survey survey) {
		this.parentCard = surveyCard;
		this.surveyToShow = survey;
	}
	
	
	public SurveyCardEdit(SurveyContent content, SurveyManagementHeader header) {
		this.content = content;
		this.header = header;

		
	}
	
	public void onLoad() {
		super.onLoad();	
		this.setStyleName("EditCard");
		person = new Person();
		person.setId(1);
		surveyManagement = ClientsideSettings.getSurveyManagement();
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();	
		
	}
	
	class ScreeningRow extends FlowPanel{
		
		CheckBox cb;
		Screening s;
		public ScreeningRow(Screening s) {
			this.s = s;
		}

		public void onLoad() {
			super.onLoad();
			cb = new CheckBox();
			cinemaAdministration.getCinemaById(s.getCinemaFK(), new GetCinemaCallback(this));
			
			this.add(cb);		
			
		}
		
	}
	
	
	//Clickhandler muss CreateScreening2/2 aufrufen und eingetragene Werte �bernehmen
	class AddScreeningsClickHandler implements ClickHandler{
		
		SurveyCardEdit surveyCardEdit;
		public AddScreeningsClickHandler(SurveyCardEdit surveyCardEdit) {
			this.surveyCardEdit = surveyCardEdit;
		}
		@Override
		
		public void onClick(ClickEvent event) {
		
			cinemaAdministration.getMoviesByName(movieSuggestBox.getText(), new GetMovieCallback());

		}
	}
	
	class AllMoviesCallback implements AsyncCallback<Vector<Movie>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Movie> result) {
			// TODO Auto-generated method stub
			for(Movie m: result) {
				allMovies.add(m.getName());
			}
		}
	}
	
	class GetGroupsByFKCallback implements AsyncCallback<Vector<Group>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Group> result) {
			// TODO Auto-generated method stub
			for(Group g: result) {
				allGroups.addItem(g.getName());
			}
					
		}
		
	}
	
	class GetMovieCallback implements AsyncCallback<Vector<Movie>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Fehler beim Laden des Movies");
		}

		@Override
		public void onSuccess(Vector<Movie> result) {
			// TODO Auto-generated method stub
			movie = result.firstElement();

		}
		
	}
	
	class GetGroupCallback implements AsyncCallback<Vector<Group>>{
		SurveyCardEdit surveyCardEdit;
		public GetGroupCallback(SurveyCardEdit surveyCardEdit) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit=surveyCardEdit;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Group> result) {
			// TODO Auto-generated method stub
			group = result.firstElement();
			city = cityTextBox.getText();
			
			startDate = new java.sql.Date(startDateBox.getValue().getTime());
			endDate = new java.sql.Date(endDateBox.getValue().getTime());
			
			if(cardDescription1.getText()=="1/2 Create Survey") {
				surveyCardEdit.clear();
				surveyCardEdit.showEditSurveyEntriesCard(movie, group, city, startDate, endDate);
			}
		}
		
		
	}

	class CancelClickHandler implements ClickHandler{
		SurveyCardEdit surveyCardEdit;
		public CancelClickHandler(SurveyCardEdit surveyCardEdit) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit = surveyCardEdit;
		}
		

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(parentCard==null) {
				surveyCardEdit.hide();
			}else {
				parentCard.showSurveyCardView(surveyToShow);
				surveyCardEdit.hide();
			}
		}
	}
	
	public void showCreateCard() {
		this.clear();	
		formWrapper = new FlowPanel();
		
		cardDescription1 = new Label("Create Survey 1/2");
		cardDescription1.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		movieLabel = new Label("Movie");
		movieLabel.setStyleName("TextBoxLabel");
		allMovies = new MultiWordSuggestOracle();
		cinemaAdministration.getAllMovies(new AllMoviesCallback());
		movieSuggestBox = new SuggestBox(allMovies);
		movieSuggestBox.setStyleName("CardSuggestBox");
		
		groupLabel =  new Label("Group");
		groupLabel.setStyleName("TextBoxLabel");
		allGroups = new ListBox();
		surveyManagement.getGroupByPersonFK(person.getId(), new GetGroupsByFKCallback());
		allGroups.setStyleName("CardSuggestBox");
		
		cityLabel = new Label("City");
		cityLabel.setStyleName("TextBoxLabel");
		cityTextBox = new TextBox();
		cityTextBox.setStyleName("CardSuggestBox");
		
		startDateLabel = new Label("Start Date");
		startDateLabel.setStyleName("TextBoxLabel");
		startDateBox = new DateBox();
		startDateBox.setStyleName("CardDatePicker");
		startDateBox.setFormat(
				new DateBox.DefaultFormat(
						DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
		endDateLabel = new Label("End Date");
		endDateLabel.setStyleName("TextBoxLabel");
		endDateBox = new DateBox();
		endDateBox.setStyleName("CardDatePicker");
		endDateBox.setFormat(
				new DateBox.DefaultFormat(
						DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));

		formWrapper.add(cardDescription1);
		formWrapper.add(cancelIcon);
		formWrapper.add(movieLabel);
		formWrapper.add(movieSuggestBox);
		formWrapper.add(groupLabel);
		formWrapper.add(allGroups);
		formWrapper.add(cityLabel);
		formWrapper.add(cityTextBox);
		formWrapper.add(startDateLabel);
		formWrapper.add(startDateBox);
		formWrapper.add(endDateLabel);
		formWrapper.add(endDateBox);
		

		addScreenings = new Button("Add Screenings");
		addScreenings.setStyleName("SaveButton");
		addScreenings.addClickHandler(new AddScreeningsClickHandler(this));

		formWrapper.add(addScreenings);
		this.add(formWrapper);
	}

	public void showEditCardInfo() {
		this.clear();
		this.setStyleName("EditCard");
		
		surveyManagement.getMovieBySurveyFK(surveyToShow.getId(), new GetMovieForHeaderCallback());
		surveyManagement.getGroupById(surveyToShow.getGroupFK(), new GetGroupForHeaderCallback());
		
		
		
		formWrapper = new FlowPanel();
		
		formWrapper.setStyleName("Placeholder of second");
		cardDescription2 = new Label("Create Survey 2/2");
		cardDescription2.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		//TO DO BEf�llung der Label mit INhalt der vorherigen Auswahl
		
		selectedMovie = new Label("Movie:" + movie.getName());
		selectedMovie.setStyleName("TextBoxLabel");
		selectedGroup = new Label ("Group:" + group.getName());
		selectedGroup.setStyleName("TextBoxLabel");
		selectedCity = new Label ("City:" + city);
		selectedCity.setStyleName("TextBoxLabel");
		selectedPeriod = new Label ("selected Period: "+startDate.toString() + " - " + endDate.toString());// + startDate.toString() + " - " + endDate.toString());
		selectedPeriod.setStyleName("TextBoxLabel");
		
		screeningSelection = new FlowPanel();
		screeningSelection.setStyleName("Screening Selection");
		
		screeningRowVector = new Vector <ScreeningRow>();
		
		showSelected = new Label("Show All Screenings");
		
		formWrapper.add(cardDescription2);
		formWrapper.add(cancelIcon);
		formWrapper.add(selectedMovie);
		formWrapper.add(selectedGroup);
		formWrapper.add(selectedCity);
		formWrapper.add(selectedPeriod);
		formWrapper.add(screeningSelection);
		
		saveSurvey = new Button("Create Survey");
		saveSurvey.setStyleName("SaveButton");
		saveSurvey.addClickHandler(new CreateSurveyClickHandler(this));
		if(parentCard!=null) {
			saveSurvey.setText("Save");
			deleteSurveyLabel = new Label("Delete Survey");
			deleteSurveyLabel.addClickHandler(new DeleteSurveyClickHandler(this));
			formWrapper.add(deleteSurveyLabel);
		}
		formWrapper.add(saveSurvey);
		this.add(formWrapper);
	}
	
	public void showEditSurveyEntriesCard(Movie movie, Group group, String city, java.sql.Date startDate, java.sql.Date endDate) {
		cinemaAdministration.getScreeningsforSurveyCreation(movie, city, startDate, endDate, new GetScreeningsCallback(formWrapper));
	}	
	
	public void showVoteForm() {
		this.setStyleName("EditCard");
		surveyManagement.getSurveyEntryBySurveyFK(surveyToShow.getId(), new GetSurveyEntriesCallback());
		
		formWrapper = new FlowPanel();
		formWrapper.setStyleName("Placeholder of second");
		cardDescription2 = new Label("Vote");
		cardDescription2.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		//TO DO BEf�llung der Label mit INhalt der vorherigen Auswahl
		
		selectedMovie = new Label("Movie:" + movie.getName());
		selectedMovie.setStyleName("TextBoxLabel");
		selectedGroup = new Label ("Group:" + group.getName());
		selectedGroup.setStyleName("TextBoxLabel");
		selectedCity = new Label ("City:" + city);
		selectedCity.setStyleName("TextBoxLabel");
		selectedPeriod = new Label ("selected Period: "+startDate.toString() + " - " + endDate.toString());// + startDate.toString() + " - " + endDate.toString());
		selectedPeriod.setStyleName("TextBoxLabel");
		
		screeningSelection = new FlowPanel();
		screeningSelection.setStyleName("Screening Selection");
		
		screeningRowVector = new Vector <ScreeningRow>();
		
		showSelected = new Label("Screenings");
		
		formWrapper.add(cardDescription2);
		formWrapper.add(cancelIcon);
		formWrapper.add(selectedMovie);
		formWrapper.add(selectedGroup);
		formWrapper.add(selectedCity);
		formWrapper.add(selectedPeriod);
		formWrapper.add(screeningSelection);
		
		saveSurvey = new Button("Create Survey");
		saveSurvey.setStyleName("SaveButton");
		saveSurvey.addClickHandler(new VoteSurveyClickHandler(this));

		formWrapper.add(saveSurvey);
		this.add(formWrapper);
		
	}
	
	class VoteSurveyClickHandler implements ClickHandler{
		
		SurveyCardEdit surveyCardEdit;
		
		public VoteSurveyClickHandler(SurveyCardEdit surveyCardEdit) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit = surveyCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			surveyCardEdit.hide();
		}
		
	}
	
	
	class CreateSurveyClickHandler implements ClickHandler{
		SurveyCardEdit surveyCardEdit;
		
		public CreateSurveyClickHandler(SurveyCardEdit surveyCardEdit) {
			
			this.surveyCardEdit = surveyCardEdit;
		}
		@Override
		
		public void onClick(ClickEvent event) {
			
			screeningVector = new Vector <Screening>();
			Window.alert(Integer.toString(screeningRowVector.size()));
			for(ScreeningRow sr: screeningRowVector) {
				if(sr.cb.getValue() == true) {
					screeningVector.add(sr.s);
				}
			}
			
			surveyManagement.createSurvey(group.getId(), person.getId(), new CreateSurveyCallback(surveyCardEdit));

		}	
	}
	
	class DeleteSurveyClickHandler implements ClickHandler{
		SurveyCardEdit surveyCardEdit;
		public DeleteSurveyClickHandler(SurveyCardEdit surveyCardEdit) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit = surveyCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			surveyManagement.deleteSurvey(surveyToShow, new DeleteSurveyCallback(surveyCardEdit));
		}
		
	}
	
	class GetMovieForHeaderCallback implements AsyncCallback<Movie>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Movie result) {
			// TODO Auto-generated method stub
			movie = result;
			cinemaAdministration.getScreeningsforSurveyCreation(movie, "Rottweil", surveyToShow.getStartDate(), surveyToShow.getEndDate(), new AddScreeningsCallback());
		}
		
	}
	
	class AddScreeningsCallback implements AsyncCallback<Vector<Screening>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Screening> result) {
			// TODO Auto-generated method stub
			sv = result;
		}
		
	}
	
	class GetGroupForHeaderCallback implements AsyncCallback<Group>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Group result) {
			// TODO Auto-generated method stub
			group = result;
		}
		
	}
	
	class GetSurveyEntriesCallback implements AsyncCallback<Vector<SurveyEntry>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<SurveyEntry> result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class DeleteSurveyCallback implements AsyncCallback<Void>{
		SurveyCardEdit surveyCardEdit;
		public DeleteSurveyCallback(SurveyCardEdit surveyCardEdit) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit = surveyCardEdit;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			surveyCardEdit.hide();
			parentCard.remove();
		}
		
	}
	
	class GetScreeningsCallback implements AsyncCallback<Vector<Screening>>{

		public GetScreeningsCallback(FlowPanel formWrapper) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Screening> result) {
			// TODO Auto-generated method stub
			sv = result;
			//if vote is clicked: VoteRow else ScreeningRow
			
	
		}
	}

	class CreateSurveyCallback implements AsyncCallback<Survey>{
		
		SurveyCardEdit surveyCardEdit;
		public CreateSurveyCallback(SurveyCardEdit surveyCardEdit) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit=surveyCardEdit;
		}	
		@Override
		public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
			Window.alert("Callback: Survey could not be created.");
		
		}

		@Override
		public void onSuccess(Survey result) {
		
			survey = result;	
			
			for(Screening selectedScreening : screeningVector) {
				Window.alert(selectedScreening.getScreeningDate().toString());
				surveyManagement.createSurveyEntry(selectedScreening.getId(), survey.getId(), person.getId(), new CreateSurveyEntryCallback());
			}
			
			parentCard = new SurveyCard(content, result);
//			parentCard.showSurveyCardView(result);
			content.add(parentCard);
			surveyCardEdit.hide();
		
		}
	}
	
	class GetCinemaCallback implements AsyncCallback<Cinema>{
		ScreeningRow screeningRow;
		public GetCinemaCallback(ScreeningRow screeningRow) {
			// TODO Auto-generated constructor stub
			this.screeningRow = screeningRow;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Cinema result) {
			// TODO Auto-generated method stub
			cinema = result;
			screeningRow.cb.setText(cinema.getName() + screeningRow.s.getScreeningDate().toString() + screeningRow.s.getScreeningTime());		
		}
	}
	
	class CreateSurveyEntryCallback implements AsyncCallback<SurveyEntry>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void onSuccess(SurveyEntry result) {

		}
		
	}
	
	class VoteRow extends FlowPanel{
		SurveyEntry surveyEntry;
		Vector<Vote> positiveVotes;
		Vector<Vote> negativeVotes;
		
		Vote voteOfPerson;
		
		Label cinemaName;
		Label dateTime;
		
		int positiveVoteAmount;
		int negativeVoteAmount;
		
		Label positiveCounter;
		Label negativeCounter;
		
		public VoteRow(SurveyEntry surveyEntry) {
			this.surveyEntry = surveyEntry;
		}
		
		public void onLoad() {
			super.onLoad();
			surveyManagement.getScreeningById(surveyEntry.getScreeningFK(), new GetScreeningCallback());
			surveyManagement.getVoteBySurveyEntryFK(surveyEntry.getId(), new GetVotedCallback());
			
			dateTime = new Label();
			cinemaName = new Label();
			
			positiveCounter = new Label();
			positiveCounter.addClickHandler(new PositiveVoteClickHandler());
			
			negativeCounter = new Label();
			negativeCounter.addClickHandler(new NegativeVoteClickHandler());
			
			this.add(cinemaName);
			this.add(dateTime);
			this.add(positiveCounter);
			this.add(negativeCounter);
			
			
		}
		class GetScreeningCallback implements AsyncCallback<Screening>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Screening result) {
				
				dateTime.setText(result.getScreeningDate().toString()+ " "+ result.getScreeningTime());
				cinemaAdministration.getCinemaById(result.getCinemaFK(), new GetCinemaCallback());
			}
			
		}
		
		class GetCinemaCallback implements AsyncCallback<Cinema>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Cinema result) {
				// TODO Auto-generated method stub
				
				cinemaName.setText(result.getName());
				
			}
			
		}
		
		class GetVotedCallback implements AsyncCallback<Vector<Vote>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Vote> result) {
				// TODO Auto-generated method stub
				for(Vote v:result) {
					if(v.getpersonFK()==1) {
						voteOfPerson = v;
					}
					if(v.getVotingWeight()==1) {
						positiveVotes.add(v);
					}else {
						negativeVotes.add(v);
					}
					
				}
				positiveCounter.setText(Integer.toString(positiveVotes.size()));
				negativeCounter.setText(Integer.toString(negativeVotes.size()));
			
			}
		
		}
			
		class PositiveVoteClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//Case 1: Allready positively voted: delete vote;
				//Case 2: Allready negatively voted: update voteWeight to one, pop from negativeVote Vector
				//Case 3: Not yet voted: add positive vote
				
				if(voteOfPerson.getVotingWeight()==1) {
					surveyManagement.deleteVote(voteOfPerson, new DeleteVoteCallback());

				}else if(voteOfPerson.getVotingWeight()==-1) {
					voteOfPerson.setVotingWeight(1);
					surveyManagement.updateVote(voteOfPerson, new UpdateVoteCallback());
				}else {
					surveyManagement.createVote(1, surveyEntry.getId(), 1, new CreateVoteCallback());
				}
				positiveCounter.setText(Integer.toString(positiveVotes.size()));
				negativeCounter.setText(Integer.toString(negativeVotes.size()));
					
			}
		}
			
		
		class NegativeVoteClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//Case 1: Allready negatively voted: delete vote; 
				//Case 2: Allready positively voted: update voteWeight to zero, pop from positive Vote Vector
				//Case 3: Not yet voted: add negative vote
				
				if(voteOfPerson.getVotingWeight()==1) {
					voteOfPerson.setVotingWeight(-1);
					
					surveyManagement.updateVote(voteOfPerson, new UpdateVoteCallback());

				}else if(voteOfPerson.getVotingWeight()==-1) {
					surveyManagement.deleteVote(voteOfPerson, new DeleteVoteCallback());
					
				}else {
					surveyManagement.createVote(1, surveyEntry.getId(), 1, new CreateVoteCallback());

				}
				positiveCounter.setText(Integer.toString(positiveVotes.size()));
				negativeCounter.setText(Integer.toString(negativeVotes.size()));
				
			}
			
		}
		
		class UpdateVoteCallback implements AsyncCallback<Vote>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vote result) {
				// TODO Auto-generated method stub
				if(voteOfPerson.getVotingWeight()==1) {
					negativeVotes.remove(voteOfPerson);
					positiveVotes.add(result);
				}else if(voteOfPerson.getVotingWeight()==-1) {
					positiveVotes.remove(voteOfPerson);
					negativeVotes.add(result);			
				}
				voteOfPerson=result;
				positiveCounter.setText(Integer.toString(positiveVotes.size()));
				negativeCounter.setText(Integer.toString(negativeVotes.size()));
			}
			
		}
		
		class DeleteVoteCallback implements AsyncCallback<Void>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				if(voteOfPerson.getVotingWeight()==1) {
					positiveVotes.remove(voteOfPerson);
					voteOfPerson = null;
					
				}else if(voteOfPerson.getVotingWeight()==-1) {
					negativeVotes.remove(voteOfPerson);
					voteOfPerson = null;
								
				}
				positiveCounter.setText(Integer.toString(positiveVotes.size()));
				negativeCounter.setText(Integer.toString(negativeVotes.size()));
			}

			
		}
		
		class CreateVoteCallback implements AsyncCallback<Vote>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vote result) {
				// TODO Auto-generated method stub
				voteOfPerson=result;
				positiveVotes.add(result);
				
			}

			
		}
		
	}


}