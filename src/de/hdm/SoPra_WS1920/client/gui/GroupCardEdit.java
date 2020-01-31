package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import de.hdm.SoPra_WS1920.shared.bo.Group;


public class GroupCardEdit extends DialogBox {
	
	FlowPanel formWrapper;
	GroupCard parentCard;
	Group groupToShow;
	
	Label cardDescription;
	Button invisibleButton;
	
	Label groupName;
	TextBox groupNameBox;
	Label memberName;
	ListBox memberNameBox;
	Button save;
	Button cancel;
	Button delete;
	
	Image saveIcon;
	Image cancelIcon;
	Image deleteIcon;
	
	Label deleteLabel;
	Button saveButton;
	
	SurveyManagementHeader header;
	SurveyContent content;
	
	public GroupCardEdit(GroupCard groupCard, Group group) {
		this.parentCard = groupCard;
		this.groupToShow = group;
	}
	
	public GroupCardEdit(SurveyContent content, SurveyManagementHeader header) {
		this.content = content;
		this.header = header;
		
		Group g = new Group();
//		g.setId(1);
		g.setName("");
		g.setPersonFK(content.p.getId());
		groupToShow = g;
		
	}
	
	public void onLoad() {
		super.onLoad();
		
		this.setStylePrimaryName("EditCard");
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("Add Group");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		groupName = new Label("Groupname");
		groupName.setStylePrimaryName("TextBoxLabel");
		groupNameBox = new TextBox();
		groupNameBox.setStylePrimaryName("CardTextBox");
		memberName = new Label("Groupmember");
		memberNameBox = new ListBox();
		memberNameBox.addItem("Yesin");
		memberNameBox.addItem("Sebi");
		memberNameBox.setStylePrimaryName("CardListBox");
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(groupName);
		formWrapper.add(groupNameBox);
		formWrapper.add(memberName);
		formWrapper.add(memberNameBox);
		
		if(parentCard!=null) {
			cardDescription.setText("Edit Cinema");
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
		
		groupToShow.setName(groupNameBox.getText());
		
		formWrapper.add(saveButton);
		this.add(formWrapper);
		
	}
	
	class SaveClickHandler implements ClickHandler {
		GroupCardEdit groupCardEdit;
		
		public SaveClickHandler(GroupCardEdit groupCardEdit) {
			this.groupCardEdit = groupCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			
			groupToShow.setName(groupNameBox.getText());
			
			if(parentCard==null) {
				parentCard = new GroupCard(content,groupToShow);
				parentCard.showGroupCardView(groupToShow);
				content.add(parentCard);
				groupCardEdit.hide();
			}else {
				parentCard.showGroupCardView(groupToShow);
				groupCardEdit.hide();
			
			
		}
		
	}
	
	}
	
	class CancelClickHandler implements ClickHandler{
		GroupCardEdit groupCardEdit;
		public CancelClickHandler(GroupCardEdit groupCardEdit) {
			// TODO Auto-generated constructor stub
			this.groupCardEdit = groupCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(parentCard==null) {
				groupCardEdit.hide();
			}else {
				parentCard.showGroupCardView(groupToShow);
				groupCardEdit.hide();
			}
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{
		GroupCardEdit groupCardEdit;
		
		public DeleteClickHandler(GroupCardEdit groupCardEdit) {
			// TODO Auto-generated constructor stub
			this.groupCardEdit = groupCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			groupCardEdit.hide();
			parentCard.remove();
		}
		
	}


}

		
		/*groupName = new Label("Title");
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
		this.add(delete);*/
		
	/*}
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
}*/
		
		
	
	
	



