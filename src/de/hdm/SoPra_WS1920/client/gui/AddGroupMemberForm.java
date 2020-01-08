package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.gui.GroupForm.CancelClickHandler;
import de.hdm.SoPra_WS1920.shared.bo.Group;

public class AddGroupMemberForm extends DialogBox {
	
	GroupForm gf;
	
	
	
	FlowPanel formWrapper;
	
	CheckBox check;
	TextBox memberTextBox;
	Image cancelIcon;
	
	Label memberLabel;
	Label descriptionCard;
	Label groupNameLabel;
	
	ListBox memberListBox;
	
	Button addButton;
	
	public void onLoad() {
		super.onLoad();
		
		FlowPanel formWrapper = new FlowPanel();
		this.setStylePrimaryName("EditCard");
		
		descriptionCard = new Label("Add Member");
		descriptionCard.setStylePrimaryName("CardDescription");
		
		memberLabel = new Label("Member");
		memberLabel.setStylePrimaryName("TextBoxLabel");
		
		memberListBox = new ListBox();
		memberListBox.setStylePrimaryName("CardTextBox");
		memberListBox.addItem("Yesin");
		memberListBox.addItem("Sebi");
		memberListBox.addItem("David");
		memberListBox.addItem("Shila");
		memberListBox.addItem("Gianluca");
		
		
		addButton = new Button("Add");
		addButton.setStylePrimaryName("SaveButton");
		addButton.addClickHandler(new AddClickHandler(this, gf));
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
	
		formWrapper.add(descriptionCard);
		formWrapper.add(memberLabel);
		formWrapper.add(memberListBox);
		formWrapper.add(cancelIcon);
		formWrapper.add(addButton);
		this.add(formWrapper);
		this.show();

}
	public void showAddGroupMemberForm() {		
		this.show();
		
	
		
	}
	
	class CancelClickHandler implements ClickHandler{
		AddGroupMemberForm agmf;
		
		public CancelClickHandler(AddGroupMemberForm agmf) {
			this.agmf = agmf;
			
		}

		@Override
		public void onClick(ClickEvent event) {
			
			agmf.hide();
			
		}
		
		
	}
	
	class AddClickHandler implements ClickHandler{
		AddGroupMemberForm agmf;
		GroupForm gf;
		
		public AddClickHandler(AddGroupMemberForm agmf, GroupForm gf) {
			this.agmf = agmf;
			this.gf = gf;
		}

		@Override
		public void onClick(ClickEvent event) {
			Group g = new Group();
			g.setName(memberListBox.getName());
			
			agmf.clear();
			agmf.hide();
			gf.showGroupForm();
			
			
		}
		
	}
	
}
