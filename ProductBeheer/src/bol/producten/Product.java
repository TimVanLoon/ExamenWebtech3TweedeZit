package bol.producten;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {

	private String naam;
	private String producent;
	private double prijs;
	private String id;
	
	public Product(String naam, String producent, double prijs, String id){
		this.naam = naam;
		this.producent = producent;
		this.prijs = prijs;
		this.id = id;
	}
	
	public JSONObject getJSON()
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("naam", naam);
            obj.put("producent", producent);
            obj.put("prijs", prijs);
            obj.put("id", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return obj;
    }
}
