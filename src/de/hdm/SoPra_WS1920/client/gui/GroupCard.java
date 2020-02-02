package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import de.hdm.SoPra_WS1920.shared.bo.Group;

/**
 * @author SebastianHermann
 * Klasse, die eine Karte zum Anzeigen einer Gruppe erzeugt
 */
public class GroupCard extends FlowPanel {
	
	/**
	 *  Variablen der Klasse GroupCard
	 */
	Group groupToShow;
	SurveyContent content;
	
	GroupCardView groupCardView;
	GroupForm groupGroupForm;
	
	
	public void onLoad() {
		super.onLoad();
	
		this.setStylePrimaryName("Card CinemaCardView");
		this.showGroupCardView(groupToShow);
		
	}
	
	/**
	 * Konstruktor der GroupCard
	 * @param content
	 * @param groupToShow
	 */
	public GroupCard(SurveyContent content, Group groupToShow) {
		this.content = content;
		this.groupToShow = groupToShow;
	}
	
	/**
	 * Konstruktor der GroupCard
	 */
	public GroupCard() {
		
	}
	
	/**
	 * Methode um die GroupCardView aufzurufen
	 * @param groupToShow
	 */
	public void showGroupCardView(Group groupToShow) {
		this.groupToShow = groupToShow;
		this.clear();
		this.add(new GroupCardView(this,groupToShow));
	}
	
	/**
	 * Methode um die GroupCardForm aufzurufen
	 * @param groupToShow
	 */
	public void showGroupCardForm(Group groupToShow) {
		this.groupToShow = groupToShow;
		GroupForm groupForm = new GroupForm(this,groupToShow);
		groupForm.center();
		groupForm.show();
	}
	
	/**
	 * Methode um die GroupCard zu entfernen
	 */
	public void remove() {
		content.remove(this);
	}
	

}
