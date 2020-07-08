package ca.jrvs.apps.twitter.spring;


import ca.jrvs.apps.twitter.TwitterCLIApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "ca.jrvs.apps.twitter")
public class TwitterCLIComScan {


  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        TwitterCLIComScan.class);
    TwitterCLIApp twitterCLIApp = applicationContext.getBean(TwitterCLIApp.class);
    twitterCLIApp.run(args);
    }

}
