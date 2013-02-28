package com.meltmedia.selenium;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.mockito.Mockito.*;

/**
 * @author jacobheun
 */
public class ScreenshotTestRuleTest {

  /**
   * Verifies that on a statement exception, saveFailureScreenshot is called on the given manager
   * @throws Throwable
   */
  @Test(expected = Exception.class)
  public void ensureScreenshotOnError() throws Throwable {

    ScreenshotManager mockManager = mock(ScreenshotManager.class);
    ScreenshotTestRule rule = new ScreenshotTestRule(mockManager);

    Statement mockBase = mock(Statement.class, CALLS_REAL_METHODS);
    Description mockDescription = mock(Description.class);

    // Throw an exception when evaluate is called on statement
    doThrow(new Exception()).when(mockBase).evaluate();
    doReturn("My test statement").when(mockDescription).getDisplayName();

    // Get the statement to evaluate
    Statement returnedStatement = rule.apply(mockBase, mockDescription);


    try {

      // Evaulate the statement
      returnedStatement.evaluate();

    } catch(Exception ex) {

      // Verify failed screenshot was called
      verify(mockManager).saveFailureScreenshot("My test statement");

      throw ex;

    }

  }

  /**
   * Ensure that if a statement passes, saveFailureScreenshot is not called on the screenshot manager
   * @throws Throwable
   */
  @Test
  public void ensureNoScreenshotOnPass() throws Throwable {

    ScreenshotManager mockManager = mock(ScreenshotManager.class);
    ScreenshotTestRule rule = new ScreenshotTestRule(mockManager);

    Statement mockBase = mock(Statement.class, CALLS_REAL_METHODS);
    Description mockDescription = mock(Description.class);

    // Throw an exception when evaluate is called on statement
    doNothing().when(mockBase).evaluate();
    doReturn("My test statement").when(mockDescription).getDisplayName();

    // Get the statement to evaluate
    Statement returnedStatement = rule.apply(mockBase, mockDescription);


    try {

      // Evaulate the statement
      returnedStatement.evaluate();

    } catch(Exception ex) {

      throw ex;

    }

    // Verify failed screenshot was called
    verifyZeroInteractions(mockManager);

  }

  /**
   * Validates that the ScreenshotTestRule's ScreenshotManager cannot be null
   */
  @Test
  public void ensureScreenshotManager() {

    ScreenshotTestRule rule = new ScreenshotTestRule();
    Assert.assertNotNull(rule.getScreenshotManager());

    ScreenshotTestRule rule2 = new ScreenshotTestRule( new ScreenshotManager() );
    Assert.assertNotNull(rule2.getScreenshotManager());

  }

}
