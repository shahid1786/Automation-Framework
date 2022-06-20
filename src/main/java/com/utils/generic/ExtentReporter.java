package com.utils.generic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReporter implements ITestListener {

    static Logger log = LogManager.getLogger(ExtentReporter.class);

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    @Override
    public void onStart(ITestContext iTestContext) {
        extent.attachReporter(spark);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("I am in onFinish method " + iTestContext.getName());
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(iTestResult.getName() + " test is starting.");
        test.set(extent.createTest(iTestResult.getName()));
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
        test.get().log(Status.FAIL,"Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(iTestResult.getName() + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        test.get().log(Status.SKIP, "Test Skipped");
    }
}
