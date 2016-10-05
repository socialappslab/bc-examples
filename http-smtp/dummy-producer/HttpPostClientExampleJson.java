import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.net.URI;

import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.Json;

import com.google.gson.JsonPrimitive;
import com.google.gson.JsonArray;


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
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;

/*
 * Example Java HTTP publisher using Apache HttpClient 4.5.2 API
 * This publisher uses form data to send emails:
 * application/json
 * http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/
 *
 * Instructions:
 * - Write your recipients emails in the "to" parameter of the form data.
 * - Download Gson, and HttpComponents Core and HttpComponents Core and
 * - HttpComponents Client libraries from http://hc.apache.org
 * - Compile: javac -cp ".:lib/*" HttpPostClientExampleJson.java
 * - Run: java -cp ".:lib/*" HttpPostClientExampleJson
 */
public class HttpPostClientExampleJson {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            String content = "<html><p><strong>Example Email from AppCivist</strong></p>";
            content.concat("<img src=\"http://www.airport-orly.com/images/paris-tour-eiffel-at-night.jpg\"");
            content.concat("alt=\"Mountain View\" style=\"width:304px;height:228px;\"></html>");

            //this is awkward, I didn't know how to create this
            //kind of array with javax.json
            com.google.gson.JsonObject obj = new com.google.gson.JsonObject();
            JsonArray array = new JsonArray();
            array.add(new JsonPrimitive("one@email.com"));
            array.add(new JsonPrimitive("two@email.fr"));
            obj.add("to", array);

            JsonBuilderFactory factory = Json.createBuilderFactory(null);
            JsonObject value = factory.createObjectBuilder()
             .add("destination",  "email")
             .add("to",  obj.toString())
             .add("from", "AppCivist Bot <bot@appcivist.org>")
             .add("subject", "This is just a test!")
             .add("text", content)
             .build();

             StringEntity requestEntity = new StringEntity(
             value.toString(), ContentType.APPLICATION_JSON);

             //HttPost receives the http binding component endpoint
             //ttp://localhost:3000/send is the BC url, so maybe replace it
             HttpPost httpPost = new HttpPost("http://localhost:3000/send");


            httpPost.setEntity(requestEntity);
            //set the query string
            //"email" is the name of the receiver in the platform
            //URI uri = new URIBuilder(httpPost.getURI()).addParameter("destination",
            //"email").build();
            URI uri = new URIBuilder(httpPost.getURI()).build();

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
