/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.service;

import com.mycompany.library.model.model.entity.Book;
import com.mycompany.library.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel Expedito
 */
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void insertBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBook() {
        return bookRepository.findAll();
    }
    
    //Fazer uma consulta com o criteria para a query dinâmica

    public void deleteBook(Integer ID) {
        bookRepository.deleteById(ID);
    }

    public Book updateBook(Integer id, Book bookDetails) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setAuthor(bookDetails.getAuthor());
            existingBook.setPublisher(bookDetails.getPublisher());
            existingBook.setPublicationDate(bookDetails.getPublicationDate());
            
            return bookRepository.save(existingBook);
        } else {
            return null;
        }
    }

}
