package ca.jrvs.apps.twitter.dao;


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
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by melo45 on 2020-07-02.
 */
@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {
  @Mock
  HttpHelper mockHttpHelper;

  @InjectMocks
  TwitterDao twitterDao;

  @Test
  public void showTweet() throws Exception{
    // test failed request
    String hashTag = "#abc";
    String text = "somtext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;

    //exception is expected that
    when(mockHttpHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      twitterDao.create(TweetUtil.buildTweet(text,lon,lat));
    }catch (RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "  \"id\":1097607853932564480,\n"
        + "  \"id_str\":\"1097607853932564480\",\n"
        + "  \"text\":\"test with location\",\n"
        + "  \"entities\":{\n"
        + "    \"hashtags\":[],\n"
        + "    \"user_mentions\":[]\n"
        + "  },\n"
        + "  \"coordinates\":null,\n"
        + "  \"retweet_count\":0,\n"
        + "  \"favorite_count\":0,\n"
        + "  \"favorited\":false,\n"
        + "  \"retweeted\":false\n"
        + "}";

    when(mockHttpHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(twitterDao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(TweetUtil.buildTweet(text, lat, lon));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());

  }

  @Test
  public void findById() throws IOException {
    String hashTag = "#abc";
    String text = "somtext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    String tweetJsonStr = "{\n"
        + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "  \"id\":1097607853932564480,\n"
        + "  \"id_str\":\"1097607853932564480\",\n"
        + "  \"text\":\"test with location\",\n"
        + "  \"entities\":{\n"
        + "    \"hashtags\":[],\n"
        + "    \"user_mentions\":[]\n"
        + "  },\n"
        + "  \"coordinates\":null,\n"
        + "  \"retweet_count\":0,\n"
        + "  \"favorite_count\":0,\n"
        + "  \"favorited\":false,\n"
        + "  \"retweeted\":false\n"
        + "}";
    when(mockHttpHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(twitterDao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
   Tweet tweet = spyDao.findById("1097607853932564480");
   assertNotNull(tweet);
   assertNotNull(tweet.getText());
   assertEquals("test with location",tweet.getText());
   assertEquals("Mon Feb 18 21:24:39 +0000 2019",tweet.getCreated_at());

  }

  @Test
  public void deletePost() throws IOException {
    String hashTag = "#abc";
    String text = "somtext " + hashTag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    String tweetJsonStr = "{\n"
        + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "  \"id\":1097607853932564480,\n"
        + "  \"id_str\":\"1097607853932564480\",\n"
        + "  \"text\":\"test with location\",\n"
        + "  \"entities\":{\n"
        + "    \"hashtags\":[],\n"
        + "    \"user_mentions\":[]\n"
        + "  },\n"
        + "  \"coordinates\":null,\n"
        + "  \"retweet_count\":0,\n"
        + "  \"favorite_count\":0,\n"
        + "  \"favorited\":false,\n"
        + "  \"retweeted\":false\n"
        + "}";
    when(mockHttpHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(twitterDao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.deleteById("1097607853932564480");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
    assertEquals("test with location",tweet.getText());
    assertEquals("Mon Feb 18 21:24:39 +0000 2019",tweet.getCreated_at());
  }

}
