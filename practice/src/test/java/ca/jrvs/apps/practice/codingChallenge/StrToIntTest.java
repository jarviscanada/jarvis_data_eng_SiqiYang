package ca.jrvs.apps.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by melo45 on 2020-07-06.
 */
public class StrToIntTest {
  private StrToInt strToInt;
  @Before
  public void setup() {
    strToInt = new StrToInt();
  }

  @Test
  public void builtIn() throws Exception {
    assertEquals(123123,strToInt.builtIn("123123"),0);
    assertNull(strToInt.builtIn("12a123"));

  }

  @Test
  public void normalWay() throws Exception {
    assertEquals(123123,strToInt.builtIn("123123"),0);
    assertNull(strToInt.builtIn("12a123"));
  }

}