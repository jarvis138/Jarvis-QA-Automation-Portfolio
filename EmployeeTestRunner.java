import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.EmployeeInfoPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class EmployeeTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    EmployeeInfoPage employeeInfoPage;

    @Test(priority = 1, description = "Login With Second User")
    public void doLoginWithSecondUser() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        JSONObject userObject = Utils.loadJSONFileContainingArray("./src/test/resources/Employee.json", 1);
        String username = userObject.get("username").toString();
        String password = userObject.get("password").toString();
        loginPage.doLogin(username, password);

        // Wait until the header text is displayed
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement headerText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h6")));

        String header_actual = headerText.getText();
        String header_expected = "Dashboard";
        Assert.assertEquals(header_actual, header_expected);
    }

    @Test(priority = 2, description = "Insert second user's Gender, Blood Type, Address, and Email")
    public void updateUserInformation() throws IOException, ParseException {
        employeeInfoPage = new EmployeeInfoPage(driver);
        employeeInfoPage.userMenu.get(2).click();

        Utils.doScroll(driver, 500);
        employeeInfoPage.selectGender();

        Utils.doScroll(driver, 500);
        employeeInfoPage.selectBloodType();

        driver.navigate().refresh();

        employeeInfoPage.selectContact();

        // Wait until the header text is displayed
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement headerText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h6")));

        String header_actual = headerText.getText();
        String header_expected = "PIM";
        Assert.assertEquals(header_actual, header_expected);
    }

    @Test(priority = 3, description = "Second User Logout Successfully")
    public void logOut() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
        driver.close();
    }
}
