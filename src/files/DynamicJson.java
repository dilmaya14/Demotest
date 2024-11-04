package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {
	
@Test(dataProvider="BookData")
		public void Addbook(String isbn, String aisle) {
//Add Book
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
				.body(payloads.Addbook(isbn,aisle))
		.when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= new JsonPath(response);
		String BookID=js.getString("ID");
		System.out.println("BOOK ID IS:"+""+BookID);
		
		// GET book
		
	given().header("Content-Type","application/json")
	.when().get("Library/GetBook.php?ID="+BookID+"")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().toString();
}
		
@DataProvider(name="BookData")
	public Object[][] getdata() {
		return new Object[][] {{"klkl","89009"},{"uioiu","78686"},{"hjhj","7878"}};
	}
		
	}


