package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by melo45 on 2020-06-30.
 */
public class TwitterDaoTest {
  TwitterDao twitterDao;
  Tweet newTweet;
  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    twitterDao = new TwitterDao(httpHelper);
    String hashTag = "#abc";
    String text = "somtext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = new Tweet();
    postTweet.setText(text);

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});
    postTweet.setCoordinates(coordinates);
    newTweet = twitterDao.create(postTweet);
  }


  @After
  public void tearDown() throws Exception {
    Tweet findOne = twitterDao.deleteById(newTweet.getId_str());
    assertEquals(newTweet.getText(),findOne.getText());
    assertEquals(newTweet.getCoordinates().getCoordinates()[0], findOne.getCoordinates().getCoordinates()[0],0);
    assertEquals(newTweet.getCoordinates().getCoordinates()[1],  findOne.getCoordinates().getCoordinates()[1],0);


  }

  @Test
  public void create() throws Exception {
    String hashTag = "#abc";
    String text = "somtext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    Tweet postTweet = new Tweet();
    postTweet.setText(text);

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});
    postTweet.setCoordinates(coordinates);
    Tweet tweet = twitterDao.create(postTweet);
    assertEquals(text,tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2,tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon,tweet.getCoordinates().getCoordinates()[0],0);
    assertEquals(lat,tweet.getCoordinates().getCoordinates()[1],0);
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags()[0].getText()));
    twitterDao.deleteById(tweet.getId_str());
  }



  @Test
  public void findById() throws Exception {
    Tweet findOne = twitterDao.findById(newTweet.getId_str());
    assertEquals(newTweet.getText(),findOne.getText());
    assertEquals(newTweet.getCoordinates().getCoordinates()[0], findOne.getCoordinates().getCoordinates()[0],0);
    assertEquals(newTweet.getCoordinates().getCoordinates()[1],  findOne.getCoordinates().getCoordinates()[1],0);
  }


}