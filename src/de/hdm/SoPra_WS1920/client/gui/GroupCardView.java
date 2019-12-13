package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import de.hdm.SoPra_WS1920.shared.bo.Group;

public class GroupCardView extends FlowPanel {
	
	Label groupName;
	Label member;
	Button edit;
	Group groupToShow;
	GroupCard parentCard;
	Image editIcon;
	FlowPanel editIconWrapper;
	
	public GroupCardView(GroupCard groupCard, Group groupToShow) {
		this.parentCard = groupCard;
		this.groupToShow = groupToShow;
		
		}
	
	public void onLoad() {
		super.onLoad();
		
		groupName = new Label(groupToShow.getName());
		groupName.setStylePrimaryName("title");
		member = new Label(groupToShow.getName());
		member.setStylePrimaryName("subtitle");
		edit = new Button("");
		edit.setStylePrimaryName("invisibleButton");
		editIcon = new Image("/Images/004-edit.svg");
		editIcon.setStyleName("icon");
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
				parentCard.showGroupCardView(groupToShow);
				
			}
			
		}
		
		
		
	}



