package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

//ViewMode of a MovieCard
public class MovieCardView extends FlowPanel{

	public MovieCardView(MovieCard movieCard, Movie movieToShow) {
		// TODO Auto-generated constructor stub
	}

	public void onLoad() {
		super.onLoad();
		Label l = new Label("editmode");
		this.add(l);
	}
}
