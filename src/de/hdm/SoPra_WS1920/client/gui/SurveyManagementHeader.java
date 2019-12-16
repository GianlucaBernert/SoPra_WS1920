package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;




public class SurveyManagementHeader extends FlowPanel {
	
	Label headline;
	Button createBo;
	SearchBox searchBox;
	
	GroupForm gf;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("header");
		createBo.addClickHandler(new CreateBoClickHandler(this, gf));
		
	}
		class CreateBoClickHandler implements ClickHandler{
			
			public CreateBoClickHandler(SurveyManagementHeader header, GroupForm gf) {
				
			}

			@Override
			public void onClick(ClickEvent event) {
				GroupForm gf = new GroupForm();
				gf.showGroupForm();
				
			}
		
		}
			
		
		class SearchBox extends FlowPanel {
			TextBox searchText;
			Button submitSearch;
			Image searchIcon;

		
		public void onLoad() {
			super.onLoad();
			this.setStylePrimaryName("searchBox");
			
			searchText = new TextBox();
			searchText.setStylePrimaryName("searchText");
			searchText.getElement().setPropertyString("placeholder", "Search...");
			
			
			submitSearch = new Button();

			searchIcon = new Image("/Images/search.png");
			searchIcon.setStyleName("searchIcon");

			this.add(searchIcon);
			this.add(searchText);
		}
		
		}
		
		public void showGroupHeader() {
			this.clear();
			headline = new Label("Groups");
			headline.setStylePrimaryName("headline");
			
			searchBox = new SearchBox();
			
			createBo = new Button("+Add Group");
			createBo.setStylePrimaryName("createBoButton");
			createBo.addClickHandler(new CreateBoClickHandler(this, gf));
			
			this.add(headline);
			this.add(createBo);
			this.add(searchBox);
		}
		

		
		public void showSurveyHeader() {
			this.clear();
			headline = new Label("Survey");
			headline.setStylePrimaryName("headline");
			
			searchBox = new SearchBox();
			
			createBo = new Button("+Add Survey");
			createBo.setStylePrimaryName("createBoButton");
			createBo.addClickHandler(new CreateBoClickHandler(this, gf));
			
			this.add(headline);
			this.add(createBo);
			this.add(searchBox);
		}
		

		public void showMoviesHeader(){
			this.clear();
			headline = new Label("Movies");
			headline.setStylePrimaryName("headline");
			
			searchBox = new SearchBox();
			
			this.add(headline);
		}
		
		}
		

			
		
	


