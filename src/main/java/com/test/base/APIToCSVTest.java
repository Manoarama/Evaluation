package com.test.base;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APIToCSVTest {
    @Test
    public void exportDataToCSV() {
        String apiUrl = "https://data.sfgov.org/resource/p4e4-a5a7.json";
      
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        String csvFileName = "sfgov_" + timestamp + ".json";
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(apiUrl));
        	
             InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
             CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFileName))) {

            // Parse JSON data from the API response and write it to a CSV file
            // You may need to use a JSON library like Gson or Jackson for parsing JSON data

            // Example: Writing a CSV header row and a single data row
            String[] header = {"Column1", "Column2", "Column3"};
            String[] dataRow = {"Value1", "Value2", "Value3"};

            csvWriter.writeNext(header);
            csvWriter.writeNext(dataRow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

