package apiTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIactions;
import resources.Utils;
import java.io.IOException;

public class GoogleLocationPayload extends Utils {
	Response response;
	RequestSpecification res;
	APIactions apiActions;
	private static Logger log = LogManager.getLogger(GoogleLocationPayload.class.getName());
	
	@Test(priority=1)
	public void addplacePayload() throws Exception
	{
		apiActions=new APIactions();
		response=apiActions.addAPI("javaclass", null);
	}
	
	@Test(priority=2)
	public void validateAddplaceResponse()
	{
		Assert.assertEquals("OK", getJsonPath(response,"status"));
		Assert.assertEquals("APP", getJsonPath(response,"scope"));
	}
	
	
	@Test(priority=3)
	public void verifyGetplaceWithValidPlaceID() throws Exception
	{
		response=apiActions.addAPI("nonfile", null);
		String placeid=getJsonPath(response,"place_id");

		response=apiActions.getAPI(placeid, 200);
		
		Assert.assertEquals("http://google.com", getJsonPath(response,"website"));
		System.out.println(getJsonPath(response,"website"));
	}
	
	@Test(priority=4)
	public void verifyGetplaceWithInvalidPlaceID() throws IOException
	{
		response=apiActions.getAPI("999fd424c9aa9bad52ae19b84408c01", 404);
	
		Assert.assertEquals("Get operation failed, looks like place_id  doesn't exists", getJsonPath(response,"msg"));
		System.out.println(getJsonPath(response,"msg"));
	}
}
