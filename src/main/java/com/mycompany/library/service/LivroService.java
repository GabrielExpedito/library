package com.mycompany.library.service;

import com.mycompany.library.controller.ConsumoAPI;
import com.mycompany.library.dao.LivroDAO;
import com.mycompany.library.model.entity.Livro;
import org.json.JSONObject;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe LivroService para poder realizar o processamento da informações
 * recebidas pela API 
 * 
 * 
 * @author Gabriel Expedito
 */
public class LivroService {

    private LivroDAO livroDAO =  new LivroDAO();

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
        
        if(livro.getClassificacao() == null ) {
            livro.setClassificacao(livro.getClassificacao().NÃO_INFORMADO);
        }
        
        if (livro != null){ 
            livroDAO.salvarLivro(livro);
        }
        return livro;
    }

    /**
     * Converter o JSON vindo da API para um Objeto Livro
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

        //Váriavel para armazenar a data vinda do JSON da API
        String dataString = objJson.optString("publish_date", "");

        //Váriavel para armazenar a data em formato DATE
        Date dataConvertida = null;

        //Tentativa de converter a data do JSON(API) para Date
        if (!dataString.isEmpty()) {
            try {
                dataConvertida = JSON_DATA_FORMAT.parse(dataString);
            } catch (Exception e) {
                System.err.println("Não foi possível converter a data"
                        + dataString);
            }
        }

        livro.setDataPublicacao(dataConvertida);

        livro.setIsbn(objJson.optString("isbn_13", objJson.optString("isbn_10", "")));

        return livro;
    }

}
