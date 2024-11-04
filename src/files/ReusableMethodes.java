package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethodes {
	
public static JsonPath rawtojson(String response) {
		
		JsonPath js1= new JsonPath(response);
		return js1;
		
	}


}
