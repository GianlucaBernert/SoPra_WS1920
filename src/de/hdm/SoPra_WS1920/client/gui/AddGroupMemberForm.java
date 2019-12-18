package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.gui.GroupForm.CancelClickHandler;

public class AddGroupMemberForm extends DialogBox {
	
	TextBox memberTextBox;
	FlowPanel main;
	CheckBox check;
	Image cancelIcon;
	
	public void onLoad() {
		super.onLoad();
		
		FlowPanel main = new FlowPanel();
		this.setStylePrimaryName("moviecard");
		
		memberTextBox = new TextBox();
		memberTextBox.setStylePrimaryName("inputTextBox");
		
		check = new CheckBox();
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("cancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		
		
		main.add(cancelIcon);
		main.add(check);
		main.add(memberTextBox);
		this.add(main);
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
	
}
