package recieving.request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import loggin.JavaLogger;

/**
 * Displays webpage after user accepts certificate
 *
 */
@Path("/certificate")
public class CertificatePage {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public static String displayCertificateMessage() {
		JavaLogger.log("CertificatePage| displayCertificateMessage|");
		return "<html> " + "<title>" + "Thank you for accepting certificate!" + "</title>"
		+ "<body><h1>" + "Thank you for accepting certificate!" + "</h1>"
				+ "<p>" + "You can go back to the webpage" +"</p></body>" + "</html> ";
	}

}
