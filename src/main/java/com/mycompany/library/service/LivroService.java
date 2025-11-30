package com.mycompany.library.service;

import com.mycompany.library.controller.ConsumoAPI;
import com.mycompany.library.dao.LivroDAO;
import com.mycompany.library.model.entity.Livro;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe LivroService para poder realizar o processamento da informações
 * recebidas pela API
 *
 *
 * @author Gabriel Expedito
 */
public class LivroService {

    private LivroDAO livroDAO = new LivroDAO();

    //Constante com a máscara esperada da data formatada
    private static final SimpleDateFormat JSON_DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Recebe a entrada do controller com o retorno da API para processar o JSON
     *
     * @param isbn
     * @return
     */
    public Livro buscarSalvarPorIsbn(String isbn) {
        String json = ConsumoAPI.buscarLivroPorIsbn(isbn);

        Livro livro = jsonParaLivro(json);

        if (livro.getClassificacao() == null) {
            livro.setClassificacao(livro.getClassificacao().NÃO_INFORMADO);
        }

        if (livro != null) {
            livroDAO.salvarLivro(livro);
        }
        return livro;
    }

    /**
     * Converter o JSON vindo da API para um Objeto Livro
     *
     *
     * @param json
     * @return Objeto Livro
     */
    public static Livro jsonParaLivro(String json) {

        JSONObject objJson = new JSONObject(json);

        Livro livro = new Livro();

        livro.setTitulo(objJson.optString("title", ""));

        livro.setAutor(objJson.optJSONArray("authors") != null
                ? objJson.getJSONArray("authors").getJSONObject(0).optString("name", "")
                : "");

        livro.setEditora(objJson.optString("publishers", ""));

        String dataString = objJson.optString("publish_date", "");

        DateTimeFormatter dataFormatter
                = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

        LocalDate date = LocalDate.parse(dataString, dataFormatter);
        livro.setDataPublicacao(date);

        livro.setIsbn(objJson.optString("isbn_13", objJson.optString("isbn_10",
                "")));

        return livro;
    }
    }

