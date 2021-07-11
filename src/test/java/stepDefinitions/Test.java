package stepDefinitions;

import static io.restassured.RestAssured.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		/*RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		String response= given().log().all().header("Content-type", "application/json").body("{\r\n" + 
				"    \"id\": 1999,\r\n" + 
				"    \"petId\": 191923,\r\n" + 
				"    \"quantity\": 25,\r\n" + 
				"    \"shipDate\": \"2021-07-09T11:56:30.604Z\",\r\n" + 
				"    \"status\": \"placed\",\r\n" + 
				"    \"complete\": true\r\n" + 
				"}").
		 when().post("/store/order").
		 then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		System.out.println(js.get("id"));*/
		
//	String defaultTimezone = TimeZone.getDefault().getID();
//	System.out.println(defaultTimezone);
//	
//	OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
//	 
//	 System.out.println(now);
	 
	 SimpleDateFormat format = new SimpleDateFormat(
			    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date date = new Date();
		String	strDate = format.format(date);
		System.out.println(strDate);
		
	}

}
