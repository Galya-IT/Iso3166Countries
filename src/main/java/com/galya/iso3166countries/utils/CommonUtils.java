package com.galya.iso3166countries.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class CommonUtils {

    public static String getPageHtml(String url) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getPageRequest = new HttpGet(url);

        HttpResponse getPageResponse = httpClient.execute(getPageRequest);

        // TODO: manage response status code : response.getStatusLine().getStatusCode()

        BufferedReader getPageResponseReader = new BufferedReader(new InputStreamReader(getPageResponse.getEntity()
                .getContent()));

        String pageHtml = CommonUtils.readBufferedInputStream(getPageResponseReader);

        getPageResponseReader.close();
        
        return pageHtml;
    }

    public static String readBufferedInputStream(BufferedReader reader) throws IOException {
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public static <E> void printAllInConsole(Collection<E> collection, String heading) {
        System.out.println("-----------------------");
        System.out.println(heading);
        System.out.println("-----------------------");
        
        for (E element : collection) {
            System.out.println(element);
        }
    }
}
