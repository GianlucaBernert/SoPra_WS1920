package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
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
	Label eMailLabel;
	TextBox eMailTextBox;
	
	Button cancel;
	Label deleteLabel;
	Image deleteIcon;
	Button saveButton;
	
	SurveyManagementHeader header;
	SurveyContent content;
	SurveyNavigationBar surveyNavigationBar;
	SurveyManagementAsync surveyManagement;

	public UserSettingsForm(SurveyNavigationBar surveyNavigationBar, Person p) {
		this.surveyNavigationBar = surveyNavigationBar;
		this.personToShow = p;
	}

	public void onLoad() {
		super.onLoad();
		surveyManagement = ClientsideSettings.getSurveyManagement();
		surveyManagement.getPersonByEmail(personToShow.getEMail(), new GetPersonCallback());
		
		this.setStyleName("EditCard");
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("Profile Settings");
		cardDescription.setStylePrimaryName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStylePrimaryName("CancelIcon");
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
		eMailLabel = new Label("E-Mail");
		eMailLabel.setStyleName("TextBoxLabel");
		eMailTextBox=new TextBox();
		eMailTextBox.setStyleName("CardTextBox");
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(firstNameLabel);
		formWrapper.add(firstNameTextBox);
		formWrapper.add(lastNameLabel);
		formWrapper.add(lastNameTextBox);
		formWrapper.add(eMailLabel);
		formWrapper.add(eMailTextBox);
		
		deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
		deleteIcon.setStyleName("DeleteIcon");
		deleteIcon.addClickHandler(new CancelClickHandler(this));
		
		deleteLabel = new Label("Delete Profile");
		deleteLabel.setStyleName("DeleteLabel");
		deleteLabel.addClickHandler(new DeleteClickHandler(this));
		formWrapper.add(deleteIcon);
		formWrapper.add(deleteLabel);
		
		saveButton = new Button("Save");
		saveButton.addClickHandler(new SaveClickHandler(this));
		saveButton.setStyleName("SaveButton");
		
		firstNameTextBox.setText(personToShow.getFirstname());
		lastNameTextBox.setText(personToShow.getLastname());
		eMailTextBox.setText(personToShow.getEMail());
		
		formWrapper.add(saveButton);
		this.add(formWrapper);
		
	}
	
	class GetPersonCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Person result) {
			// TODO Auto-generated method stub
			personToShow = result;
			firstNameTextBox.setText(personToShow.getFirstname());
			lastNameTextBox.setText(personToShow.getLastname());
			eMailTextBox.setText(personToShow.getEMail());
		}
		
	}
	
	class SaveClickHandler implements ClickHandler{
		UserSettingsForm userSettingsForm;
		public SaveClickHandler(UserSettingsForm userSettingsForm) {
			// TODO Auto-generated constructor stub
			this.userSettingsForm = userSettingsForm;
		}

		@Override
		public void onClick(ClickEvent arg0) {
			// TODO Auto-generated method stub
			personToShow.setFirstname(firstNameTextBox.getText());
			personToShow.setLastname(lastNameTextBox.getText());
			personToShow.setEMail(eMailTextBox.getText());
			userSettingsForm.hide();
			surveyManagement.updatePerson(personToShow, new UpdatePersonCallback());
		}
		
	}
	
	class UpdatePersonCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable arg0) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the connection. Please try again later");
		}

		@Override
		public void onSuccess(Person arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{
		UserSettingsForm userSettingsForm;
		
		public CancelClickHandler(UserSettingsForm userSettingsForm) {
			this.userSettingsForm = userSettingsForm;
		}
		public void onClick(ClickEvent event) {
			userSettingsForm.hide();
			
		}
	}
		
	class DeleteClickHandler implements ClickHandler{
		UserSettingsForm userSettingsForm;
		
		public DeleteClickHandler(UserSettingsForm userSettingsForm) {
			this.userSettingsForm = userSettingsForm;
		}

		@Override
		public void onClick(ClickEvent event) {
			userSettingsForm.hide();
			surveyManagement.deletePerson(personToShow, new DeletePersonCallback());
		}
		
	}
	
	class DeletePersonCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable result) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the connection. Please try again later");
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			Window.Location.assign(surveyNavigationBar.getLogOutURL());
		}
		
	}
	
}
		
	
	
		
	

