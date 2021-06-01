package resources;

import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtentReportTest.getReportObject();
	ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) {
		ThreadContext.put("id", UUID.randomUUID().toString());
		ThreadContext.put("TCname", result.getMethod().getMethodName());
		test=extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS, result.getMethod().getMethodName()+" : executed succesfully");
	}

	public void onTestFailure(ITestResult result) {
		extenttest.get().fail(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		extenttest.get().skip(result.getMethod().getMethodName()+"Test case is skipped due to dependent methods is facing issue");
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
