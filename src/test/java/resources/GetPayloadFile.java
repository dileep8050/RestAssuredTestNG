package resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetPayloadFile {
	public static String jsonFile(String filename) throws IOException 
	{
		String payload=new String(Files.readAllBytes(Paths.get("src\\main\\java\\jsonfiles\\"+filename+".json")));
		return payload;
	}
	
	public static String txtFile(String filename) throws IOException 
	{
		String payload=new String(Files.readAllBytes(Paths.get("src\\main\\java\\jsonfiles\\"+filename+".txt")));
		return payload;
	}
}
