package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class GroupForm extends DialogBox {
	
	SurveyManagementHeader header;
	SurveyContent content;
	AddGroupMemberForm agmf;
	
	Label groupName;
	Label memberName;
	TextBox groupTextBox;
	TextBox memberTextBox;
	FlowPanel main;
	HorizontalPanel buttonPanel;
	Button cancel;
	Image cancelIcon;
	Image saveIcon;
	Image addIcon;
	
	GroupForm gf;
	
	public GroupForm() {
		
		
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		FlowPanel main = new FlowPanel();
		this.setStylePrimaryName("moviecard");
		groupName = new Label("Gruppenname:");
		groupName.setStylePrimaryName("inputLabel");
		
		memberName = new Label("Gruppenmitglied:");
		memberName.setStylePrimaryName("inputLabel");
		
		
		groupTextBox = new TextBox();
		groupTextBox.setStylePrimaryName("inputTextBox");
		
		memberTextBox = new TextBox();
		memberTextBox.setStylePrimaryName("inputTextBox");
		
		//cancel = new Button("cancel");
		//cancel.setStylePrimaryName("createBoButton");
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("cancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		saveIcon = new Image("/Images/002-checked.svg");
		saveIcon.setStylePrimaryName("saveIcon");
		saveIcon.addClickHandler(new SaveClickHandler(this));
		
		addIcon = new Image("/Images/003-edit.png");
		addIcon.setStylePrimaryName("editIcon");
		addIcon.addClickHandler(new AddClickHandler(this, agmf));
		
		
	
		main.add(groupName);
		main.add(groupTextBox);
		main.add(cancelIcon);
		main.add(saveIcon);
		main.add(addIcon);
		
		main.add(memberName);
		main.add(memberTextBox);
		
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
		
		public SaveClickHandler(GroupForm gf) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
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
	
	
	
}
