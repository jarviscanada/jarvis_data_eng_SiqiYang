package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  TwitterDao twitterDao;
  Tweet newTweet;
  TwitterService twitterService;
  Tweet postTweet = new Tweet();
  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    twitterDao = new TwitterDao(httpHelper);
    twitterService = new TwitterService(twitterDao);
    String hashTag = "#abc";
    String text = "somtext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    postTweet.setText(text);

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});
    postTweet.setCoordinates(coordinates);
    newTweet = twitterService.postTweet(postTweet);
    assertNotNull(newTweet.getCoordinates());
    assertEquals(2,newTweet.getCoordinates().getCoordinates().length);
    assertEquals(postTweet.getCoordinates().getCoordinates()[0],newTweet.getCoordinates().getCoordinates()[0],0);
    assertEquals(postTweet.getCoordinates().getCoordinates()[1],newTweet.getCoordinates().getCoordinates()[1],0);
  }


  @After
  public void tearDown() throws Exception {
    String hashTag = "#abc";
    String text = "somtext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    postTweet.setText(text);

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});
    postTweet.setCoordinates(coordinates);
    Tweet second = twitterService.postTweet(postTweet);

    List<Tweet> findOne = twitterService.deleteTweets(new String[]{newTweet.getId_str(),second.getId_str()});
    assertEquals(newTweet.getText(),findOne.get(0).getText());
    assertEquals(newTweet.getCoordinates().getCoordinates()[0], findOne.get(0).getCoordinates().getCoordinates()[0],0);
    assertEquals(newTweet.getCoordinates().getCoordinates()[1],  findOne.get(0).getCoordinates().getCoordinates()[1],0);
    assertEquals(second.getText(),findOne.get(1).getText());
    assertEquals(second.getCoordinates().getCoordinates()[0], findOne.get(1).getCoordinates().getCoordinates()[0],0);
    assertEquals(second.getCoordinates().getCoordinates()[1],  findOne.get(1).getCoordinates().getCoordinates()[1],0);
    assertEquals(2,findOne.size());
  }


  @Test
  public void showTweet() throws Exception {
    Tweet findOne = twitterService.showTweet(newTweet.getId_str(),new String[]{});
    assertEquals(newTweet.getText(),findOne.getText());
    assertEquals(newTweet.getCoordinates().getCoordinates()[0], findOne.getCoordinates().getCoordinates()[0],0);
    assertEquals(newTweet.getCoordinates().getCoordinates()[1],  findOne.getCoordinates().getCoordinates()[1],0);
    try {
      twitterService.showTweet(newTweet.getId_str() + "some string",new String[]{});
    }catch (RuntimeException e) {
      assertTrue(true);
    }
  }

}