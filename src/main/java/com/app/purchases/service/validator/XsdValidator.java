package com.app.purchases.service.validator;

import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;

@Service
public class XsdValidator {
    public boolean validate(File input, File xsd){
        boolean valid = false;
        try(FileInputStream fis = new FileInputStream(input)){
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsd);
            Validator validator = schema.newValidator();
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fis);
            StAXSource stAXSource = new StAXSource(xmlStreamReader);
            validator.validate(stAXSource);
            valid = true;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return valid;
    }
}
