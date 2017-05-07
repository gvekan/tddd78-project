package se.liu.ida.gusan092.tddd78.project.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The abstract class for classes that handles a property
 * It should not have any abstract method because it works more lika a helpclass
 */
public abstract class AppProperties
{
    private final String fileName;
    protected Properties prop = new Properties();

    protected AppProperties(final String fileName) {
        this.fileName = fileName;
        read();
    }

    protected void read() {
        read(prop,fileName);
    }

    protected void read(final Properties prop, final String fileName) {
	FileInputStream in = null;
	try {
	    in = new FileInputStream(fileName);
	    prop.load(in);
	} catch (IOException e) {
	    System.out.println("Error accessing " + fileName);
	    e.printStackTrace();
	} finally {
	    if(in != null) {
	        try {
	            in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    protected void write() {
        write(prop, fileName);
    }

    protected void write(final Properties prop, final String fileName) {
	FileOutputStream out = null;
        try {
	    out = new FileOutputStream(fileName);
	    prop.store(out,null);
	} catch (IOException e) {
	    System.out.println("Error writing app");
	    e.printStackTrace();
	} finally {
	    if(out != null) {
	        try {
	            out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
