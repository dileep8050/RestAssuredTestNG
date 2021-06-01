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
		log.info("Initialize addAPI and constructing the url, body is taking from the  source type : "+type );
		
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
		log.info("successfully constructed the addAPI url and returns the RequestSpecification");
		log.info("Used Http method : post");
		response=res.when().post(apiResource("AddPlaceAPI"))
				.then().spec(validateStauscode(200)).extract().response();
		
		
		log.info("Add place API execution is completed");
		return response;
	}
	
	public Response getAPI(String placeid,int statuscode) throws IOException
	{
		log.info("Initialize getAPI and constructing the url, with the place id = "+placeid+" as a query param" );
		res=given()
				.spec(requestSpecification())
				.queryParam("place_id", placeid);
		log.info("successfully constructed the getAPI url and returns the RequestSpecification");
		log.info("Used Http method : get");
		response=res.when().get(apiResource("getPlaceAPI"))
				.then().spec(validateStauscode(statuscode)).extract().response();
		log.info("Get place API execution is completed");
		return response;
	}
	
	public Response updateAPI(String placeid, String address, int statuscode) throws IOException
	{
		log.info("Initialize updateAPI and constructing the url, with the place id = "+placeid+" , address = "+address );
		res=given().spec(requestSpecification()).body(Payloads.updatePayload(placeid, address));
		log.info("successfully constructed the UpdateAPI url and returns the RequestSpecification");
		log.info("Used Http method : put");
		response=res.when().put(apiResource("updatePlaceAPI"))
				.then().spec(validateStauscode(statuscode)).extract().response();
		log.info("Update place API execution is completed");
		return response;
	}
	
	public Response deleteAPI(String placeid, int statuscode) throws IOException
	{
		log.info("Initialize deleteAPI and constructing the url, with the place id = "+placeid);
		res=given().spec(requestSpecification()).body(Payloads.deletePayload(placeid));
		log.info("successfully constructed the deleteAPI url and returns the RequestSpecification");
		log.info("Used Http method : delete");
		response=res.when().delete(apiResource("deletePlaceAPI"))
				.then().spec(validateStauscode(statuscode)).extract().response();
		log.info("delete place API execution is completed");
		return response;
	}

	

}
