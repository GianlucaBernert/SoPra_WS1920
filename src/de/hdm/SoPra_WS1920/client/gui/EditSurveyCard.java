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
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

public class EditSurveyCard extends DialogBox {
	
	FlowPanel formWrapper;
	SurveyCard parentCard;
	Survey surveyToShow;
	Cinema cinemaInScreening;
	SurveyManagementAsync sma; 
	CinemaAdministrationAsync caa; 
	
	
	//EditSurvey 1/2
	
	Label cardDescription1;
	Label movieLabel;
	Label groupLabel;
	Label cityLabel;
	Label startDateLabel;
	Label endDateLabel;
	
	MultiWordSuggestOracle allMovies;
	SuggestBox movieSuggestBox;
	
	ListBox allGroups;
	
	MultiWordSuggestOracle allCities;
	SuggestBox citySuggestBox;
	
	DateBox startDateBox;
	DateBox endDateBox;
	
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
	DateBox filterDateBox;
	
	FlowPanel screeningSelection;
	CheckBox screeningToSelect;
	
	Vector <ScreeningRow> screeningRowVector;
	Vector <Screening> screeningVector;
	
	Label showSelected;
	Button saveSurvey;
	
	
	SurveyManagementHeader header;
	SurveyContent content;
	
	
	
	
	public EditSurveyCard(SurveyCard surveyCard, Survey survey, SurveyContent content, SurveyManagementHeader header) {
		this.parentCard = surveyCard;
		this.surveyToShow = survey;
		this.content = content;
		this.header = header;	
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		caa = ClientsideSettings.getCinemaAdministration();
		sma = ClientsideSettings.getSurveyManagement();
		
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
		allMovies = new MultiWordSuggestOracle();
		allMovies.add(content.m.getName());
		movieSuggestBox = new SuggestBox(allMovies);
		movieSuggestBox.setStyleName("CardSuggestBox");
		
		groupLabel =  new Label("Group");
		groupLabel.setStyleName("TextBoxLabel");
		allGroups = new ListBox();
		allGroups.addItem(content.g.getName());
		allGroups.setStyleName("CardSuggestBox");
		
		cityLabel = new Label("City");
		cityLabel.setStyleName("TextBoxLabel");
		allCities = new MultiWordSuggestOracle();
		allCities.add("");
		citySuggestBox = new SuggestBox(allCities);
		citySuggestBox.setStyleName("CardSuggestBox");
		
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
				
		
		//setValue, setText notwendig?
		
		formWrapper.add(cardDescription1);
		formWrapper.add(cancelIcon);
		formWrapper.add(movieLabel);
		formWrapper.add(movieSuggestBox);
		formWrapper.add(groupLabel);
		formWrapper.add(allGroups);
		formWrapper.add(cityLabel);
		formWrapper.add(citySuggestBox);
		formWrapper.add(startDateLabel);
		formWrapper.add(startDateBox);
		formWrapper.add(endDateLabel);
		formWrapper.add(endDateBox);
		
		
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
		m.setId(1);
		Group g = new Group();
		g.setName(allGroups.getSelectedItemText());
		String c = new String();
		c = citySuggestBox.getText();
		
		Date sd =(Date) startDateBox.getValue();
		Date ed =(Date) endDateBox.getValue();
		
		
		
			
//			
//			if(movieSuggestBox.getText() == null || allGroups.getSelectedItemText() == null
//					|| citySuggestBox.getText() == null ||
//					startDateBox == null || endDateBox == null ) {
//				Window.alert("Fill in required fields");
//			}else {
				
				editSurveyCard.clear();
				editSurveyCard.showEditScreenings(m, g, c, sd, ed);
				
				
//		}
			
		}
	}
	
	
	class CancelClickHandler implements ClickHandler{
		EditSurveyCard editSurveyCard;
		public CancelClickHandler(EditSurveyCard editSurveyCard) {
			// TODO Auto-generated constructor stub
			this.editSurveyCard = editSurveyCard;
		}
		

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(parentCard==null) {
				editSurveyCard.hide();
			}else {
				parentCard.showSurveyCardView(surveyToShow);
				editSurveyCard.hide();
			}
		}
	}
	
	public void showEditScreenings(Movie m, Group g, String c, Date sd, Date ed) {
		
		Screening testScreening = new Screening();
		testScreening.setCinemaFK(1);
	//	testScreening.setScreeningDate(screeningDate);
		testScreening.setId(2);
		Vector <Screening> sv = new Vector();
		sv.add(testScreening);
		Screening testScreening1 = new Screening();
		testScreening1.setId(3);
		//s = this.getScreeningBy..(m, c, startDate, endDate);
		sv.add(testScreening1);
		
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
		
		dateFilter = new Label("Date Filter");
		dateFilter.setStyleName("TextBoxLabel");
		filterDateBox = new DateBox();
		filterDateBox.setStyleName("CardDatePicker");
		filterDateBox.setFormat(
				new DateBox.DefaultFormat(
						DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
		
		
		screeningSelection = new FlowPanel();
		
		screeningRowVector = new Vector <ScreeningRow>();
		
		
		//Methode zur Erstellung der Screening Elemente sowie Bef�llung der ScreeningSelectionBox
		
		
		for (Screening s : sv) {
			ScreeningRow sr = new ScreeningRow(s);
			screeningRowVector.add(sr);
			screeningSelection.add(sr);
		    }
		
		
		
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
		
		formWrapper.add(saveSurvey);
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
			
			Survey s = new Survey();
//			s = SurveyManagementImpl.
//			for(CheckBox selectedCheckBox : screeningCheckBoxVector) {
//		/		if(selectedCheckBox.getValue() == true) {
//					Screening screen = new Screening();
//				}
//			}
		
			
			
		}
		}
	
	class ScreeningRow extends FlowPanel{
		
		CheckBox cb;
		Screening s;
		public ScreeningRow(Screening s) {
			this.s = s;
		}
		
		
		public void onLoad() {
			super.onLoad();
		
			caa.getCinemaById(s.getCinemaFK(), new GetCinemaCallback());
			cb = new CheckBox(cinemaInScreening.getName() + " " + s.getScreeningDate() + " " + s.getScreeningTime() + " Uhr" + s.getId());
			this.add(cb);		
			
		}
		
		class GetCinemaCallback implements AsyncCallback<Cinema> {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Cinema result) {
				cinemaInScreening = result;
				
			}
			
		}
		
	}
}
