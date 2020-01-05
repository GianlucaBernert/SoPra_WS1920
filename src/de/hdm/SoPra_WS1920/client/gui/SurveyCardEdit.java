package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
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
import de.hdm.SoPra_WS1920.shared.bo.Survey;

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
	
	MultiWordSuggestOracle allCities;
	SuggestBox citySuggestBox;
	
	DateBox startDateBox;
	DateBox endDateBox;
	
	Button invisibleButton;
	Button addScreenings;
	Button cancel; 
	Image cancelIcon;
	
	
	//CreateSurvey 2/2 
	
	Label cardDescription2;
	Label selectedMovie;
	Label selectedGroup;
	Label selectedCity;
	Label selectedPeriod;
	
	
	Label cinemaFilter;
	ListBox availableCinemas;
	
	FlowPanel screeningSelection;
	CheckBox selectedScreening;
	
	Button createSurvey;
	
	//question
	SurveyManagementHeader header;
	SurveyContent content;
	
	public SurveyCardEdit(SurveyCard surveyCard, Survey survey) {
		this.parentCard = surveyCard;
		this.surveyToShow = survey;
	}
	
	
	public SurveyCardEdit(SurveyContent content, SurveyManagementHeader header) {
		this.content = content;
		this.header = header;
		
		Survey s = new Survey();
		s.setId(1);
		s.setGroupFK(1);
		s.setStartDate(DateTimeFormat.getFormat("dd.MM.yyyy").parse("30.12.2019"));
		s.setEndDate(DateTimeFormat.getFormat("dd.MM.yyyy").parse("31.12.2019"));
		
	}
	
	public void onLoad() {
		super.onLoad();
		
		this.setStyleName("EditCard");
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
		allMovies.add("MovieToAdd");
		movieSuggestBox = new SuggestBox(allMovies);
		movieSuggestBox.setStyleName("CardSuggestBox");
		
		groupLabel =  new Label("Group");
		groupLabel.setStyleName("TextboxLabel");
		allGroups = new ListBox();
		allGroups.setStyleName("CardSuggestBox");
		
		cityLabel = new Label("City");
		cityLabel.setStyleName("TextBoxLabel");
		allCities = new MultiWordSuggestOracle();
		allCities.add("CityToAdd");
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
		//question
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
		
		// Editieren wie edit or delte notwendig??
		
		//addScreenings Button im SaveButtonStyle. ok?
//		addScreenings = new Button("Save");
//		addScreenings.setStyleName("SaveButton");
//		addScreenings.addClickHandler(new addScreeningsClickHandler(this));
//		
//		
//		formWrapper.add(addScreenings);
//		this.add(formWrapper);
//		
//		
	}
	
//	class addScreeningsClickHandler implements ClickHandler{
//		//Clickhandler muss CreateScreening2/2 aufrufen und eingetragene Werte übernehmen
//	}
	
	
	//question
	class CancelClickHandler implements ClickHandler{
		SurveyCardEdit surveyCardEdit;
		public CancelClickHandler(SurveyCardEdit surveyCardEdit2) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit = surveyCardEdit;
		}
		
		//question
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
	
	public void onLoad2() {
		super.onLoad();
		
		this.setStyleName("EditCard");
		formWrapper = new FlowPanel();
		
		cardDescription2 = new Label("Create Survey 1/2");
		cardDescription2.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		
		//TO DO BEfüllung der Label mit INhalt der vorherigen Auswahl
		
		selectedMovie = new Label("Movie:");
		selectedMovie.setStyleName("TextBoxLabel");
		selectedGroup = new Label ("Group:");
		selectedGroup.setStyleName("TextBoxLabel");
		selectedCity = new Label ("City:");
		selectedCity.setStyleName("TextBoxLabel");
		selectedPeriod = new Label ("selected Period");
		selectedPeriod.setStyleName("TextBoxLabel");
		
		
	}
	
	
	

	
	
	
	
	

}
