package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class TwitterHttpHelper implements HttpHelper {

    /**
     * Dependencies are specified as private member variables
     */
    private OAuthConsumer consumer;
    private DefaultHttpClient httpClient;
    static final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);

    /**
     * Constructor
     * set up dependencies using secrets
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param tokenSecret
     */

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
                             String tokenSecret) {
        consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(accessToken,tokenSecret);

        httpClient = new DefaultHttpClient();
    }

    /**
     * default constructor
     */
    public TwitterHttpHelper() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(accessToken,tokenSecret);

        httpClient = new DefaultHttpClient();
    }
    /**
     * Execute a HTTP Post call
     *
     * @param uri
     * @return
     */
    @Override
    public HttpResponse httpPost(URI uri){
        try {
            return executeHttpRequest(HttpMethod.POST,uri,null);
        } catch (OAuthException | IOException e) {
            throw new RuntimeException("execute failed",e);
        }
    }

    /**
     * Execute a HTTP Get call
     *
     * @param uri
     * @return
     */
    @Override
    public HttpResponse httpGet(URI uri) {
        try {
          return executeHttpRequest(HttpMethod.GET,uri,null);
        } catch (OAuthException | IOException e) {
            throw new RuntimeException("failed to execute",e);
        }
    }

    private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity) throws OAuthException, IOException{
        if (method == HttpMethod.GET) {
            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        }else if (method == HttpMethod.POST) {
            HttpPost request = new HttpPost(uri);
            if(stringEntity != null) {
                request.setEntity(stringEntity);
            }
            consumer.sign(request);
            return httpClient.execute(request);
        }else {
            throw new IllegalArgumentException("Unknown http method:" + method.name());
        }
    }

    
}
