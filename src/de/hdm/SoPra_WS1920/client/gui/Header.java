package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class Header extends FlowPanel{
	Label headline;
	TextBox searchBox;
	Button searchSubmit;
	Button createBo;
	public void onLoad() {
		super.onLoad();
		//initialize the widget
		headline=new Label();
		//set style class for the headline -->Analog: searchBox & searchSubmit
		headline.setStyleName("headline");
		
		searchBox=new TextBox();
		searchBox.setStyleName("searchBox");
		
		searchSubmit= new Button("Search");
		searchSubmit.setStyleName("searchSubmit");
		
		createBo = new Button();
//		To be defined:
//		createBo.setStyleName("");
		
		//Add child widgets to this panel
		this.add(headline);
		this.add(searchBox);
		this.add(searchSubmit);
		this.add(createBo);
	}
	
	public void showCinemaHeader() {
		//Set the Header to Cinemas
		//add the respective clickhandler to the createBo button
		
	}
	
	public void showMovieHeader() {
		//Set the Header to Cinemas
		//add the respective clickhandler to the createBo button
	}
	
	public void showScreeningHeader() {
		//Set the Header to Cinemas
		//add the respective clickhandler to the createBo button
	}
}
