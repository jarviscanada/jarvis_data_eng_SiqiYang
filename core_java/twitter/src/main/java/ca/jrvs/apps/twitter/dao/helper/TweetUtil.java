package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

/**
 * Created by melo45 on 2020-07-02.
 */
public class TweetUtil {
  public static Tweet buildTweet(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    tweet.setText(text);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});
    tweet.setCoordinates(coordinates);
    return tweet;
  }
}
