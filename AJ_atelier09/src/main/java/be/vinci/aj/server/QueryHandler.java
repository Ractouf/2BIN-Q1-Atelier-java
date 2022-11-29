package be.vinci.aj.server;

import be.vinci.aj.domaine.QueryImpl;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class QueryHandler extends Thread {
    private QueryImpl query;

    public QueryHandler(QueryImpl query) {
        this.query = query;
    }

    @Override
    public void run() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(query.getUrl());

            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

                EntityUtils.consume(entity);
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
