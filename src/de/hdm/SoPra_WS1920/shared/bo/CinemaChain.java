package de.hdm.SoPra_WS1920.shared.bo;

public class CinemaChain extends Ownership{
	
	private String name;
	
	public CinemaChain() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
   		return "CinemaChainID #CC" + this.getId() + " " + this.getName();
   	}

}
