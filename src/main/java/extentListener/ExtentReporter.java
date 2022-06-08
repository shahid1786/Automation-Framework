package extentListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReporter implements ITestListener {

    static Logger log = LogManager.getLogger(ExtentReporter.class);
    ThreadLocal<ExtentReports> extent = new ThreadLocal<>();
    ThreadLocal<ExtentSparkReporter> spark = new ThreadLocal<>();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

//    public static void main(String args[])  throws ClassNotFoundException {
//            ExtentReports extent = new ExtentReports();
//            ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
//            extent.attachReporter(spark);
//
//    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("I am in onStart method " + iTestContext.getName());
        extent.set(new ExtentReports());
        spark.set(new ExtentSparkReporter("ExtentReport.html"));
        extent.get().attachReporter(spark.get());

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("I am in onFinish method " + iTestContext.getName());
        extent.get().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(iTestResult.getName() + " test is starting.");
        test.set(extent.get().createTest(iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(iTestResult.getName() + " test is succeed.");
        //ExtentReports log operation for passed tests.
        test.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(iTestResult.getName() + " test is failed.");
        test.get().log(Status.PASS,"Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(iTestResult.getName() + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        test.get().log(Status.SKIP, "Test Skipped");
    }
}
