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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CinemaChain) {
			CinemaChain cH= (CinemaChain) obj;
			if((this.getId()== cH.getId())){				
				return true;	
			}
			else {
				return false;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = this.getId();
		return result;
	}

}
