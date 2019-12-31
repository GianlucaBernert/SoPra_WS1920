
package de.hdm.SoPra_WS1920.client.gui.Admin;

import java.sql.Date;
import java.sql.Time;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
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
	
	Header header;
	Content content;

	public ScreeningCardEdit(ScreeningCard screeningCard, Screening screeningToShow) {
		// TODO Auto-generated constructor stub
		this.parentCard=screeningCard;
		this.screeningToShow=screeningToShow;
	}
	
	public ScreeningCardEdit(Header header, Content content) {
		this.header = header;
		this.content = content;
		
		Screening s = new Screening();
		s.setCinemaFK(0);
		s.setMovieFK(0);
		s.setPersonFK(0);
		s.setScreeningDate(DateTimeFormat.getFormat("dd.MM.yyyy").parse("30.12.2019"));
		Time t = new Time(DateTimeFormat.getFormat("hh:mm").parse("02:30").getTime());
		s.setScreeningTime(t);
		screeningToShow = s;
		
	}
	
	//Method for mapping the suggestbox items to the respective IDs
	

	public void onLoad() {
		super.onLoad();
		this.setStyleName("EditCard");
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
		allMovies.add("Joker");
		
		movieSuggestBox = new SuggestBox(allMovies);
		movieSuggestBox.setStyleName("CardSuggestBox");
		
		
		
		cinemaLabel = new Label("Cinema");
		cinemaLabel.setStyleName("TextBoxLabel");
		allCinemas = new ListBox();
		allCinemas.setStyleName("CardListBox");
		
		dateLabel = new Label("Date");
		dateLabel.setStyleName("TextBoxLabel");
		datePicker = new DateBox();
		datePicker.setStyleName("CardDatePicker");
		datePicker.setFormat(
				new DateBox.DefaultFormat(
						DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
		
		timeLabel = new Label("Time");
		timeLabel.setStyleName("TextBoxLabel");
//		hourTimePicker = new TextBox();
//		hourTimePicker.setStyleName("CardTimePicker");
//		minuteTimePicker = new TextBox();
//		minuteTimePicker.setStyleName("CardTimePicker");
		timePicker = new TimePicker();
		
		movieSuggestBox.setText("Joker");
		datePicker.setValue(screeningToShow.getScreeningDate());
//		hourTimePicker.setText(screeningToShow.getScreeningTime().toString().substring(0, 2));
//		minuteTimePicker.setText(screeningToShow.getScreeningTime().toString().substring(3, 5));
//		
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
//		formWrapper.add(hourTimePicker);
//		formWrapper.add(minuteTimePicker);
		
		
		if(parentCard!=null) {
			cardDescription.setText("Edit Screening");
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
	
	class SaveClickHandler implements ClickHandler{
		ScreeningCardEdit screeningCardEdit;
		public SaveClickHandler(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit = screeningCardEdit;
		}
		@Override
		public void onClick(ClickEvent event) {
			if(timePicker.hourPicker.getText().length()!=2 || timePicker.minutePicker.getText().length()!=2) {
				Window.alert("Please type in a correct time in the format hh:mm");
			}
			screeningToShow.setCinemaFK(0);
			screeningToShow.setMovieFK(0);
			screeningToShow.setScreeningDate(datePicker.getValue());
			Time t = new Time(DateTimeFormat.getFormat("hh:mm").parse(timePicker.hourPicker.getText()+":"+timePicker.minutePicker.getText()).getTime());
			screeningToShow.setScreeningTime(t);
			// TODO Auto-generated method stub
			//screeningToShow.setMovieFK(proxy.findMovieByName(movieSuggestBox.getText()));
			//screeningToShow.setCinemaFK(proxy.findCinemaByName(allCinemas.getText()));
			//screeningToShow.setDate(datePicker.getDate());
			//screeningToShow.setTime(timePicker.getText());
			if(parentCard==null) {
				parentCard = new ScreeningCard(content,screeningToShow);
				parentCard.showScreeningCardView(screeningToShow);
				content.add(parentCard);
				screeningCardEdit.hide();
			}else {
				parentCard.showScreeningCardView(screeningToShow);
				screeningCardEdit.hide();
			}
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
	
	class DeleteClickHandler implements ClickHandler{
		ScreeningCardEdit screeningCardEdit;
		
		public DeleteClickHandler(ScreeningCardEdit screeningCardEdit) {
			// TODO Auto-generated constructor stub
			this.screeningCardEdit = screeningCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			screeningCardEdit.hide();
			parentCard.remove();
			
		}
		
	}
	
	class TimePicker extends FlowPanel{
		SuggestBox hourPicker;
		SuggestBox minutePicker;
		Label colon;
		
		public void onLoad() {
			super.onLoad();
			this.setStyleName("CardTimePicker");
			
			MultiWordSuggestOracle hourOptions = new MultiWordSuggestOracle();
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
//				        if (!input.matches("[0-9]*")) {
				        if (input.length()==1) {
//				            Window.alert("Only Integers allowed. Fomat hh");
				        	((DefaultSuggestionDisplay) hourPicker.getSuggestionDisplay()).hideSuggestions();
				            minutePicker.setFocus(true);
				            return;
				        }
				    }
				});
			   
			   colon = new Label(":");
			   colon.setStyleName("TimePickerLabel");
			   
			 MultiWordSuggestOracle minuteOptions = new MultiWordSuggestOracle();
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
			   
			   this.add(hourPicker);
			   this.add(colon);
			   this.add(minutePicker);
		}
	}
}
