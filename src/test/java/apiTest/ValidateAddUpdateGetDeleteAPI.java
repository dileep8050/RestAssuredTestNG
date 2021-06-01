package apiTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import resources.APIactions;
import resources.Utils;


public class ValidateAddUpdateGetDeleteAPI extends Utils {
	APIactions apiActions;
	Response response;
	String address;
	String placeid;
	private static Logger log = LogManager.getLogger(ValidateAddUpdateGetDeleteAPI.class.getName());
	
	/* 
	 * Verify Add place API using Json file for request body 
	 */
	
	@Test(priority = 1,groups = { "parallel" })
	public void addPlaceUsingJsonFile() throws Exception
	{	
		log.info("addPlaceUsingJsonFile is initialzed");
		apiActions=new APIactions();
		response=apiActions.addAPI("jsonfile", "addplace");		
		System.out.println(getJsonPath(response,"status"));
	}
	
	
	/*
	 *  Verify Update place API by sending new address 
	 */
	
	@Test(dependsOnMethods = { "addPlaceUsingJsonFile" },groups = { "parallel" },priority = 2)
	public void updatePlaceApi() throws IOException
	{
		log.info("updatePlaceApi is initialzed");
		placeid=getJsonPath(response,"place_id");
		address="Updating my address";
		response=apiActions.updateAPI(placeid, address, 200);
		Assert.assertEquals("Address successfully updated", getJsonPath(response,"msg"));
	}
	
	
	
	/* 
	 * Verify Get API and also validate the updated address 
	 */
	@Test(dependsOnMethods = { "updatePlaceApi" },groups = { "parallel" },priority = 3)
	public void getUpdatedAddress() throws IOException
	{
		log.info("getUpdatedAddress is initialzed");
		response=apiActions.getAPI(placeid, 200);
		Assert.assertEquals(address, getJsonPath(response,"address"));
		Assert.assertTrue(false);
	}
	
	
	
	/* 
	 * Verify Delete place API with existing location
	 */
	
	@Test(groups = { "parallel" },dependsOnMethods= {"getUpdatedAddress"},priority = 5)
	public void deleteplace() throws IOException
	{
		log.info("deleteplace is initialzed");
		response=apiActions.deleteAPI(placeid, 200);
		Assert.assertEquals("OK", getJsonPath(response,"status"));	
	}
	
	
	
	/* 
	 * Verify Delete API with non existing address
	 */
	
	@Test(groups = {"parallel"},priority = 4)
	public void deleteNonExistPlace() throws IOException
	{
		log.info("deleteNonExistPlace is initialzed");
		Response response=apiActions.deleteAPI("abcd", 404);
		Assert.assertEquals("Delete operation failed, looks like the data doesn't exists", getJsonPath(response,"msg"));	
	}
	
}