package com.mycompany.library.dao;

import com.mycompany.library.model.entity.Livro;
import com.mycompany.library.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;

/**
 * Classe responsável por realizar as operações de persistência no banco da
 * Entidade Livro com JPA e Hibernate
 *
 * <p>
 * Este DAO possuí as ações relacionadas ao gerenciamento de livros no banco de
 * dados como salvar, consultar, editar e deletar</p>
 *
 * @author Gabriel Expedito
 */
public class LivroDAO {

    /**
     * Instância um EntityManager a partir do Hibernate.
     *
     * @return novo EntityManager para operações em JPA
     */
    private EntityManager getEntityManager() {
        return HibernateUtil.obterEntityManager();
    }

    /**
     * Persiste um objeto Livro no banco de dados.
     *
     * @param livro
     */
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

    /**
     * Consulta todos os livros salvos no banco de dados.
     *
     * @return lista contendo todos os livros encontrados
     */
    public List<Livro> consutarTodosLivros() {
        EntityManager em = getEntityManager();

        try {
            return getEntityManager().createQuery("FROM "
                    + Livro.class.getName()).getResultList();
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

    /**
     * Consulta livros aplicando filtro de pesquisa dinâmica.
     *
     * <p>
     * É utilizado critérios JPA para montar a consulta apenas com os campos que
     * foram preenchidos pelo usuário.</p>
     *
     * @param titulo
     * @param autor
     * @param isbn
     * @param editora
     * @param dataPublicacao
     * @param classificacao
     * @return Um lista dos livros com base nos filtros
     */
    public List<Livro> consultarLivro(Integer id, String titulo, String autor,
            String isbn, String editora, LocalDate dataPublicacao,
            String classificacao) {
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Livro> query = criteriaBuilder.createQuery(Livro.class);
            Root<Livro> root = query.from(Livro.class);

            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

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

            if (dataPublicacao != null) {
                predicates.add(criteriaBuilder.equal(root.get("dataPublicacao"), 
                        dataPublicacao)); 
            }

            if (classificacao != null && !classificacao.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("classificacao"),
                        "%" + classificacao + "%"));
            }

            if (!predicates.isEmpty()) {
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[0]);
                query.where(arrayPredicates);
            }

            return em.createQuery(query).getResultList();

        } finally {
            em.close();
        }

    }

    /**
     * Exclui um livro do banco de dados com base no ID
     *
     * <p>
     * Este método utiliza uma query HQL para realizar uma remoção diretamente
     * sem necessidade de carregar o objeto</p>
     *
     * @param id identificador do livro a ser removido
     */
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

    /**
     * Atualiza os dados de um livro existente no banco
     *
     * @param livro objeto contendo os novos valores a serem persistidos
     */
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

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

}
