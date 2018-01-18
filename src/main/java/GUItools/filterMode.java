package main.java.GUItools;

import java.io.Serializable;

public class filterMode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean andORor;
	public boolean NOT;
	public boolean idF;
	public boolean locF;
	public boolean dateF;
	
	public filterMode(boolean andORor,boolean NOT,
					boolean idF,boolean locF,boolean dateF) {
		this.andORor = andORor;
		this.NOT = NOT;
		this.idF = idF;
		this.locF = locF;
		this.dateF = dateF;
	}
}
