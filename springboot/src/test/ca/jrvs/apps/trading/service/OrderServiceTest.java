package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.omg.CORBA.Any;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
  @Captor
  ArgumentCaptor<SecurityOrder> securityOrderArgumentCaptor;

  @Mock
  private AccountDao accountDao;

  @Mock
  private SecurityOrderDao securityOrderDao;

  @Mock
  private QuoteDao quoteDao;

  @Mock
  private PositionDao positionDao;

  @InjectMocks
  private OrderService orderService;

  private MarketOrderDto marketOrderDto;



  @Test
  public void executeMarketOrderTest() {
    marketOrderDto = new MarketOrderDto();
    marketOrderDto.setAccountId(null);
    marketOrderDto.setSize(0);
    marketOrderDto.setTicker("AAPL");
    try{
      orderService.executeMarketOrder(marketOrderDto);
      fail();
    }catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try{
      orderService.executeMarketOrder(marketOrderDto);
      fail();
    }catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void handleBuy() {
    marketOrderDto = new MarketOrderDto();
    marketOrderDto.setSize(12);
    marketOrderDto.setTicker("AAPL");
    marketOrderDto.setAccountId(123);
    Account account = new Account();
    account.setAmount(20d);
    SecurityOrder securityOrder = new SecurityOrder();

    Quote quote = new Quote();
    quote.setAskPrice(1d);


    when(quoteDao.findById(any())).thenReturn(Optional.of(quote));
    when(accountDao.findById(any())).thenReturn(Optional.of(account));
    when(securityOrderDao.save(any())).thenReturn(securityOrder);
    when(accountDao.save(any())).thenReturn(account);
    try{
      securityOrder = orderService.executeMarketOrder(marketOrderDto);
      System.out.print("123");
    }catch (Exception e) {
      assertNotNull(securityOrder);
      assertEquals(123,securityOrder.getAccount_id(),1);
      assertEquals(8d,account.getAmount(),0);
      assertEquals(1d,securityOrder.getPrice(),1);
    }


  }
  @Test
  public void handleSell() {
    marketOrderDto = new MarketOrderDto();
    marketOrderDto.setSize(-12);
    marketOrderDto.setTicker("AAPL");
    marketOrderDto.setAccountId(123);
    Account account = new Account();
    account.setAmount(20d);
    SecurityOrder securityOrder = new SecurityOrder();

    Quote quote = new Quote();
    quote.setBidPrice(1d);
    Position position = new Position();
    position.setPosition(20);



    when(quoteDao.findById(any())).thenReturn(Optional.of(quote));
    when(accountDao.findById(any())).thenReturn(Optional.of(account));
    when(securityOrderDao.save(any())).thenReturn(securityOrder);
    when(positionDao.findByIdAndTicker(any(),any())).thenReturn(Optional.of(position));
    when(accountDao.save(any())).thenReturn(account);
    try{
      securityOrder = orderService.executeMarketOrder(marketOrderDto);
      System.out.print("123");
    }catch (Exception e) {
      assertNotNull(securityOrder);
      assertEquals(123,securityOrder.getAccount_id(),1);
      assertEquals(32d,account.getAmount(),0);
      assertEquals(1d,securityOrder.getPrice(),1);
    }


  }

}