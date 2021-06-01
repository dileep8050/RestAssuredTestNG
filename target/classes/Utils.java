package resources;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.junit.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Utils {
	
	public static RequestSpecification req;
	public ResponseSpecification resspec;
	
	/* To construct the url with Query params and headers
	 * Print the logs with request and responses*/
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			String maindir=System.getProperty("user.dir");
			System.out.println(maindir+"\\log\\logging"+dtf.format(now)+".txt");
			PrintStream log=new PrintStream(new FileOutputStream(maindir+"\\log\\logging"+dtf.format(now)+".txt"));
			
			req =new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			return req;
		}
		return req;
	}
	
	/* To validate the response code based on user input */
	public ResponseSpecification validateStauscode(int statuscode)
	{
		if(statuscode==200)
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		else if(statuscode==404)
		resspec=new ResponseSpecBuilder().expectStatusCode(404).expectContentType(ContentType.JSON).build();
		else
			Assert.assertEquals("Status is not handled in the staus code validation ", statuscode, 0);
		return resspec;
	}
	
	/* To read the properties file and fetch the values based on key we requested and we are giving as string*/
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop=new Properties();
		String basepath=System.getProperty("user.dir");
		FileInputStream fis=new FileInputStream(basepath+"\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);		
	}
	
	/* To parse the response and returns the String value by using the key */
	
	public String getJsonPath(Response response, String key)
	{
		String resp= response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
	}
	
	 /* To fetch the API resources and returns the resources, which user is requested */
	
	public String apiResource(String apiResource)
	{
		APIResources resourceAPI=APIResources.valueOf(apiResource);
		String apiRes=resourceAPI.getResource();
		return apiRes;
	}

}
