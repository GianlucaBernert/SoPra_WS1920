package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class ScreeningCardView extends FlowPanel{
		
	Label movie;
	Label cinema;
	Label date;
	Label time;
	Button edit;
	Image editIcon;
	
	Screening screeningToShow;
	Movie movieOfScreening;
	Cinema cinemaOfScreening;
	
	ScreeningCard parentCard;
	CinemaAdministrationAsync cinemaAdministration;
	public ScreeningCardView(ScreeningCard screeningCard, Screening screeningToShow) {
		// TODO Auto-generated constructor stub
		this.parentCard= screeningCard;
		this.screeningToShow = screeningToShow;
	}

	@SuppressWarnings("deprecation")
	public void onLoad() {
		super.onLoad();

		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
		cinemaAdministration.getCinemaById(screeningToShow.getCinemaFK(), new CinemaCallback());
		cinemaAdministration.getMovieById(screeningToShow.getMovieFK(), new MovieCallback());
		//movieOfScreening = cinemaAdminImpl.getMovieById(screening.getMovieFK)
		movie = new Label();//parameter would be movieOfScreening.getName();
		movie.setStyleName("CardViewTitle");
		//cinemaOfScreening= cinemaAdminImpl.getCinemaById(screening.getCinemaFK)
		cinema = new Label();//parameter would be cinemaOfScreening.getName();
		cinema.setStyleName("CardViewSubTitle");
		date = new Label(DateTimeFormat.getFormat("dd.MM.yyyy").format(screeningToShow.getScreeningDate()));
		date.setStyleName("CardViewParagraph");
		
		time = new Label(DateTimeFormat.getFormat("HH:mm").format(screeningToShow.getScreeningTime()));
		time.setStyleName("CardViewParagraph");
		
		edit=new Button("");
		edit.setStyleName("InvisibleButton");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.setStyleName("EditIcon");
		editIcon.addClickHandler(new EditClickHandler());
		
		this.add(movie);
		this.add(cinema);
		this.add(date);
		this.add(time);
		this.add(edit);
		this.add(editIcon);
	}
	
	class CinemaCallback implements AsyncCallback<Cinema>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Cinema result) {
			// TODO Auto-generated method stub
//			Window.alert(result.getName());
			cinemaOfScreening = result;
			cinema.setText(result.getName());
		}
		
	}
	
	class MovieCallback implements AsyncCallback<Movie>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Movie result) {
			// TODO Auto-generated method stub
			movieOfScreening = result;
			movie.setText(result.getName());
		}
		
	}
	
	class EditClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showScreeningCardEdit(screeningToShow, movieOfScreening, cinemaOfScreening);
		}
		
	}
}
