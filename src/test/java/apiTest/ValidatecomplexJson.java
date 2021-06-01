package apiTest;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class ValidatecomplexJson {
	
	JsonPath js;
	int count;
	@Test(priority = 1,groups = { "complex" })
	public void getCountOfCourses()
	{
		js=new JsonPath(ValidatecomplexJson.complexjson());
		count=js.getInt("courses.size");
		System.out.println("CountOfCourses : "+count);
	}
	
	@Test(priority = 2,groups = { "complex" })
	public void getPurchaseAmount()
	{
		System.out.println("PurchaseAmount: "+js.getInt("dashboard.purchaseAmount"));
	}
	
	@Test(priority = 3,groups = { "complex" })
	public void getTitleOfFirstCourse()
	{
		System.out.println("Title of first course: "+js.get("courses[0].title").toString());
	}
	
	@Test(priority = 4,groups = { "complex" })
	public void getCourseTitleAndPrice()
	{
		for(int i=0;i<count;i++)
		{
			String title=js.get("courses["+i+"].title");
			int price=js.getInt("courses["+i+"].price");
			System.out.println("Title of course "+ i +" : "+title+", "+"Pirce is : "+price);
		}
	}
	@Test(priority = 5,groups = { "complex" })
	public void getNumOfCopiesSoldRPA()
	{
			for(int i=0;i<count;i++)
			{
				String title=js.get("courses["+i+"].title");
					if(title.equalsIgnoreCase("Cypress")) 
					{
						int price=js.getInt("courses["+i+"].price");
						System.out.println("Title of course "+ i +" : "+title+", "+"Pirce is : "+price);
						break;
					}
			}
	
	}
	
	@Test(priority = 6,groups = { "complex" })
	public void sumOfAllCoursePriceWithPurchseAmount()
	{
		int sum=0;
		for(int i=0;i<count;i++)
		{
			String title=js.get("courses["+i+"].title");
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount=price*copies;
			sum=sum+amount;
		}
		System.out.println("Total sum amount : "+sum);
		int purchseamount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Total Purchase amount : "+purchseamount);
		Assert.assertEquals(sum, purchseamount);		
	}
	
	public static String complexjson()
	{
		String s="{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"purchaseAmount\": 910,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n"
				+ "      \"price\": 50,\r\n"
				+ "      \"copies\": 6\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Cypress\",\r\n"
				+ "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n"
				+ "      \"copies\": 10\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		return s;
	}

}
