package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Group;

public class GroupCard extends FlowPanel {
	
	Group groupToShow;
	FlowPanel main;
	GroupCardView groupCardView;
	GroupCardEdit groupCardEdit;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("moviecard");
		this.showGroupCardView(groupToShow);
		groupCardView = new GroupCardView(this, groupToShow);
		this.showGroupCardView(groupToShow);
	}
	
	public GroupCard(Group groupToShow) {
		this.groupToShow = groupToShow;
		
	}
	
	public GroupCard(FlowPanel main,Group groupToShow) {
		this.main = main;
		this.groupToShow = groupToShow;
	}
	
	public void showGroupCardView(Group groupToShow) {
		this.groupToShow = groupToShow;
		this.clear();
		this.add(new GroupCardView(this,groupToShow));
	}
	
	public void showGroupCardEdit(Group groupToShow) {
		this.groupToShow = groupToShow;
		this.clear();
		this.add(new GroupCardEdit(this, groupToShow));
	}
	
	public void remove() {
		main.remove(this);
	}
	

}
