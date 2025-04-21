package GenericUtilities;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Json_Utility {
	public String FetchDataFromJsonFile(String key) throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./src/test/resources/Json advance.json"));
		JSONObject js = (JSONObject) obj;
		String data = js.get(key).toString();
		return data;
	}
}
