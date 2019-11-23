package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.view.client.ListDataProvider;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class CinemaMovieScreeningModel {
	Cinema cinemaToShow;
	Movie movieToShow;
	Screening screeningToShow;
	
	//CinemaForm: cinemaForm
	//MovieCard: movieForm
	//SreeningCard: screeningForm
	
	ListDataProvider<Cinema> cinemaProvider;
	ListDataProvider<Movie> movieProvider;
	ListDataProvider<Screening> screeningProvider;
	
	//To be declared: Proxy-object
	//BO-Key Provider to get the Key of the respective bo. !Carefull: We have to differenciate between the ids by manipulating the bo-Keys
	//SelectionModel: Define, which bo is selected, to efficiently update or remove the bo from the model
	
	public CinemaMovieScreeningModel() {
		//initialize BO-Key Provider
		//initialize SelectionModel
		//initialize Proxy
	}
	
}
