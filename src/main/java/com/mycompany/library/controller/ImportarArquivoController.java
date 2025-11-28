package com.mycompany.library.controller;

import com.mycompany.library.dao.LivroDAO;
import com.mycompany.library.model.entity.Classificacao;
import com.mycompany.library.model.entity.Livro;
import com.mycompany.library.views.ImportacaoArquivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *  Classe controller para realizar o processamento de Importação de arquivo CSV
 * 
 * <p><b>Formato esperado do arquivo: CSV</b></p>
 * 
 * Requisitos do arquivo:
 * <ul>
 *  <li><b>dataPublicacao</b> estar no formato dd/MM/yyyy</li>
 *  <li><b>classificacao</b> deve corresponder a um valor válido do ENUM
 * 
 * @author Gabriel Expedito
 */
public class ImportarArquivoController {
    
    /**
     * Importação do DAO que busca operações com o Banco de Dados
     * */
    private LivroDAO livroDAO;
    
    private final ImportacaoArquivo importadorTela;
    
    /**
     * Construtor da classe que recebe a tela de importação de arquivo
     * 
     * @param arquivo  
     */
    public ImportarArquivoController(ImportacaoArquivo arquivo) {
        this.importadorTela = arquivo;
        this.livroDAO = new LivroDAO();
    }
    
    
    /**
     * Abre a seleção de arquivos com o (FileChooser) para que seja importado
     * um arquivo CSV
     * 
     * <p>Após a seleção o método valida o tipo do arquivo para ser feito o 
     * processamento
     * </p>
     */
    public void abrirFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecione arquivo CSV");
        
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
    
    
    /**
     * Realiza a leitura e importação de um arquivo no formato CSV contendo dados
     * para persistir no banco com base na entidade Livro
     * 
     * <p>Cada linha validada é criado um objeto Livro de acordo 
     * com os campos definidos no cabeçalho</p>
     * 
     * <p>Após o processamento é persistido as informações no banco de dados</p>
     * 
     * @param arquivo
     * @throws Exception 
     */
    public void importarCSV(File arquivo) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;
        List<Livro> livros = new ArrayList<>();
        
        //Ignorado a primeira linha que contém cabeçalho
        br.readLine();
        
        while((linha = br.readLine()) != null) {
            
            String[] dados = linha.split(",");
            
            Livro livro1 = new Livro();
            livro1.setTitulo(dados[1]);
            livro1.setAutor(dados[2]);
            
            //Conversão do String do CSV para Date conforme entidade
            LocalDate data = LocalDate.parse(dados[3]);
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
        //Salva cada livro no banco
        for (Livro livro : livros) {
            livroDAO.salvarLivro(livro);
        }
    }
    
    
    
    
    
}
