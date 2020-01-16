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
import de.hdm.SoPra_WS1920.shared.bo.Membership;
import de.hdm.SoPra_WS1920.shared.bo.Person;


public class GroupForm extends DialogBox {
	
	FlowPanel formWrapper;
	Group groupToShow;
	GroupCard parentCard;

	Label cardDescription;
	Image cancelIcon;
	Button invisibleButton;
	
	Label groupNameLabel;
	TextBox groupNameTextBox;
	Label addMembersLabel;
	MultiWordSuggestOracle allMembers;
	SuggestBox memberSuggestBox;
	Image addIcon;
	
	FlowPanel membersPanel;
	
	Image deleteIcon;
	Label deleteLabel;
	
	Button saveButton;
	
	Vector<Person> groupMembers;
	
	SurveyManagementHeader header;
	SurveyContent content;
	
	SurveyManagementAsync surveyManagementAdministration;
	
	
	public GroupForm(GroupCard groupCard, Group group) {
		this.parentCard = groupCard;
		this.groupToShow = group;
		
	}
	
	public GroupForm(SurveyManagementHeader header, SurveyContent content) {
		this.header = header;
		this.content = content;
	}
	
	public void onLoad() {
		super.onLoad();
		surveyManagementAdministration = ClientsideSettings.getSurveyManagement();
		surveyManagementAdministration.getAllPersons(new GetAllPersonsCallback());
		
		if(parentCard!=null) {
			surveyManagementAdministration.getMembershipsOfGroup(groupToShow, new GetMembershipCallback());
		}
		
		groupMembers = new Vector<Person>();
		
		formWrapper = new FlowPanel();
		this.setStylePrimaryName("EditCard");
		
		cardDescription = new Label("Add Group");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		
		groupNameLabel = new Label("Goupname:");
		groupNameLabel.setStylePrimaryName("TextBoxLabel");
		groupNameTextBox = new TextBox();
		groupNameTextBox.setStyleName("CardTextBox");
		groupNameTextBox.getElement().setPropertyString("placeholder", "person@mail.com");
		
		addMembersLabel = new Label(" Add Group Member:");
		addMembersLabel.setStylePrimaryName("TextBoxLabel");
		
		membersPanel = new FlowPanel();
		allMembers = new MultiWordSuggestOracle();
		memberSuggestBox = new SuggestBox(allMembers);
		memberSuggestBox.setStylePrimaryName("CardTextBox");
		this.showMembers();
				
		addIcon = new Image("/Images/png/001-add-button.png");
		addIcon.setStyleName("AddIcon");
		addIcon.addClickHandler(new AddMemberClickHandler(this));

		saveButton = new Button("Save");
		saveButton.setStylePrimaryName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler(this));
			
		formWrapper.add(cardDescription);
		formWrapper.add(groupNameLabel);
		formWrapper.add(groupNameTextBox);
		formWrapper.add(cancelIcon);
		formWrapper.add(addMembersLabel);
		formWrapper.add(memberSuggestBox);
		formWrapper.add(addIcon);
//		formWrapper.add(addedMembers);
		formWrapper.add(membersPanel);
		formWrapper.add(saveButton);

		this.add(formWrapper);
		this.center();
		this.show();
	
	}
	
	public void showMembers() {
		membersPanel.clear();
		for(Person p: groupMembers) {
			MemberRow memberRow = new MemberRow(membersPanel,p);
			membersPanel.add(memberRow);
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
			deleteIcon.setStyleName("DeletetIcon");
			deleteIcon.addClickHandler(new DeleteMemberClickHandler(this));
			
			fullNameLabel = new Label(p.getEMail());
			fullNameLabel.setStyleName("TextBoxLabel");
			
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
				
//				surveyManagementAdministration.deleteMembership(parentCard.groupToShow.getId(), p.getId(), new DeleteMembershipCallback());
				groupMembers.remove(p);
				memberPanel.remove(memberRow);
			}
			
		}
		
		class DeleteMembershipCallback implements AsyncCallback<Void>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
		}
	}
	
	class GetAllPersonsCallback implements AsyncCallback<Vector<Person>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("funzt net");
		}

		@Override
		public void onSuccess(Vector<Person> result) {
			// TODO Auto-generated method stub
			for(Person p: result) {
				allMembers.add(p.getEMail());
			}
		}
		
	}
	
	
		
	public boolean checkforMembership(Person person) {
		boolean isMember= false;
		
		for(Person p: groupMembers) {
			if(!p.getEMail().equals(person.getEMail())) {
				continue;
			}else {
				isMember = true;
			}
		}
		return isMember;
	}

	
	public void showGroupForm() {
		this.center();
		this.show();

	}
	
	class GetMembershipCallback implements AsyncCallback<Vector<Membership>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Membership> result) {
			// TODO Auto-generated method stub
			for(Membership m:result) {
				surveyManagementAdministration.getPersonById(m.getPersonFK(), new GetMembersCallback());
			}
		}
		
	}
	
	class GetMembersCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Person result) {
			// TODO Auto-generated method stub
				groupMembers.add(result);
				
			}
		}
		
	
	class CancelClickHandler implements ClickHandler {
		GroupForm gf;
		
		public CancelClickHandler(GroupForm gf) {
			this.gf = gf;
		}

		@Override
		public void onClick(ClickEvent event) {
			if(gf.parentCard== null) {
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
			
			surveyManagementAdministration.createGroup(groupNameTextBox.getText(),
					1, 
					new CreateGroupCallback(gf));
			
		}
	}
		
		class CreateGroupCallback implements AsyncCallback<Group>{
			GroupForm gf;
			public CreateGroupCallback(GroupForm gf) {
				// TODO Auto-generated constructor stub
				this.gf=gf;
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with the Callback");
				
			}

			@Override
			public void onSuccess(Group result) {
				groupToShow = result;

				for(Person p: groupMembers) {
					surveyManagementAdministration.createMembership(result, p, new AddMembersCallback());
				}
				if(parentCard==null) {
					parentCard = new GroupCard(content,result);
					parentCard.showGroupCardView(groupToShow);
					content.add(parentCard);
					gf.hide();
				}else {
					parentCard.showGroupCardView(groupToShow);
					gf.hide();
				}
			}
		}
		
		class AddMembersCallback implements AsyncCallback<Membership>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with adding the members");
			}

			@Override
			public void onSuccess(Membership result) {
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
			surveyManagementAdministration.getPersonByEmail(gf.memberSuggestBox.getValue(), new GetPersonCallback(gf));
		
		}
	}
	
	class GetPersonCallback implements AsyncCallback<Person>{
		GroupForm gf;
		public GetPersonCallback(GroupForm gf) {
			// TODO Auto-generated constructor stub
			this.gf = gf;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Person not found");
		}

		@Override
		public void onSuccess(Person result) {
			// TODO Auto-generated method stub
			if(gf.checkforMembership(result)==false) {
				gf.groupMembers.add(result);
				MemberRow newMember = new MemberRow(gf.membersPanel,result);
				gf.membersPanel.add(newMember);
			}
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
}

		
	
	
	


	
	
	
	
	

