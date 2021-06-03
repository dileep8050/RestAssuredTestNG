package resources;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Utils implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtentReportTest.getReportObject();
	private static Logger log = LogManager.getLogger(Utils.class.getName());
	ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) {
		log.info("\n"+"\n"+"\n"+
				"##########################################################################################################"+"\n"+
				"***************   "+result.getMethod().getMethodName() + "   Test cases execution started "+"****************"+"\n"+
				"##########################################################################################################");
		ThreadContext.put("id", UUID.randomUUID().toString());
		ThreadContext.put("TCname", result.getMethod().getMethodName());
		test=extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		extenttest.get().log(Status.PASS, result.getMethod().getMethodName()+" : executed succesfully");
		log.info("\n"+"\n"+"\n"+
				"##########################################################################################################"+"\n"+
				"***************   "+result.getMethod().getMethodName() + " is Pass "+"****************"+"\n"+
				"##########################################################################################################"+"\n"+
				"\n"+"\n"+"\n"+"\n"+"\n");
		
	}

	public void onTestFailure(ITestResult result) {

		extenttest.get().fail(result.getThrowable());
		log.error("Error Log : "+ result.getThrowable());
		log.info("\n"+"\n"+"\n"+
				"##########################################################################################################"+"\n"+
				"***************   "+result.getMethod().getMethodName() + " is Fail "+"****************"+"\n"+
				"##########################################################################################################"+"\n"+
				"\n"+"\n"+"\n"+"\n"+"\n");
	}

	public void onTestSkipped(ITestResult result) {
		extenttest.get().skip(result.getMethod().getMethodName()+"Test case is skipped due to dependent methods is facing issue");
		log.info("\n"+"\n"+"\n"+
				"##########################################################################################################"+"\n"+
				"***************   "+result.getMethod().getMethodName() + " is Skipped "+"****************"+"\n"+
				"##########################################################################################################"+"\n"+
				"\n"+"\n"+"\n"+"\n"+"\n");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		ThreadContext.clearMap();
		extent.flush();
	}
}
