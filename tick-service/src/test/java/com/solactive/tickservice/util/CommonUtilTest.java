package com.solactive.tickservice.util;

import com.solactive.tickservice.exception.ApiException;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilTest {

  @Test
  void parseTicksSuccessfullyForCorrectFormat() {
      var tickStrings = List.of("TIMESTAMP=1614398881|PRICE=5.24|CLOSE_PRICE=6.24|CURRENCY=EUR|RIC=AAPL.OQ");
      var tickList = CommonUtil.parseTicks(tickStrings);
      assertEquals(1, tickList.size());
      var tick = tickList.get(0);
      assertEquals(new Date(Long.parseLong("1614398881")), tick.getTimeStamp());
      assertEquals(5.24, tick.getPrice());
      assertEquals(6.24, tick.getClosePrice());
      assertEquals("EUR", tick.getCurrency());
      assertEquals("AAPL.OQ", tick.getRic());
  }

  @Test
  void parseTicksShouldThrowExceptionForIncorrectFormat() {
      var tickStrings = List.of("TIMESTAMP1614398881|PRICE=5.24|CLOSE_PRICE=6.24|CURRENCY=EUR|RIC=AAPL.OQ");
      assertThrows(ApiException.class, () -> CommonUtil.parseTicks(tickStrings));
  }

}