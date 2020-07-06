package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TwitterCLIBean {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
    TwitterCLIApp twitterCLIApp = applicationContext.getBean(TwitterCLIApp.class);
    twitterCLIApp.run(args);
  }

  @Bean
  public TwitterCLIApp twitterCLIApp (Controller twitterController) {
    return new TwitterCLIApp(twitterController);
  }

  @Bean
  public Controller controller(Service twitterService) {
    return new TwitterController(twitterService);
  }

  @Bean
  public Service service (CrdDao dao) {
    return new TwitterService(dao);
  }

  @Bean
  public CrdDao dao (HttpHelper httpHelper) {
    return new TwitterDao(httpHelper);
  }


  @Bean
  HttpHelper helper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    return new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
  }
}
