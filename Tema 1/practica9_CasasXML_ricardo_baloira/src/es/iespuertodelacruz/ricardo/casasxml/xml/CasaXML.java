/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ricardo.casasxml.xml;

import es.iespuertodelacruz.ricardo.casasxml.entities.Casa;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ricardo baloira
 */
public class CasaXML implements JavaToXMLString<Casa>{

    @Override
    public String objToStringXML(Casa c) {
        JAXBContext contexto;
        Marshaller marshaller;
        OutputStream os=null;
        StringWriter sw = new StringWriter();   
        try{
            contexto = JAXBContext.newInstance(c.getClass());
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(c, sw);
        }
        catch(JAXBException ex){
            System.out.println(ex);
        }
        finally{
            return sw.toString();
        }
    }

    @Override
    public Casa stringXMLToObj(String textoXML) {
        JAXBContext contexto;
        Marshaller marshaller;
        StringReader sr = new StringReader(textoXML);
        Casa c = null;
        try{
           contexto = JAXBContext.newInstance(Casa.class);
           marshaller = contexto.createMarshaller();
           marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
           Unmarshaller unmarshaller = contexto.createUnmarshaller();
           c = (Casa) unmarshaller.unmarshal(sr); 
        }
        catch(JAXBException ex){
            System.out.println(ex);
        }
        finally{
            return c;
        }
    }
    
}
