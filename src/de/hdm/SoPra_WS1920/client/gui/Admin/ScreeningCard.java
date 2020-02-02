package de.hdm.SoPra_WS1920.client.gui.Admin;


import java.util.TimeZone;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

/*
 * @author Yesin Soufi
 * @author Sebastian Herrmann
 * In der Klasse ScreeningCard finden die Anordnungen der ScreeningCard, ScreeningCardEdit und ScreeningCardView
 * statt.
 * 
 */

public class ScreeningCard extends FlowPanel{
	Screening screeningToShow;
	Cinema cinemaOfScreening;
	Movie movieOfScreening;
	
	Content content;
	
	/*
	 * Konstruktor der ScreeningCard
	 * @param content
	 * @param screeningToShow
	 */
		
	public ScreeningCard(Content content,Screening screeningToShow) {
		this.content=content;
		this.screeningToShow = screeningToShow;
	}
	
	/*
	 * Default Konstruktor der ScreeningCard
	 */
	public ScreeningCard() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("Card ScreeningCardView");
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT+1:00"));
		this.showScreeningCardView(screeningToShow);
		
	}
	
	/*
	 * Methode um sich die ScreeningCardView anzeigen zu lassen
	 * @param screeningToShow
	 */
	
	public void showScreeningCardView(Screening screeningToShow) {
		this.screeningToShow=screeningToShow;
		this.clear();
		this.add(new ScreeningCardView(this, screeningToShow));
	}
	
	/*
	 * Methode um die showScreeningCardEdit aufzurufen
	 * @param screeningToShow
	 * @param movieOfScreening
	 * @param cinemaOfScreening
	 */
	
	public void showScreeningCardEdit(Screening screeningToShow, Movie movieOfScreening, Cinema cinemaOfScreening) {
		this.screeningToShow=screeningToShow;
		this.movieOfScreening=movieOfScreening;
		this.cinemaOfScreening=cinemaOfScreening;
		ScreeningCardEdit screeningCardEdit = new ScreeningCardEdit(this,screeningToShow,movieOfScreening,cinemaOfScreening);
		screeningCardEdit.center();
		screeningCardEdit.show();
//		this.add(new ScreeningCardEdit(this, screening));
	}
	
	/*
	 * Methode um die ScreeningCard zu entfernen
	 */

	public void remove() {
		// TODO Auto-generated method stub
		content.remove(this);
	}
}
