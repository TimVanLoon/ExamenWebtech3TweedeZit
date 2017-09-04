package bol.bestellingen;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import bol.xml.XMLParser;

public class RegistratiesResource extends ServerResource{

	@Get("html")
	public String getRegistraties() {
		XMLParser parser = new XMLParser();
		return parser.getRegistraties();
	}
	
	@Post("txt")
	public String addRegistratie(String registratie) {
		XMLParser parser = new XMLParser();
		return parser.addRegistratie(registratie);
	}
}
