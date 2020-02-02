package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Membership;

/**
 * Klasse, die den Inhalt einer GroupCard zum Anzeigen einer Gruppe erzeugt
 * @author SebastianHermann
 *
 */
public class GroupCardView extends FlowPanel {
	
	/**
	 * Variablen der Klasse GroupCardView
	 */
	Label groupName;
	Label member;
	Button edit;
	Group groupToShow;
	GroupCard parentCard;
	Image editIcon;
	FlowPanel editIconWrapper;
	
	SurveyManagementAsync surveyManagementAdministration;
	
	/**
	 * Konstruktor der Klasse GroupCardView
	 * @param groupCard
	 * @param groupToShow
	 */
	public GroupCardView(GroupCard groupCard, Group groupToShow) {
		this.parentCard = groupCard;
		this.groupToShow = groupToShow;
		
		}
	
	public void onLoad() {
		super.onLoad();
		surveyManagementAdministration = ClientsideSettings.getSurveyManagement();
		surveyManagementAdministration.getMembershipsOfGroup(groupToShow, new GetMembershipsOfGroup());
		
		groupName = new Label(groupToShow.getName());
		groupName.setStyleName("CardViewTitle");
		member = new Label("");
		member.setStyleName("CardViewSubTitle");
		edit = new Button("");
		this.add(groupName);
		this.add(member);
		if(groupToShow.getPersonFK()==Integer.parseInt(Cookies.getCookie("userId"))) {
			edit.setStyleName("InvisibleButton");
			editIcon = new Image("/Images/png/006-pen.png");
			editIcon.setStyleName("EditIcon");
			editIcon.addClickHandler(new EditClickHandler(this));
			this.add(edit);
			this.add(editIcon);
		}
		
	}
	/**
	 * ClickHandler zum Editieren einer Gruppe
	 * @author SebastianHermann
	 *
	 */
		class EditClickHandler implements ClickHandler{
			
			GroupCardView groupCardView;
			
			public EditClickHandler(GroupCardView groupCardView) {
				this.groupCardView = groupCardView;
			}
			

			@Override
			public void onClick(ClickEvent event) {
				parentCard.showGroupCardForm(groupToShow);
				
			}
			
		}
		
		/**
		 * Callback der bei Erfolg einen Vector von Memberships der Gruppe zurueck gibt
		 * @author SebastianHermann
		 *
		 */
		class GetMembershipsOfGroup implements AsyncCallback<Vector<Membership>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Membership> result) {
				// TODO Auto-generated method stub
				member.setText("Group Members: "+Integer.toString(result.size()));
			}
			
		}
	
	}



