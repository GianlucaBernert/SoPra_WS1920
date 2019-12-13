package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class GroupContent extends FlowPanel{
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("content");
	}
	
	public void showGroups() {
		this.clear();
		this.add(new Label("Groups"));
	}
	
	public void showSurveys() {
		this.clear();
		this.add(new Label("Surveys"));
	}
	
	

}

