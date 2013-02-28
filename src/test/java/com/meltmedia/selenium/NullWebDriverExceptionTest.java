package com.meltmedia.selenium;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author jacobheun
 */
public class NullWebDriverExceptionTest {

  /**
   * Make sure the exception doesn't explode when it is sent a null message
   */
  @Test
  public void testNullMessage() {

    NullWebDriverException ex = new NullWebDriverException(null);

    Assert.assertNull(ex.getMessage());

  }

  /**
   * Make sure the default message is set when nothing is passed
   */
  @Test
  public void testDefaultMessage() {

    NullWebDriverException ex = new NullWebDriverException();

    Assert.assertEquals(ex.getMessage(), "The current WebDriver must not be null.");

  }

  /**
   * Make sure we can set the message
   */
  @Test
  public void testPassingMessage() {

    NullWebDriverException ex = new NullWebDriverException("It's null bro!");

    Assert.assertEquals(ex.getMessage(), "It's null bro!");

  }

}
