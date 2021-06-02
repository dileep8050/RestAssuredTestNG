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
	 @Test
	    public void testCase() {
	    }
	 
	    @BeforeSuite
	    public void beforeSuite() {
	        System.out.println("Before Suite method");
	    }
	 
	    @AfterSuite
	    public void afterSuite() {
	        System.out.println("After Suite method");
	    }
	 
	    @BeforeTest
	    public void beforeTest() {
	        System.out.println("Before Test method");
	    }
	     
	    @AfterTest
	    public void afterTest() {
	        System.out.println("After Test method");
	    }
	     
	    @BeforeClass
	    public void beforeClass() {
	        System.out.println("Before Class method");
	    }
	 
	    @AfterClass
	    public void afterClass() {
	        System.out.println("After Class method");
	    }
	 
	    @BeforeMethod
	    public void beforeMethod() {
	        System.out.println("Before Method");
	    }
	 
	    @AfterMethod
	    public void afterMethod() {
	        System.out.println("After Method");
	    }
}
