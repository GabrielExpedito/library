/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 *
 * @author Gabriel Expedito
 */
public class ConsumoAPI {

    private static final String URL_API = "https://openlibrary.org/isbn/";

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
