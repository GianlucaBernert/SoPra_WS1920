package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.client.gui.Admin.CinemaCardEdit.CancelClickHandler;
import de.hdm.SoPra_WS1920.client.gui.Admin.CinemaCardEdit.DeleteCinemaCallback;
import de.hdm.SoPra_WS1920.client.gui.Admin.CinemaCardEdit.DeleteClickHandler;
import de.hdm.SoPra_WS1920.client.gui.Admin.CinemaCardEdit.SaveClickHandler;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;

public class CinemaChainForm extends DialogBox {

	FlowPanel formWrapper;
	CinemaChainCard parentCard;
	CinemaChain cinemaChainToShow;
	
	Label cardDescription;
	Image cancelIcon;
	Button invisibleButton;
	
	Label cinemaChainNameLabel;
	TextBox cinemaChainTextBox;

	
	Label deleteLabel;
	Image deleteIcon;
	Button saveButton;
	
	Header header;
	Content content;
	
	CinemaAdministrationAsync cinemaAdministration;
	
	public CinemaChainForm(CinemaChainCard cinemaChainCard, CinemaChain cinemaChain) {
		this.parentCard=cinemaChainCard;
		this.cinemaChainToShow=cinemaChain;
	}
	
	public CinemaChainForm(Header header, Content content) {
		this.header = header;
		this.content = content;
		
		CinemaChain cinemaChain = new CinemaChain();
		cinemaChain.setName("");
		cinemaChain.setPersonFK(Integer.parseInt(Cookies.getCookie("userId")));
		cinemaChainToShow = cinemaChain;
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("EditCard");
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
		
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("New Cinema Chain");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		cinemaChainNameLabel = new Label("Name");
		cinemaChainNameLabel.setStyleName("TextBoxLabel");
		cinemaChainTextBox = new TextBox();
		cinemaChainTextBox.setStyleName("CardTextBox");
		cinemaChainTextBox.getElement().setPropertyString("placeholder", "name");
		
		cinemaChainTextBox.setText(cinemaChainToShow.getName());
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(cinemaChainNameLabel);
		formWrapper.add(cinemaChainTextBox);
		
		if(parentCard != null) {
			cardDescription.setText("Edit Cinema Chain");
			deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
			deleteIcon.setStyleName("DeleteIcon");
			deleteIcon.addClickHandler(new DeleteClickHandler(this));
			deleteLabel = new Label("Delete");
			deleteLabel.setStyleName("DeleteLabel");
			deleteLabel.addClickHandler(new DeleteClickHandler(this));
			formWrapper.add(deleteIcon);
			formWrapper.add(deleteLabel);
		}
		
		saveButton = new Button("Save");
		saveButton.setStyleName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler(this));
		
		formWrapper.add(saveButton);
		this.add(formWrapper);
	}
	
	class CancelClickHandler implements ClickHandler{
		CinemaChainForm cinemaChainForm;
		public CancelClickHandler(CinemaChainForm cinemaChainForm) {
			// TODO Auto-generated constructor stub
			this.cinemaChainForm = cinemaChainForm;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(parentCard==null) {
				cinemaChainForm.hide();
			}else {
				parentCard.showCinemaChainCardView(cinemaChainToShow);
				cinemaChainForm.hide();
			}
		}
		
	}
	
	class SaveClickHandler implements ClickHandler{
		CinemaChainForm cinemaChainForm;
		public SaveClickHandler(CinemaChainForm cinemaChainForm) {
			// TODO Auto-generated constructor stub
			this.cinemaChainForm = cinemaChainForm;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(parentCard != null) {
				cinemaChainToShow.setName(cinemaChainTextBox.getText());
				cinemaAdministration.updateCinemaChain(cinemaChainToShow, new SaveCinemaChainCallback(cinemaChainForm));
			}else {
				cinemaAdministration.createCinemaChain(cinemaChainTextBox.getText(), Integer.parseInt(Cookies.getCookie("userId")), new CreateCinemaChainCallback(cinemaChainForm));
				
			}
		}
	}
	
	class SaveCinemaChainCallback implements AsyncCallback<CinemaChain>{
		CinemaChainForm cinemaChainForm;
		public SaveCinemaChainCallback(CinemaChainForm cinemaChainForm) {
			// TODO Auto-generated constructor stub
			this.cinemaChainForm = cinemaChainForm;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Connection");
		}

		@Override
		public void onSuccess(CinemaChain result) {
			cinemaChainToShow = result;
			cinemaChainForm.hide();
			parentCard.showCinemaChainCardView(cinemaChainToShow);
		}
		
	}
	
	class CreateCinemaChainCallback implements AsyncCallback<CinemaChain>{
		CinemaChainForm cinemaChainForm;
		public CreateCinemaChainCallback(CinemaChainForm cinemaChainForm) {
			// TODO Auto-generated constructor stub
			this.cinemaChainForm = cinemaChainForm;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Connection");
		}

		@Override
		public void onSuccess(CinemaChain result) {
			// TODO Auto-generated method stub
			parentCard = new CinemaChainCard(content,result);
			cinemaChainForm.hide();
			parentCard.showCinemaChainCardView(result);
			content.add(parentCard);
			
			
		}
		
	}
	class DeleteClickHandler implements ClickHandler{
		CinemaChainForm cinemaChainForm;
		public DeleteClickHandler(CinemaChainForm cinemaChainForm) {
			// TODO Auto-generated constructor stub
			this.cinemaChainForm = cinemaChainForm;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			//proxy.deleteCinema()
			cinemaAdministration.deleteCinemaChain(cinemaChainToShow, new DeleteCinemaChainCallback(cinemaChainForm));
			
		}
		
	}
	
	class DeleteCinemaChainCallback implements AsyncCallback<Void>{
		CinemaChainForm cinemaChainForm;
		
		public DeleteCinemaChainCallback(CinemaChainForm cinemaChainForm) {
			// TODO Auto-generated constructor stub
			this.cinemaChainForm = cinemaChainForm;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Callback");
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			cinemaChainForm.hide();
			parentCard.remove();
		}
		
	}
}
