package de.hdm.SoPra_WS1920.client.gui.Admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;

/**
 * Methode zur Darstellung einer CinemaChain auf einer Karte.
 * @author Sebastian
 *
 */
public class CinemaChainCardView extends FlowPanel{
	
	Label cinemaChainName;
	Label amountOfCinemas;
	Image editIcon;
	
	CinemaChain cinemaChainToShow;
	CinemaChainCard parentCard;
	CinemaAdministrationAsync cinemaAdministration;
	
	/**
	 * Konstruktor der CinemaChainCardView
	 * @param cinemaChainCard
	 * @param cinemaChainToShow
	 */
	public CinemaChainCardView(CinemaChainCard cinemaChainCard, CinemaChain cinemaChainToShow) {
		this.parentCard = cinemaChainCard;
		this.cinemaChainToShow = cinemaChainToShow;
	}
	
	/**
	 * on Load Methode die das Anzeigen der CinemaChain als Karte ermoeglicht.
	 */
	public void onLoad() {
		super.onLoad();
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
		cinemaChainName = new Label(cinemaChainToShow.getName());
		cinemaChainName.setStyleName("CardViewTitle");
		amountOfCinemas = new Label("");
		amountOfCinemas.setStyleName("CardViewSubTitle");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.addClickHandler(new EditClickHandler());
		editIcon.setStyleName("EditIcon");
		cinemaAdministration.getCinemasByCinemaChainFK(cinemaChainToShow, new CinemasByCinemaChainCallback());
		
		this.add(cinemaChainName);
		this.add(amountOfCinemas);
		this.add(editIcon);
	}
	
	/**
	 * Clickhandler zum Aufrufen der CinemaChainCardForm
	 */
	class EditClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showCinemaChainForm(cinemaChainToShow);
		}
		
	}
	
	/**
	 * Callback zum Aufrufen der Anzahl von Kinoobjekten die zur ausgewaehlten CinemaChain gehoeren
	 */
	class CinemasByCinemaChainCallback implements AsyncCallback<Vector<Cinema>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Connection");
		}

		@Override
		public void onSuccess(Vector<Cinema> result) {
			// TODO Auto-generated method stub
			amountOfCinemas.setText(Integer.toString(result.size())+" Cinemas");
		}

		
		
	}
}
