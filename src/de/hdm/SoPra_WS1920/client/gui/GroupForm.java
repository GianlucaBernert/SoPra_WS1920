package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCard;
import de.hdm.SoPra_WS1920.shared.bo.Group;

public class GroupForm extends DialogBox {
	
	SurveyManagementHeader header;
	SurveyContent content;
	AddGroupMemberForm agmf;
	Group groupToShow;
	GroupCard parentCard;
	
	Label groupName;
	Label memberName;
	Label cardDescription;
	ListBox groupListBox;
	ListBox memberListBox;
	FlowPanel main;
	HorizontalPanel buttonPanel;
	Button cancel;
	Image cancelIcon;
	Button saveButton;
	Image addIcon;
	
	GroupForm gf;
	
	public GroupForm() {
		
		
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		FlowPanel main = new FlowPanel();
		this.setStylePrimaryName("EditCard");
		
		cardDescription = new Label("Add Group");
		cardDescription.setStyleName("CardDescription");
		
		groupName = new Label("Gruppenname:");
		groupName.setStylePrimaryName("TextBoxLabel");
		
		memberName = new Label("Gruppenmitglied:");
		memberName.setStylePrimaryName("TextBoxLabel");
		
		
		groupListBox = new ListBox();
		groupListBox.setStylePrimaryName("CardTextBox");
		groupListBox.addItem("Popcorns");
		groupListBox.addItem("Friends");
		
		memberListBox = new ListBox();
		memberListBox.setStylePrimaryName("CardTextBox");
		memberListBox.addItem("Yesin");
		memberListBox.addItem("Sebi");
		
		//cancel = new Button("cancel");
		//cancel.setStylePrimaryName("createBoButton");
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		saveButton = new Button("Save");
		saveButton.setStylePrimaryName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler(this));
		
		//addIcon = new Image("/Images/003-edit.png");
		//addIcon.setStylePrimaryName("editIcon");
		//addIcon.addClickHandler(new AddClickHandler(this, agmf));
		
		
		main.add(cardDescription);
		main.add(groupName);
		main.add(groupListBox);
		main.add(cancelIcon);
		//main.add(addIcon);
		main.add(memberName);
		main.add(memberListBox);
		main.add(saveButton);
		
		//main.add(cancel);
		
		this.add(main);
		
		this.show();
		

	}
	
	public void showGroupForm() {
		this.show();
		this.center();
		

}
	
	class CancelClickHandler implements ClickHandler {
		GroupForm gf;
		
		public CancelClickHandler(GroupForm gf) {
			this.gf = gf;
		}

		@Override
		public void onClick(ClickEvent event) {
			gf.hide();
		
			
			
		}
		
	}
	
	class SaveClickHandler implements ClickHandler {
		GroupForm gf;
		
		public SaveClickHandler(GroupForm gf) {
			this.gf = gf;
			
		}

		@Override
		public void onClick(ClickEvent event) {
			groupToShow.setName(groupListBox.getValue(1));
			groupToShow.setName(memberListBox.getValue(0));
			
			
			if(parentCard==null) {
				parentCard = new GroupCard(content,groupToShow);
				parentCard.showGroupCardView(groupToShow);
				content.add(parentCard);
				gf.hide();
			}else {
				parentCard.showGroupCardView(groupToShow);
				gf.hide();
			}
			
		}
		
			
		}
		
	}
	
	class AddClickHandler implements ClickHandler{
		
		public AddClickHandler(GroupForm gf, AddGroupMemberForm agmf) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			AddGroupMemberForm agmf = new AddGroupMemberForm();
			agmf.showAddGroupMemberForm();
			
		}
	}
	
	
	

