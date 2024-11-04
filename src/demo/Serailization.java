package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.GoogleMAP;
import Pojo.Location;

public class Serailization {
	
	
	public static void main(String[] args) {
		
		
		GoogleMAP gm= new GoogleMAP();
		gm.setAccuracy(50);
		gm.setAddress("29, side layout, cohen 09");
		gm.setLanguage("English");
		gm.setName("Dilmaya");
		gm.setPhone_number("8889888998");
		gm.setWebsite("http://google.com");
		
		List<String> arraylist= new ArrayList<String>();
		arraylist.add("shop");
		arraylist.add("carrot");
		gm.setTypes(arraylist);
		
		Location l= new Location();
		gm.setLocation(l);
		l.setLat(2.34);
		l.setLng(8.9);
		
		
		
		Response res =given().log().all().queryParam("key","qaclick123").body(gm)
		.when().post("https://rahulshettyacademy.com/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response();
		String responsestring=res.asString();
		System.out.println(responsestring);
		
		JsonPath js= new JsonPath(responsestring);
		String ID=js.get("id");
		String Status=js.get("status");
		
		System.out.println(ID);
		System.out.println(Status);
		
		
		
	}

}
