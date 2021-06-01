package apiTest;



import org.testng.annotations.Test;

import io.restassured.response.Response;
import resources.APIactions;
import resources.Utils;

public class ValidateAddApiUsingTextFile extends Utils  {
	APIactions apiActions;
	Response response;
	
	@Test(groups = { "single" })
	public void verifyAddAPIUsingTextFile() throws Exception
	{
		apiActions=new APIactions();
		response=apiActions.addAPI("txtfile", "addplace");		
		System.out.println(getJsonPath(response,"status"));
	}
}
