package com.oreilly.entities;

import org.springframework.stereotype.Component;

@Component
public class Cubs implements Team {
    
    private String name = "Chicago Cubs";
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name){
    	this.name = name;
    }
    public String toString() {
        return getName();
    }
}
