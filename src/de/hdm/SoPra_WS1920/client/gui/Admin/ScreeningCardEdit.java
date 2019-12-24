package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.datepicker.client.DateBox;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class ScreeningCardEdit extends FlowPanel {
	Label movie;
	MultiWordSuggestOracle allMovies;
	SuggestBox movieSuggestBox;
	Label cinema;
	ListBox allCinemas;
	Label date;
	DateBox datePicker;
	Label time;
	
	Button save;
	Button cancel;
	Button delete;
	
	Image saveIcon;
	Image cancelIcon;
	Image deleteIcon;
	
	Screening screeningToShow;
	Movie movieOfScreening;
	Cinema cinemaOfScreening;
	
	ScreeningCard parentCard;
	
	public ScreeningCardEdit(ScreeningCard screeningCard, Screening screening) {
		// TODO Auto-generated constructor stub
	}

	public void onLoad() {
		super.onLoad();
		
		
		
		save=new Button("");
		save.setStyleName("InvisibleButton");
		cancel=new Button("");
		cancel.setStyleName("InvisibleButton");
		delete=new Button("");
		delete.setStyleName("InvisibleButton");

//		saveIcon = new Image("/Images/002-checked.svg");
//		saveIcon.setStyleName("saveIcon");
		saveIcon.addClickHandler(new SaveClickHandler());
		
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("cancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler());
		
		deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
		deleteIcon.setStyleName("deleteIcon");
		deleteIcon.addClickHandler(new DeleteClickHandler());
		
		this.add(save);
		this.add(cancel);
		this.add(delete);
		this.add(saveIcon);
		this.add(cancelIcon);
		this.add(deleteIcon);
	}
	
	class SaveClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
