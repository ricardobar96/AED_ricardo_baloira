/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.xml;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author ricardo baloira
 */
public interface JavaToXMLString<T> {

    String objToStringXML( T obj);
    
    T stringXMLToObj(String texto);
}
