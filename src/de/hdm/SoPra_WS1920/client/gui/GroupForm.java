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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCard;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import javafx.scene.control.cell.CheckBoxListCell;

public class GroupForm extends DialogBox {
	
	SurveyManagementHeader header;
	SurveyContent content;
	AddGroupMemberForm agmf;
	Group groupToShow;
	GroupCard parentCard;

	
	Label groupName;
	Label memberName;
	Label cardDescription;
	Label addedMembers;
	TextArea listedMembersTextArea;
	TextBox groupTextBox;
	ListBox memberListBox;
	FlowPanel main;
	HorizontalPanel buttonPanel;
	Button cancel;
	Image cancelIcon;
	Button saveButton;
	Button addMember;
	Button add;
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
		
		groupName = new Label("Goupname:");
		groupName.setStylePrimaryName("TextBoxLabel");
		
		memberName = new Label("Groupmember:");
		memberName.setStylePrimaryName("TextBoxLabel");
		
		
		groupTextBox = new TextBox();
		groupTextBox.setStylePrimaryName("CardTextBox");
		
		
		memberListBox = new ListBox();
		memberListBox.setStylePrimaryName("CardTextBox");
		memberListBox.addItem("Yesin");
		memberListBox.addItem("Sebi");
		
		addedMembers = new Label("Added Members");
		addedMembers.setStylePrimaryName("TextBoxLabel");
		
		listedMembersTextArea =new TextArea();
		listedMembersTextArea.setStyleName("CardTextArea");
		listedMembersTextArea.getElement().setAttribute("maxlength", "350");
		
		//cancel = new Button("cancel");
		//cancel.setStylePrimaryName("createBoButton");
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		saveButton = new Button("Save");
		saveButton.setStylePrimaryName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler(this));
		
		addMember = new Button("Add Member");
		addMember.setStylePrimaryName("SaveButton");
		addMember.addClickHandler(new AddMemberClickHandler(this, agmf));
		
		//addIcon = new Image("/Images/003-edit.png");
		//addIcon.setStylePrimaryName("editIcon");
		//addIcon.addClickHandler(new AddClickHandler(this, agmf));
		
		
		main.add(cardDescription);
		main.add(groupName);
		main.add(groupTextBox);
		main.add(cancelIcon);
		//main.add(addIcon);
		main.add(memberName);
		main.add(memberListBox);
		main.add(addedMembers);
		main.add(listedMembersTextArea);
		main.add(addMember);
		main.add(saveButton);
		
		//main.add(cancel);
		
		this.add(main);
		this.center();
		this.show();
		

	}
	
	public void showGroupForm() {
		this.center();
		this.show();
	
		

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
			groupToShow.setName(groupTextBox.getValue());
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
	
	class AddMemberClickHandler implements ClickHandler{
		
		public AddMemberClickHandler(GroupForm gf, AddGroupMemberForm agmf) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			AddGroupMemberForm agmf = new AddGroupMemberForm();
			agmf.showAddGroupMemberForm();
			
		}
		

	}
	class AddClickHandler implements ClickHandler{
		GroupForm gf;
	
		
		public AddClickHandler(GroupForm gf) {
			
			
		}

		@Override
		public void onClick(ClickEvent event) {
			
			
			
			
		}
	}
	
	
	
	
	

