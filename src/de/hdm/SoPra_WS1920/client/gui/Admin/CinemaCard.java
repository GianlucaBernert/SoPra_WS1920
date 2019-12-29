package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;


public class CinemaCard extends FlowPanel{
	
	Cinema cinemaToShow;
	Content content;
	
	CinemaCardView cinemaCardView;
	CinemaCardEdit cinemaCardEdit;
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("Card CinemaCardView");
		this.showCinemaCardView(cinemaToShow);
		cinemaCardView = new CinemaCardView(this, cinemaToShow);
	}
	
	public CinemaCard(Content content, Cinema cinemaToShow) {
		this.content=content;
		this.cinemaToShow=cinemaToShow;
		}
	
	public CinemaCard() {
		
	}

	public void showCinemaCardEdit(Cinema cinemaToShow) {
		this.cinemaToShow=cinemaToShow;
		CinemaCardEdit cinemaCardEdit = new CinemaCardEdit(this,cinemaToShow);
		cinemaCardEdit.center();
		cinemaCardEdit.show();

//		this.add(new CinemaCardEdit(this,cinemaToShow));
	}
		
	public void showCinemaCardView(Cinema cinemaToShow) {
		this.cinemaToShow=cinemaToShow;
		this.clear();
		this.add(new CinemaCardView(this,cinemaToShow));
	}

	public void remove() {
		content.remove(this);
		
	}
}
