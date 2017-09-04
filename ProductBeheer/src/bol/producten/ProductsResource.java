package bol.producten;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


public class ProductsResource extends ServerResource{
	
	public static Products products = new Products("C:\\Users\\Tim\\workspace\\ProductBeheer\\WebContent\\products.json");
	
	 @Get("json")
	    public String doGet() {
	        return products.getJSONArray().toString();
	    }
	 
	  @Post("json")
	    public String doPost(JsonRepresentation entity) {

	        JSONObject json = null;

	        try {
	            json = entity.getJsonObject();
	            Product product = products.convertJSONObject(json);
	            products.add(product);
	            return product.getJSON().toString();

	        } catch (JSONException e) {
	            return e.toString();
	        }
	    }
}

