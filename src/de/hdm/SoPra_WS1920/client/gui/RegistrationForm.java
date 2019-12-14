package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RegistrationForm extends VerticalPanel {
	
	/**
	 * Die Klasse <code>RegistrationForm</code> ist eine Form zur Registrierung des Nutzers im CinemaSurveySystem.
	 * 
	 * @author Yesin Soufi
	 */
	HorizontalPanel formHeaderPanel = new HorizontalPanel();
	HorizontalPanel bottomButtonPanel = new HorizontalPanel();
	
	Label infoTitleLable = new Label("Herzlich Willkommen zu Popcorn");
	Label lastname = new Label("Name:");
	Label firstname = new Label("Vorname:");
	
	TextBox lastnameTextBox = new TextBox();
	TextBox firstnameTextBox = new TextBox();
	
	Button registerButton = new Button("Registrieren");
	Button cancelButton = new Button("Abbrechen");
	
	Grid grid = new Grid (2,2);
	
	
	public void onLoad() {
		
		this.setStylePrimaryName("moviecard");
		formHeaderPanel.add(infoTitleLable);
		this.add(formHeaderPanel);
		
		this.setWidth("80%");
		grid.setCellSpacing(25);
		
		grid.setWidget(0, 0, firstname);
		grid.setWidget(0, 1, firstnameTextBox);
		grid.setWidget(1, 0, lastname);
		grid.setWidget(1, 1, lastnameTextBox);
		

		cancelButton.setPixelSize(130, 40);
		infoTitleLable.setStylePrimaryName("infoTitleLable");
		lastname.setStylePrimaryName("registerLabel");
		firstname.setStylePrimaryName("registerLabel");
		cancelButton.setStylePrimaryName("searchSubmit");
		registerButton.setStylePrimaryName("searchSubmit");
		registerButton.setPixelSize(130, 40);
		bottomButtonPanel.setSpacing(20);
		bottomButtonPanel.add(registerButton);
		bottomButtonPanel.add(cancelButton);
		
		
		
		
		this.add(grid);
		this.add(bottomButtonPanel);
		
	}
}
