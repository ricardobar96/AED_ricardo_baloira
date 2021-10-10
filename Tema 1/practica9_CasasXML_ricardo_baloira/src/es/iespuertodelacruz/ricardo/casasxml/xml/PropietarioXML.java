/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ricardo.casasxml.xml;

import es.iespuertodelacruz.ricardo.casasxml.entities.Propietario;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Usuario
 */
public class PropietarioXML implements JavaToXMLString<Propietario>{

    @Override
    public String objToStringXML(Propietario p) {
        JAXBContext contexto;
        Marshaller marshaller;
        OutputStream os=null;
        StringWriter sw = new StringWriter();
        try{
            contexto = JAXBContext.newInstance(p.getClass());
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(p, sw);
        }
        catch(JAXBException ex){
            System.out.println(ex);
        }
        finally{
            return sw.toString();
        }
    }

    @Override
    public Propietario stringXMLToObj(String textoXML) {
        JAXBContext contexto;
        Marshaller marshaller;
        StringReader sr = new StringReader(textoXML);
        Propietario p = null;
        try{
            contexto = JAXBContext.newInstance(Propietario.class);
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();
            p = (Propietario) unmarshaller.unmarshal(sr);
        }
        catch(JAXBException ex){
            System.out.println(ex);
        }
        finally{
            return p;
        }  
    }
    
}
