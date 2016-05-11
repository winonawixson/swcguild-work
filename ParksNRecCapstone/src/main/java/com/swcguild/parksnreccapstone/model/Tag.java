package com.swcguild.parksnreccapstone.model;

import java.util.Objects;

/**
 *
 * @author Patrick Culp
 */
public class Tag {
    
    private int iD;
    private String tag;

    public Tag() {
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.iD;
        hash = 29 * hash + Objects.hashCode(this.tag);
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
        final Tag other = (Tag) obj;
        if (this.iD != other.iD) {
            return false;
        }
        if (!Objects.equals(this.tag, other.tag)) {
            return false;
        }
        return true;
    }
    
}
