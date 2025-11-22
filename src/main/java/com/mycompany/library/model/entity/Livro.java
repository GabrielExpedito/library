package com.mycompany.library.model.entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

/**
 *
 * @author Gabriel Expedito
 */
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private Date dataPublicacao;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String editora;

    @Column(nullable = false)
    private String classificacao;

    /**
     * Métodos construtores com ou sem argumentos
     */
    public Livro() {
    }

    public Livro(int id, String titulo, String autor, Date dataPublicacao, String isbn, String editora, String classificacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.editora = editora;
        this.classificacao = classificacao;
    }

    public Livro(String titulo, String autor, Date dataPublicacao, String isbn, String editora, String classificacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.editora = editora;
        this.classificacao = classificacao;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", title=" + titulo + ", author=" + autor
                + ", publicationDate=" + dataPublicacao + ", isbn=" + isbn
                + ", publisher=" + editora;
    }

}
