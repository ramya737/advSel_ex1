package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Property_Utility {
	public String FetchDataFromPropFile(String key) throws IOException {

		// fetch the data from properties file
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");

		// create properties object
		Properties p = new Properties();
		// STORE AN EMPTY CONTAINER
		p.load(fis);
		// Store the data in variable
		String data = p.getProperty(key);
		return data;

	}

	public void WriteDataBackToPropFile(String key, String value) throws IOException {
		// fetch the data from properties file
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");

		// create properties object
		Properties p = new Properties();
		// STORE AN EMPTY CONTAINER
		p.load(fis);
		p.put(key, value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/CommonData.properties");
		p.store(fos, "update successfully");
		System.out.println("Data inserted into property file");
	}
}
