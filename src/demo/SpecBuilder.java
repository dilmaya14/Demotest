package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.GoogleMAP;
import Pojo.Location;

public class SpecBuilder {
	
	
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
		
		RequestSpecification rq=new RequestSpecBuilder()
		.addQueryParam("key","qaclick123").build();
		
		RequestSpecification res =given().spec(rq).body(gm);
		
		ResponseSpecification respec= new ResponseSpecBuilder().expectStatusCode(200).build();
		
		
		
		
		 Response response=res.when().post("https://rahulshettyacademy.com/maps/api/place/add/json")
		.then().spec(respec).extract().response();
		String responsestring=response.asString();
		System.out.println(responsestring);
		
		JsonPath js= new JsonPath(responsestring);
		String ID=js.get("id");
		String Status=js.get("status");
		
		System.out.println(ID);
		System.out.println(Status);
		
		
		
	}

}
