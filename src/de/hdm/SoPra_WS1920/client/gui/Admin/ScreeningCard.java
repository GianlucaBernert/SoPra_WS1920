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
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("Card");
		this.showScreeningCardView(screeningToShow);
		
	}
	
	public void showScreeningCardView(Screening screening) {
		this.screeningToShow=screening;
		this.clear();
		this.add(new ScreeningCardView(this, screening));
	}
	
	public void showScreeningCardEdit(Screening screening) {
		this.screeningToShow=screening;
		this.clear();
		this.add(new ScreeningCardEdit(this, screening));
	}
}
