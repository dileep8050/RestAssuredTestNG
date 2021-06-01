package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportTest {
	static ExtentReports extent;
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter e=new ExtentSparkReporter(path);
		e.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(e);
		extent.setSystemInfo("Name", "Dileep");
		extent.setSystemInfo("Device", "Laptop");
		extent.setSystemInfo("Country", "India");
		return extent;
	}

}
