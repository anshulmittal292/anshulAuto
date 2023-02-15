package apiAutomation;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.json.simple.parser.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class restAssuredAPITesting  {

	@Test
	public void GETWithoutAuth() throws Exception
	{
		//	test = report.startTest("validatePageTitle");
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
		int statusCode = response.getStatusCode();

		// By using the ResponseBody.asString() method, we can convert the  body
		// into the string representation.
		org.testng.Assert.assertEquals(statusCode, 200);
		System.out.println("Response Body is: " + body.asString());
	}

	@Test
	public void AuthenticationBasics() throws Exception
	{
		RestAssured.baseURI = "https://postman-echo.com/basic-auth";
		RequestSpecification request = RestAssured.given().auth().preemptive().basic("postman", "password");
		Response response = request.get();
		
		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Status message " + response.body().asString());
	}

	@Test
	public void GETResponseJSONArray() throws Exception
	{
		// Retrieve the body of the Response
		JSONParser parser = new JSONParser();
		int countIndia = 0;
		int countOthers = 0;
		try {
			Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/testData.json"));
			JSONObject jsonObject = (JSONObject)obj;
			JSONArray player = (JSONArray)jsonObject.get("player");
			for(Object o:player) {
				if(((JSONObject)o).get("country").toString().equals("India")) {
					countIndia++;
				}
				else {
					countOthers++;
				}

			}
			for(Object b:player) {
					if(((JSONObject)b).get("role").toString().equals("Wicket-keeper")) {
						System.out.println("wicket keeper is present");
						break;
					}
			}
			System.out.println("count of indian players "+countIndia);
			System.out.println("count of foreign players "+countOthers);
		}
		catch(Exception e){
			e.printStackTrace();
		}


	}
}
