package com.practice.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class test2 {

    Logger logger  = LogManager.getLogger(test2.class);

    @Test(description = "sample test")
    public void sampleTest3(){
        throw new RuntimeException("Sample Test failed");
    }

    @Test(description = "sample test")
    public void sampleTest4(){
        throw new RuntimeException("Sample Test failed");
    }
}
