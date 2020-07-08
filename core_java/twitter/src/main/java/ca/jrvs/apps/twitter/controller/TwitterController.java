package ca.jrvs.apps.twitter.controller;


import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {
  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";
  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {
    if(args.length < 3) {
      throw new IllegalArgumentException("the number of argument is not correct, need three args");
    }

    String tweetText = args[1];
    String[] coordinates = args[2].split(COORD_SEP);
    if (tweetText.length() > 140) {
      throw new IllegalArgumentException("the text of tweet is exceed 140 characters");
    }
    if (coordinates.length != 2) {
      throw new IllegalArgumentException("the number of correct coordinates is not correct.");
    }

    Double lon = Double.valueOf(coordinates[0]);
    Double lat = Double.valueOf(coordinates[1]);
    Tweet tweet = TweetUtil.buildTweet(tweetText,lon,lat);

    return service.postTweet(tweet);
  }

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 3) {
      throw new IllegalArgumentException("TwitterCLI show tweet_id [field1,fields2]");
    }else if (!args[1].matches("^[0-9]+$")) {
      throw new IllegalArgumentException("id should be pure digits");
    }
    Tweet returnTweet = service.showTweet(args[1],new String[]{});

    return returnTweet;
  }

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("USAGE: TwitterCLI delete [id1,id2,..]");
    }
    String[] ids = args[1].split(COMMA);
    for(String i : ids) {
      if(!i.matches("^[0-9]+$")) {
        throw new IllegalArgumentException("id should be pure digits");
      }
    }


    return service.deleteTweets(ids);
  }
}
