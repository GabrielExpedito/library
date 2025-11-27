package com.mycompany.library.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Método para acessar a API OpenLibrary e buscar o JSON do livro informado pelo
 * ISBN
 * @author Gabriel Expedito
 */
public class ConsumoAPI {

    //Constante definida da configuração da URL de acesso a API
    private static final String URL_API = "https://openlibrary.org/isbn/";

    /**
     * Acessa a URL da API informando o ISBN informado pelo usuário
     * 
     * <p>Possuí uma verificação do retorno do status HTTP da API que em caso
     * foi diferente de 200 informa que não foi possível de acessar a API</p>
     * 
     * @param isbn
     * @return String do acesso a API 
     */
    public static String buscarLivroPorIsbn(String isbn) {
        try {
            String requisicaoURL = URL_API + isbn + ".json";
            URI uri = new URI(requisicaoURL);
            URL url = uri.toURL();

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Aceito", "application/json");

            if (conexao.getResponseCode() != 200) {
                throw new RuntimeException("Erro ao acessar api: "
                        + conexao.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conexao.getInputStream()));
            StringBuilder retorno = new StringBuilder();
            String linhaRetorno;

            while ((linhaRetorno = br.readLine()) != null) {
                retorno.append(linhaRetorno);
            }
            
            br.close();
            conexao.disconnect();
            return retorno.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
