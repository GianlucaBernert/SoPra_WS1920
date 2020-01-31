package de.hdm.SoPra_WS1920.client.gui.Admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class Header extends FlowPanel{
	
	Label headline;
	Button createBo;
	SearchBox searchBox;
	Person person;

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
			
		}
		
	}
	
	class SearchBox extends FlowPanel{
		Header header;
		TextBox searchText;
		Button submitSearch;
		Image searchIcon;
		Image searchSubmitIcon;
		
		
		public SearchBox(Header header) {
			// TODO Auto-generated constructor stub
			this.header = header;
		}



		public void onLoad(){
			super.onLoad();
			this.setStyleName("SearchBox");
			
			searchText = new TextBox();
			searchText.setStyleName("SearchText");
			searchText.getElement().setPropertyString("placeholder", "Search...");
			searchText.addKeyPressHandler(new SearchKeyPressHandler(this,header));
			
			submitSearch = new Button();

			searchSubmitIcon = new Image("/Images/png/009-arrow-pointing-to-right-1.png");
			searchSubmitIcon.setStyleName("SearchSubmitIcon");
			
			searchIcon = new Image("/Images/png/003-search.png");
			searchIcon.setStyleName("SearchIcon");

			this.add(searchIcon);
			this.add(searchText);
			this.add(searchSubmitIcon);
			
			searchIcon.addClickHandler(new SearchClickHandler(this,header));
			searchSubmitIcon.addClickHandler(new SearchClickHandler(this,header));
			 	
			
		}	 	 
		
		public void searchWord(SearchBox searchBox, Header header) {
			if(header.headline.getText().equals("Movies")){
				cinemaAdministration.searchMovie(searchBox.searchText.getText(), new SearchMovieCallback());
			}else if(header.headline.getText().equals("Cinemas")){
				cinemaAdministration.searchCinema(1,searchBox.searchText.getText(), new SearchCinemaCallback());
			}else if(header.headline.getText().equals("Cinema Chains")){
		  				cinemaAdministration.searchCinemaChain(1,searchBox.searchText.getText(), new SearchCinemaChainCallback());
		  	}else if(header.headline.getText().equals("Screenings")){
		  				cinemaAdministration.searchScreening(1,searchBox.searchText.getText(), new SearchScreeningCallback());
		  	}
		}
		
		}
		class SearchKeyPressHandler implements KeyPressHandler{
			SearchBox searchBox;
			Header header;
			public SearchKeyPressHandler(SearchBox searchBox, Header header) {
				// TODO Auto-generated constructor stub
				this.searchBox = searchBox;
				this.header = header;
			}

			@Override
			public void onKeyPress(KeyPressEvent event) {
				// TODO Auto-generated method stub
				boolean enterPressed = KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode();
                if (enterPressed)
                {
                    searchBox.searchWord(searchBox, header);
                }
			}
			
		}
		
		class SearchClickHandler implements ClickHandler{
	 		Header header;
	 		SearchBox searchBox;
			public SearchClickHandler(SearchBox searchBox, Header header) {
				// TODO Auto-generated constructor stub
				this.searchBox = searchBox;
				this.header = header;
				
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				searchBox.searchWord(searchBox, header);
			}
		}
		class SearchMovieCallback implements AsyncCallback<Vector<Movie>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Movie> result) {
				if(result.size()==0) {
					Window.alert("No Search Results");
					content.clear();
					content.showMovies();
					searchBox.searchText.selectAll();
				}else {
					content.clear();
					for(Movie m: result) {
						MovieCard movieCard = new MovieCard(content, m);
						content.add(movieCard);
					}
				}
				
			}
			
		}
		
		class SearchCinemaCallback implements AsyncCallback<Vector<Cinema>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Cinema> result) {
				// TODO Auto-generated method stub
				if(result.size()==0) {
					Window.alert("No Search Results");
					content.clear();
					content.showCinemas();
					searchBox.searchText.selectAll();
				}else {
					content.clear();
					for(Cinema c: result) {
						CinemaCard cinemaCard = new CinemaCard(content, c);
						content.add(cinemaCard);
					}
				}
			}
			
		}
		
		class SearchCinemaChainCallback implements AsyncCallback<Vector<CinemaChain>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<CinemaChain> result) {
				// TODO Auto-generated method stub
				if(result.size()==0) {
					Window.alert("No Search Results");
					content.clear();
					content.showCinemaChains();
					searchBox.searchText.selectAll();
				}else {
					content.clear();
					for(CinemaChain cH: result) {
						CinemaChainCard cinemaChainCard = new CinemaChainCard(content, cH);
						content.add(cinemaChainCard);
					}
				}
			}
			
		}
		
		class SearchScreeningCallback implements AsyncCallback<Vector<Screening>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Screening> result) {
				// TODO Auto-generated method stub
				if(result.size()==0) {
					Window.alert("No Search Results");
					content.clear();
					content.showScreenings();
					searchBox.searchText.selectAll();
				}else {
					content.clear();
					for(Screening s: result) {
						ScreeningCard screeningCard = new ScreeningCard(content, s);
						content.add(screeningCard);
					}
				}
			}
			
		}
		
		
			
	
	public void showCinemaHeader() {
		//Set the Header to Cinemas
		this.clear();
		headline=new Label("Cinemas");
		headline.setStyleName("Headline");
		
		searchBox = new SearchBox(this);
		
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
		
		searchBox = new SearchBox(this);
		
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
		
		searchBox = new SearchBox(this);
		
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
		
		searchBox = new SearchBox(this);
		
		createBo = new Button("+Add CinemaChain");
		createBo.setStyleName("CreateBoButton");
		createBo.addClickHandler(new CreateCinemaChainClickHandler(this,content));
		
		this.add(headline);
		this.add(createBo);
		this.add(searchBox);
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}
}
