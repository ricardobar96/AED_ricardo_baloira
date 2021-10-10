/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ricardo.casasxml.xml;

/**
 *
 * @author ricardo baloira
 */
public interface JavaToXMLString<Objeto> {
    
    String objToStringXML( Objeto obj);
    
    Objeto stringXMLToObj(String texto);
}
