package bol.producten;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ProductResource extends ServerResource{

	public static Products products = new Products("C:\\Users\\Tim\\workspace\\ProductBeheer\\WebContent\\products.json");
	
	@Get("html")
    public String getProduct() {
		String product_id = getAttribute("product_id");
		return products.getProduct(product_id);
    }
}
