
package de.hdm.SoPra_WS1920.client.gui.Admin;

import java.sql.Date;
import java.sql.Time;
import java.util.TimeZone;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.DefaultSuggestionDisplay;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.mysql.cj.util.DataTypeUtil;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class ScreeningCardEdit extends DialogBox {
	
	FlowPanel formWrapper;
	ScreeningCard parentCard;
	Screening screeningToShow;
	Movie movieOfScreening;
	Cinema cinemaOfScreening;
	
	Label cardDescription;
	Image cancelIcon;
	Button invisibleButton;
	
	Label movieLabel;
	MultiWordSuggestOracle allMovies;
	SuggestBox movieSuggestBox;
	Label cinemaLabel;
	ListBox allCinemas;
	Label dateLabel;
	DateBox datePicker;
	Label timeLabel;
//	TextBox hourTimePicker;
//	TextBox minuteTimePicker;
	TimePicker timePicker;
	
	Button cancel;
	Label deleteLabel;
	Image deleteIcon;
	Button saveButton;
	
	Vector<Movie> listOfMovies;
	Vector<Cinema> listOfCinemas;
	
	Movie selectedMovie;
	Cinema selectedCinema;
	
	Header header;
	Content content;

	CinemaAdministrationAsync cinemaAdministration;
	
	public ScreeningCardEdit(ScreeningCard screeningCard, Screening screeningToShow, Movie movieOfScreening, Cinema cinemaOfScreening) {
		// TODO Auto-generated constructor stub
		this.parentCard=screeningCard;
		this.screeningToShow=screeningToShow;
		this.movieOfScreening=movieOfScreening;
		this.cinemaOfScreening=cinemaOfScreening;
	}
	
	public ScreeningCardEdit(Header header, Content content) {
		this.header = header;
		this.content = content;
		
		screeningToShow = new Screening();
		selectedMovie = new Movie();
		selectedCinema = new Cinema();

	}
	
	//Method for mapping the suggestbox items to the respective IDs
	

	public void onLoad() {
		super.onLoad();
		this.setStyleName("EditCard");
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
		
		
		
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("Add Screening");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		movieLabel = new Label("Movie");
		movieLabel.setStyleName("TextBoxLabel");
		allMovies = new MultiWordSuggestOracle();
		listOfMovies = new Vector<Movie>();
		cinemaAdministration.getAllMovies(new AllMoviesCallback());

		movieSuggestBox = new SuggestBox(allMovies);
		movieSuggestBox.setStyleName("CardSuggestBox");
		
		cinemaLabel = new Label("Cinema");
		cinemaLabel.setStyleName("TextBoxLabel");
		allCinemas = new ListBox();
//		allCinemas.setItemText(0, cinemaOfScreening.getName());
		allCinemas.setStyleName("CardListBox");
		listOfCinemas = new Vector<Cinema>();
		cinemaAdministration.getCinemasByPersonFK(Integer.parseInt(Cookies.getCookie("userId")),new CinemasOfPersonCallback());
		
		
		dateLabel = new Label("Date");
		dateLabel.setStyleName("TextBoxLabel");
		datePicker = new DateBox();
		datePicker.setValue(screeningToShow.getScreeningDate());
		datePicker.setStyleName("CardDatePicker");
		datePicker.setFormat(
				new DateBox.DefaultFormat(
						DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
		
//		datePicker.setValue(screeningToShow.getScreeningDate());
		timeLabel = new Label("Time");
		timeLabel.setStyleName("TextBoxLabel");
		timePicker = new TimePicker(this);
//		timePicker.hourPicker.setText(screeningToShow.getScreeningTime().toString().substring(0, 2));
//		timePicker.minutePicker.setText(screeningToShow.getScreeningTime().toString().substring(2, 4));

		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(movieLabel);
		formWrapper.add(movieSuggestBox);
		formWrapper.add(cinemaLabel);
		formWrapper.add(allCinemas);
		formWrapper.add(dateLabel);
		formWrapper.add(datePicker);
		formWrapper.add(timeLabel);
		formWrapper.add(timePicker);

		if(parentCard!=null) {
			cardDescription.setText("Edit Screening");
			movieSuggestBox.setText(movieOfScreening.getName());
			
			datePicker.setValue(screeningToShow.getScreeningDate());
			deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
			deleteIcon.setStyleName("DeleteIcon");
			deleteIcon.addClickHandler(new DeleteClickHandler(this));
			
			deleteLabel = new Label("Delete");
			deleteLabel.setStyleName("DeleteLabel");
			deleteLabel.addClickHandler(new DeleteClickHandler(this));
			formWrapper.add(deleteIcon);
			formWrapper.add(deleteLabel);
		}
		
		
		saveButton=new Button("Save");
		saveButton.setStyleName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler(this));
		
		
		
		formWrapper.add(saveButton);
		this.add(formWrapper);
	}
	
	class TimePicker extends FlowPanel{
		ScreeningCardEdit screeningCardEdit;
		SuggestBox hourPicker;
		SuggestBox minutePicker;
		Label colon;
		MultiWordSuggestOracle hourOptions;
		MultiWordSuggestOracle minuteOptions;
		
		public TimePicker(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit = screeningCardEdit;
		}

		@SuppressWarnings("deprecation")
		public void onLoad() {
			super.onLoad();
			this.setStyleName("CardTimePicker");
			
			hourOptions = new MultiWordSuggestOracle();
			   for(int i=0;i<24;i++) {
				   String s = "";
				   if(i<10) {
				   s = "0"+Integer.toString(i);
				   }else {
				   s = Integer.toString(i);   
				   }
				   hourOptions.add(s);
			 }
			   hourPicker = new SuggestBox(hourOptions);
			   hourPicker.setStyleName("TimePickerSuggestBox");
			   hourPicker.getElement().setPropertyString("placeholder", "hh");
			   hourPicker.addKeyPressHandler(new KeyPressHandler() {
				    @Override
				    public void onKeyPress(KeyPressEvent event) {
				        String input = hourPicker.getText();
				        if (input.matches("([0-9]*)")) {
				        	if (input.length()==1) {
				        		((DefaultSuggestionDisplay) hourPicker.getSuggestionDisplay()).hideSuggestions();
				            	minutePicker.setFocus(true);
				            return;
				        	}
				        }else {
				        	Window.alert("Please use the correct time format 'hh:mm'");
				        }
				    }
				});
			   
			   colon = new Label(":");
			   colon.setStyleName("TimePickerLabel");
			   
			 minuteOptions = new MultiWordSuggestOracle();
			   for(int i=0;i<60;i++) {
				   String s = "";
				   if(i<10) {
				   s = "0"+Integer.toString(i);
				   }else {
				   s = Integer.toString(i);   
				   }
				   minuteOptions.add(s);
			 }
			   minutePicker = new SuggestBox(minuteOptions);
			   minutePicker.setStyleName("TimePickerSuggestBox");
			   minutePicker.getElement().setPropertyString("placeholder", "mm");
			   
			   if(screeningCardEdit.parentCard!=null) {
				   Time t = screeningToShow.getScreeningTime();
//				   t.setHours(t.getHours()-1);
				   String s = DateTimeFormat.getFormat("HH:mm").format(t);
//				   hourPicker.setText(screeningToShow.getScreeningTime().toString().substring(0, 2));
//				   minutePicker.setText(screeningToShow.getScreeningTime().toString().substring(3, 5));
				   hourPicker.setText(s.substring(0, 2));
				   minutePicker.setText(s.substring(3, 5));
			   }
			   
			   this.add(hourPicker);
			   this.add(colon);
			   this.add(minutePicker);
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
				listOfMovies.add(m);
				allMovies.add(m.getName());
			}
		}
		
	}
	
	class CinemasOfPersonCallback implements AsyncCallback<Vector<Cinema>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Cinema> result) {
			// TODO Auto-generated method stub
			for(Cinema c: result) {
				listOfCinemas.add(c);
				allCinemas.addItem(c.getName());
			}
//			allCinemas.setItemText(0, cinemaOfScreening.getName());
		}

		
		
	}
	
	class SaveClickHandler implements ClickHandler{
		ScreeningCardEdit screeningCardEdit;
		public SaveClickHandler(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit = screeningCardEdit;
		}
		@Override
		public void onClick(ClickEvent event) {

			java.sql.Date dt = new java.sql.Date(datePicker.getValue().getTime());
			
			Time t =new Time(DateTimeFormat.getFormat("HH:mm").parse(timePicker.hourPicker.getText()+":"+timePicker.minutePicker.getText()).getTime());
			if(parentCard==null) {				
				cinemaAdministration.createScreening(
						dt, 
						t,
						screeningCardEdit.getSelectedCinema(allCinemas.getSelectedValue()), 
						screeningCardEdit.getSelectedMovie(movieSuggestBox.getText()), 
						Integer.parseInt(Cookies.getCookie("userId")), 
						new CreateScreeningCallback(screeningCardEdit));
			}else {
				screeningToShow.setCinemaFK(screeningCardEdit.getSelectedCinema(allCinemas.getSelectedValue()));
				screeningToShow.setMovieFK(screeningCardEdit.getSelectedMovie(movieSuggestBox.getText()));
//				screeningToShow.setScreeningDate((Date) datePicker.getValue()); --> Geht nicht!
				screeningToShow.setScreeningDate(dt);
				screeningToShow.setScreeningTime(t);
				
				cinemaAdministration.updateScreening(screeningToShow, new UpdateScreeningCallback(screeningCardEdit));
			}
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{
		ScreeningCardEdit screeningCardEdit;
		
		public DeleteClickHandler(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit = screeningCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			cinemaAdministration.deleteScreening(screeningToShow, new DeleteCallback(screeningCardEdit));
			screeningCardEdit.hide();
			parentCard.remove();
			
		}
		
	}
	
	class DeleteCallback implements AsyncCallback<Void>{
		ScreeningCardEdit screeningCardEdit;

		public DeleteCallback(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit = screeningCardEdit;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			screeningCardEdit.parentCard.remove();
		}
		
	}
	
	class CreateScreeningCallback implements AsyncCallback<Screening>{
		ScreeningCardEdit screeningCardEdit;
		public CreateScreeningCallback(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit=screeningCardEdit;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the connection.");
		}

		@Override
		public void onSuccess(Screening result) {
			// TODO Auto-generated method stub
//			Window.alert("In ScreeningCallback");
			parentCard = new ScreeningCard(content,result);
			parentCard.showScreeningCardView(result);
			content.add(parentCard);
			screeningCardEdit.hide();
		}
		
	}
	class UpdateScreeningCallback implements AsyncCallback<Screening>{
		ScreeningCardEdit screeningCardEdit;
		public UpdateScreeningCallback(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit=screeningCardEdit;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the connection.");
		}

		@Override
		public void onSuccess(Screening result) {
			// TODO Auto-generated method stub
			parentCard.showScreeningCardView(result);
			screeningCardEdit.hide();
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{
		ScreeningCardEdit screeningCardEdit;
		public CancelClickHandler(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit = screeningCardEdit;
		}

		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			if(parentCard==null) {
				screeningCardEdit.hide();
			}else {
				parentCard.showScreeningCardView(screeningToShow);
				screeningCardEdit.hide();
			}
			
		}
		
	}
	
	
	
	
	public int getSelectedCinema(String selectedCinema) {
		Cinema cinema = null;
		for(Cinema c: listOfCinemas) {
			if(c.getName().equals(selectedCinema)) {
				cinema=c;
			}
		} return cinema.getId();
	}
	
	public int getSelectedMovie(String selectedMovie) {
		Movie movie = null;
		for(Movie m: listOfMovies) {
			if(m.getName().equals(selectedMovie)) {
				movie=m;
			}
		} return movie.getId();
	}
}
