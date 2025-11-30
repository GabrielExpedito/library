package com.mycompany.library.views;

import com.mycompany.library.model.entity.Livro;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Método para carregas as informações da tabela conforme constante padrão 
 * definida.
 * 
 * @author Gabriel Expedito
 */
public class LivroTableModel extends AbstractTableModel {

    //Constante que define os campos da tabela 
    private static final String[] COLUNAS = {"ID", "Título", "Autor", "ISBN", 
        "Editora", "Data Pub.", "Classificação"};

    private List<Livro> livros;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LivroTableModel(List<Livro> livros) {
        this.livros = livros != null ? livros : new ArrayList<>();
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
        //Atualiza a tabela ao ter alteração nos livros
        fireTableDataChanged();
    }

    public Livro getLivro(int rowIndex) {
        return livros.get(rowIndex);
    }

    public void removeRow(int row) {
        livros.remove(row);
        fireTableRowsDeleted(row, row);
    }

    // Métodos de AbstractTableModel (Contratos do extend AbstractTableModel)
    @Override
    public int getRowCount() {
        return livros.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUNAS[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro livro = livros.get(rowIndex);

        //Monta o indíce dos campos da tabela
        switch (columnIndex) {
            case 0: // ID
                return livro.getId();
            case 1: // Título
                return livro.getTitulo();
            case 2: // Autor
                return livro.getAutor();
            case 3: // ISBN
                return livro.getIsbn();
            case 4: // Editora
                return livro.getEditora();
            case 5: // Data Publicacao
                LocalDate data = livro.getDataPublicacao();
                return data != null ? format.format(data) : "";
            case 6: // Classificação
                return livro.getClassificacao();
            default:
                throw new IndexOutOfBoundsException("Índice de coluna inválido");
        }
    }
}
