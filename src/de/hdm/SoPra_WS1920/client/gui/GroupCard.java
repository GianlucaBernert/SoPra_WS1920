package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Group;

public class GroupCard extends FlowPanel {
	
	Group groupToShow;
	SurveyContent content;
	
	GroupCardView groupCardView;
	GroupCardEdit groupCardEdit;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("Card CinemaCardView");
		this.showGroupCardView(groupToShow);
		
	}
	
	public GroupCard(SurveyContent content, Group groupToShow) {
		this.groupToShow = groupToShow;
		this.content = content;
	}
	
	public void showGroupCardView(Group groupToShow) {
		this.groupToShow = groupToShow;
		this.clear();
		this.add(new GroupCardView(this,groupToShow));
	}
	
	public void showGroupCardEdit(Group groupToShow) {
		this.groupToShow = groupToShow;
		GroupCardEdit groupCardEdit = new GroupCardEdit(this, groupToShow);
		groupCardEdit.center();
		groupCardEdit.show();
	}
	
	public void remove() {
		content.remove(this);
	}
	

}
