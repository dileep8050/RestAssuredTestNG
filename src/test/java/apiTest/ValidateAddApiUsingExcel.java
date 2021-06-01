package apiTest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import resources.APIactions;
import resources.Utils;

public class ValidateAddApiUsingExcel extends Utils  {
	APIactions apiActions;
	Response response;
	
	@Test(groups = { "hashmap" })
	public void verifyAddAPIUsingHashMap() throws Exception
	{
		apiActions=new APIactions();
		response=apiActions.addAPI("hashmap", null);		
		System.out.println(getJsonPath(response,"status"));
	}
	@Test(groups = { "excel" })
	public void verifyAddAPIUsingExcel() throws Exception
	{
		apiActions=new APIactions();
		response=apiActions.addAPI("excelfile", null);		
		System.out.println(getJsonPath(response,"status"));
	}
	

}
