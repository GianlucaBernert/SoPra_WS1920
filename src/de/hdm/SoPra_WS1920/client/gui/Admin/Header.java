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

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;

public class Header extends FlowPanel{
	
	Label headline;
	Button createBo;
	SearchBox searchBox;
	PopupPanel pPanel;

	Content content;
	CinemaAdministrationAsync cinemaAdministration;
	
	
	public Header(Content content) {
		// TODO Auto-generated constructor stub
		this.content=content;
		
	}

	public void onLoad() {
		super.onLoad();
		this.setStyleName("Header");
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
//		vPanel = new VerticalPanel();
//		cinema = new Label("New Cinema");
//		cinemaChain = new Label("New CinemaChain");
//		vPanel.add(cinema);
//		vPanel.add(cinemaChain);
//		
//		this.add(pPanel);
	}
	
	class CreateCinemaChainClickHandler implements ClickHandler{
		Header header;
		Content content;
		public CreateCinemaChainClickHandler(Header header, Content content) {
			this.header = header;
			this.content = content;
		}

		@Override
		public void onClick(ClickEvent event) {
			
//			CinemaChainCardEdit cinemaChainCardEdit = new CinemaChainCardEdit(header, content);
//			cinemaCardEdit.center();
//			cinemaCardEdit.show();
//			CreateBoDialogbox createBoDialogbox = new CreateBoDialogbox(header, content);
//			createBoDialogbox.showMovieCardEdit();
//			createBoDialogbox.center();
//			createBoDialogbox.show();
			CinemaChainForm cinemaChainForm = new CinemaChainForm(header, content);
			cinemaChainForm.center();
			cinemaChainForm.show();
			
		}
		
	}
	
	class CreateCinemaClickHandler implements ClickHandler{
		Header header;
		Content content;
		public CreateCinemaClickHandler(Header header, Content content) {
			this.header = header;
			this.content = content;
		}

		@Override
		public void onClick(ClickEvent event) {
			
			CinemaCardEdit cinemaCardEdit = new CinemaCardEdit(header, content);
			cinemaCardEdit.center();
			cinemaCardEdit.show();
//			CreateBoDialogbox createBoDialogbox = new CreateBoDialogbox(header, content);
//			createBoDialogbox.showMovieCardEdit();
//			createBoDialogbox.center();
//			createBoDialogbox.show();
			
		}
		
	}
	
	class CreateMovieClickHandler implements ClickHandler{
		Header header;
		Content content;
		public CreateMovieClickHandler(Header header, Content content) {
			this.header = header;
			this.content = content;
		}

		@Override
		public void onClick(ClickEvent event) {
			MovieCardEdit movieCardEdit = new MovieCardEdit(header, content);
			movieCardEdit.center();
			movieCardEdit.show();
//			CreateBoDialogbox createBoDialogbox = new CreateBoDialogbox(header, content);
//			createBoDialogbox.showMovieCardEdit();
//			createBoDialogbox.center();
//			createBoDialogbox.show();
			
		}
		
	}
	
	class CreateScreeningClickHandler implements ClickHandler{
		Header header;
		Content content;
		public CreateScreeningClickHandler(Header header, Content content) {
			this.header = header;
			this.content = content;
		}

		@Override
		public void onClick(ClickEvent event) {
			ScreeningCardEdit screeningCardEdit = new ScreeningCardEdit(header, content);
			screeningCardEdit.center();
			screeningCardEdit.show();
//			CreateBoDialogbox createBoDialogbox = new CreateBoDialogbox(header, content);
//			createBoDialogbox.showMovieCardEdit();
//			createBoDialogbox.center();
//			createBoDialogbox.show();
			
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
		createBo.addClickHandler(new CreateCinemaClickHandler(this,content));
		
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
		createBo.addClickHandler(new CreateMovieClickHandler(this,content));
		
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
		createBo.addClickHandler(new CreateScreeningClickHandler(this,content));
		
		this.add(headline);
		this.add(createBo);
		this.add(searchBox);
		//add the respective clickhandler to the createBo button
	}

	public void showCinemaChainHeader() {
		// TODO Auto-generated method stub
		this.clear();
		headline=new Label("Cinema Chains");
		headline.setStyleName("Headline");
		
		searchBox = new SearchBox();
		
		createBo = new Button("+Add CinemaChain");
		createBo.setStyleName("CreateBoButton");
		createBo.addClickHandler(new CreateCinemaChainClickHandler(this,content));
		
		this.add(headline);
		this.add(createBo);
		this.add(searchBox);
	}
}
