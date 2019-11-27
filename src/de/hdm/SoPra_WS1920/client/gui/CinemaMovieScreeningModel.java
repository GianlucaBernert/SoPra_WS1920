package de.hdm.SoPra_WS1920.client.gui;

import java.util.List;

import com.google.gwt.view.client.ListDataProvider;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class CinemaMovieScreeningModel {
	Cinema cinemaToShow;
	Movie movieToShow;
	Screening screeningToShow;
	
	//CinemaCard: cinemaForm
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
	
	public void addCinema(Cinema cinema) {
		cinemaProvider.getList().add(cinema);
	}
	
	public void addMovie(Movie movie) {
		movieProvider.getList().add(movie);
	}
	
	public void addScreening(Screening screening) {
		screeningProvider.getList().add(screening);
	}
	
	public void updateCinema(Cinema cinema) {
		List<Cinema> cinemaList = cinemaProvider.getList();
		int i = 0;
		for (Cinema c : cinemaList) {
			if (c.getId() == cinema.getId()) {
				cinemaList.set(i, cinema);
				break;
			} else {
				i++;
			}
		}
		cinemaProvider.refresh();
	}
	
	public void updateMovie(Movie movie) {
		List<Movie> movieList = movieProvider.getList();
		int i = 0;
		for (Movie m : movieList) {
			if (m.getId() == movie.getId()) {
				movieList.set(i, movie);
				break;
			} else {
				i++;
			}
		}
		movieProvider.refresh();
	}
	
	public void updateScreening(Screening screening) {
		List<Screening> screeningList = screeningProvider.getList();
		int i = 0;
		for (Screening s : screeningList) {
			if (s.getId() == screening.getId()) {
				screeningList.set(i, screening);
				break;
			} else {
				i++;
			}
		}
		screeningProvider.refresh();
	}
	
	public void removeCinema(Cinema cinema) {
		cinemaProvider.getList().remove(cinema);
	}
	
	public void removeMovie(Movie movie) {
		movieProvider.getList().remove(movie);
	}
	
	public void removeScreening(Screening screening) {
		screeningProvider.getList().remove(screening);
	}

}
