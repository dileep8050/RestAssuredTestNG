package payload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jsonFiles.ExcelDataDriven;



public class Payloads {
	
	public static String addPayload()
	{
		String addpayloadbody ="{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Using Java class to make the body\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
		return addpayloadbody;
	}
	
	public static String updatePayload(String placeid, String address)
	{
		String updatepayloadbody="{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
		return updatepayloadbody;
	}
	
	public static String deletePayload(String placeid)
	{
		String deletePayloadbody="{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}";
		return deletePayloadbody;
	}
	
	public static HashMap<String, Object> addpayloadHashmap()
	{
		HashMap<String, Object>  map = new HashMap<String, Object>();
		ArrayList<String> al=new ArrayList<String>();
		al.add("shoe park");
		al.add("shop");
		
		HashMap<String, Object>  maploc = new HashMap<String, Object>();
		maploc.put("lat","-38.383494");
		maploc.put("lng","33.427362");

		map.put("location",maploc);
		map.put("accuracy","50");
		map.put("name","Using Hashmap file");
		map.put("phone_number","(+91) 983 893 3937");
		map.put("address","Address, fromHashmap");
		map.put("types",al);
		map.put("website","http://google.com");
		map.put("language","French-IN");
		
		return map;
	}
	
	public static  String dataFromExcel() throws IOException
	{
		ExcelDataDriven d=new ExcelDataDriven();
		ArrayList al=d.getExcelData("RequestbodyPayloads","Testdata","APIName","addPlaceExcel");
		System.out.println(al.get(2).toString());
		return al.get(1).toString();
	}
}
