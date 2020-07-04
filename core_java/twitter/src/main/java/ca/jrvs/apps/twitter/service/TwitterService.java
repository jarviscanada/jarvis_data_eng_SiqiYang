package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TwitterService implements Service {
  private CrdDao dao;

  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);

    return (Tweet)dao.create(tweet);
  }


  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateId(id);

    return (Tweet)dao.findById(id);
  }


  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();
    Arrays.stream(ids).filter(id -> validateId(id)).forEach(id -> tweets.add((Tweet)dao.deleteById(id)));
    return tweets;
  }

  private boolean validateId(String id) {
    if(!id.matches("^[0-9]+$")) {
      throw new IllegalArgumentException("this id is not in a correct format");
    }
    return id.matches("^[0-9]+$");

  }

  private void validatePostTweet(Tweet tweet) {
    if(tweet.getText().length() > 140) {
      throw new IllegalArgumentException("this tweet text length is too long");

    } else if(tweet.getCoordinates().getCoordinates()[0] > 180 || tweet.getCoordinates().getCoordinates()[0] < -180) {
      throw new IllegalArgumentException("longtitude range must be in -180 to 180");

    } else if(tweet.getCoordinates().getCoordinates()[1] > 90 || tweet.getCoordinates().getCoordinates()[1] < -90) {
      throw new IllegalArgumentException("latitude must be in -90 to 90");
    }

  }
}
