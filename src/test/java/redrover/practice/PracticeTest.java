package redrover.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import redrover.practice.runner.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PracticeTest extends BaseTest {

    private static final String STANDARD_USER_LOGIN = "standard_user";
    private static final String STANDARD_USER_PASSWORD = "secret_sauce";

    @Test
    public void testItemsSortedInReverseOrder() {
        getDriver().get("https://www.saucedemo.com/");
        getDriver().findElement(By.id("user-name")).sendKeys(STANDARD_USER_LOGIN);
        getDriver().findElement(By.id("password")).sendKeys(STANDARD_USER_PASSWORD);
        getDriver().findElement(By.id("login-button")).click();

        WebElement itemsSortingCriterion = getDriver().findElement(By.className("product_sort_container"));
        Select select = new Select(itemsSortingCriterion);
        select.selectByVisibleText("Name (Z to A)");

        List<WebElement> items = getDriver().findElements(By.xpath("//div[@class='inventory_item_name ']"));

        List<String> itemsNames = new ArrayList<>();
        for (WebElement itemName : items) {
            String name = itemName.getText();
            itemsNames.add(name);
        }

        List<String> expectedSortedNames = new ArrayList<>(itemsNames);
        expectedSortedNames.sort(Collections.reverseOrder());

        Assert.assertEquals(itemsNames, expectedSortedNames);
    }

// Trying to protect main

    @Test
    public void testSauceDemo() throws InterruptedException {
        getDriver().get("https://www.saucedemo.com/");
        getDriver().findElement(By.id("user-name")).sendKeys("problem_user");
        getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        getDriver().findElement(By.id("login-button")).click();

        String itemName = getDriver().findElement(By.id("item_5_title_link")).getText();

        Assert.assertEquals(itemName, "Sauce Labs Fleece Jacket");

//        String pageHeading = getDriver().findElement(By.className("app_logo")).getText();
//        String currentUrl = getDriver().getCurrentUrl();
//        String expectedUrl = "https://www.saucedemo.com/inventory.html";
//
//        Assert.assertEquals(pageHeading, "Swag Labs", "The page heading is not Swag Labs");
//
//        Assert.assertEquals(currentUrl, expectedUrl, "Something is wrong");

    }
}
