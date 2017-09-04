package bol.producten;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Products {

	private ArrayList<Product> productsArray;
    private String filename;


    public Products(String filename) {
        this.filename = filename;
        this.productsArray = new ArrayList<Product>();
        updateList();

    }

    public ArrayList<Product> getHospitalizationsArray() {
        return productsArray;
    }

    public void add(Product product)
    {
    	productsArray.add(product);
    }

    public void updateFile()
    {
        writeFile(getJSONArray().toString());
    }

    public Product convertJSONObject(JSONObject productJson)
    {
        Product product = null;
        try
        {
            // Get all JSON Objects
            String naamJson = productJson.getString("naam");
            String producentJson = productJson.getString("producent");
            double prijsJson = productJson.getDouble("prijs");
            String idJson = productJson.getString("id");

            // Convert to Java objects

            product = new Product(naamJson, producentJson, prijsJson, idJson);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return product;
    }
    private void updateList()
    {
        String json = readFile();

        try {
            productsArray.clear();
            JSONArray products = new JSONArray(json);

            for (int i = 0; i < products.length(); ++i)
            {
                JSONObject productJson = products.getJSONObject(i);

             // Get all JSON Objects
                String naamJson = productJson.getString("naam");
                String producentJson = productJson.getString("producent");
                double prijsJson = productJson.getDouble("prijs");
                String idJson = productJson.getString("id");

                // Convert to Java objects

                Product product = new Product(naamJson, producentJson, prijsJson, idJson);

                add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFile()
    {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public JSONArray getJSONArray()
    {
        JSONArray arr = new JSONArray();
        for(Product product : productsArray)
        {
            arr.put(product.getJSON());
        }
        return arr;
    }

    private void writeFile(String data)
    {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))) {
            writer.write(data);
        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
public String getProduct(String product_id) {
    	
    	String json = readFile();
    	 String result = "<h2>Product</h2>";
    	
    	
    	 try {
    		 JSONArray products = new JSONArray(json);


        for(int i = 0; i < products.length(); i++)
        {
        	JSONObject newProduct = products.getJSONObject(i);
        	
            if(newProduct.getString("id").equalsIgnoreCase(product_id)) {
            	
            	result += "<br/><b>Naam : </b>" + newProduct.getString("naam").toString();
            	result += "<br/><b>Producent : </b>" + newProduct.getString("producent").toString();
            	result += "<br/><b>Prijs : </b>" + newProduct.getDouble("prijs");
            	result += "<br/><b>Id : </b>" + newProduct.getString("id").toString();
            }
        }

       
        
    }
    	 catch (Exception e)
         {
             e.printStackTrace();
         }
    	 
    	 return result;
    }   
    
}
