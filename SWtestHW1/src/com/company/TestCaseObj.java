package com.company;

import java.util.ArrayList;
import java.util.List;

public class TestCaseObj {
    private int testCaseNum;
    private int[] inputs;
    private String expected;
    private String actual;
    private int errCode;

    public TestCaseObj(int testCaseNum, int[] inputs, String expected) {
        this.testCaseNum = testCaseNum;
        this.inputs = inputs;
        this.expected = expected;
    }

    public int getTestCaseNum() {
        return testCaseNum;
    }

    public int[] getInputs() {
        return inputs;
    }

    public String getExpected() {
        return expected;
    }

    public String getActual() {
        return actual;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setTestCaseNum(int testCaseNum) {
        this.testCaseNum = testCaseNum;
    }

    public void setInputs(int[] inputs) {
        this.inputs = inputs;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }
}
