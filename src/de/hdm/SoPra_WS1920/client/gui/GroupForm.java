package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;


import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Person;


public class GroupForm extends DialogBox {
	
	SurveyManagementHeader header;
	SurveyContent content;
	AddGroupMemberForm agmf;
	Group groupToShow;
	GroupCard parentCard;
	GroupForm gf;
	
	FlowPanel main;
	HorizontalPanel buttonPanel;
	Button cancel;

	
	Label groupName;
	Label memberName;
	Label cardDescription;
	Label addedMembers;
	
	TextArea listedMembersTextArea;
	
	TextBox groupTextBox;
	
	MultiWordSuggestOracle allMembers;
	SuggestBox memberSuggestBox;
	
	Button saveButton;
	Button addMember;
	Button add;
	
	Image cancelIcon;
	Image addIcon;
	Image searchIcon;
	
	FlowPanel memberPanel;
	Vector<Person> groupMembers;
	
	
	
	public GroupForm() {
		
	}
	
	public void showMembers() {
		memberPanel.clear();
		for(Person p: groupMembers) {
			MemberRow memberRow = new MemberRow(memberPanel,p);
			memberPanel.add(memberRow);
		}
		
	}
	
	class MemberRow extends FlowPanel{
		Image deleteIcon;
		Label fullNameLabel;
		
		Person p;
		FlowPanel memberPanel;
		
		public MemberRow(FlowPanel memberPanel, Person p) {
			this.memberPanel = memberPanel;
			this.p = p;
		}
		
		public void onLoad() {
			super.onLoad();
			deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
			deleteIcon.setStyleName("DeleteIcon");
			deleteIcon.addClickHandler(new DeleteMemberClickHandler(this));
			
			fullNameLabel = new Label(p.getFirstname()+ " "+p.getLastname());
			
			this.add(deleteIcon);
			this.add(fullNameLabel);
		}
		
		class DeleteMemberClickHandler implements ClickHandler{
			MemberRow memberRow;
			
			public DeleteMemberClickHandler(MemberRow memberRow) {
				// TODO Auto-generated constructor stub
				this.memberRow = memberRow;
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				memberPanel.remove(memberRow);
			}
			
		}
	}
	
	public void onLoad() {
		super.onLoad();
		
		groupMembers = new Vector<Person>();
		
		FlowPanel main = new FlowPanel();
		this.setStylePrimaryName("EditCard");
		
		memberPanel = new FlowPanel();
//		this.setStylePrimaryName(style);
		
		cardDescription = new Label("Add Group");
		cardDescription.setStyleName("CardDescription");
		
		groupName = new Label("Goupname:");
		groupName.setStylePrimaryName("TextBoxLabel");
		
		memberName = new Label(" Add Group Member:");
		memberName.setStylePrimaryName("TextBoxLabel");
		
		
		groupTextBox = new TextBox();
		groupTextBox.setStylePrimaryName("CardTextBox");
		

		searchIcon = new Image("/Images/png/003-search.png");
		searchIcon.setStyleName("SearchIcon");
		searchIcon.addClickHandler(new AddMemberClickHandler(this));

		
		
		
		allMembers = new MultiWordSuggestOracle();
		allMembers.add("Yesin");
		allMembers.add("Sebi");
		allMembers.add("Matthias");
		memberSuggestBox = new SuggestBox(allMembers);
		memberSuggestBox.setStylePrimaryName("CardTextBox");
		
		
		
		addedMembers = new Label("Added Members");
		addedMembers.setStylePrimaryName("TextBoxLabel");
		
		//listedMembersTextArea =new TextArea();
		//listedMembersTextArea.setStyleName("CardTextArea");
		//listedMembersTextArea.getElement().setAttribute("maxlength", "350");
		
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
		addMember.addClickHandler(new AddMemberClickHandler(this));
		
		//addIcon = new Image("/Images/003-edit.png");
		//addIcon.setStylePrimaryName("editIcon");
		//addIcon.addClickHandler(new AddClickHandler(this, agmf));
		
		
		main.add(cardDescription);
		main.add(groupName);
		main.add(groupTextBox);
		main.add(cancelIcon);
		//main.add(addIcon);
		main.add(memberName);
		main.add(memberSuggestBox);
		main.add(searchIcon);
		main.add(addedMembers);
		//main.add(listedMembersTextArea);
//		main.add(addMember);
		main.add(memberPanel);
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
	class AddMemberClickHandler implements ClickHandler{
		GroupForm gf;
		
		public AddMemberClickHandler(GroupForm gf) {
			this.gf = gf;
			
		}

		@Override
		public void onClick(ClickEvent event) {
			Person p = new Person();
			p.setFirstname(gf.memberSuggestBox.getValue());
			gf.groupMembers.add(p);
			gf.showMembers();
			
			
		}
		

	}
}
	class AddClickHandler implements ClickHandler{
		GroupForm gf;
	
		
		public AddClickHandler(GroupForm gf) {
			
			
		}

		@Override
		public void onClick(ClickEvent event) {
			
			
			
			
		}
	
		
	public void showAddedGroupMembers(String m, Group g) {
		
	}
	
	}
	
	

	
	
	
	
	

