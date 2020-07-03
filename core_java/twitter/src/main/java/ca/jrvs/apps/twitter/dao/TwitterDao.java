package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.util.EntityUtils;


/**
 * Created by melo45 on 2020-06-30.
 */
public class TwitterDao implements CrdDao<Tweet,String>{

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";

  //URI symbols.
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //response code
  private static final int HTTP_OK = 200;


  private HttpHelper httpHelper;
  static final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

  @Autowired
  public TwitterDao (HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }


  @Override
  public Tweet create(Tweet tweet) {
    URI uri = null;
    try {
      uri = generateUri("POST",tweet);
    } catch (URISyntaxException e) {
      logger.error("URI Syntax error",e);
    }
    HttpResponse httpResponse = httpHelper.httpPost(uri);
    Tweet returnTweet = parseResponseBody(httpResponse,HTTP_OK);
    return returnTweet;
  }

  @Override
  public Tweet findById(String o) {
    URI uri = null;
    try {
      uri = generateUri("GET", o);
    } catch (URISyntaxException e) {
      logger.error("URI Syntax error",e);
    }
    HttpResponse httpResponse = httpHelper.httpGet(uri);
    Tweet returnTweet = parseResponseBody(httpResponse,HTTP_OK);
    return returnTweet;
  }

  @Override
  public Tweet deleteById(String o) {
    URI uri = null;
    try {
      uri = generateUri("DELETE", o);
    } catch (URISyntaxException e) {
      logger.error("URI Syntax error",e);
    }
    HttpResponse httpResponse = httpHelper.httpPost(uri);
    Tweet returnTweet = parseResponseBody(httpResponse,HTTP_OK);
    return returnTweet;
  }

  public URI generateUri(String method,Object o) throws URISyntaxException {
    String uri;
    URI answerUri;
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    if(method.equals("POST")) {
      uri = API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(((Tweet)o).getText()) + AMPERSAND
          + "long" + EQUAL + ((Tweet)o).getCoordinates().getCoordinates()[0] + AMPERSAND +
          "lat" + EQUAL + ((Tweet)o).getCoordinates().getCoordinates()[1];
      answerUri = new URI(uri);
      return answerUri;
    } else if (method.equals("GET")) {
      uri = API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + o;
      answerUri = new URI(uri);
      return answerUri;
    } else if (method.equals("DELETE")) {
      uri = API_BASE_URI + DELETE_PATH + o + ".json";
      answerUri = new URI(uri);
      return answerUri;
    }
    return null;
  }

  /**
   * this method is used to parse the response body and then convert the json response to the tweet object.
   * @param httpResponse the response from the http method
   * @param expectedStatusCode the expect response code returned by the http method.
   * @return the tweet object that return.
   */
  Tweet parseResponseBody(HttpResponse httpResponse, Integer expectedStatusCode) {
    Tweet tweet =null;
    //check the status code;
    int status = httpResponse.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try{
        logger.info(EntityUtils.toString(httpResponse.getEntity()));
      } catch (IOException e) {
        logger.error("there is no entity for this response",e);
      }
      throw new RuntimeException("http response status code invalid");
    }
    if (httpResponse.getEntity() == null) {
      throw new RuntimeException("http response's entity is empty");
    }
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(httpResponse.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("failed to convert the entity to string",e);
    }
    try {
      tweet = JsonParser.toObjectFromJson(jsonStr,Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("can not convert the json to the object",e);
    }
    return tweet;
  }
}
