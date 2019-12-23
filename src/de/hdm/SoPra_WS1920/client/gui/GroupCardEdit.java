package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCardEdit.CancelClickHandler;
import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCardEdit.DeleteClickHandler;
import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCardEdit.SaveClickHandler;
import de.hdm.SoPra_WS1920.shared.bo.Group;


public class GroupCardEdit extends FlowPanel {
	
	Label groupName;
	TextBox groupNameBox;
	Label member;
	TextBox memberBox;
	Button save;
	Button cancel;
	Button delete;
	Group groupToShow;
	GroupCard parentCard;
	Image saveIcon;
	Image cancelIcon;
	Image deleteIcon;
	
	public GroupCardEdit(GroupCard groupCard, Group groupToShow) {
		this.parentCard = groupCard;
		this.groupToShow = groupToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		
		groupName = new Label("Title");
		groupName.setStylePrimaryName("inputLabel");
		groupNameBox = new TextBox();
		groupNameBox.setStylePrimaryName("inputTextBox");
		member = new Label("Gruppenmitglieder");
		member.setStylePrimaryName("inputLabel");
		memberBox = new TextBox();
		memberBox.setStylePrimaryName("inputTextBox");
		save = new Button("SAVE");
		save.setStylePrimaryName("invisibleButton");
		cancel = new Button("CANCEL");
		cancel.setStylePrimaryName("invisibleButton");
		delete = new Button("DELETE");
		delete.setStylePrimaryName("invisibleButton");
		groupName.setText(groupToShow.getName());
		member.setText(groupToShow.getName());
		
		saveIcon = new Image("/Images/002-checked.svg");
		saveIcon.setStyleName("saveIcon");
		saveIcon.addClickHandler(new SaveClickHandler(this));
		
	
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("cancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));

		deleteIcon = new Image("/Images/003-delete.svg");
		deleteIcon.setStyleName("deleteIcon");
		deleteIcon.addClickHandler(new DeleteClickHandler(this));
		
			
		this.add(groupName);
		this.add(groupNameBox);
		this.add(member);
		this.add(memberBox);
		this.add(save);
		this.add(cancel);
		this.add(delete);
		
	}
		class SaveClickHandler implements ClickHandler{
			
			GroupCardEdit groupCardEdit;
			
			public SaveClickHandler(GroupCardEdit groupCardEdit) {
				this.groupCardEdit = groupCardEdit;
			}

			@Override
			public void onClick(ClickEvent event) {
				groupToShow.setName(groupName.getText());
				groupToShow.setName(member.getText());
				parentCard.showGroupCardView(groupToShow);
			}
		
		}
		class CancelClickHandler implements ClickHandler{
			
			GroupCardEdit groupCardEdit;
			
			public CancelClickHandler(GroupCardEdit groupCardEdit) {
				this.groupCardEdit = groupCardEdit;
			}

			@Override
			public void onClick(ClickEvent event) {
				parentCard.showGroupCardEdit(groupToShow);
				
			}
			
		}
			
			class DeleteClickHandler implements ClickHandler{
				GroupCardEdit groupCardEdit;
				public DeleteClickHandler(GroupCardEdit groupCardEdit) {
					// TODO Auto-generated constructor stub
					this.groupCardEdit=groupCardEdit;
				}

				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					parentCard.remove();
			
		}
			}
}
		
		
	
	
	



