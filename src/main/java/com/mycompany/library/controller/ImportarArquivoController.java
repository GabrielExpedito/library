package com.mycompany.library.controller;

import com.mycompany.library.dao.LivroDAO;
import com.mycompany.library.model.entity.Classificacao;
import com.mycompany.library.model.entity.Livro;
import com.mycompany.library.views.ImportacaoArquivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Expedito
 */
public class ImportarArquivoController {
    
    private LivroDAO livroDAO;
    
    private final ImportacaoArquivo importadorTela;
    
    public ImportarArquivoController(ImportacaoArquivo arquivo) {
        this.importadorTela = arquivo;
        this.livroDAO = new LivroDAO();
    }
    
    public void abrirFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecione arquivo CSV ou XML");
        
        int arquivoEscolhido = chooser.showOpenDialog(null);
        
        if (arquivoEscolhido == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            String nomeArquivo = arquivo.getName().toLowerCase();
            
            try {
                if(nomeArquivo.endsWith(".csv")) {
                    importarCSV(arquivo);
                    importadorTela.mensagem("Importação do arquivo CSV realizada");
                } else {
                    importadorTela.mensagem("Formato não reconhecido");
                }
            } catch (Exception e) {
                importadorTela.mensagem("Erro ao importar " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    public void importarCSV(File arquivo) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;
        List<Livro> livros = new ArrayList<>();
        
        br.readLine();
        
        while((linha = br.readLine()) != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            String[] dados = linha.split(",");
            
            Livro livro1 = new Livro();
            livro1.setTitulo(dados[1]);
            livro1.setAutor(dados[2]);
            
            //Conversão do String do CSV para Date conforme entidade
            Date data = simpleDateFormat.parse(dados[3]);
            livro1.setDataPublicacao(data);
            
            livro1.setIsbn(dados[4]);
            livro1.setEditora(dados[5]);
            
            //Conversão do String do CSV em ENUM para Classificação
            String classificacaoTxt = dados[6].trim().toUpperCase();
            
            try {
                Classificacao classificacao = Classificacao.valueOf(classificacaoTxt);
                livro1.setClassificacao(classificacao);
            } catch (Exception e) {
                throw new RuntimeException("Classificação inválida "
                        + "dentro do CSV: " + classificacaoTxt);
            }
            livros.add(livro1);
        }
        br.close();
        for (Livro livro : livros) {
            livroDAO.salvarLivro(livro);
        }
    }
    
    
    
    
    
}
