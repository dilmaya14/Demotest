package demo;

import static io.restassured.RestAssured.*;

import java.util.List;

import Pojo.API;
import Pojo.GetCourses;
import io.restassured.path.json.JsonPath;

public class OuthserverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String response=given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		 String Acess_Token= js.getString("access_token");
		 System.out.println("Acess_Token extract" +"= "+Acess_Token);
		 
		 
		GetCourses gc =given().queryParam("access_token",Acess_Token )
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(GetCourses.class);
		System.out.println(gc);
		
		System.out.println(gc.getLinkedIn());
		
		System.out.println("API" +"="+gc.getCourses().getApi().get(1).getCourseTitle());
		System.out.println("WebAutomation"+""+gc.getCourses().getMobile().get(0).getPrice());
		
		List<API> apicourses= gc.getCourses().getApi();
		
		for(int i=0; i<apicourses.size();i++) {
			if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				
				String Price=apicourses.get(i).getPrice();
				System.out.println("Price of api title:"+""+Price);
			}
			
		}

	}

}
