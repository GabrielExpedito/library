/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.model.model.entity;

import jakarta.persistence.Entity;
import java.util.Date;

/**
 *
 * @author Gabriel Expedito
 */
@Entity
public class book {
    
    private int id;
    
    private String title;
    
    private String author;
    
    private Date publicationDate;
    
    private String isbn;
    
    private String publisher;
    
    private Tag tag;

    
    /**
     * Métodos construtores com ou sem argumentos
     */
    public book() {
    }

        
    public book(int id, String title, String author, Date publicationDate, String isbn, String publisher, Tag tag) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.publisher = publisher;
        this.tag = tag;
    }

    public book(String title, String author, Date publicationDate, String isbn, String publisher, Tag tag) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.publisher = publisher;
        this.tag = tag;
    }

    public book(String title, String author, Date publicationDate, String isbn, String publisher) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public book(String title, String author, String isbn, String publisher) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
    }

   
    /**
     * Métodos Getters e Setters
     *
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    
    /**
     * Método toString
     */
    @Override
    public String toString() {
        return "book{" + "id=" + id + ", title=" + title + ", author=" + author 
                + ", publicationDate=" + publicationDate + ", isbn=" + isbn 
                + ", publisher=" + publisher + ", tag=" + tag + '}';
    }
    
    
    
    

    
    
    
           
    
    
}
