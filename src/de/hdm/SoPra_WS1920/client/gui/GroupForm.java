package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
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
	Label deleteLabel;
	
	TextArea listedMembersTextArea;
	
	TextBox groupTextBox;
	
	MultiWordSuggestOracle allMembers;
	SuggestBox memberSuggestBox;
	
	Button saveButton;
	Button addMember;
	Button add;
	
	Image deleteIcon;
	Image cancelIcon;
	Image addIcon;
	Image searchIcon;
	
	FlowPanel memberPanel;
	Vector<Person> groupMembers;
	
	SurveyManagementAsync surveyManagementAdministration;
	
	
	public GroupForm(GroupCard groupCard, Group group) {
		this.parentCard = groupCard;
		this.groupToShow = group;
		
	}
	
	public GroupForm(SurveyManagementHeader header, SurveyContent content) {
		this.header = header;
		this.content = content;
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
			deleteIcon.setStyleName("searchSubmitIcon");
			deleteIcon.addClickHandler(new DeleteMemberClickHandler(this));
			
			
			fullNameLabel = new Label(p.getFirstname()+ " "+p.getLastname());
			fullNameLabel.setStyleName("MemberTextBoxLabel");
			
			
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
		surveyManagementAdministration = ClientsideSettings.getSurveyManagement();
		
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
		allMembers.add("ys018@hdm-stuttgart.de");
		allMembers.add("sh67@hdm-stuttgart.de");
		allMembers.add("mm34@hdm-stuttgart.de");
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
		main.add(memberName);
		main.add(memberSuggestBox);
		main.add(searchIcon);
		main.add(addedMembers);
		main.add(memberPanel);
		main.add(saveButton);
		
//		if(parentCard!=null) {
//			cardDescription.setText("Edit Cinema");
//			deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
//			deleteIcon.setStyleName("DeleteIcon");
//			deleteIcon.addClickHandler(new DeleteClickHandler(this));
//			deleteLabel = new Label("Delete");
//			deleteLabel.setStyleName("DeleteLabel");
//			deleteLabel.addClickHandler(new DeleteClickHandler(this));
//			main.add(deleteIcon);
//			main.add(deleteLabel);
//		
//		
		
		this.add(main);
		this.center();
		this.show();
	
		}
		//groupToShow.setName(groupTextBox.getText());
		//groupToShow.setId(1);
		

		
	

	
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
			if(parentCard== null) {
			gf.hide();
			}else {
				parentCard.showGroupCardView(groupToShow);
				gf.hide();
			}
			
			
		
		
		}
		
	}
	

	class SaveClickHandler implements ClickHandler {
		GroupForm gf;
		
		public SaveClickHandler(GroupForm gf) {
			this.gf = gf;
			
		}

		@Override
		public void onClick(ClickEvent event) {
			
			surveyManagementAdministration.createGroup(groupTextBox.getText(),
					2, 
					new CreateGroupCallback());
					
					groupToShow.setName(groupTextBox.getText());
					groupToShow.setPersonFK(2);
					
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
		
		class CreateGroupCallback implements AsyncCallback<Group>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with the Callback");
				
			}

			@Override
			public void onSuccess(Group result) {
				// TODO Auto-generated method stub
				
				
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

		class DeleteClickHandler implements ClickHandler{
			
			GroupForm gf;
			
			public DeleteClickHandler(GroupForm gf) {
				this.gf = gf;
			}

			@Override
			public void onClick(ClickEvent event) {
//				surveyManagementAdministration.deleteGroup(groupToShow, new DeleteGroupCallback(gf));
				
			}
			
			
		}
		class DeleteGroupCallback implements AsyncCallback<Void>{
			GroupForm gf;
			
			public DeleteGroupCallback(GroupForm gf) {
				this.gf = gf;
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with DeleteCallback");
				
			}

			@Override
			public void onSuccess(Void result) {
				gf.hide();
//				parentCard.remove();
				
			}
			
		
	
		}
		
	
	
	


	
	
	
	
	

