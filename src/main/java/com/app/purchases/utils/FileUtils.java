package com.app.purchases.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class FileUtils {
    private static final String XSD_SCHEME_PATH = "resources/xsd.xsd";
    private static final String XML_INPUT_PATH = "resources/input.xml";

    public static File getXsdScheme(){
        return new File(XSD_SCHEME_PATH);
    }

    public static File writeAndGetXmlInputFile(String input){
        try {
            Files.writeString(Path.of(XML_INPUT_PATH), input, CREATE, TRUNCATE_EXISTING);
            return new File(XML_INPUT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
