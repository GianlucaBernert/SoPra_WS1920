package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;


public class CinemaCard extends FlowPanel{
	
	Cinema cinemaToShow;
	FlowPanel main;
	
	CinemaCardView cinemaCardView;
//	CinemaCardEdit cinemaCardEdit;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("cinemacard");
		this.showCinemaCardView(cinemaToShow);
		cinemaCardView = new CinemaCardView(this, cinemaToShow);
		this.showCinemaCardView(cinemaToShow);
	}
	
	public CinemaCard(FlowPanel main, Cinema cinemaToShow) {
		this.main=main;
		this.cinemaToShow=cinemaToShow;
		}

	public void showCinemaCardEdit(Cinema cinemaToShow) {
		this.cinemaToShow=cinemaToShow;
		this.clear();
//		this.add(new CinemaCardEdit(this,cinemaToShow));
	}
		
	public void showCinemaCardView(Cinema cinemaToShow) {
		this.cinemaToShow=cinemaToShow;
		this.clear();
		this.add(new CinemaCardView(this,cinemaToShow));
	}
	public void remove() {
		main.remove(this);
	}
}
