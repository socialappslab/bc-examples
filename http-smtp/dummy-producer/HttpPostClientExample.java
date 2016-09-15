import java.util.ArrayList;
import java.util.List;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.fluent.Form;
/*
 * Example Java HTTP publisher using Apache HttpClient 4.5.2 API
 * This publisher uses form data to send emails:
 * application/x-www-form-urlencoded
 * http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/
 *
 * Instructions:
 * - Write your recipients emails in the "to" parameter of the form data.
 * - Download HttpComponents Core and HttpComponents Core and
 * - HttpComponents Client libraries from http://hc.apache.org
 * - Compile: javac -cp ".:lib/*" HttpPostClientExample.java
 * - Run: java -cp ".:lib/*" HttpPostClientExample
 */
public class HttpPostClientExample {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            //HttPost receives the http binding component endpoint
            HttpPost httpPost = new HttpPost("http://localhost:3000/send");

            //set the form data
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("to", "write an email address here"));
            nvps.add(new BasicNameValuePair("to", "maybe another one"));
            nvps.add(new BasicNameValuePair("from", "from@example.org"));
            nvps.add(new BasicNameValuePair("subject", "This is just a test!"));
            nvps.add(new BasicNameValuePair("text", "<html><p><strong>Example Email from AppCivist</strong></p>\
            <img src=\"http://www.airport-orly.com/images/paris-tour-eiffel-at-night.jpg\"\
             alt=\"Mountain View\" style=\"width:304px;height:228px;\"></html>"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            //set the query string
            URI uri = new URIBuilder(httpPost.getURI()).addParameter("destination",
            "sp.appcivist.assembly.assemblytest.forum.post").build();

            ((HttpRequestBase) httpPost).setURI(uri);

            //now, execute it!
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
