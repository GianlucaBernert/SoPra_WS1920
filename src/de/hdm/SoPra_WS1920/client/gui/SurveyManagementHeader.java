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
	Button createSurvey;
	SearchBox searchBox;
	
	GroupForm gf;
	SurveyForm sf;
	
	SurveyContent content;
	
	public SurveyManagementHeader(SurveyContent content) {
		this.content = content;
	}
	
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("header");
		//createBo.addClickHandler(new CreateBoClickHandler(this, gf));
		//createSurvey.addClickHandler(new CreateSurveyClickHandler(this, sf));
		
	}
		class CreateBoClickHandler implements ClickHandler{
			SurveyManagementHeader header;
			SurveyContent content;
			GroupForm gf;
			
			public CreateBoClickHandler(SurveyManagementHeader header, SurveyContent content, GroupForm gf) {
				this.header = header;
				this.content = content;
				this.gf = gf;
						
			}
			
		

			@Override
			public void onClick(ClickEvent event) {
				GroupForm gf = new GroupForm();
				gf.showGroupForm();
				
			}
		
		}
		
		class CreateSurveyClickHandler implements ClickHandler{
			
			public CreateSurveyClickHandler(SurveyManagementHeader header, SurveyForm sf) {
				
			}

			@Override
			public void onClick(ClickEvent event) {
				SurveyForm sf = new SurveyForm();
				sf.showSurveyForm();
				
				
				
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
			createBo.addClickHandler(new CreateBoClickHandler(this, content, gf));
			
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
		//	createBo.addClickHandler(new CreateSurveyClickHandler(this, content));
			
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
		

			
		
	


