package eu.openminted.share.annotations.util;

import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import javanet.staxutils.IndentingXMLEventWriter;

public class XmlUtil
{
    public static <T> void write(T aObject, OutputStream aOS)
        throws JAXBException, XMLStreamException
    {
        XMLEventWriter xmlEventWriter = null;
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            xmlEventWriter = new IndentingXMLEventWriter(
                    xmlOutputFactory.createXMLEventWriter(aOS));
    
            JAXBContext context = JAXBContext.newInstance(aObject.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, 
                    "http://www.meta-share.org/OMTD-SHARE_XMLSchema "
                    + "http://www.meta-share.org/OMTD-SHARE_XMLSchema/v200/OMTD-SHARE-Component.xsd");
            marshaller.marshal(aObject, xmlEventWriter);
        }
        finally {
            if (xmlEventWriter != null) {
                xmlEventWriter.close();
            }
        }
    }
}
