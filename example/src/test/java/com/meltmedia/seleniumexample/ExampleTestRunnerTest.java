package com.meltmedia.seleniumexample;

import com.meltmedia.selenium.NullWebDriverException;
import com.meltmedia.selenium.ScreenshotManager;
import com.meltmedia.selenium.ScreenshotTestRule;
import com.meltmedia.selenium.WebDriverManager;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExampleTestRunnerTest {

  private WebDriverManager manager;
  private ScreenshotManager screenshotManager;
  private RemoteWebDriver driver;

  @Rule
  public ScreenshotTestRule screenshotTestRule;

  @Before
  public void setUp() throws Exception {

    // Create the screenshot manager
    screenshotManager = new ScreenshotManager();

    // Create the test rule
    screenshotTestRule = new ScreenshotTestRule(screenshotManager);

    // Web driver manager
    manager = new WebDriverManager();

    // Request a new driver
    driver = (RemoteWebDriver)manager.requestDriver();

    // Setup timeout delays
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  /**
   * Test some interaction at meltmedia.com
   */
  @Test
  public void exampleSuite() throws IOException, NullWebDriverException {
    driver.get("http://meltmedia.com/");

    screenshotManager.saveScreenshot("home/landing_page");

    driver.findElement(By.linkText("About")).click();

    screenshotManager.saveScreenshot("about/landing_page");

    driver.findElement(By.linkText("Gimme more!")).click();

    screenshotManager.saveScreenshot("about/gimme_more");

    driver.findElement(By.linkText("Gimme more!")).click();
    driver.findElement(By.linkText("Blog")).click();

    screenshotManager.saveScreenshot("blog/landing_page");

    driver.findElement(By.id("s")).click();
    driver.findElement(By.id("s")).clear();
    driver.findElement(By.id("s")).sendKeys("Hack");

    screenshotManager.saveScreenshot("blog/search_hack");

    driver.findElement(By.id("searchsubmit")).click();

    screenshotManager.saveScreenshot("blog/search_hack_submit");

    driver.findElement(By.xpath("//article[@id='post-743']//span[.='Hack-a-palooza']")).click();

    screenshotManager.saveScreenshot("blog/click_blog_hack");

    driver.findElement(By.xpath("//nav[@id='nav-below']//span[.='meltmedia.com']")).click();

    screenshotManager.saveScreenshot("blog/click_relatedblog_refresh");

  }

  @After
  public void tearDown() {

    // Release our useage of this driver
    manager.releaseDriver();

  }

}