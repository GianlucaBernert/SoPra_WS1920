package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Membership;

public class GroupCardView extends FlowPanel {
	
	Label groupName;
	Label member;
	Button edit;
	Group groupToShow;
	GroupCard parentCard;
	Image editIcon;
	FlowPanel editIconWrapper;
	
	SurveyManagementAsync surveyManagementAdministration;
	
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
		edit.setStyleName("InvisibleButton");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.setStyleName("EditIcon");
		editIcon.addClickHandler(new EditClickHandler(this));
		
		this.add(groupName);
		this.add(member);
		this.add(edit);
		this.add(editIcon);
		
	}
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



