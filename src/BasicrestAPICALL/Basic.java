package BasicrestAPICALL;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Basic {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// AddPlace API
		String newaddress="70 Summer walk, USA";
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String Response=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\YRNLAP016-SHREYA\\Documents\\Adddata.json"))))
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(Response);
		String placeID=js.getString("place_id");
		
		System.out.println("Extract placeID"+" ="+placeID);
		
		// Update Addplace ADress
		
		given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//Get place id
		
		String getresponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("getresonse"+" = "+getresponse);
		
		
		
		JsonPath js1=new JsonPath(getresponse);
		String actualAddress = js1.getString("address");

      System.out.println("Actual Address: " + actualAddress);
//		
//		
//		
//        //Assert.assertEquals(newaddress, actualAddress);
//		
		System.out.println("sucesfulllllllllllll!!!!!!!!!!!!!!!");
		
		
}
}
