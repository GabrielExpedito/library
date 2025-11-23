/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.dao;

import com.mycompany.library.model.entity.Livro;
import com.mycompany.library.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import java.util.Date;

/**
 *
 * @author Gabriel Expedito
 */
public class LivroDAO {

    private EntityManager getEntityManager() {
        return HibernateUtil.obterEntityManager();
    }

    public void salvarLivro(Livro livro) {
        EntityManager em = getEntityManager();

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

    public List<Livro> consultarLivro(String titulo, String autor,
            String isbn, String editora, String classificacao) {
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Livro> query = criteriaBuilder.createQuery(Livro.class);
            Root<Livro> root = query.from(Livro.class);

            List<Predicate> predicates = new ArrayList<>();

            if (titulo != null && !titulo.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("titulo"), "%"
                        + titulo + "%"));
            }

            if (autor != null && !autor.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("autor"), "%"
                        + autor + "%"));
            }

            if (isbn != null && !isbn.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("isbn"), "%"
                        + isbn + "%"));
            }

            if (editora != null && !editora.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("editora"), "%"
                        + editora + "%"));
            }

            if (classificacao != null && !classificacao.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("classificacao"),
                        "%" + classificacao + "%"));
            }
            
            Predicate[] arrayPredicates = predicates.toArray(new Predicate[0]);
            query.where(arrayPredicates);
            
            return em.createQuery(query).getResultList();

        } finally {
            em.close();
        }

    }

}
