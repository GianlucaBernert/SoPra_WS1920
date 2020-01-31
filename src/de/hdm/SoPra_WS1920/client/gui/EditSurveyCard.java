package de.hdm.SoPra_WS1920.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

import de.hdm.SoPra_WS1920.client.ClientsideSettings;

import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

public class EditSurveyCard extends DialogBox {
	
	FlowPanel formWrapper;
	SurveyCard parentCard;
	Survey surveyToShow;
	Cinema cinemaInScreening;
	Movie currentMovie;
	Group currentGroup;
//	Date currentStartDate;
//	Date currentEndDate;
	SurveyManagementAsync sma; 
	
	
	
	//EditSurvey 1/2
	
	Label cardDescription1;
	Label movieLabel;
	Label groupLabel;
	Label cityLabel;
//	Label startDateLabel;
//	Label endDateLabel;
	
	MultiWordSuggestOracle allMovies;
	SuggestBox movieSuggestBox;
	
	ListBox allGroups;
	TextBox movieListBox;
	
	MultiWordSuggestOracle allCities;
	SuggestBox citySuggestBox;
	
//	DateBox startDateBox;
//	DateBox endDateBox;
	
	Button invisibleButton;
	Button editScreenings; 
	Image cancelIcon;
	
	
	//EditSurvey 2/2 
	
	Label cardDescription2;
	Label selectedMovie;
	Label selectedGroup;
	Label selectedCity;
	Label selectedPeriod;
	
	
	Label cinemaFilter;
	ListBox availableCinemas;
	
	Label dateFilter;
	ListBox filterDateBox;
	
	FlowPanel screeningSelection;
	CheckBox screeningToSelect;
	
	Vector<ScreeningRow> screeningRowVector;
	Vector<Screening> screeningVector;
	Vector<Screening> screeningInSurvey;
	Vector<Cinema> selectedCinemas;
	Vector<Date> screeningDates;
	Vector<Screening> currentScreenings;
	
	Label showSelected;
	Button saveSurvey;
	Button stopSurvey;
	
	
	SurveyContent content;
	
	
	
	
	public EditSurveyCard(SurveyCard surveyCard, Survey survey) {
		this.parentCard = surveyCard;
		this.surveyToShow = survey;
		
		
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		
		sma = ClientsideSettings.getSurveyManagement();
		sma.getMovieBySurveyFK(surveyToShow.getId(), new GetMovieCallback());
		sma.getGroupById(surveyToShow.getGroupFK(), new GetGroupCallback());
		
		this.setStyleName("EditCard");
		formWrapper = new FlowPanel();
		
		cardDescription1 = new Label("Edit Survey 1/2");
		cardDescription1.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		movieLabel = new Label("Movie");
		movieLabel.setStyleName("TextBoxLabel");
		movieListBox = new TextBox();
		movieListBox.setStyleName("CardTextBox");
		movieListBox.getElement().setPropertyString("placeholder", currentMovie.getName());
		allMovies = new MultiWordSuggestOracle();
		allMovies.add(currentMovie.getName());
		movieSuggestBox = new SuggestBox(allMovies);
		movieSuggestBox.setStyleName("CardSuggestBox");
		
		groupLabel =  new Label("Group");
		groupLabel.setStyleName("TextBoxLabel");
		allGroups = new ListBox();
		allGroups.setStyleName("CardSuggestBox");
		allGroups.getElement().setPropertyString("placeholder", currentGroup.getName());
		
//		cityLabel = new Label("City");
//		cityLabel.setStyleName("TextBoxLabel");
//		allCities = new MultiWordSuggestOracle();
//		allCities.add(""); // TODO aktuelle Stadt anzeigen
//		citySuggestBox = new SuggestBox();
//		citySuggestBox.setStyleName("CardSuggestBox");
//		citySuggestBox.getElement().setPropertyString("placeholder", currentCity);
		
//		startDateLabel = new Label("Start Date");
//		startDateLabel.setStyleName("TextBoxLabel");
//		startDateBox = new DateBox();
//		startDateBox.setStyleName("CardDatePicker");
//		startDateBox.setFormat(
//				new DateBox.DefaultFormat(
//						DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
//		
//		
//		endDateLabel = new Label("End Date");
//		endDateLabel.setStyleName("TextBoxLabel");
//		endDateBox = new DateBox();
//		endDateBox.setStyleName("CardDatePicker");
//		endDateBox.setFormat(
//				new DateBox.DefaultFormat(
//						DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
				
		
		
		
		formWrapper.add(cardDescription1);
		formWrapper.add(cancelIcon);
		formWrapper.add(movieLabel);
		formWrapper.add(movieSuggestBox);
		formWrapper.add(groupLabel);
		formWrapper.add(allGroups);
		formWrapper.add(cityLabel);
		formWrapper.add(citySuggestBox);
//		formWrapper.add(startDateLabel);
//		formWrapper.add(startDateBox);
//		formWrapper.add(endDateLabel);
//		formWrapper.add(endDateBox);
		
		
		editScreenings = new Button("Edit Screenings");
		editScreenings.setStyleName("SaveButton");
		editScreenings.addClickHandler(new AddScreeningsClickHandler(this));
//		
//		
		formWrapper.add(editScreenings);
		this.add(formWrapper);
//		
//		
	}
	
	class GetMovieCallback implements AsyncCallback<Movie> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Problem with the Callback");
			
		}

		@Override
		public void onSuccess(Movie result) {
			currentMovie = result;
			movieLabel.setText(result.getName());
		}
		
	}
	
	class GetGroupCallback implements AsyncCallback<Group> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Problem with the Callback");			
		}

		@Override
		public void onSuccess(Group result) {
			currentGroup = result;
			groupLabel.setText(result.getName());
		}
		
	}
	
	
	//Clickhandler muss EditScreening2/2 aufrufen und eingetragene Werte �bernehmen
	class AddScreeningsClickHandler implements ClickHandler{
		EditSurveyCard editSurveyCard;
		public AddScreeningsClickHandler(EditSurveyCard editSurveyCard) {
		
		this.editSurveyCard = editSurveyCard;
	}
		@Override
		
		public void onClick(ClickEvent event) {
		
		// Als �bergabe reicht startDate, endDate, Vector<Sreenings> zum Testen erstelle ich 
		// vorerst objekte manuell	
		Movie m = new Movie();
		m.setName(movieSuggestBox.getText());
		//m.setId(1);
		Group g = new Group();
		g.setName(allGroups.getSelectedItemText());
		String c = new String();
		c = citySuggestBox.getText();
		
//		Date sd =(Date) startDateBox.getValue();
//		Date ed =(Date) endDateBox.getValue();
		
		
		
			
//			
//			if(movieSuggestBox.getText() == null || allGroups.getSelectedItemText() == null
//					|| citySuggestBox.getText() == null ||
//					startDateBox == null || endDateBox == null ) {
//				Window.alert("Fill in required fields");
//			}else {
				
				editSurveyCard.clear();
				editSurveyCard.showEditScreenings(m, g, c);
				
				
//		}
			
		}
	}
	
	
	class CancelClickHandler implements ClickHandler{
		EditSurveyCard editSurveyCard;
		public CancelClickHandler(EditSurveyCard editSurveyCard) {
			this.editSurveyCard = editSurveyCard;
		}
		

		@Override
		public void onClick(ClickEvent event) {
			if(parentCard==null) {
				editSurveyCard.hide();
			}else {
				parentCard.showSurveyCardView(surveyToShow);
				editSurveyCard.hide();
			}
		}
	}
	
	public void showEditScreenings(Movie m, Group g, String c) {
		
		
		
		this.setStyleName("EditCard");
		formWrapper = new FlowPanel();
		cardDescription2 = new Label("Edit Survey 2/2");
		cardDescription2.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		//TO DO BEf�llung der Label mit INhalt der vorherigen Auswahl
		
		selectedMovie = new Label("Movie: " + m.getName());
		selectedMovie.setStyleName("TextBoxLabel");
		selectedGroup = new Label ("Group: " + g.getName());
		selectedGroup.setStyleName("TextBoxLabel");
		selectedCity = new Label ("City: " + c);
		selectedCity.setStyleName("TextBoxLabel");
		selectedPeriod = new Label ("selected Period: ");// + sd.toString() + " - " + ed.toString());
		selectedPeriod.setStyleName("TextBoxLabel");
		
		cinemaFilter = new Label("Cinema Filter");
		cinemaFilter.setStyleName("TextBoxLabel");
		availableCinemas = new ListBox();
	    for (Cinema cin : selectedCinemas) {
	      availableCinemas.addItem(cin.getName());
		}
	    availableCinemas.addClickHandler(new AvailableCinemasClickHandler());
		availableCinemas.setStyleName("CardSuggestBox");
		
		screeningDates = new Vector<Date>();
		for(Screening s : currentScreenings) {
			screeningDates.add(s.getScreeningDate());
		}
		
		dateFilter = new Label("Date Filter");
		dateFilter.setStyleName("TextBoxLabel");
		filterDateBox = new ListBox();
		for (Date d : screeningDates) {
			filterDateBox.addItem(d.toString());
		}
		filterDateBox.addClickHandler(new FilterDateBoxClickHandler());
		filterDateBox.setStyleName("CardSuggestBox");
		
		screeningSelection = new FlowPanel();
		
		screeningRowVector = new Vector <ScreeningRow>();
		
		screeningVector = new Vector<Screening>();		
		
		for (Screening s : screeningVector) {
			ScreeningRow sr = new ScreeningRow(s);
			screeningRowVector.add(sr);
			screeningSelection.add(sr);
		    }
		
		selectedMovie.setText(currentMovie.getName());
		selectedGroup.setText(currentGroup.getName());
		
		showSelected = new Label("Show All Screenings");
		
		formWrapper.add(cardDescription2);
		formWrapper.add(cancelIcon);
		formWrapper.add(selectedMovie);
		formWrapper.add(selectedGroup);
		formWrapper.add(selectedCity);
		formWrapper.add(selectedPeriod);
		formWrapper.add(screeningSelection);
		
		saveSurvey = new Button("Save Survey");
		saveSurvey.setStyleName("SaveButton");
		saveSurvey.addClickHandler(new SaveSurveyClickHandler(this));
		
		stopSurvey = new Button("Stop Survey");
		stopSurvey.setStyleName("SaveButton");;
		stopSurvey.addClickHandler(new StopSurveyClickHandler(this));
		
		formWrapper.add(saveSurvey);
		formWrapper.add(stopSurvey);
		this.add(formWrapper);
	}	
	
	class SaveSurveyClickHandler implements ClickHandler{ 
		EditSurveyCard editSurveyCard;
		public SaveSurveyClickHandler(EditSurveyCard editSurveyCard) {
		
		this.editSurveyCard = editSurveyCard;
	}
		@Override
		
		public void onClick(ClickEvent event) {
			
			screeningVector = new Vector <Screening>();
			
			for(ScreeningRow sr: screeningRowVector) {
				if(sr.cb.getValue() == true) {
					screeningVector.add(sr.s);
				}
			}
			
			Window.alert(screeningVector.size() +" checkboxes selected");
			
			sma.updateSurvey(editSurveyCard.surveyToShow, new SaveSurveyCallback());
		
			
			
		}
		
		class SaveSurveyCallback implements AsyncCallback<Survey> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the Callback");				
			}

			@Override
			public void onSuccess(Survey result) {
				parentCard.showSurveyCardView(result);
				editSurveyCard.hide();
			}
			
		}
		}
	
	class AvailableCinemasClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Methode die nur die Screenings des Ausgewählten Cinemas anzeigt
			
		}
		
	}
	
	class FilterDateBoxClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Methode die nur die Screenings am ausgewählten Datum anzeigt
			
		}
		
	}
	
	class StopSurveyClickHandler implements ClickHandler {
		EditSurveyCard editsurveyCard;
		
		public StopSurveyClickHandler (EditSurveyCard editSurveyCard) {
			this.editsurveyCard = editSurveyCard;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Methode um den Status der Umfrage auf Beendet zu setzen.
			
		}
		
	}
	
	class ScreeningRow extends FlowPanel{
		
		CheckBox cb;
		Screening s;
		Vector<SurveyEntry> seV;
		Vector<Vote> vV;
		Vector<Vote> pVV;
		Vector<Vote> nVV;
		public ScreeningRow(Screening s) {
			this.s = s;
		}
		
		
		public void onLoad() {
			super.onLoad();
		
			sma.getSurveyEntryByScreeningFK(s.getId(), new GetSurveyEntryCallback());
			sma.getCinemaById(s.getCinemaFK(), new GetCinemaCallback());
			for (SurveyEntry se : seV) {
				sma.getVotesBySurveyEntryFK(se.getId(), new GetVoteCallback());
			}
			for (Vote v : vV) {
				if (v.getVotingWeight() > 0) {
					pVV.add(v);
				}else {
					nVV.add(v);
				}
			}
			cb = new CheckBox(cinemaInScreening.getName() + " " + s.getScreeningDate() + " " + s.getScreeningTime() 
			+ " Uhr" + "positiv Votes " + pVV.size() + " " + "negativ Votes " + nVV.size() + s.getId());
			this.add(cb);		
			
		}
		
		class GetCinemaCallback implements AsyncCallback<Cinema> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the Callback");				
			}

			@Override
			public void onSuccess(Cinema result) {
				cinemaInScreening = result;
				
			}
			
		}
		
		class GetSurveyEntryCallback implements AsyncCallback<Vector<SurveyEntry>> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the Callback");				
				
			}

			@Override
			public void onSuccess(Vector<SurveyEntry> result) {
				seV = result;
			}
			
		}
		
		class GetVoteCallback implements AsyncCallback<Vector<Vote>> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the Callback");				
				
			}

			@Override
			public void onSuccess(Vector<Vote> result) {
				vV = result;
			}
			
		}
		
	}
}
