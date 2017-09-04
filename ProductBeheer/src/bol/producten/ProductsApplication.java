package bol.producten;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;


public class ProductsApplication extends Application{

	 @Override
	    public synchronized Restlet createInboundRoot() {

	        Router router = new Router(getContext());

	        router.attach("/product", ProductsResource.class);
	        router.attach("/product/{product_id}", ProductResource.class);
	        
	        return router;
	    }
}
