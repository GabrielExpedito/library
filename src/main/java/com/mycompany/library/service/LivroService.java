/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.service;

import com.mycompany.library.dao.LivroDAO;
import com.mycompany.library.model.entity.Livro;
import java.util.List;

/**
 *
 * @author Gabriel Expedito
 */
public class LivroService {
    
    private LivroDAO livroDAO = new LivroDAO();
    
    public void salvarLivro(Livro livro) {
        livroDAO.salvarLivro(livro);
    }
    
    
}
