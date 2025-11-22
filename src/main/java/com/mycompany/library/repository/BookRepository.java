package com.mycompany.library.repository;

import com.mycompany.library.model.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gabriel Expedito
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
}
