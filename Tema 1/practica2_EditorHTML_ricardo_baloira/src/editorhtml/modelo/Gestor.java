/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorhtml.modelo;

/**
 *
 * @author ricardo baloira armas
 */
public class Gestor {
    public String reemplazarHTML(String texto){
        String textoFinal = texto.replaceAll("<[^>]*>", "");
        return textoFinal;
        
    }
}
