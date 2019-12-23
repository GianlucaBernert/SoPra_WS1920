package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.gui.GroupForm.CancelClickHandler;
import de.hdm.SoPra_WS1920.client.gui.GroupForm.SaveClickHandler;

public class SurveyForm extends DialogBox {
	
	SurveyManagementHeader header;
	SurveyForm sf;
	
	Label movie;
	SuggestBox movielist;
	TextBox movieTextBox;
	Image cancelIcon;
	Image saveIcon;
	Image addIcon;
	FlowPanel main;
	
	public SurveyForm() {
		
	}
		
		public void onLoad() {
			super.onLoad();
			
			FlowPanel main = new FlowPanel();
			this.setStylePrimaryName("moviecard");
			
			movie = new Label("Movie:");
			movie.setStylePrimaryName("inputLabel");
			
			//movielist = new SuggestBox();
			//this.setStylePrimaryName("inputTextBox");
			
			movieTextBox = new TextBox();
			this.setStylePrimaryName("inputTextBox");
			
			cancelIcon = new Image("/Images/001-unchecked.svg");
			cancelIcon.setStyleName("cancelIcon");
			cancelIcon.addClickHandler(new CancelClickHandler(this));
			
			saveIcon = new Image("/Images/002-checked.svg");
			saveIcon.setStylePrimaryName("saveIcon");
			saveIcon.addClickHandler(new SaveClickHandler(this));
			
			main.add(movie);
			//main.add(movielist);
			main.add(movieTextBox);
			main.add(cancelIcon);
			main.add(saveIcon);
			
			this.add(main);
			
			this.show();
			
		}
		
		public void showSurveyForm() {
			this.show();
			this.center();
		}
		
		class CancelClickHandler implements ClickHandler {
			SurveyForm sf;
			
			public CancelClickHandler(SurveyForm sf) {
				this.sf = sf;
			}

			@Override
			public void onClick(ClickEvent event) {
				sf.hide();
			
				
				
			}
			
		}
		
		class SaveClickHandler implements ClickHandler {
			
			public SaveClickHandler(SurveyForm sf) {
				
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}

			
			
		
	}

}
