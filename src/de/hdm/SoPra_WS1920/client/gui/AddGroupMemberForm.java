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

public class AddGroupMemberForm extends DialogBox {
	
	TextBox memberTextBox;
	FlowPanel main;
	CheckBox check;
	Image cancelIcon;
	Label memberLabel;
	ListBox memberListBox;
	Button saveButton;
	
	public void onLoad() {
		super.onLoad();
		
		FlowPanel main = new FlowPanel();
		this.setStylePrimaryName("EditCard");
		
		memberLabel = new Label("Member");
		memberLabel.setStylePrimaryName("TextBoxLabel");
		
		memberListBox = new ListBox();
		memberListBox.addItem("Yesin");
		memberListBox.addItem("Sebi");
		memberListBox.addItem("David");
		memberListBox.addItem("Shila");
		memberListBox.addItem("Gianluca");
		memberTextBox.setStylePrimaryName("inputTextBox");
		
		saveButton = new Button("Save");
		saveButton.setStylePrimaryName("SaveButton");
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
	
		
		main.add(cancelIcon);
		main.add(memberLabel);
		main.add(memberListBox);
		main.add(saveButton);
		this.add(main);
		this.show();

}
	public void showAddGroupMemberForm() {
		this.show();
		this.center();
	
		
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
	
}
