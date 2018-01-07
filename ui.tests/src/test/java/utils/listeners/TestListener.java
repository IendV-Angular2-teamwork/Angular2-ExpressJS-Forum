package utils.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.excelUtils.ExcelUtil;

public class TestListener extends BaseTest implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        ExcelUtil.setCellData("PASSED", ExcelUtil.getRowNumber(), ExcelUtil.getColumnNumber());
    }

    public void onTestFailure(ITestResult iTestResult) {
        ExcelUtil.setCellData("FAILED", ExcelUtil.getRowNumber(), ExcelUtil.getColumnNumber());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        ExcelUtil.setCellData("SKIPPED", ExcelUtil.getRowNumber(), ExcelUtil.getColumnNumber());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
