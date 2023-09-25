package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-button--secondary")
    public List<WebElement> button;
    @FindBy(css = "[name=firstName]")
    public WebElement txtFirstName;
    @FindBy(css = "[name=lastName]")
    public WebElement txtLastName;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtEmployeeId;
    @FindBy(className = "oxd-switch-input")
    public WebElement btnToggle;
    @FindBy(tagName = "input")
    public List<WebElement> txtUserName;
    @FindBy(tagName = "input")
    public List<WebElement> txtPassword;
    @FindBy(tagName = "input")
    public List<WebElement> txtConfirmPassword;
    @FindBy(css = "[type=submit]")
    public WebElement Submit;
    @FindBy(tagName = "input")
    public List<WebElement> txtSearchEmpName;
    @FindBy(tagName = "button")
    public List<WebElement> btnUpdateEmployee;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtUpdateEmployeeId;

    private WebDriverWait wait;

    public PIMPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void createEmployee(String firstname, String lastname, String employeeId, String username, String password) {
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        WebElement empID = txtEmployeeId.get(4);
        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(employeeId);
        btnToggle.click();
        txtUserName.get(7).sendKeys(username);
        txtPassword.get(10).sendKeys(password);
        txtConfirmPassword.get(11).sendKeys(password);
        Submit.click();
    }

    public void createEmployeeWithoutUsername(String firstname, String lastname, String employeeId, String password) {
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        WebElement empID = txtEmployeeId.get(4);
        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(employeeId);
        btnToggle.click();
        txtPassword.get(10).sendKeys(password);
        txtConfirmPassword.get(11).sendKeys(password);
        Submit.click();
    }

    public void searchEmployeeByInvalidName(String employeeName) {
        txtSearchEmpName.get(1).sendKeys(employeeName);
        Submit.click();
    }

    public void searchEmployeeByValidName(String employeeName) {
        txtSearchEmpName.get(1).sendKeys(employeeName);
        Submit.click();
    }

    public void updateEmployeeById(String employeeId) {
        btnUpdateEmployee.get(6).click();
        txtUpdateEmployeeId.get(5).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUpdateEmployeeId.get(5).sendKeys(employeeId);
        btnUpdateEmployee.get(1).click();
    }

    public void searchEmployeeByValidId(String randomEmployeeId) {
        txtEmployeeId.get(1).sendKeys(randomEmployeeId);
        Submit.click();
    }
}
