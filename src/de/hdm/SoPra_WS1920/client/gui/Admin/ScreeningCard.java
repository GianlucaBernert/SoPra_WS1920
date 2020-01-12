package de.hdm.SoPra_WS1920.client.gui.Admin;


import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class ScreeningCard extends FlowPanel{
	Screening screeningToShow;
	Cinema cinemaOfScreening;
	Movie movieOfScreening;
	
	Content content;
		
	public ScreeningCard(Content content,Screening screeningToShow) {
		this.content=content;
		this.screeningToShow = screeningToShow;
	}
	public ScreeningCard() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("Card ScreeningCardView");
		this.showScreeningCardView(screeningToShow);
		
	}
	
	public void showScreeningCardView(Screening screeningToShow) {
		this.screeningToShow=screeningToShow;
		this.clear();
		this.add(new ScreeningCardView(this, screeningToShow));
	}
	
	public void showScreeningCardEdit(Screening screeningToShow, Movie movieOfScreening, Cinema cinemaOfScreening) {
		this.screeningToShow=screeningToShow;
		this.movieOfScreening=movieOfScreening;
		this.cinemaOfScreening=cinemaOfScreening;
		ScreeningCardEdit screeningCardEdit = new ScreeningCardEdit(this,screeningToShow,movieOfScreening,cinemaOfScreening);
		screeningCardEdit.center();
		screeningCardEdit.show();
//		this.add(new ScreeningCardEdit(this, screening));
	}

	public void remove() {
		// TODO Auto-generated method stub
		content.remove(this);
	}
}
