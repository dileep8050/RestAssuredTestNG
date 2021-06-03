package apiTest;



import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import resources.APIactions;
import resources.GetPayloadFile;
import resources.Utils;

public class SampleTest extends Utils  {
	
	 @Test(groups = { "aaaa" },priority = 2)
	 public void trytest()
	 {
		 try {
			Assert.assertTrue(false);
		    }
		    
		    catch (Throwable e) {
		    	System.out.println("I know this should throw error"+e.getMessage());
		      
		    }
	 }
	 
	  
	    
	    
	   
}
