package resources;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Utils {
	
	public static RequestSpecification req;
	public ResponseSpecification resspec;
	private static Logger log = LogManager.getLogger(Utils.class.getName());
	

	
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
			log.debug("Log file created with the name logging"+dtf.format(now)+".txt");
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
	public ResponseSpecification resSpecification()
	{
			resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			
		return resspec;
	}
	
	/* To read the properties file and fetch the values based on key we requested and we are giving as string*/
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop=new Properties();
		String basepath=System.getProperty("user.dir");
		FileInputStream fis=new FileInputStream(basepath+"\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		log.info("Fetching the url from the properties file, Url : "+prop.getProperty(key));
		return prop.getProperty(key);		
	}
	
	/* To parse the response and returns the String value by using the key */
	
	public String getJsonPath(Response response, String key)
	{
		String resp= response.asString();
		JsonPath js=new JsonPath(resp);
		log.info("Response is : "+ resp);
		log.info("Request key is : "+ key +" and the value is : " +js.get(key).toString());
		return js.get(key).toString();
	}
	
	 /* To fetch the API resources and returns the resources, which user is requested */
	
	public String apiResource(String apiResource)
	{
		APIResources resourceAPI=APIResources.valueOf(apiResource);
		String apiRes=resourceAPI.getResource();
		log.info("Url constructed with the resource :"+apiResource+" == " + apiRes);
		return apiRes;
	}
	
	public void requestResponselog(RequestSpecification res,Response response,String resources)
	{
		QueryableRequestSpecification queryable = SpecificationQuerier.query(res);
		String reqResDetails="\n"+
				"\n"+
		"====================== Request,Status code,Response Details ========================="+"\n"+
		"\n"+
		"------------------------"+"\n"+
		"**** Header Details ****"+"\n"+
		"------------------------"+"\n"+
		"Request method: " + queryable.getMethod()+"\n"+
		"Request URI: " + queryable.getBaseUri()+resources+"\n"+
		"Proxy: " + queryable.getProxySpecification()+"\n"+
		"Request params: " + queryable.getQueryParams()+"\n"+
		"Form params: " + queryable.getFormParams()+"\n"+
		"Headers: " + queryable.getHeaders()+"\n"+
		"Cookies: " + queryable.getCookies()+"\n"+
		"Multiparts: " + queryable.getMultiPartParams()+"\n"+
		"\n"+
		"----------------------"+"\n"+
		"**** Body Details ****"+"\n"+
		"----------------------"+"\n"+
		"body is: " + queryable.getBody()+"\n"+
		"\n"+
		"---------------------"+"\n"+
		"**** Status code ****"+"\n"+
		"---------------------"+"\n"+
		"Response Status code: " + response.getStatusCode()+"\n"+
		"\n"+
		"--------------------------"+"\n"+
		"**** Response Details ****"+"\n"+
		"--------------------------"+"\n"+
		response.asString()+"\n"+
		"\n"+"\n";
		
		log.info(reqResDetails);
		
	}
	
	public void validateStatusCode(Response response, int expectedStatusCode)
	{
		int actualStatuscode=response.getStatusCode();
		
		if(actualStatuscode==expectedStatusCode)
		{
			log.info("Expected is ("+expectedStatusCode+") and Actual status code ("+actualStatuscode+") is matched and validated successfully");
			Assert.assertEquals(expectedStatusCode, actualStatuscode);
		}
		else
		{
			log.error("Expected is ("+expectedStatusCode+") and Actual status code ("+actualStatuscode+") is not matched and validation failed");
			Assert.assertEquals(expectedStatusCode, actualStatuscode);
		}
	}
	public void responseValidation(Response response, String expectedKey,String expectedValue)
	{
		String resp= response.asString();
		JsonPath js=new JsonPath(resp);
		String actualvalue=js.get(expectedKey).toString();
		if(expectedValue.equals(actualvalue))
		{
			log.info("Expected value is ("+expectedValue+") and Actual status key value ("+actualvalue+") is matched and validated successfully");
			Assert.assertEquals(expectedValue, actualvalue);
		}
		else
		{
			log.error("Expected value is ("+expectedValue+") and Actual status code ("+actualvalue+") is not matched and validation failed");
			Assert.assertEquals(expectedValue, actualvalue);
		}
	}
		
}
