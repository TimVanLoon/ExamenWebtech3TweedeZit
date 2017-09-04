package bol.bestellingen;

import org.restlet.resource.ClientResource;

public class RegistratieClient {

	 public static void main(String[] args) {
         
         try {
        	ClientResource resource = new ClientResource("http://localhost:8181/registraties/registratie");
        	// Post een nieuwe registratie
        	String registratie1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        	registratie1 += "<registratie klantNaam=\"Maarten\" adres=\"Meistraat 5\" datumBestelling=\"10/10/2015\" produktNaam=\"Bureaustoel\" hoeveelheid=\"2\"></registratie>";

    		resource.post(registratie1);
    		
    		String registratie2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        	registratie2 += "<registratie klantNaam=\"Tom Elslander\" adres=\"Beveren\" datumBestelling=\"07/08/2014\" produktNaam=\"Hooivork\" hoeveelheid=\"8\"></registratie>";

    		resource.post(registratie2);
    		// get the response
        	System.out.println(resource.getResponseEntity().getText());
        }
        catch (Exception e) {
            System.out.println("In main : " + e.getMessage());
        }
    }
}
