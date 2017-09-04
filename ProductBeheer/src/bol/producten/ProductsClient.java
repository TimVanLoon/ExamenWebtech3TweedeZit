package bol.producten;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class ProductsClient {

public static void main(String[] args) {
		
		// Main Objects
        JSONObject product = new JSONObject();
        
        try {
        	product.put("naam", "XBOX One");
        	product.put("producent", "Microsoft");
        	product.put("prijs", 399.99);
        	product.put("id", "3");
        	

        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        try {

            URL url = new URL("http://localhost:8080/ProductBeheer/product");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input1 = product.toString();

            OutputStream os = conn.getOutputStream();
            os.write(input1.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
	}
}
