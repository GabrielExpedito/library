/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.dao;

import com.mycompany.library.model.entity.Livro;
import com.mycompany.library.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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

    public List<Livro> consutarTodosLivros() {
        EntityManager em = getEntityManager();

        try {
            return getEntityManager().createQuery("FROM " + 
                    Livro.class.getName()).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao consultar todos os livros: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (em != null) {
                em.close();
            }
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

    public void deletarLivro(int id) {
        EntityManager em = getEntityManager();

        try {
            String hql = "DELETE FROM Livro l WHERE l.id = :id";
            Query query = em.createQuery(hql);

            query.setParameter("id", id);

            em.getTransaction().begin();

            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao deletar o livro");
            e.printStackTrace();

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();

            }
        }

    }
    
    public void editarLivro(Livro livro) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(livro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao editar o livro");
            e.printStackTrace();
            
        }finally {
            if(em != null){
                em.close();
            }
        }
        
    }

}
