package ca.jrvs.apps.twitter.service;

import static javafx.beans.binding.Bindings.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService twitterService;

  @Test
  public void postTweet() throws Exception {
    when(dao.create(any())).thenReturn(new Tweet());
    Tweet tweet = twitterService.postTweet(TweetUtil.buildTweet("test",50.0,0.0));
    assertNotNull(tweet);
    try{
      twitterService.postTweet(TweetUtil.buildTweet("test",-500.0,0.0));
    }catch (RuntimeException e) {
      assertTrue(true);
    }

    try{
      twitterService.postTweet(TweetUtil.buildTweet("test",-50.0,-99.0));
    }catch (RuntimeException e) {
      assertTrue(true);
    }

  }

  @Test
  public void showTweet() throws Exception {
    when(dao.findById(any())).thenReturn(new Tweet());
    Tweet tweet = twitterService.showTweet("123123123",new String[]{});
    assertNotNull(tweet);
    try{
      twitterService.showTweet("abc",new String[]{});
    }catch (RuntimeException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteTweets() throws Exception {
    when(dao.deleteById(any())).thenReturn(new Tweet());
    List<Tweet> tweet = twitterService.deleteTweets(new String[]{"123123123","13453453"});
    assertNotNull(tweet);
    assertEquals(2,tweet.size());

    try{
      twitterService.deleteTweets(new String[]{"asdasd","13453453"});
    }catch (RuntimeException e) {
      assertTrue(true);
    }

  }

}