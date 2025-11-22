/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.dao;

import com.mycompany.library.model.entity.Livro;
import com.mycompany.library.model.entity.Livro;
import com.mycompany.library.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gabriel Expedito
 */
public class LivroDAO {
    
    public void salvarLivro(Livro livro) {
        EntityManager em = HibernateUtil.obterEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
            
    }
    
    
}
