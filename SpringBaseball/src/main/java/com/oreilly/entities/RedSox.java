package com.oreilly.entities;

import org.springframework.stereotype.Component;

@Component
public class RedSox implements Team {
    @Override
    public String getName() {
        return "Boston Red Sox";
    }
    public void setName(String name) {
    	
    }
    public String toString() {
        return getName();
    }
}