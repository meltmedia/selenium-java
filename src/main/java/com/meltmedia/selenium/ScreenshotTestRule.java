package com.meltmedia.selenium;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A TestRule based annotation used to capture a screenshot when a test fails
 * @author jacobheun
 */
public class ScreenshotTestRule implements TestRule {

  private ScreenshotManager screenshotManager;

  /**
   * Will ensure this instance has a ScreenshotManager reference
   * @param screenshotManager The screenshot manager responsible for handling failure screenshots
   */
  public ScreenshotTestRule(ScreenshotManager screenshotManager) {

    // Ensure screenshotManagerClass is not null
    this.screenshotManager = (screenshotManager != null) ? screenshotManager : new ScreenshotManager();

  }

  /**
   * Will set the screenshot manager
   */
  public ScreenshotTestRule() {

    this(new ScreenshotManager());

  }

  /**
   * Returns the current ScreenshotManager instance
   * @return ScreenshotManager
   */
  public ScreenshotManager getScreenshotManager() {

    return this.screenshotManager;

  }

  @Override
  public Statement apply(final Statement base, final Description description) {

    // Create and return a new Statement
    return new Statement() {

      @Override
      public void evaluate() throws Throwable {

        try {

          // Run the base statement
          base.evaluate();

        } catch (Throwable t) {

          // Snap a screenshot of the failure
          screenshotManager.saveFailureScreenshot(description.getDisplayName());

          // Rethrow to allow failure reporting in junit
          throw t;

        }

      }

    };

  }

}
