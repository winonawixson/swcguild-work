package com.swcguild.parksnreccapstone.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Patrick Culp
 */
public class BlogPost {

    private int id;
    private LocalDate dateTime;
    private String title;
    private String author;
    private String postContent;
    private List<Tag> tags;
    private Category category;
    private boolean approved;

    public BlogPost() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 47 * hash + this.id;
//        hash = 47 * hash + Objects.hashCode(this.dateTime);
//        hash = 47 * hash + Objects.hashCode(this.title);
//        hash = 47 * hash + Objects.hashCode(this.author);
//        hash = 47 * hash + Objects.hashCode(this.postContent);
//        hash = 47 * hash + Objects.hashCode(this.tags);
//        hash = 47 * hash + Objects.hashCode(this.category);
//        hash = 47 * hash + (this.approved ? 1 : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final BlogPost other = (BlogPost) obj;
//        if (this.id != other.id) {
//            return false;
//        }
//        if (this.approved != other.approved) {
//            return false;
//        }
//        if (!Objects.equals(this.title, other.title)) {
//            return false;
//        }
//        if (!Objects.equals(this.author, other.author)) {
//            return false;
//        }
//        if (!Objects.equals(this.postContent, other.postContent)) {
//            return false;
//        }
//        if (!Objects.equals(this.dateTime, other.dateTime)) {
//            return false;
//        }
//        if (!Objects.equals(this.tags, other.tags)) {
//            return false;
//        }
//        if (!Objects.equals(this.category, other.category)) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.author);
        hash = 17 * hash + Objects.hashCode(this.postContent);
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
        final BlogPost other = (BlogPost) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.postContent, other.postContent)) {
            return false;
        }
        return true;
    }


}
