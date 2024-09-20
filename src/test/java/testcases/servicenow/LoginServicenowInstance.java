package testcases.servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataLibrary;
import utils.RetryEngine;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LoginServicenowInstance {


    @Test(dataProvider = "fetchData", retryAnalyzer = RetryEngine.class)
    public void wakeupServicenowInstance(String mail, String password) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://developer.servicenow.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement body = driver.findElement(By.xpath("//dps-app"));
        SearchContext shadowRoot1 = body.getShadowRoot();
        WebElement element1 = shadowRoot1.findElement(By.cssSelector("dps-navigation-header"));
        SearchContext shadowRoot2 = element1.getShadowRoot();
        WebElement element2 = shadowRoot2.findElement(By.cssSelector("dps-login"));
        SearchContext shadowRoot3 = element2.getShadowRoot();
        WebElement element3 = shadowRoot3.findElement(By.cssSelector("dps-button"));
        SearchContext shadowRoot4 = element3.getShadowRoot();
        WebElement signInButton = shadowRoot4.findElement(By.cssSelector("button.dps-link.-primary.-md"));
        signInButton.click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(mail);
        driver.findElement(By.id("username_submit_button")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password_submit_button")).click();
        Thread.sleep(5000);


        WebElement startBuildHost1 = driver.findElement(By.cssSelector("dps-app"));
        SearchContext startBuildRoot1 = startBuildHost1.getShadowRoot();
        WebElement startBuildHost2 = startBuildRoot1.findElement(By.cssSelector("dps-home-auth-quebec"));
        SearchContext startBuildRoot2 = startBuildHost2.getShadowRoot();
        //        dps-button -round -md has-action -external-action has-button-and-icon

        //span[text()='Waking up instance']
        try {
            new WebDriverWait(driver, Duration.ofSeconds(60))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text()='Waking up instance']")));
        } catch (Exception e) {
            System.out.println("");
        }
//        Thread.sleep(50000);
        WebElement startBuildButton = startBuildRoot2.findElement(By.cssSelector("button.dps-button.-round.-md.has-action.-external-action.has-button-and-icon"));
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.elementToBeClickable(startBuildButton))
                .click();
        driver.switchTo().window(driver.getWindowHandle());
        WebElement profileHost = driver.findElement(By.tagName("dps-app"));
        SearchContext profileHostShadowRoot = profileHost.getShadowRoot();
        WebElement profileHost2 = profileHostShadowRoot.findElement(By.cssSelector("dps-navigation-header"));
        SearchContext profileHostShadowRoot2 = profileHost2.getShadowRoot();
        WebElement profile = profileHostShadowRoot2.findElement(By.cssSelector("dps-login"));
        profile.click();
        WebElement navigationHeaderHost = profileHostShadowRoot2.findElement(By.cssSelector("dps-navigation-login-management"));
        SearchContext navigationHeaderHostShadowRoot = navigationHeaderHost.getShadowRoot();
        WebElement navigationInstanceManagementHost = navigationHeaderHostShadowRoot.findElement(By.cssSelector("dps-navigation-instance-management"));
        SearchContext navigationInstanceManagementHostShadowRoot = navigationInstanceManagementHost.getShadowRoot();
//        navigationInstanceManagementHostShadowRoot.findElement(By.xpath("//dps-button[text()='Manage instance password']")).click();
        navigationInstanceManagementHostShadowRoot.findElement(By.cssSelector("img[src='reset-admin-password-icon.svg'] + dps-button")).click();
        WebElement instanceModalHost = profileHostShadowRoot.findElement(By.cssSelector("dps-instance-modal"));
        SearchContext instanceModalHostShadowRoot = instanceModalHost.getShadowRoot();
//        String instance = instanceModalHostShadowRoot.findElement(By.cssSelector("div.instanceModal-confirmationView")).getText();
//        System.out.println(instance);

        List<WebElement> instanceData = instanceModalHostShadowRoot.findElements(By.cssSelector("span.resetPassword-value"));
        System.out.println("============================="+mail+" Instance=====================================");
        System.out.println("Instance url: "+instanceData.get(1).getText());
        System.out.println("Instance username: "+instanceData.get(2).getText());
        System.out.println("Instance password: "+instanceData.get(3).getText());
        System.out.println("==========================COMPLETED=================================");


        driver.quit();
        /*WebElement shadowHost1 = driver.findElement(By.xpath("//dps-app"));
        WebElement shadowRoot1 = (WebElement) driver.executeScript("return arguments[0].shadowRoot", shadowHost1);
        WebElement shadowHost2 = shadowRoot1.findElement(By.xpath("//dps-navigation-header"));
        WebElement shadowRoot2 = (WebElement) driver.executeScript("return arguments[0].shadowRoot", shadowHost2);
        WebElement shadowHost3 = shadowRoot2.findElement(By.xpath("//dps-login"));
        WebElement shadowRoot3 = (WebElement) driver.executeScript("return arguments[0].shadowRoot", shadowHost3);
        WebElement shadowHost4 = shadowRoot3.findElement(By.xpath("//dps-button"));
        WebElement shadowRoot4 = (WebElement) driver.executeScript("return arguments[0].shadowRoot", shadowHost4);
        WebElement signInButton = shadowRoot4.findElement(By.xpath("//span[text()='Sign In']/.."));
        signInButton.click();
*/
    }

    @DataProvider(name = "fetchData")
    public String[][] getData() throws IOException {
        return DataLibrary.excelData();
    }

}
