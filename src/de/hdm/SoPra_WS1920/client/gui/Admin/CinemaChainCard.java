package de.hdm.SoPra_WS1920.client.gui.Admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;

public class CinemaChainCard extends FlowPanel {
	
	CinemaChain cinemaChainToShow;
	Content content;
	
	
	
	CinemaCardEdit cinemaCardEdit;
	
	CinemaAdministrationAsync cinemaAdministration;
	
	public CinemaChainCard(Content content, CinemaChain cinemaChainToShow) {
		this.content = content;
		this.cinemaChainToShow = cinemaChainToShow;
	}
	
	public CinemaChainCard() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("Card CinemaCardView");
		
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
		this.showCinemaChainCardView(cinemaChainToShow);
		
	}
	
	public void showCinemaChainCardView(CinemaChain cinemaChain) {
		this.cinemaChainToShow = cinemaChain;
		this.clear();
		CinemaChainCardView cinemaChainCardView = new CinemaChainCardView(this,cinemaChainToShow);
		this.add(cinemaChainCardView);
		
		
	}
	
	public void showCinemaChainForm(CinemaChain cinemaChain) {
		this.cinemaChainToShow = cinemaChain;
		CinemaChainForm cinemaChainForm = new CinemaChainForm(this, cinemaChainToShow);
		cinemaChainForm.center();
		cinemaChainForm.show();
	}
	
	public void remove() {
		content.remove(this);
	}
	
}
