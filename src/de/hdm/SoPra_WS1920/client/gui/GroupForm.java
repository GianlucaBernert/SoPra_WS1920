package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Membership;
import de.hdm.SoPra_WS1920.shared.bo.Person;

/*
 * Dies ist die Gruppenform-Klasse. Sie wird aufgerufen wenn entweder eine neue Gruppe erstellt werden soll, oder eine bereits existierende
 * Gruppe editiert werden soll.
 * @author SebastianHermann
 */
public class GroupForm extends DialogBox {
	
	/**
	 * Variablen der Klasse GroupForm
	 */
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
	Vector<Person> newGroupMembers;
	Vector<Person> groupMembersToBeDeleted;
	Vector<MemberRow> memberRows;
	
	SurveyManagementHeader header;
	SurveyContent content;
	Person groupAdmin;
	
	SurveyManagementAsync surveyManagementAdministration;
	
	/**
	 * Konstruktor der Klasse GroupForm
	 * @param groupCard
	 * @param group
	 */
	public GroupForm(GroupCard groupCard, Group group) {
		this.parentCard = groupCard;
		this.groupToShow = group;
		
	}
	
	/**
	 * Konstruktor der Klasse GroupForm
	 * @param header
	 * @param content
	 */
	public GroupForm(SurveyManagementHeader header, SurveyContent content) {
		this.header = header;
		this.content = content;
	}
	
	public void onLoad() {
		super.onLoad();
		
		groupAdmin = new Person();
		groupAdmin.setEMail(Cookies.getCookie("gmail"));
		groupAdmin.setFirstname(Cookies.getCookie("firstName"));
		groupAdmin.setLastname(Cookies.getCookie("lastName"));
		groupAdmin.setId(Integer.parseInt(Cookies.getCookie("userId")));
		
		surveyManagementAdministration = ClientsideSettings.getSurveyManagement();
		surveyManagementAdministration.getAllPersons(new GetAllPersonsCallback());
		
		groupMembers = new Vector<Person>();
		newGroupMembers = new Vector<Person>();
		groupMembersToBeDeleted = new Vector<Person>();
		memberRows = new Vector<MemberRow>();
		
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
		groupNameTextBox.getElement().setPropertyString("placeholder", "e.g. Friends");
		
		addMembersLabel = new Label(" Add Group Member:");
		addMembersLabel.setStyleName("TextBoxLabel");
		
		membersPanel = new FlowPanel();
		membersPanel.setStyleName("MembersPanel");
		allMembers = new MultiWordSuggestOracle();
		memberSuggestBox = new SuggestBox(allMembers);
		memberSuggestBox.getElement().setPropertyString("placeholder", "Start typing in mail adress...");
		memberSuggestBox.setStyleName("CardTextBox MemberTextBox");
				
		addIcon = new Image("/Images/png/001-add-button.png");
		addIcon.setStyleName("AddIcon");
		addIcon.addClickHandler(new AddMemberClickHandler(this));

		saveButton = new Button("Save");
		saveButton.setStyleName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler(this));
			
		formWrapper.add(cardDescription);
		formWrapper.add(groupNameLabel);
		formWrapper.add(groupNameTextBox);
		formWrapper.add(cancelIcon);
		formWrapper.add(addMembersLabel);
		formWrapper.add(memberSuggestBox);
		formWrapper.add(membersPanel);
		if(groupToShow!=null) {
			surveyManagementAdministration.getMembershipsOfGroup(groupToShow, new GetMembershipCallback(this));
			groupNameTextBox.setText(groupToShow.getName());
			deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
			deleteIcon.setStyleName("DeleteIcon");
			deleteLabel = new Label("Delete Group");
			deleteLabel.setStyleName("DeleteLabel");
			deleteIcon.addClickHandler(new DeleteGroupClickHandler(this));
			deleteLabel.addClickHandler(new DeleteGroupClickHandler(this));
			
			formWrapper.add(deleteIcon);
			formWrapper.add(deleteLabel);
		}else {
			MemberRow memberRow = new MemberRow(membersPanel, groupAdmin);
			memberRows.add(memberRow);
			membersPanel.add(memberRow);
		}
		
		formWrapper.add(saveButton);

		this.add(formWrapper);
		this.center();
		this.show();
	
	}
	
	/**
	 * Methode um die Mitglieder einer Gruppe anzuzeigen
	 */
	public void showMembers() {
		membersPanel.clear();
		for(Person p: groupMembers) {
			MemberRow memberRow = new MemberRow(membersPanel,p);
			memberRows.add(memberRow);
			membersPanel.add(memberRow);
		}
		
	}
	
	/**
	 * Klasse die eine Zeile erstellt in der ein Gruppen Mitglied angezeigt wird, mit der Moeglichkeit es zu editieren oder zu entfernen
	 * @author SebastianHermann
	 *
	 */
	class MemberRow extends FlowPanel{
		
		/**
		 * Variablen der Klasse MemberRow
		 */
		Image deleteIcon;
		Image adminIcon;
		Label fullNameLabel;
		
		Person p;
		FlowPanel memberPanel;
		
		/**
		 * Konstruktor der Klasse MemberRow
		 * @param memberPanel
		 * @param p
		 */
		public MemberRow(FlowPanel memberPanel, Person p) {
			this.memberPanel = memberPanel;
			this.p = p;
		}
		
		public void onLoad() {
			super.onLoad();
			this.setStyleName("MemberRow");
			
			if(groupToShow==null) {
					if(p.getId()==Integer.parseInt(Cookies.getCookie("userId"))) {
						adminIcon = new Image("/Images/png/002-user-1.png");
						adminIcon.setStyleName("MemberDeleteIcon");	
						this.add(adminIcon);
					}else {
						deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
						deleteIcon.setStyleName("MemberDeleteIcon");
						deleteIcon.addClickHandler(new DeleteMemberClickHandler(this));
						this.add(deleteIcon);
					}		
					newGroupMembers.add(p);
					

			}else if(p.getId()==groupToShow.getPersonFK()){
				adminIcon = new Image("/Images/png/002-user-1.png");
				adminIcon.setStyleName("MemberDeleteIcon");
				this.add(adminIcon);
			}else {
				deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
				deleteIcon.setStyleName("MemberDeleteIcon");
				deleteIcon.addClickHandler(new DeleteMemberClickHandler(this));
				this.add(deleteIcon);
			}
			
			fullNameLabel = new Label(p.getEMail());
			fullNameLabel.setStyleName("MemberLabel");
			
			
			this.add(fullNameLabel);
		}
		
		/**
		 * ClickHandler um ein Mitglied zu entfernen
		 * @author SebastianHermann
		 *
		 */
		class DeleteMemberClickHandler implements ClickHandler{
			MemberRow memberRow;
			
			public DeleteMemberClickHandler(MemberRow memberRow) {
				// TODO Auto-generated constructor stub
				this.memberRow = memberRow;
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				if(newGroupMembers.contains(memberRow.p)){
					newGroupMembers.remove(memberRow.p);
				}
				groupMembersToBeDeleted.add(memberRow.p);
				memberPanel.remove(memberRow);
			}
			
		}
		
	}
	
	/**
	 * Callback der bei Erfolg einen Vector von Personen zurueck gibt
	 * @author SebastianHermann
	 *
	 */
	class GetAllPersonsCallback implements AsyncCallback<Vector<Person>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the callback");
		}

		@Override
		public void onSuccess(Vector<Person> result) {
			// TODO Auto-generated method stub
			for(Person p: result) {
				allMembers.add(p.getEMail());
			}
		}
		
	}
	
	/**
	 * Methode die überprüft ob die angegebene Person bereits Mitglied ist
	 * @param person
	 * @return isMember
	 */
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

	/**
	 * Methode um die GrouForm anzuzeigen
	 */
	public void showGroupForm() {
		this.center();
		this.show();

	}
	
	/**
	 * Callback der bei Erfolg einen Vector von Memberships zurueck gibt
	 * @author SebastianHermann
	 *
	 */
	class GetMembershipCallback implements AsyncCallback<Vector<Membership>>{
		GroupForm groupForm;
		public GetMembershipCallback(GroupForm groupForm) {
			// TODO Auto-generated constructor stub
			this.groupForm = groupForm;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Membership> result) {
			// TODO Auto-generated method stub
			for(Membership m:result) {
				surveyManagementAdministration.getPersonById(m.getPersonFK(), new GetMembersCallback(groupForm));
			}
		}
		
	}
	
	/**
	 * Callback der bei Erfolg eine Person zurueck gibt
	 * @author SebastianHermann
	 *
	 */
	class GetMembersCallback implements AsyncCallback<Person>{
		GroupForm groupForm;
		public GetMembersCallback(GroupForm groupForm) {
			// TODO Auto-generated constructor stub
			this.groupForm = groupForm;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Person result) {
			// TODO Auto-generated method stub
				groupMembers.add(result);
				groupForm.showMembers();
				
		}
	}
		
	/**
	 * ClickHandler zum entfernen einer Gruppe
	 * @author SebastianHermann
	 *
	 */
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
	
	/**
	 * ClickHandler zum speichern von Gruppen
	 * @author SebastianHermann
	 *
	 */
	class SaveClickHandler implements ClickHandler {
		GroupForm gf;
		
		public SaveClickHandler(GroupForm gf) {
			this.gf = gf;
			
		}

		@Override
		public void onClick(ClickEvent event) {
			if(gf.groupNameTextBox.getText().length()>2)
				if(groupToShow==null) {
					surveyManagementAdministration.createGroup(groupNameTextBox.getText(), Integer.parseInt(Cookies.getCookie("userId")) , new CreateGroupCallback(gf));	
				}else {
					groupToShow.setName(groupNameTextBox.getText());
					for(Person p: newGroupMembers) {
						surveyManagementAdministration.createMembership(groupToShow, p, new AddMembersCallback());
					}
					
					for(Person p: groupMembersToBeDeleted) {
						surveyManagementAdministration.deleteMembership(groupToShow.getId(), p.getId(), new DeleteMembershipCallback());
					}
					surveyManagementAdministration.updateGroup(groupToShow, new UpdateGroupCallback(gf));
					
				}
			else {
				Window.alert("Group name must have at least 3 characters.");
			}
		}
	}
	
	/**
	 * Callback der bei Erfolg die GroupForm updated
	 * @author gianluca
	 *
	 */
	class UpdateGroupCallback implements AsyncCallback<Group>{
		GroupForm gf;
		public UpdateGroupCallback(GroupForm gf) {
			// TODO Auto-generated constructor stub
			this.gf = gf;
		}

		@Override
		public void onFailure(Throwable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Group result) {
			// TODO Auto-generated method stub
			parentCard.showGroupCardView(result);
			gf.hide();
		}
		
	}
	
	/**
	 * Callback der bei Erfolg  eine Membership entfernt
	 * @author SebastianHermann
	 *
	 */
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
		
	/**
	 * Callback der bei Erfolg eine Gruppe erstellt
	 * @author SebastianHermann
	 *
	 */
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

			for(Person p: newGroupMembers) {
				surveyManagementAdministration.createMembership(result, p, new AddMembersCallback());
			}
			if(parentCard==null) {
				parentCard = new GroupCard(content,result);
//				parentCard.showGroupCardView(groupToShow);
				content.add(parentCard);
				gf.hide();
			}else {
				parentCard.showGroupCardView(groupToShow);
				gf.hide();
			}
//			content.showGroups();
		}
	}
		
	/**
	 * Callback der bei Erfolg die Member der Gruppe hinzufuegt
	 * @author SebastianHermann
	 *
	 */
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
				
	/**
	 * ClickHandler um Member einer Gruppe hinzuzufuegen
	 * @author SebastianHermann
	 *
	 */
	class AddMemberClickHandler implements ClickHandler{
		GroupForm gf;
		
		public AddMemberClickHandler(GroupForm gf) {
			this.gf = gf;		
		}

		@Override
		public void onClick(ClickEvent event) {
			if((gf.memberSuggestBox.getValue().length())>1) {
				surveyManagementAdministration.getPersonByEmail(gf.memberSuggestBox.getValue(), new GetPersonCallback(gf));
			}else {
				Window.alert("Please enter a valid e-Mail");
			}	
		}
	}
	
	/**
	 * Callback der bei Erfolg eine Person zurueck gibt
	 * @author SebastianHermann
	 *
	 */
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
//			if(gf.checkforMembership(result)==false) {
			if((!groupMembers.contains(result)&&!newGroupMembers.contains(result))||(groupMembers.contains(result)&&groupMembersToBeDeleted.contains(result))) {
//				gf.groupMembers.add(result);
				if(groupMembers.contains(result)&&groupMembersToBeDeleted.contains(result)) {
					groupMembersToBeDeleted.remove(result);
				}
				MemberRow newMember = new MemberRow(gf.membersPanel,result);
				newGroupMembers.add(result);
				gf.memberRows.add(newMember);
				gf.membersPanel.add(newMember);
				gf.memberSuggestBox.setText("");
			}else {
				Window.alert("User is allready a member.");
			}
		}
		
	}
	
	/**
	 * ClickHandler um eine Gruppe zu entfernen
	 * @author SebastianHermann
	 *
	 */
	class DeleteGroupClickHandler implements ClickHandler{
		
		GroupForm gf;
		
		public DeleteGroupClickHandler(GroupForm gf) {
			this.gf = gf;
		}

		@Override
		public void onClick(ClickEvent event) {
			surveyManagementAdministration.deleteGroup(groupToShow, new DeleteGroupCallback(gf));
			
		}
		
		
	}
	
	/**
	 * Callback der bei Erfolg die Gruppe entfernt
	 * @author SebatianHermann
	 *
	 */
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
			parentCard.remove();
			
		}
		
	

	}
}

		
	
	
	


	
	
	
	
	

