package de.hdm.SoPra_WS1920.client.gui.Admin;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

/**
 * Klasse, die den Content auf dem Rootpanel darstellt.
 * @author Sebastian Hermann
 */
public class Content extends FlowPanel{
	
	Cinema c;
	Screening s;
	Movie m;
	DatePicker dP;
	Header h;
	Button b;
	DateBox dB;
	TextBox textBox;
	SuggestBox box;
	Person person;
	
	CinemaAdministrationAsync cinemaAdministration;
	
	public Content() {
	}
	
	@SuppressWarnings("deprecation")
	public void onLoad() {
		super.onLoad();
		this.setStyleName("content");
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();	
		
	}

	/**
	 * Methode, die alle Cinemas anzeigt, die der User angelegt hat.
	 */
	public void showCinemas() {
		this.clear();
		cinemaAdministration.getCinemasByPersonFK(person.getId(), new GetCinemasByPersonCallback(this));
		
	}
	
	/**
	 * Callback, der alle Cinemas eines Users zurückgibt.
	 */
	class GetCinemasByPersonCallback implements AsyncCallback<Vector<Cinema>>{
		Content content;
		public GetCinemasByPersonCallback(Content content) {
			// TODO Auto-generated constructor stub
			this.content = content;
		}
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Callback!");
		}
		@Override
		public void onSuccess(Vector<Cinema> result) {
			// TODO Auto-generated method stub
			for(Cinema c: result) {
				CinemaCard cinemaCard = new CinemaCard(content,c);
				content.add(cinemaCard);
			}
			
		}

		
		
	}
	
	/**
	 * Methode, die alle Movies aus der Datenbank anzeigt.
	 */
	public void showMovies() {
		this.clear();
		cinemaAdministration.getAllMovies(new GetMoviesCallback(this));

	}
	
	/**
	 * Callback, um alle Movie Objekte aus der Datenbank abzurufen.
	 */
	class GetMoviesCallback implements AsyncCallback<Vector<Movie>>{
		Content content;
		public GetMoviesCallback(Content content) {
			// TODO Auto-generated constructor stub
			this.content = content;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Callback!");
		}

		@Override
		public void onSuccess(Vector<Movie> result) {
			// TODO Auto-generated method stub
			for(Movie m: result) {
				MovieCard movieCard = new MovieCard(content, m);
				content.add(movieCard);
			}
		}
		
	}

	/**
	 * Methode, die alle Screenings anzeigt, die der User angelegt hat.
	 */
	public void showScreenings() {
		this.clear();
		cinemaAdministration.getScreeningsByPersonFK(person.getId(), new GetScreeningsCallback(this));
	}
	
	
	/**
	 * Callback, der alle Screenings eines Users zurückgibt.
	 */
	class GetScreeningsCallback implements AsyncCallback<Vector<Screening>>{
		Content content;
		public GetScreeningsCallback(Content content) {
			// TODO Auto-generated constructor stub
			this.content=content;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Screening> result) {
			// TODO Auto-generated method stub
			for(Screening s:result) {
				ScreeningCard screeningCard = new ScreeningCard(content,s);
				content.add(screeningCard);
			}
		}
		
	}
//	public Time getTimeFromString(String s2) {
//		Time t;
//			try {
//			SimpleDateFormat sDF = new SimpleDateFormat("hh:mm");
//			t = (Time) sDF.parse(s2);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//			return t;
//	}
//	public Date getDateFromString(String dateString) {
//		    Date result;
//		    try {
//		        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
//		        result = (Date) dateTimeFormat.parse(dateString);
//		        Window.alert(dateString);
//		        return result;
//		    } catch (Exception e){
//		    	e.printStackTrace();
//		    	 Window.alert("Fail");
//		    	return null;
//		        // ignore
//		    }
//		 
//		}

	
	/**
	 * Methode, die alle CinemaChains anzeigt, die der User angelegt hat.
	 */
	public void showCinemaChains() {
		// TODO Auto-generated method stub
		this.clear();
		cinemaAdministration.getCinemaChainByPersonFK(person.getId(), new CinemaChainCallback(this));
	}
	
	/**
	 * Callback, der alle CinemaChains eines Users zurückgibt
	 */
	class CinemaChainCallback implements AsyncCallback<Vector<CinemaChain>>{
		Content content;
		public CinemaChainCallback(Content content) {
			this.content = content;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<CinemaChain> result) {
			for(CinemaChain cC: result) {
				CinemaChainCard cinemaChainCard = new CinemaChainCard(content,cC);
				content.add(cinemaChainCard);
			}
			
		}
	
	}
	
	/**
	 * Methode, die den Content der eingeloggten Person zuordnet.
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	
	/**
	 * Methode, die die Person des angezeigten Contents zurueckgibt.
	 * @param person
	 * @return person
	 */
	public Person getPerson() {
		return person;
	}
}
