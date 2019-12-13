package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class GroupForm extends DialogBox {
	
	SurveyManagementHeader header;
	SurveyContent content;
	
	Label groupName;
	Label memberName;
	TextBox groupTextBox;
	TextBox memberTextBox;
	FlowPanel main;
	HorizontalPanel buttonPanel;
	Button cancel;
	
	GroupForm gf;
	
	public GroupForm() {
		
		
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		FlowPanel main = new FlowPanel();
		this.setStylePrimaryName("groupcard");
		groupName = new Label("Gruppenname:");
		groupName.setStylePrimaryName("inputLabel");
		
		memberName = new Label("Gruppenmitglied:");
		memberName.setStylePrimaryName("inputLabel");
		
		
		groupTextBox = new TextBox();
		groupTextBox.setStylePrimaryName("inputTextBox");
		
		memberTextBox = new TextBox();
		memberTextBox.setStylePrimaryName("inputTextBox");
		
		cancel = new Button("cancel");
		cancel.setStylePrimaryName("createBoButton");
	
		main.add(groupName);
		main.add(groupTextBox);
		
		main.add(memberName);
		main.add(memberTextBox);
		
		main.add(cancel);
		
		this.add(main);
		
		this.show();
		

	}
	
	public void showGroupForm() {
		this.show();
		this.center();
		

}
	
}
