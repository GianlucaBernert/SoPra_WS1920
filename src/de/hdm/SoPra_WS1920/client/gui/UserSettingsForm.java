package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import de.hdm.SoPra_WS1920.shared.bo.Person;

public class UserSettingsForm extends DialogBox {
	
	FlowPanel formWrapper;
	Person personToShow;
	
	Label cardDescription;
	Image cancelIcon;
	Button invisibleButton;
	
	Label firstNameLabel;
	TextBox firstNameTextBox;
	Label lastNameLabel;
	TextBox lastNameTextBox;
	Label emailLabel;
	TextBox emailTextBox;
	
	Button cancel;
	Label deleteLabel;
	Image deleteIcon;
	Button saveButton;
	
	SurveyManagementHeader header;
	SurveyContent content;
	
	public UserSettingsForm(Person personToShow) {
		this.personToShow = personToShow;
		
	}
	
	public UserSettingsForm(SurveyManagementHeader header, SurveyContent content) {
		this.header = header;
		this.content = content;
	
	
	Person p = new Person();
	p.setFirstname("Yesin");
	p.setLastname("Soufi");
	p.setEMail("ys018@hdm-stuttgart.de");
	personToShow = p;

}
	public void onLoad() {
		super.onLoad();
		
		this.setStylePrimaryName("EditCard");
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("Profile Settings");
		cardDescription.setStylePrimaryName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStylePrimaryName("cancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		firstNameLabel = new Label("First Name");
		firstNameLabel.setStyleName("TextBoxLabel");
		firstNameTextBox=new TextBox();
		firstNameTextBox.setStyleName("CardTextBox");
		firstNameTextBox.getElement().setPropertyString("placeholder", "Title");
		lastNameLabel = new Label("Last Name");
		lastNameLabel.setStyleName("TextBoxLabel");
		lastNameTextBox=new TextBox();
		lastNameTextBox.setStyleName("CardTextBox");
		lastNameTextBox.getElement().setPropertyString("placeholder", "Genre");
		emailLabel = new Label("E-Mail");
		emailLabel.setStyleName("TextBoxLabel");
		emailTextBox=new TextBox();
		emailTextBox.setStyleName("CardTextBox");
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(firstNameLabel);
		formWrapper.add(firstNameTextBox);
		formWrapper.add(lastNameLabel);
		formWrapper.add(lastNameTextBox);
		formWrapper.add(emailLabel);
		formWrapper.add(emailTextBox);
		
		deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
		deleteIcon.setStyleName("DeleteIcon");
		deleteIcon.addClickHandler(new CancelClickHandler(this));
		
		deleteLabel = new Label("Delete Profile");
		deleteLabel.setStyleName("DeleteLabel");
		//deleteLabel.addClickHandler(new DeleteClickHandler(this));
		formWrapper.add(deleteIcon);
		formWrapper.add(deleteLabel);
		
		saveButton = new Button("Save");
		//saveButton.addClickHandler(new SaveClickHandler(this));
		saveButton.setStyleName("SaveButton");
		
		firstNameTextBox.setText(personToShow.getFirstname());
		lastNameTextBox.setText(personToShow.getLastname());
		emailTextBox.setText(personToShow.getEMail());
		

		formWrapper.add(saveButton);
		this.add(formWrapper);
		
	}
	
	class CancelClickHandler implements ClickHandler{
		UserSettingsForm userSettingsForm;
		
		public CancelClickHandler(UserSettingsForm userSettingsForm) {
			this.userSettingsForm = userSettingsForm;
		}
		public void onClick(ClickEvent event) {
			userSettingsForm.hide();
			
		}
		
	class DeleteClickHandler implements ClickHandler{
		UserSettingsForm userSettingsForm;
		
		public DeleteClickHandler(UserSettingsForm userSettingsForm) {
			this.userSettingsForm = userSettingsForm;
		}

		@Override
		public void onClick(ClickEvent event) {
			userSettingsForm.hide();
			
		}
		
	}
	}
	
}
		
	
	
		
	

