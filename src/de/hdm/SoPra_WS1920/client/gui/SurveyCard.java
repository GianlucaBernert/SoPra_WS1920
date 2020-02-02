package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

/**
 * 
 * @author Sebastian Hermann
 * Karte zur Anzeige(-Steuerung) von Surveys.
 * Diese Klasse steuert sowohl die Anzeige der SurveyView-Karte, als auch die Anzeige der SurveyEdit-Karte. Sie wird dem Survey-Content-Panel hinzugefügt.
 * Sie implementiert die Panel-Klasse FlowPanel damit die ViewKarte auf ihr abgelegt werden kann.
 */
public class SurveyCard extends FlowPanel {
	
	Survey surveyToShow;	//Umfrage die angezeigt werden soll
	SurveyContent surveyContent;	//SurveyContentPanel
	SurveyCardView surveyCardView;	//Subkarte SurveyCardView. Wird im weiteren Verlauf auf der Karte abgelegt.
	Movie movieOfSurvey;	//Film der zu einer Umfrage gehört. Wird benötigt, da die Referenz des Films im BO Survey ausschließlich über den FK gesetzt wird.
	
	/**
	 * Definition der onLoad-Methode. Wird aufgerufen sobald das Panel aufgerufen wird.
	 * die <code>super.onLoad()<code> Methode wird aufgerufen, damit zunächst alle Eigenschaften der vererbten Flow-Panel Klasse übernommen werden.
	 */
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("SurveyCard");	//Referenz zur CSS Klasse->Style der Survey-Karte
		this.showSurveyCardView(surveyToShow);	//Aufruf der showSurveyCardView Methode damit der View Modus einer Karte dieser Karte hinzugefügt werden kann.
//		surveyCardView = new SurveyCardView(this, surveyToShow);
	}
	
	/**
	 * Konstruktor
	 * @param surveyToShow
	 */
	public SurveyCard(Survey surveyToShow) {
		this.surveyToShow = surveyToShow;
	}
	
	/**
	 * Konstruktor der verwendet wird, wenn eine neue Umfrage hinzugefügt wird
	 * @param surveyContent
	 * @param surveyToShow
	 */
	public SurveyCard(SurveyContent surveyContent, Survey surveyToShow) {
		this.surveyContent = surveyContent;
		this.surveyToShow = surveyToShow;
	}
	
	/**
	 * Methode zum Hinzufuegen des View-Modus zu dieser Karte. Hierzu wird zunächst die alte Information der Karte gelöscht und 
	 * mit der neuen Information des übergebenen @param surveyToShow initialisiert.
	 * @param surveyToShow
	 */
	public void showSurveyCardView(Survey surveyToShow) {
		this.surveyToShow = surveyToShow;
		this.clear();
		this.add(new SurveyCardView(this, surveyToShow));
	}
	
	/**
	 * Beim Löschen einer Umfrage wird die Karte durch diesen Methodenaufruf vom SurveyContent entfernt
	 */
	public void remove() {
		surveyContent.remove(this);
	}
	
	/**
	 * Methode um den Film einer Umfrage zu erhalten
	 * @return movieOfSurvey
	 */
	public Movie getMovie() {
		return movieOfSurvey;
	}

	/**
	 * Methode um den Film einer Umfrage als Objekt zu setzen.
	 * @param movie
	 */
	public void setMovie(Movie movie) {
		this.movieOfSurvey = movie;
	}

}
