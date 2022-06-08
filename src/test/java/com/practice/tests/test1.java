package com.practice.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class test1 {

    static Logger logger = LogManager.getLogger(TestFile.class);

    @Test(description = "sample test")
    public void sampleTest1() {
        logger.debug("Test 1 executed");
    }

    @Test(description = "sample test2")
    public void sampleTest2() {
        logger.debug("Test 2 executed");

    }
}
