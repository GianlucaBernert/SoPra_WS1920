package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CinemaAdminEntry implements EntryPoint{
	
	FlowPanel sidenav;
	FlowPanel main;
	//To be defined: Header header;
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		sidenav = new FlowPanel();
		main = new FlowPanel();
		
		sidenav.setStyleName("sidenav");
		main.setStyleName("main");
		
		Label cinema = new Label("Cinema");	//Menu Item 1
		Label movie = new Label("Movie"); //Menu Item 2
		Label screenings = new Label("Screening"); //Menu Item 3
		
		sidenav.add(cinema); //Add Item 1 to Menu
		sidenav.add(movie);	 //Add Item 2 to Menu
		sidenav.add(screenings); //Add Item 3 to Menu
		
		RootPanel.get().add(sidenav); //Add the Menu to the RootPanel
		RootPanel.get().add(main); //Add the (main-)content to the RootPanel
	}
}
