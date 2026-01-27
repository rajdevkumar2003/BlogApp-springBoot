package com.example.demo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blog")
public class Blog {
    @Id
    private ObjectId id;

    private String title;

    private String content;

    @DBRef
    private ObjectId createdBy;

    public ObjectId getCreatedBy() {
        return createdBy;
    }

    public Blog(String title, String content, ObjectId createdBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public void setCreatedBy(ObjectId createdBy) {
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
