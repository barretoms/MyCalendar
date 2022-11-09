package util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.validation.Schema;
import javax.xml.XMLConstants;


public class Xml {
    

    public static void writeXml (Document document, OutputStream output) throws TransformerException{
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    public static Document readXmlFile(File file) throws Exception{

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            
            return document;
        
    }

    public static Document createBlankDocument() throws Exception{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        return document;

    }

    public static String prettyPrintByTransformer(Document document, int indent, boolean ignoreDeclaration) {

        try {

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, ignoreDeclaration ? "yes" : "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    
            Writer out = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(out));
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error occurs when pretty-printing xml\n", e);
        }
    }

    public static Boolean validateAgainsXsd(String xmlPath, String xsdPath) {
        
        
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema s = sf.newSchema(new File(xsdPath));
            Validator validator = s.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            
        } catch (SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        } catch (IOException e1) {
            System.out.println("SAX Exception: "+e1.getMessage());
            return false;
        }

        return true;
    }
    
    public static Element getElementFromNode(Node node){
        Element element = null;
    if (node.getNodeType() == Node.ELEMENT_NODE) element = (Element) node;
        return element;
    }
}
