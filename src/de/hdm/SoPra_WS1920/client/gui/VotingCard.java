package de.hdm.SoPra_WS1920.client.gui;

import java.awt.Checkbox;
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
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

public class VotingCard extends DialogBox {
	
	SurveyManagementAsync sma; 
	CinemaAdministrationAsync caa; 
	
	FlowPanel formWrapper;
	FlowPanel screeningSelection;
	
	SurveyCard parentCard;
	
	Survey currentSurvey;
	
	Movie currentMovie;
	
	Group currentGroup;
	
	Cinema cinemaInScreening;
	
	Vector<Screening> currentScreenings;
	Vector<SurveyEntry> currentSurveyEntrys;
	Vector<ScreeningRow> screeningRowVector;
	Vector<Cinema> selectedCinemas;
	Vector<Date> screeningDates;
	Vector<Screening> screeningVector;
	
	Date startDate;
	Date endDate;
	
	Image cancelIcon;
	Image saveIcon;
	Image deleteIcon;
	
	Label cardDescription;
	Label selectedSurvey;
	Label selectedMovie;
	Label selectedGroup;
	Label selectedPeriod;
	Label cinemaFilter;
	Label dateFilter;
	Label showSelected;
	Label deleteSurvey;
	Label movieLabel;
	Label groupLabel;
	Label surveyPeriod;
	
	ListBox availableCinemas;
	ListBox filterDateBox;
	
	DateBox surveyIntervall;
	
	Checkbox screeningToSelect;
	
	Button saveSurvey;
	Button stopSurvey;
	Button cancel;
	Button invisibleButton;
	
	SurveyManagementHeader header;
	
	SurveyContent content;
	
	public VotingCard(SurveyCard surveyCard, Survey survey, SurveyContent content) {
		this.parentCard = surveyCard;
		this.currentSurvey = survey;
		this.content = content;
	}
	
	public void onLoad() {
		caa = ClientsideSettings.getCinemaAdministration();
		sma = ClientsideSettings.getSurveyManagement();
		
		sma.getMovieBySurveyFK(this.currentSurvey.getId(), new GetMovieCallback());
		sma.getGroupById(this.currentSurvey.getGroupFK(), new GetGroupCallback());
		super.onLoad();
		
		this.setStyleName("EditCard");
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("Edit Survey");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");

		movieLabel = new Label("Movie: " + this.currentMovie);
		movieLabel.setStyleName("TextBoxLabel");
		
		groupLabel =  new Label("Group: " + this.currentGroup);
		groupLabel.setStyleName("TextBoxLabel");
		
//		surveyPeriod = new Label("Survey Period: " + this.startDate + " - " + this.endDate);
//		surveyPeriod.setStyleName("TextBoxLabel");
		
		selectedCinemas = new Vector<Cinema>();
		for (Screening sc : currentScreenings) {
			caa.getCinemaById(sc.getCinemaFK(), new GetCinemaCallback());
		}
		
		cinemaFilter = new Label("Cinema Filter");
		cinemaFilter.setStyleName("TextBoxLabel");
		availableCinemas = new ListBox();
	    for (Cinema c : selectedCinemas) {
	      availableCinemas.addItem(c.getName());
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
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(movieLabel);
		formWrapper.add(groupLabel);
//		formWrapper.add(surveyPeriod);
		formWrapper.add(cinemaFilter);
		formWrapper.add(availableCinemas);
		formWrapper.add(dateFilter);
		formWrapper.add(filterDateBox);
		formWrapper.add(screeningSelection);
		

	}
	
	class GetMovieCallback implements AsyncCallback<Movie> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Problem with the Callback");							
		}

		@Override
		public void onSuccess(Movie result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class GetGroupCallback implements AsyncCallback<Group> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Problem with the Callback");							
		}

		@Override
		public void onSuccess(Group result) {
			// TODO Auto-generated method stub
			
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
	
	class GetCinemaCallback implements AsyncCallback<Cinema> {

		@Override
		public void onFailure(Throwable caught) {
			
			
		}

		@Override
		public void onSuccess(Cinema result) {
				selectedCinemas.addElement(result);
		
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{
		VotingCard votingCard;
		public CancelClickHandler(VotingCard votingCard) {
			this.votingCard = votingCard;
		}
		

		@Override
		public void onClick(ClickEvent event) {
			if(parentCard==null) {
				votingCard.hide();
			}else {
				parentCard.showSurveyCardView(currentSurvey);
				votingCard.hide();
			}
		}
	}
	
	// TODO Votebar machen
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
		
			caa.getSurveyEntryByScreeningFK(s.getId(), new GetSurveyEntryCallback());
			caa.getCinemaById(s.getCinemaFK(), new GetCinemaCallback());
			for (SurveyEntry se : seV) {
				caa.getVotesBySurveyEntryFK(se.getId(), new GetVoteCallback());
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