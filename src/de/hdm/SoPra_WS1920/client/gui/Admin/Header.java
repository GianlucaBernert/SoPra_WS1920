package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Header extends FlowPanel{
	
	Label headline;
	Button createBo;
	SearchBox searchBox;
	PopupPanel pPanel;

	Content content;
	
	public Header(Content content) {
		// TODO Auto-generated constructor stub
		this.content=content;
		
	}

	public void onLoad() {
		super.onLoad();
		this.setStyleName("Header");
//		vPanel = new VerticalPanel();
//		cinema = new Label("New Cinema");
//		cinemaChain = new Label("New CinemaChain");
//		vPanel.add(cinema);
//		vPanel.add(cinemaChain);
//		
//		this.add(pPanel);
	}
	
	class CreateBoClickHandler implements ClickHandler{
		Header header;
		Content content;
		public CreateBoClickHandler(Header header, Content content) {
			this.header = header;
			this.content = content;
		}

		@Override
		public void onClick(ClickEvent event) {
			CreateBoDialogbox createBoDialogbox = new CreateBoDialogbox(header, content);
			createBoDialogbox.showMovieCardEdit();
			createBoDialogbox.center();
			createBoDialogbox.show();
			
		}
		
	}
	
	class SearchBox extends FlowPanel{
		TextBox searchText;
		Button submitSearch;
		Image searchIcon;
		Image searchSubmitIcon;
		
		public void onLoad(){
			super.onLoad();
			this.setStyleName("SearchBox");
			
			searchText = new TextBox();
			searchText.setStyleName("SearchText");
			searchText.getElement().setPropertyString("placeholder", "Search...");
			
			submitSearch = new Button();

			searchSubmitIcon = new Image("/Images/png/009-arrow-pointing-to-right-1.png");
			searchSubmitIcon.setStyleName("SearchSubmitIcon");
			
			searchIcon = new Image("/Images/png/003-search.png");
			searchIcon.setStyleName("SearchIcon");

			this.add(searchIcon);
			this.add(searchText);
			this.add(searchSubmitIcon);
			
		}
	}
	
	public void showCinemaHeader() {
		//Set the Header to Cinemas
		this.clear();
		headline=new Label("Cinemas");
		headline.setStyleName("Headline");
		
		searchBox = new SearchBox();
		
		createBo = new Button("+Add Cinema");
		createBo.setStyleName("CreateBoButton");
		createBo.addClickHandler(new CreateBoClickHandler(this,content));
		
		this.add(headline);
		this.add(createBo);
		this.add(searchBox);
		//add the respective clickhandler to the createBo button
		
	}
	
	public void showMovieHeader() {
		//Set the Header to Movies
		//add the respective clickhandler to the createBo button
		this.clear();
		headline=new Label("Movies");
		headline.setStyleName("Headline");
		
		searchBox = new SearchBox();
		
		createBo = new Button("+Add Movie");
		createBo.setStyleName("CreateBoButton");
		createBo.addClickHandler(new CreateBoClickHandler(this,content));
		
		this.add(headline);
		this.add(createBo);
		this.add(searchBox);
	}
	
	public void showScreeningHeader() {
		//Set the Header to Screenings
		this.clear();
		headline=new Label("Screenings");
		headline.setStyleName("Headline");
		
		searchBox = new SearchBox();
		
		createBo = new Button("+Add Screening");
		createBo.setStyleName("CreateBoButton");
		createBo.addClickHandler(new CreateBoClickHandler(this,content));
		
		this.add(headline);
		this.add(createBo);
		this.add(searchBox);
		//add the respective clickhandler to the createBo button
	}
}
