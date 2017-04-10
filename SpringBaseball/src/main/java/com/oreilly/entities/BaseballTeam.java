package com.oreilly.entities;
/**
 * This represents a class that can be used as a prototype
 * bean.
 * 
 *
 */
public class BaseballTeam implements Team {
	/**
	 * Property name;
	 */
	private String name;
	/**
	 * Getter for property name.
	 * @return name of team
	 */
	@Override
    public String getName() { return name;}
    /**
     * Setter for property name.
     * @param name value to be used for name of team
     */
	@Override
    public void setName(String name) { this.name = name; } 
}

