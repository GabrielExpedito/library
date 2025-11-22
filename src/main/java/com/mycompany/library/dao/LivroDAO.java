/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.dao;

import com.mycompany.library.config.HibernateUtil;
import com.mycompany.library.model.model.entity.Livro;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gabriel Expedito
 */
public class LivroDAO {
    
    public void salvarLivro(Livro livro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        session.persist(livro);
        
        transaction.commit();
        session.close();
    }
    
}
