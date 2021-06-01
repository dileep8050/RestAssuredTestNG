package resources;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jsonFiles.ExcelDataDriven;
import payload.Payloads;

public class APIactions extends Utils {
	RequestSpecification res;
	Response response;
	private static Logger log = LogManager.getLogger(APIactions.class.getName());
	public Response addAPI(String type, String apifilename) throws Exception {
		log.info("addAPI with the filetype : "+type+" , Api file name of the api is : " +apifilename );
		
			if(type=="javaclass")
			{
			res=given()
					.spec(requestSpecification())
					.body(Payloads.addPayload());
			}
			else if(type=="txtfile")
			{
			res=given()
					.spec(requestSpecification())
					.body(GetPayloadFile.txtFile(apifilename));
			}
			else if(type=="jsonfile")
			{
				res=given()
						.spec(requestSpecification())
						.body(GetPayloadFile.jsonFile(apifilename));
			}
			else if(type=="hashmap")
			{
				res=given()
						.spec(requestSpecification())
						.body(Payloads.addpayloadHashmap());
			}
			else if(type=="excelfile")
			{
				res=given()
						.spec(requestSpecification())
						.body(Payloads.dataFromExcel());
			}
			
		response=res.when().post(apiResource("AddPlaceAPI"))
				.then().spec(validateStauscode(200)).extract().response();
		
		log.info("Successfully added the placeAPI with the status code 200");
		return response;
	}
	
	public Response getAPI(String placeid,int statuscode) throws IOException
	{
		res=given()
				.spec(requestSpecification())
				.queryParam("place_id", placeid);
		
		response=res.when().get(apiResource("getPlaceAPI"))
				.then().spec(validateStauscode(statuscode)).extract().response();
		return response;
	}
	
	public Response updateAPI(String placeid, String address, int statuscode) throws IOException
	{
		res=given().spec(requestSpecification()).body(Payloads.updatePayload(placeid, address));
		response=res.when().put(apiResource("updatePlaceAPI"))
				.then().spec(validateStauscode(statuscode)).extract().response();
		return response;
	}
	
	public Response deleteAPI(String placeid, int statuscode) throws IOException
	{
		res=given().spec(requestSpecification()).body(Payloads.deletePayload(placeid));
		response=res.when().delete(apiResource("deletePlaceAPI"))
				.then().spec(validateStauscode(statuscode)).extract().response();
		return response;
	}

	

}
