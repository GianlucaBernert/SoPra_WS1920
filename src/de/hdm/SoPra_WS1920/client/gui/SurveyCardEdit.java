package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;
import java.sql.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.client.gui.Admin.ScreeningCardEdit;
import de.hdm.SoPra_WS1920.server.SurveyManagementImpl;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;

/**
 * Diese Klasse dient dazu, entweder neue Umfragen zu erstellen oder bestehende Umfragen zu editieren.
 * Sie wird als Dialogbox realisiert.
 * @author Sebastian Hermann
 *
 */
public class SurveyCardEdit extends DialogBox {
	
	/**
	 * Deklaration der drei Attribute formWrapper, parentCard und surveyToShow
	 */
	FlowPanel formWrapper;	//Platzhalter für sämtliche Widgets, da auf einer Dialogbox nur ein Widget/Panel platziert werden kann
	SurveyCard parentCard;	//Referenz zur SurveyCard, sofern diese besteht und die SurveyCardEdit nicht im Zug der Ertellung einer neuen Umfrage aufgerufen wird
	Survey surveyToShow;
	
	/**
	 * Zunächst wird die Erstellung einer Umfrage betrachtet.
	 * Dort wird der Umfragefilm, die Umfragestadt und der Umfragezeitraum festgelegt um so die für den nächsten Schritt relevanten
	 * Filmpräsentationen nach diesen Kriterien suchen zu können.
	 */
	Label cardDescription;
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
	
	
	/**
	 * Bevor die Umfrage letztendlich erstellt werden kann, werden je nach den zuvor definierten Suchkriterien Filmpräsentationsvorschläge
	 * gemacht. Durch Auswahl der Checkboxen können dann Filmpräsentationen zu Umfrageoptionen umgewandelt und gespeichert werden.
	 * 
	 */
	Label cardDescription2;
	Label selectedMovie;
	Label selectedGroup;
	Label selectedCity;
	Label selectedPeriod;
	
	FlowPanel screeningSelection;
	Label deleteLabel;
	Image deleteIcon;
	
	Vector <ScreeningRow> screeningRowVector;
	Vector <Screening> screeningVector;
	Vector <SurveyEntry> surveyEntryVector;
	
	Label showSelected;
	Button saveSurvey;
	Button stopSurvey;
	
	Survey survey;
	
	Cinema cinema;
	
	SurveyManagementHeader header;
	SurveyContent content;
	SurveyManagementAsync surveyManagement;
	
	
	
	/**
	 * Konstruktor der aufgerufen wird, wenn eine bestehende Karte editiert werden soll
	 * @param surveyCard
	 * @param survey
	 */
	public SurveyCardEdit(SurveyCard surveyCard, Survey survey) {
		this.parentCard = surveyCard;
		this.surveyToShow = survey;
	}
	
	/**
	 * Konstruktor der aufgerufen wird, wenn eine neue Umfrage erstellt werden soll
	 * @param content
	 * @param header
	 */
	public SurveyCardEdit(SurveyContent content, SurveyManagementHeader header) {
		this.content = content;
		this.header = header;
	
	}
	
	/**
	 * Definition der onLoad-Methode. Wird aufgerufen sobald das Panel aufgerufen wird.
	 * die <code>super.onLoad()<code> Methode wird aufgerufen, damit zunächst alle Eigenschaften der vererbten DialogBox-Klasse übernommen werden.
	 */
	public void onLoad() {
		super.onLoad();
		
		this.setStyleName("EditCard");
//		person = new Person();
//		person.setId(content.getPerson().getId());
		person = new Person();
		person.setEMail(Cookies.getCookie("gmail"));
		person.setFirstname(Cookies.getCookie("firstName"));
		person.setLastname(Cookies.getCookie("lastName"));
		person.setId(Integer.parseInt(Cookies.getCookie("userId")));
		
		
		surveyManagement = ClientsideSettings.getSurveyManagement();
		formWrapper = new FlowPanel();	
	}
	/**
	 * Methode zur Zuweisung der Widgets zur angezeigten Dialogbox, die für das Erstellen der Karte zuständig sind.
	 */
	public void showCreateCard() {
		cardDescription = new Label("Create Survey 1/2");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		movieLabel = new Label("Movie");
		movieLabel.setStyleName("TextBoxLabel");
		allMovies = new MultiWordSuggestOracle();
		surveyManagement.getAllMovies(new AllMoviesCallback());
		movieSuggestBox = new SuggestBox(allMovies);
		movieSuggestBox.setStyleName("CardSuggestBox");
		
		groupLabel =  new Label("Group");
		groupLabel.setStyleName("TextBoxLabel");
		allGroups = new ListBox();
		surveyManagement.getGroupsByMemberships(person.getId(), new GetGroupsByFKCallback());
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
				
		
		//setValue, setText notwendig?
		
		formWrapper.add(cardDescription);
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
		
		/**
		 * Über den <code>addScreenings<code> button wird die nächste Schaltfläche aufgerufen, über die das Hinzufügen von Sceenings zu einer
		 * Umfrage, sprich die Filmpräsentationsvorschläge, ermöglicht wird.
		 */
		addScreenings = new Button("Add Screenings");
		addScreenings.setStyleName("SaveButton");
		addScreenings.addClickHandler(new AddScreeningsClickHandler(this));

		formWrapper.add(addScreenings);
		this.add(formWrapper);
	}
	
		/**
		 * Anonyme Klasse die das ClickHandler-Interface implementiert. Beim Klick wird der ausgewählte Film anhand des Titels gesucht.
		 * @author Sebastian Hermann
		 *
		 */
		class AddScreeningsClickHandler implements ClickHandler{
			
			SurveyCardEdit surveyCardEdit;
			public AddScreeningsClickHandler(SurveyCardEdit surveyCardEdit) {
				this.surveyCardEdit = surveyCardEdit;
			}
			@Override
			
			public void onClick(ClickEvent event) {
				surveyManagement.getMoviesByName(movieSuggestBox.getText(), new GetMovieCallback(surveyCardEdit));

			}
		}
		/**
		 * Anonyme Klasse, die das AsnycCallback Interface implementiert. Erhält per Rückruf das angeforderte Film-Objekt. 
		 * Im Anschluss daran wird die Gruppe der Umfrage ermittelt.
		 * @author Sebastian Hermann
		 *
		 */
		class GetMovieCallback implements AsyncCallback<Vector<Movie>>{
			SurveyCardEdit surveyCardEdit;
			public GetMovieCallback(SurveyCardEdit surveyCardEdit) {
				// TODO Auto-generated constructor stub
				this.surveyCardEdit = surveyCardEdit;
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Could not load movies.");
			}

			@Override
			public void onSuccess(Vector<Movie> result) {
				// TODO Auto-generated method stub
				movie = result.firstElement();
				//getGroupByNameAndPersonFk
				surveyManagement.getGroupOfPersonByGroupName(person.getId(), allGroups.getSelectedItemText(), new GetGroupCallback(surveyCardEdit));

			}
			
		}
		/**
		 * Anonyme Klasse, die die Gruppe einer Umfrage per Callback erhält. Im Anschluss daran wird der Film gesetzt und die Karte
		 * wird mit in den Editiermodus gebracht.
		 * @author Sebastian Hermann
		 *
		 */
		class GetGroupCallback implements AsyncCallback<Group>{
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
			public void onSuccess(Group result) {
				// TODO Auto-generated method stub
				group = result;
				city = cityTextBox.getText();
				
				startDate = new java.sql.Date(startDateBox.getValue().getTime());
				endDate = new java.sql.Date(endDateBox.getValue().getTime());
				
//				surveyCardEdit.showAddScreenings(movie, group, city, startDate, endDate);
				surveyManagement.getScreeningsforSurveyCreation(movie, city, startDate, endDate, new GetScreeningsCallback(surveyCardEdit));
				//surveyCardEdit.showSurveyCardEdit();
				
			}

		}

		/**
		 * End of Create Survey Card 1
		 * ------------------------------------------------------------------------------------------------------------------------
		 * Start of Create Survey Card 2 & Edit Survey Card
		 * Hier wird definiert was passieren soll, wenn die Karte in den Editier-Zustand gebracht werden soll.
		 */
		public void showSurveyCardEdit() {
			this.clear();
			this.setStyleName("EditCard");

			screeningRowVector = new Vector<ScreeningRow>();
			formWrapper = new FlowPanel();
			formWrapper.setStyleName("DialogBoxWrapper");
			cardDescription = new Label();
			cardDescription.setStyleName("CardDescription");
			cancelIcon = new Image("/Images/png/007-close.png");
			cancelIcon.setStyleName("CancelIcon");
			cancelIcon.addClickHandler(new CancelClickHandler(this));
			selectedMovie = new Label();
			selectedMovie.setStyleName("TextBoxLabel");
			selectedGroup = new Label ();
			selectedGroup.setStyleName("TextBoxLabel");
			selectedCity = new Label ();
			selectedCity.setStyleName("TextBoxLabel");
			selectedPeriod = new Label ();// + startDate.toString() + " - " + endDate.toString());
			selectedPeriod.setStyleName("TextBoxLabel");
			screeningSelection = new FlowPanel();
			screeningSelection.setStyleName("ScreeningSelection");

			showSelected = new Label("Screenings:");	
			saveSurvey = new Button("Create Survey");
			saveSurvey.setStyleName("SaveButton");
			
			formWrapper.add(cardDescription);
			formWrapper.add(cancelIcon);
			formWrapper.add(selectedMovie);
			formWrapper.add(selectedGroup);
			formWrapper.add(selectedCity);
			formWrapper.add(selectedPeriod);
			formWrapper.add(screeningSelection);
			
			/**
			 * Hier wird geprueft, ob die Survey neu angelegt werden soll, oder ob eine bestehende editiert werden soll.
			 * Je nach Fall, werden entsprechende Widgets der Karte hinzugefügt. Z.B. macht eine Delete-Funktion beim Erstellen einer
			 * neuen Karte keinen Sinn, da die Karte noch garnicht existiert.
			 */
			if(surveyToShow==null){
				cardDescription.setText("Create Survey 2/2");
				selectedMovie.setText("Movie: "+ movie.getName());
				selectedGroup.setText("Group: "+ group.getName());
				selectedPeriod.setText("Screening Period: "+ startDate.toString()+" - "+endDate.toString());
				
				//surveyManagement.getScreeningsforSurveyCreation(movie, city, startDate, endDate, new GetScreeningsCallback());
				saveSurvey.addClickHandler(new CreateSurveyClickHandler(this));
				formWrapper.add(saveSurvey);
			}else{
				cardDescription.setText("Edit Survey");
				selectedMovie.setText("Movie: "+ surveyToShow.getMovieName());
				selectedPeriod.setText("Screening Period: "+ surveyToShow.getStartDate().toString()+" - "+surveyToShow.getEndDate().toString());
				surveyManagement.getSurveyEntryBySurveyFK(surveyToShow.getId(), new GetSurveyEntriesCallback());			
				surveyManagement.getMoviesByName(surveyToShow.getMovieName(), new GetMovieByNameCallback(this));			
				surveyManagement.getGroupById(surveyToShow.getGroupFK(), new GetGroupOfSurveyCallback());
				
				deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
				deleteIcon.setStyleName("DeleteIcon");
				deleteLabel = new Label("Delete Survey");
				deleteLabel.setStyleName("DeleteLabel");
				deleteLabel.addClickHandler(new DeleteSurveyClickHandler(this));
				formWrapper.add(deleteIcon);
				formWrapper.add(deleteLabel);
				
				if(surveyToShow.getStatus()==1) {
					saveSurvey.setText("Save");
					saveSurvey.addClickHandler(new UpdateSurveyClickHandler(this));
					stopSurvey=new Button();
					stopSurvey.setText("Stop Survey");
					stopSurvey.addClickHandler(new StopSurveyClickHandler(this));
					stopSurvey.setStyleName("StopSurveyButton");
					formWrapper.add(saveSurvey);
					formWrapper.add(stopSurvey);
				}
			}
					
			this.add(formWrapper);
		}
		
		/**
		 * Diese Methode wird aufgerufen wenn die vorgenommenen Änderungen gespeichert werden sollen.
		 * Hierzu werden drei Vektoren angelegt
		 */
		public void saveSurvey() {
			/**
			 * Der erste Vektor ist für die Überprüfung der selektierten Filmpräsentationen zuständig.
			 */
			Vector<Screening> selectedScreenings = new Vector<Screening>();
			
			/**
			 * Der zweite Vektor sammelt alle Filmpräsentationen, die neu zur Umfrage hinzugefügt werden sollen.
			 */
			Vector<Screening> screeningsForCreation = new Vector<Screening>();
			
			/**
			 * Der dritte Vektor sammelt alle SurveyEntries, die gelöscht werden sollen
			 */
			Vector<SurveyEntry> surveyEntriesForDeletion = new Vector<SurveyEntry>();
			
			/**
			 * Alle ausgewählten Filmpräsentationen sollen zunächst dem Creation-Vektor hinzugefügt werden.
			 * Weiterhin sollen für den Löschvorgang ausschließlich SurveyEntries in den Vektor aufgenommen werden, 
			 * die logischerweise zuvor als SurveyEntry gespeichert wurden.
			 */
			for(ScreeningRow sR: screeningRowVector) {
				if(sR.cb.getValue()==true) {
					screeningsForCreation.add(sR.s);
				}else if(sR.cb.getValue()==false) {
					for(SurveyEntry sE: surveyEntryVector){
						if(sR.s.getId()==sE.getScreeningFK()) {
							surveyEntriesForDeletion.add(sE);
						}
					}
				}
			}
			/**
			 * Um sicherzustellen, dass auch nur diejenigen Filmpräsentationen hinzugefügt werden, die zuvor noch nicht 
			 * existiert haben, werden alle Filmpräsentationen von Creation-Vektor gelöscht, die bereits existieren.
			 */
			for(ScreeningRow sR: screeningRowVector) {
				if(sR.cb.getValue()==true) {
					for(SurveyEntry sE:surveyEntryVector) {
						if(sE.getScreeningFK()==sR.s.getId()) {
							screeningsForCreation.remove(sR.s);
						}
					}
				}
			}
			
			/**
			 * Zum Schluss wird für jede übrig gebliebene Filmpräsentation ein Umfrageeintrag erstellt
			 */

			Vector<SurveyEntry> surveyEntries = new Vector<SurveyEntry>();
			for(Screening s: screeningsForCreation) {
//					Window.alert(s.toString() + " " + surveyToShow.getId() + " " + person.toString());
					SurveyEntry sE = new SurveyEntry();
					sE.setPersonFK(person.getId());
					sE.setScreeningFK(s.getId());
					sE.setSurveyFK(surveyToShow.getId());
					surveyEntries.add(sE);
//					surveyManagement.createSurveyEntry(sr.s.getId(), result.getId(), person.getId(), new CreateSurveyEntryCallback());
			}
			surveyManagement.createSurveyEntries(surveyEntries, new CreateSurveyEntriesCallback());
			
			/**
			 * Analog wird für jedes zu löschende Objekt der Löschbefehl ausgeführt
			 */
			for(SurveyEntry sE: surveyEntriesForDeletion) {
				surveyManagement.deleteSurveyEntry(sE, new DeleteSurveyEntryCallback());
			}
			
		}
		
		/**
		 * Anonyme Klasse zum verarbeiten von vom Server zurückgegebenen Film-Vektor.
		 */
		class GetMovieByNameCallback implements AsyncCallback<Vector<Movie>>{
			SurveyCardEdit surveyCardEdit;
			public GetMovieByNameCallback(SurveyCardEdit surveyCardEdit) {
				// TODO Auto-generated constructor stub
				this.surveyCardEdit=surveyCardEdit;
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Movie> result) {
				// TODO Auto-generated method stub
				movie = result.firstElement();
				surveyManagement.getScreeningsforSurveyCreation(movie, surveyToShow.getSelectedCity(), surveyToShow.getStartDate(), surveyToShow.getEndDate(), new GetScreeningsCallback(surveyCardEdit));
			}
			
		}
		
		/**
		 * Anonyme Klasse zum verarbeiten eines Klicks auf den Löschbutton. Im Anschluss an das erfolgte Löschen wird die Karte geschlossen.
		 */
		class DeleteSurveyClickHandler implements ClickHandler{
			SurveyCardEdit surveyCardEdit;
			public DeleteSurveyClickHandler(SurveyCardEdit surveyCardEdit) {
				// TODO Auto-generated constructor stub
				this.surveyCardEdit = surveyCardEdit;
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				surveyManagement.deleteSurvey(surveyToShow, new DeleteSurveyCallback());
				surveyCardEdit.hide();
				parentCard.remove();
			}
			
		}
		
		/**
		 *  Anonyme Klasse zur Verarbeitung einer erfolgten Löschung. Hier soll eigentlich nichts passieren, außer dass der User
		 *  über den erfolgten Löschvorgang informiert wird
		 */
		class DeleteSurveyCallback implements AsyncCallback<Void>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with the connection. Survey could not be deleted");
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				Window.alert("Survey Deleted");
			}
			
		}
		
		/**
		 * 
		 *  Anonyme Klasse zur Verarbeitung einer erfolgten Löschung eines Umfrageeintrags. Hier soll nichts weiter passieren.
		 */
		class DeleteSurveyEntryCallback implements AsyncCallback<Void>{

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
		 * Anonyme Klasse um Verarbeiten eines Klicks auf den save-button. Im Anschluss an den Aufruf wird die zuvor definierte
		 * <code>saveSurvey<code> Methode aufgerufen, die die Speicherung auf dem Server anstoßt.
		 * Abschließend wird die Karte geschlossen.
		 */
		class UpdateSurveyClickHandler implements ClickHandler{
			SurveyCardEdit surveyCardEdit;
			public UpdateSurveyClickHandler(SurveyCardEdit surveyCardEdit) {
				// TODO Auto-generated constructor stub
				this.surveyCardEdit=surveyCardEdit;
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				surveyCardEdit.saveSurvey();
				surveyCardEdit.hide();
//				parentCard.surveyContent.showSurveys();
			}
			
		}
		
		/*
		 * Verarbeitung eines Klicks auf den stop-survey-button. Hierbei wird eine Statusänderung auf der Datenbank angestoßen.
		 */
		class StopSurveyClickHandler implements ClickHandler{
			SurveyCardEdit surveyCardEdit;
			public StopSurveyClickHandler(SurveyCardEdit surveyCardEdit) {
				// TODO Auto-generated constructor stub
				this.surveyCardEdit=surveyCardEdit;
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				surveyToShow.setStatus(0);
				surveyManagement.updateSurvey(surveyToShow, new StopSurveyCallback(surveyCardEdit));
			}
			
		}
		
		/*
		 * Anonyme Klasse zur Verarbeitung des Rückrufobjekts im Anschluss an die Statusänderung einer Umfrage.
		 * Die Karte soll im Anschluss danach geschlossen werden.
		 */
		class StopSurveyCallback implements AsyncCallback<Survey>{
			SurveyCardEdit surveyCardEdit;
			public StopSurveyCallback(SurveyCardEdit surveyCardEdit) {
				// TODO Auto-generated constructor stub
				this.surveyCardEdit=surveyCardEdit;
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Survey result) {
				// TODO Auto-generated method stub
				parentCard.showSurveyCardView(result);
				surveyCardEdit.hide();
//				parentCard.surveyContent.showSurveys();
			}
			
		}
		
		/*
		 * Anonyme Klasse um alle Filpräsentationsvorschläge zu erhalten und zu verarbeiten. Für jeden Präsentationsvorschlag
		 * wir ein neues Objekt der Klasse <code>class ScreeningRow<code> erstellt, um die Präsentation der Vorschlagssliste hinzuzufügen
		 */
		class GetScreeningsCallback implements AsyncCallback<Vector<Screening>>{
			SurveyCardEdit surveyCardEdit;
			public GetScreeningsCallback(SurveyCardEdit surveyCardEdit) {
				// TODO Auto-generated constructor stub
				this.surveyCardEdit = surveyCardEdit;
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Screening> result) {
				// TODO Auto-generated method stub
				
				if(result.size()==0) {
					Window.alert("No screenings found for the selected criterias.");
				}else {
					if(surveyToShow==null) {
						surveyCardEdit.showSurveyCardEdit();
					}
					screeningVector = new Vector<Screening>();
					screeningVector.addAll(result);

					for (Screening s : result) {	
						ScreeningRow sr = new ScreeningRow(s);
						screeningRowVector.add(sr);
						screeningSelection.add(sr);
					}
				}
				
			}
		}

		/**
		 * Klasse um eine Filmpräsentationszeile erstellen zu können. Diese Klasse beinhaltet Informationen über die Selektion,
		 * die Präsentationsbeschreibung und die Präsentation an sich. Hierdurch wird ein Mapping ermöglicht zwischen Präsentation und
		 * User-Auswahl
		 * @author Sebastian Hermann
		 *
		 */
		class ScreeningRow extends FlowPanel{
			
			CheckBox cb;
			Label screeningDescription;
			Screening s;
			
			public ScreeningRow(Screening s) {
				this.s = s;
			}

			public void onLoad() {
				super.onLoad();
				this.setStyleName("ScreeningRow");
				cb = new CheckBox();
				screeningDescription = new Label();
				screeningDescription.setStyleName("ScreeningRowLabel");
				if(surveyToShow!=null) {
					for(SurveyEntry sE: surveyEntryVector) {
						if(sE.getScreeningFK()==s.getId()) {
							cb.setValue(true);
						}
					}	
				}
				surveyManagement.getCinemaById(s.getCinemaFK(), new GetCinemaCallback(this));	
				
				this.add(cb);
				this.add(screeningDescription);
			}
	
		}
		/*
		 * Methode um das Kino einer Präsentation aufzurufen. Wird benötigt um auf den Kinonamen zuzugreifen, der in der ScreeningRow angezeigt wird.
		 */
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
				screeningRow.screeningDescription.setText(cinema.getName() + ", "+ screeningRow.s.getScreeningDate().toString() + ", "+ screeningRow.s.getScreeningTime());		
			}
		}
		
		/*
		 * Anonyme Klasse um den Callback einer Gruppe zu verarbeiten.
		 */
		class GetGroupOfSurveyCallback implements AsyncCallback<Group>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Group result) {
				// TODO Auto-generated method stub
				group = result;
				selectedGroup.setText("Group: " + group.getName());
			}
		}
		
		/*
		 * Anonyme Klasse, um den Rückruf der Anfrage allre bereits einer Umfrage zugewiesenen Umfrageeinträge zu verarbeiten.
		 */
		class GetSurveyEntriesCallback implements AsyncCallback<Vector<SurveyEntry>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<SurveyEntry> result) {
				// TODO Auto-generated method stub
				surveyEntryVector = new Vector<SurveyEntry>();
				surveyEntryVector.addAll(result);
//				surveyEntryVector=result;
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
	
	/*
	 * Anonyme Klasse um die Anfrage nach der Gruppe per Foreign Key zu verarbeiten.
	 */
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
	
	/*
	 *  Anonyme Klasse um den Klick auf den Cancel Button zu verarbeiten. Im Anschluss auf den Klick wird die Dialogbox geschlossen.
	 */
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
	
	/*
	 * Anonyme Klasse um den Klick auf den create-survey-button zu verarbeiten. Im Anschluss an den Klick wird eine neue Umfrage erstellt.
	 */
	class CreateSurveyClickHandler implements ClickHandler{
		SurveyCardEdit surveyCardEdit;
		
		public CreateSurveyClickHandler(SurveyCardEdit surveyCardEdit) {
			
			this.surveyCardEdit = surveyCardEdit;
		}
		@Override
		
		public void onClick(ClickEvent event) {
			
			surveyManagement.createSurvey(group.getId(), person.getId(), city, movie.getName(), startDate, endDate, new CreateSurveyCallback(surveyCardEdit));
			
			}	
		}

	/*
	 *  Anonyme Klasse um die erfolgte Erstellung einer Umfrage zu verarbeiten. Im Anschluss an die Erstellung einer Umfrage, wird jede Umfrageoption
	 *  separat in einer neuen Serveranfrage angelegt.
	 */
	class CreateSurveyCallback implements AsyncCallback<Survey>{
		
		SurveyCardEdit surveyCardEdit;
		public CreateSurveyCallback(SurveyCardEdit surveyCardEdit) {
			// TODO Auto-generated constructor stub
			this.surveyCardEdit=surveyCardEdit;
		}	
		@Override
		public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
			Window.alert("Survey could not be created.");
		
		}

		@Override
		public void onSuccess(Survey result) {
			surveyToShow = result;
			Vector<SurveyEntry> surveyEntries = new Vector<SurveyEntry>();
			for(ScreeningRow sr: screeningRowVector) {
				if(sr.cb.getValue() == true) {
					SurveyEntry sE = new SurveyEntry();
					sE.setPersonFK(person.getId());
					sE.setScreeningFK(sr.s.getId());
					sE.setSurveyFK(result.getId());
					surveyEntries.add(sE);
//					surveyManagement.createSurveyEntry(sr.s.getId(), result.getId(), person.getId(), new CreateSurveyEntryCallback());
				}
			}
			surveyManagement.createSurveyEntries(surveyEntries, new CreateSurveyEntriesCallback());
			parentCard = new SurveyCard(content, result);
			parentCard.setMovie(movie);
			content.add(parentCard);
			surveyCardEdit.hide();
//			Window.alert("Selected Screenings: "+Integer.toString(counter));
		}
	}
	
	
	/*
	 * Anonyme Klasse zur Verarbeitung der erstellten Umfrageoptionen
	 */
	class CreateSurveyEntryCallback implements AsyncCallback<SurveyEntry>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void onSuccess(SurveyEntry result) {
		}
		
	}
	
	
	class CreateSurveyEntriesCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			
		}
		
	}

}