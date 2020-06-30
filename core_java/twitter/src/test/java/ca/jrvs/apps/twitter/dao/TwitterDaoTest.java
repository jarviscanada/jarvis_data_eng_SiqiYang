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
  Tweet tweet;
  Coordinates coordinates;
  double[] coordinate;
  Tweet postOne;
  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    twitterDao = new TwitterDao(httpHelper);
    tweet = new Tweet();

    coordinates = new Coordinates();
    coordinate = new double[2];
    coordinate[0] = -122.42284884;
    coordinate[1] = 46.45645645;

    coordinates.setCoordinates(coordinate);
    coordinates.setType("Point");
    tweet.setCoordinates(coordinates);
    tweet.setText("this is for test");
    postOne = twitterDao.create(tweet);
  }


  @After
  public void tearDown() throws Exception {
    Tweet findOne = twitterDao.deleteById(postOne.getId_str());
    assertEquals(findOne.getText(),"this is for test");
    assertEquals(findOne.getCoordinates().getCoordinates()[0], -122.42284884,0);
    assertEquals(findOne.getCoordinates().getCoordinates()[1], 46.45645645,0);
  }

  @Test
  public void create() throws Exception {
    assertEquals(postOne.getText(),"this is for test");
    assertEquals(postOne.getCoordinates().getCoordinates()[0], -122.42284884,0);
    assertEquals(postOne.getCoordinates().getCoordinates()[1], 46.45645645,0);
  }



  @Test
  public void findById() throws Exception {
    Tweet findOne = twitterDao.findById(postOne.getId_str());
    assertEquals(postOne.getText(),findOne.getText());
    assertEquals(postOne.getCoordinates().getCoordinates()[0], findOne.getCoordinates().getCoordinates()[0],0);
    assertEquals(postOne.getCoordinates().getCoordinates()[1],  findOne.getCoordinates().getCoordinates()[1],0);
  }


}