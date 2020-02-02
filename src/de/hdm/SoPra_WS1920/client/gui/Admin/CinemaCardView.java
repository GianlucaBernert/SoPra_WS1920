package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;


import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;

/**
 * Klasse, die den INhalt einer CinemaCard zum Anzeigen eines Cinemas erzeugt
 * @author Sebastian Hermann
 */
public class CinemaCardView extends FlowPanel{

	Label name;
	Label cinemaChain;
	Label street;
	Label streetNr;
	Label zipCode;
	Label city;
	Button edit;
	Cinema cinemaToShow;
	CinemaCard parentCard;
	Image editIcon;
	FlowPanel streetRow;
	FlowPanel cityRow;
	
	CinemaAdministrationAsync cinemaAdministration;
	
	public CinemaCardView(CinemaCard parentCard, Cinema cinemaToShow) {
		this.parentCard=parentCard;
		this.cinemaToShow=cinemaToShow;
	}

	public void onLoad() {
		super.onLoad();
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
		cinemaAdministration.getCinemaChainById(cinemaToShow.getCinemaChainFK(), new GetCinemaChainCallback());
		
//		this.setStyleName("view");
		streetRow = new FlowPanel();
		streetRow.setStyleName("streetrow-inline");
		cityRow = new FlowPanel();
		cityRow.setStyleName("cityrow-inline");
		
		name = new Label(cinemaToShow.getName());
		name.setStyleName("CardViewTitle");
		cinemaChain = new Label("");
		cinemaChain.setStyleName("CardViewSubTitle");
		street = new Label(cinemaToShow.getStreet());
		street.setStyleName("CardViewParagraph street");
		streetNr = new Label(cinemaToShow.getStreetNo());
		streetNr.setStyleName("CardViewParagraph street");
		zipCode = new Label(cinemaToShow.getZipCode());
		zipCode.setStyleName("CardViewParagraph city");
		city = new Label(cinemaToShow.getCity());
		city.setStyleName("CardViewParagraph city");
		
		edit=new Button("");
		edit.setStyleName("InvisibleButton");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.setStyleName("EditIcon");
		editIcon.addClickHandler(new EditClickHandler());
		
		streetRow.add(street);
		streetRow.add(streetNr);
		cityRow.add(zipCode);
		cityRow.add(city);
		this.add(name);
		this.add(cinemaChain);
		this.add(streetRow);		
		this.add(cityRow);
		this.add(edit); //Without this, the clickable icon doesn't work
		this.add(editIcon);
		
	}
	
	/**
	 * Callback, der die CinemaChain des ausgewählten Kinos zurückgibt.
	 */
	class GetCinemaChainCallback implements AsyncCallback<CinemaChain>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the connection");
		}

		@Override
		public void onSuccess(CinemaChain result) {
			// TODO Auto-generated method stub
			cinemaChain.setText(result.getName());
		}

		
		
	}
	
	/**
	 * Clickhandler, der die CinemaEditCard zum bearbeiten von CInemas aufruft.
	 */
	class EditClickHandler implements ClickHandler{	

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showCinemaCardEdit(cinemaToShow);
		}
		
	}
}
