package bol.bestellingen;

import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

public class RegistratieApplication extends Application{

	@Override
    public synchronized Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/registratie", RegistratiesResource.class);

        return router;
    }


    public static void main(String[] args) throws Exception {

        Component component = new Component();

        // Add a new HTTP server listening on port 8181.
        component.getServers().add(Protocol.HTTP, 8181);

        // Attach the sample application.
        component.getDefaultHost().attach("/registraties", new RegistratieApplication());

        // Start the component.
        component.start();
    }
}
