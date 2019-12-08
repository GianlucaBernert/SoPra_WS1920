package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class Header extends FlowPanel{
	
	Label headline;
	Button createBo;
	SearchBox searchBox;
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("header");

		headline=new Label("Movies");
		headline.setStyleName("headline");
		
		searchBox = new SearchBox();
		
		createBo = new Button("+Add Movie");
		createBo.setStyleName("createBoButton");
		createBo.addClickHandler(new CreateBoClickHandler(this));
		
		this.add(headline);
		this.add(createBo);
		this.add(searchBox);

	}
	
	class CreateBoClickHandler implements ClickHandler{

		public CreateBoClickHandler(Header header) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			
			
		}
		
	}
	
	class SearchBox extends FlowPanel{
		TextBox searchText;
		Button submitSearch;
		Image searchIcon;
		
		public void onLoad(){
			super.onLoad();
			this.setStyleName("searchBox");
			
			searchText = new TextBox();
			searchText.setStyleName("searchText");
			searchText.getElement().setPropertyString("placeholder", "Search...");
			
			submitSearch = new Button();

			searchIcon = new Image("/Images/search.png");
			searchIcon.setStyleName("searchIcon");

			this.add(searchIcon);
			this.add(searchText);
			
		}
	}
	
	public void showCinemaHeader() {
		//Set the Header to Cinemas
		headline.setText("Cinemas");
		createBo.setText("+Add Cinema");
		//add the respective clickhandler to the createBo button
		
	}
	
	public void showMovieHeader() {
		//Set the Header to Movies
		headline.setText("Movies");
		createBo.setText("+Add Movie");
		//add the respective clickhandler to the createBo button
	}
	
	public void showScreeningHeader() {
		//Set the Header to Screenings
		headline.setText("Screenings");
		createBo.setText("+Add Screening");
		//add the respective clickhandler to the createBo button
	}
}
