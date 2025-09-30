package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utiles.DriverMange.DriverManager;
import utiles.commonHelper.AssertionHelper;
import utiles.commonHelper.ElementHelper;
import utiles.commonHelper.Global;
import utiles.config.LoadProperties;

public class ProductsPage {
    WebDriver driver;
    Global global;
    By firstProduct = By.xpath("//a[@href='/product_details/1']");
    By searchBar = By.xpath("//input[@id='search_product']");
    By searchLens = By.xpath("//button[@id='submit_search']");
    By searchResultItem = By.xpath("//a[@href='/product_details/28']");
    By searchResultItem2 = By.xpath("//a[@href=\"/product_details/29\"]");
    static String ProductDetailsURL = LoadProperties.PRODUCTDETAILSURL;
    By quantityField = By.xpath("//input[@id=\"quantity\"]");
    By popUpMessage = By.className("modal-content");
    By addToCartButton = By.cssSelector(".btn.btn-default.cart");
    By contiuneShoppingButton = By.xpath("//button[@data-dismiss=\"modal\"]");
    public ProductsPage() {
        this.driver = DriverManager.getDriver();
        global = Global.getInstance();
    }

    public ProductsPage searchForKeyword(String searchQuery) {
        WebElement searchBarWaited = ElementHelper.waitForVisibility(driver, searchBar);
        searchBarWaited.sendKeys(searchQuery);
        return this;
    }

    public ProductsPage clickOnSearchLens() {
        ElementHelper.scrollToElement(driver,searchLens);
        ElementHelper.click(driver, searchLens);
        return this;
    }

    public ProductsPage clickOnFirstSearchResult() {
        ElementHelper.click(driver, searchResultItem);
        return this;
    }

    public ProductsPage clickOnSecondSearchResult() {
        ElementHelper.click(driver, searchResultItem2);
        return this;
    }

    public void assertOnProductDetails() {
        By quantity = By.xpath("//input[@name='quantity']");
        ElementHelper.waitForVisibility(driver, quantity);
        AssertionHelper.assertUrl(driver, ProductDetailsURL);
    }

    public void assertOnProducts() {
        AssertionHelper.assertElementPresent(driver, firstProduct);
    }

    public void checkOnRelevantProducts(String searchQuery) {
        ElementHelper.scrollToElement(driver, searchResultItem);
        WebElement relevantElements = ElementHelper.findElementByTextContains(searchQuery, driver);
        AssertionHelper.assertNotNull(relevantElements, "No relevant items");
    }

    public void checkOnProductDetails() {
        WebElement productName = ElementHelper.findElementByTextContains("Pure", driver);
        WebElement productCategory = ElementHelper.findElementByTextContains("Category: Men", driver);
        WebElement productPrice = ElementHelper.findElementByTextContains("Rs. 1299", driver);
        AssertionHelper.assertNotNull(productName, "Product name doesn't exist");
        AssertionHelper.assertNotNull(productCategory, "Product Category doesn't exist");
        AssertionHelper.assertNotNull(productPrice, "Product Price doesn't exist");
    }

    public ProductsPage addProductToCart(int quantity) {
        ElementHelper.sendText(driver, quantityField, Integer.toString(quantity));
        ElementHelper.click(driver, addToCartButton);
        return this;
    }

    public ProductsPage assertOnAddingToCart() {
        AssertionHelper.assertElementPresent(driver, popUpMessage);
        return this;
    }

    public ProductsPage backButton() {
        ElementHelper.backFromPage(driver);
        return this;
    }
    public ProductsPage clickOnContiuneShopping()
    {
     ElementHelper.click(driver,contiuneShoppingButton);
     return this;
    }
}

