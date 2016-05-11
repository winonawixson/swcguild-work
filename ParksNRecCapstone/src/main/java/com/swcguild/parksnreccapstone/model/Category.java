package com.swcguild.parksnreccapstone.model;

import java.util.Objects;

/**
 *
 * @author Patrick Culp
 */
public class Category {
    
    private int iD;
    private String category;

    public Category() {
        
    }
    
    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.iD;
        hash = 53 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.iD != other.iD) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }
    
}
