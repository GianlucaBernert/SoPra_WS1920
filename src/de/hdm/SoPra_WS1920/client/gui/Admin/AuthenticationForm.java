package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;


public class AuthenticationForm extends DialogBox{


	FlowPanel formWrapper;
/**
 * Die <code>AuthenticationForm</code>-Klasse ist zuständig für
 * die Zugangsberechtigungsüberprüfung der User in das Cinema Survey
 * System. 
 * 
 * @author David Flattich
 */

	private Label welcomeLabel = new Label("Bitte melde dich mit deinem Google Konto an!");
	private Button googleBtn = new Button();
	private Image imgGoogle = new Image("/images/googleLogo.png");
	private Image popcornsLogo = new Image("/Images/png/clapperboard.png");
	private String loginURL;
	private Label logoText;
	
	public AuthenticationForm() {
	}
	
	/**
	 * Die Login-Url wird im Konstruktor gesetzt
	 * 
	 * @param loginURL
	 */
	public AuthenticationForm(String loginURL) {
		this.loginURL = loginURL;		
	}
	
	/**
	 * Die <code>onLoad()</code>-Methode 
	 */
	public void onLoad() {
		super.onLoad();
		this.setStyleName("GoogleEditCard");
		
		formWrapper = new FlowPanel();
		logoText = new Label("Popcorns");
		logoText.setStylePrimaryName("LogoText");
		//this.getElement().setAttribute("style", "width: 500px; height:350px; text-align: center;");
		imgGoogle.getElement().setAttribute("style", "width: 35px;");
		popcornsLogo.setStylePrimaryName("GoogleLogoIcon");
		//popcornsLogo.getElement().setAttribute("style", "padding: 30px; display: inline-block;");
		
		
		welcomeLabel.setStyleName("TextBoxLabel");
		//welcomeLabel.getElement().setAttribute("style", "display: inline_block;");
		googleBtn.setStyleName("GoogleButton");
		//("button is-block is-large is-fullwidth");
		googleBtn.getElement().appendChild(imgGoogle.getElement());
		//googleBtn.getElement().setAttribute("style", "display: inline-block;");
		googleBtn.addClickHandler(new loginClickHandler());
		
		//this.add(pinnersLogo);
		formWrapper.add(popcornsLogo);
		formWrapper.add(logoText);
		formWrapper.add(welcomeLabel);
		formWrapper.add(googleBtn);
		this.add(formWrapper);
		
	
		
	}
	
	
	/**
	 * Die private Klasse loginClickHandler implementiert das ClickHandler-Interface.
	 * Die ermöglicht das einloggen in das System
	 */
	private class loginClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			//Leite User zum Google Login weiter
			Window.Location.assign(loginURL);
		}
		
	}

}
