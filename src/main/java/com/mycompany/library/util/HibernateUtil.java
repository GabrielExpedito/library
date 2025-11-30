package com.mycompany.library.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe que cria e gerencia o acesso do Hibernate através do SessionFactory
 * 
 * @author Gabriel Expedito
 */
public class HibernateUtil {
    
    private static final EntityManagerFactory emf;
    
    static {
        try {
            emf = Persistence.createEntityManagerFactory("Biblioteca");
        } catch (Throwable e) {
            System.err.println("Falha ao inicializar EntityManagarFactory" + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static EntityManager obterEntityManager() {
            return emf.createEntityManager();
        }
    
    public static void close() {
        emf.close();
    }
        
    
}
