package util;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;

/**
 * A singleton class that reads String properties from disk by String key names.
 */
public class PropertiesDiskStorage {
    /** The one instance for this Singleton class. */
    private static PropertiesDiskStorage instance = null;

    /** The name of the text file with the properties for the class. */
    private static String fileName;

    /**
     * Set the name of the file from which the properties are read.
     * 
     * @param fileName the file from which the properties are read
     */
    public static void setFileName(String fileName) {
        PropertiesDiskStorage.fileName = fileName;
    }

    /**
     * @return is the fileName already set for the class?
     */
    public static boolean fileNameIsSet() {
        return fileName != null;
    }

    /**
     * @precond fileNameIsSet()
     * @return the actual PropertiesDiskStorage object
     */
    public static PropertiesDiskStorage getInstance() {
        if (instance == null) {
            if (!fileNameIsSet())
                throw new IllegalStateException("The file name must be set before first use.");
            instance = new PropertiesDiskStorage(fileName);
        }
        return instance;
    }

    /** The properties read from disk. */
    private Properties properties;

    /**
     * When a list of strings is desired for a property name, store the list in the hashtable.
     */
    private Hashtable<String, List<String>> hashtable;

    /**
     * Initialize the instance by reading the properties from the file whose name is stored in
     * propFileName.
     * 
     * @param propFileName the name of the file storing the properties
     * @precond the file with name propFileName stores a valid set of Properties
     */
    private PropertiesDiskStorage(String propFileName) {
        properties = new Properties();
        try {
            // FileInputStream propertiesStream = new FileInputStream(propFileName);
            // the properties are stored with the (binary version of the) classes
            InputStream propertiesStream = ClassLoader.getSystemResourceAsStream(propFileName);
            properties.load(propertiesStream);
            propertiesStream.close();
        } catch (IOException ioe) {
            throw new IllegalStateException("The properties could not be loaded from file "
                    + propFileName);
        }
        hashtable = new Hashtable<String, List<String>>(properties.size() * 2);
    }

    /**
     * @param prop the name of a property
     * @return the String associated with prop
     */
    public String getProperty(String prop) {
        return properties.getProperty(prop);
    }

    /**
     * @param prop the name of a property
     * @return a list of the values (Strings) associated with prop
     */
    public List<String> getProperties(String prop) {
        List<String> values = hashtable.get(prop);
        if (values == null) {
            values = new LinkedList<String>();
            String delimitedString = properties.getProperty(prop); // the file stores one String
            if (delimitedString != null) {
                Scanner scanner = new Scanner(delimitedString);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String name = scanner.next();
                    values.add(name);
                }
            }
            hashtable.put(prop, values);
        }
        return values;
    }
}
