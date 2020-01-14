package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Group;

public class GroupCard extends FlowPanel {
	
	Group groupToShow;
	SurveyContent content;
	
	GroupCardView groupCardView;
	GroupForm groupGroupForm;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("Card CinemaCardView");
		this.showGroupCardView(groupToShow);
		
	}
	
	public GroupCard(SurveyContent content, Group groupToShow) {
		this.content = content;
		this.groupToShow = groupToShow;
	}
	
	public GroupCard() {
		
	}
	
	public void showGroupCardView(Group groupToShow) {
		this.groupToShow = groupToShow;
		this.clear();
		this.add(new GroupCardView(this,groupToShow));
	}
	
	public void showGroupCardForm(Group groupToShow) {
		this.groupToShow = groupToShow;
		GroupForm groupForm = new GroupForm(this,groupToShow);
		groupForm.center();
		groupForm.show();
	}
	
	public void remove() {
		content.remove(this);
	}
	

}
