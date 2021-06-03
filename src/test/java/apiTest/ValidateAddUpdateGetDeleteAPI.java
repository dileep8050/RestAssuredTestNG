package apiTest;

import java.io.IOException;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import resources.APIactions;
import resources.Utils;


public class ValidateAddUpdateGetDeleteAPI extends Utils {
	APIactions apiActions;
	Response response;
	String address;
	String placeid;
	
	@BeforeTest(alwaysRun=true)
    public void beforeTest() {
		apiActions=new APIactions();
    }
	/* 
	 * Verify Add place API using Json file for request body 
	 * 
	 */


	@Test(priority = 1,groups = { "ssss" })
	public void addPlaceUsingJsonFile() throws Exception
	{	
		response=apiActions.addAPI("jsonfile", "addplace");	
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	/*
	 *  Verify Update place API by sending new address 
	 *  
	 */
	
	@Test(dependsOnMethods = { "addPlaceUsingJsonFile" },groups = { "ssss" },priority = 2)
	public void updatePlaceApi() throws IOException
	{
		placeid=getJsonPath(response,"place_id");
		address="Updating my address";
		response=apiActions.updateAPI(placeid, address);
		validateStatusCode(response,200);
		responseValidation(response,"msg","Address successfully updated");	
	}
	
	
	
	/* 
	 * Verify Get API and also validate the updated address 
	 * 
	 */
	@Test(dependsOnMethods = { "updatePlaceApi" },groups = { "ssss" },priority = 3)
	public void getUpdatedAddress() throws IOException
	{
		response=apiActions.getAPI(placeid);
		validateStatusCode(response,200);
		responseValidation(response,"address",address);	
	}
	
	
	/* 
	 * Verify Delete place API with existing location
	 * 
	 */
	
	@Test(groups = { "ssss" },dependsOnMethods= {"getUpdatedAddress"},priority = 5)
	public void deleteplace() throws IOException
	{
		response=apiActions.deleteAPI(placeid);
		validateStatusCode(response,200);
		responseValidation(response,"status","OK");	
	}
	
	
	/* 
	 * Verify Delete API with non existing address
	 * 
	 */
	
	@Test(groups = {"ssss"},priority = 4)
	public void deleteNonExistPlace() throws IOException
	{
		Response response=apiActions.deleteAPI("abcd");
		validateStatusCode(response,404);
		responseValidation(response,"msg","Delete operation failed, looks like the data doesn't exists123");	
	}
	
}