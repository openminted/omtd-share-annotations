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
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter xmlEventWriter = new IndentingXMLEventWriter(
                xmlOutputFactory.createXMLEventWriter(aOS));

        JAXBContext context = JAXBContext.newInstance(aObject.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(aObject, xmlEventWriter);
    }
}
