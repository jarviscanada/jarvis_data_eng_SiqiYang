package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController twitterController;

  @Test
  public void postTweet() throws Exception {
    when(service.postTweet(any())).thenReturn(TweetUtil.buildTweet("text",1.0,1.0));
    String[] args = new String[]{"post","text","1.0:1.0"};
    Tweet tweet = twitterController.postTweet(args);
    assertNotNull(tweet);
    try{
      String[] argOne = new String[]{"text","1.0:1.0"};
      twitterController.postTweet(argOne);
    }catch (RuntimeException e) {
      assertTrue(true);
    }


  }

  @Test
  public void showTweet() throws Exception {
    when(service.showTweet(any(),any())).thenReturn(TweetUtil.buildTweet("text",1.0,1.0));
    String[] args = new String[]{"show","123123123","1.0,1.0"};
    Tweet tweet = twitterController.showTweet(args);
    assertNotNull(tweet);
    try{
      String[] argOne = new String[]{"text","1.0:1.0"};
      twitterController.showTweet(argOne);
    }catch (RuntimeException e) {
      assertTrue(true);
    }

    try{
      String[] argOne = new String[]{"show","asdasd","1.0:1.0"};
      twitterController.showTweet(argOne);
    }catch (RuntimeException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteTweet() throws Exception {
    when(service.deleteTweets(any())).thenReturn(new ArrayList<Tweet>());
    String[] args = new String[]{"delete","123123123,123123"};
    List<Tweet> tweets = twitterController.deleteTweet(args);
    assertNotNull(tweets);
    try{
      String[] argOne = new String[]{"123123123,123123"};
      twitterController.deleteTweet(argOne);
    }catch (RuntimeException e) {
      assertTrue(true);
    }

    try{
      String[] argOne = new String[]{"delete","12312asd3123,1231"};
      twitterController.deleteTweet(argOne);
    }catch (RuntimeException e) {
      assertTrue(true);
    }
  }

}