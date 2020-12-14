package dataAccess;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * 
 * @author Alexis Massa
 * @version 1.0.0
 * @see https://www.twilio.com/
 *
 */
public class SmsUtil {
	public static final String ACCOUNT_SID = "AC99111b780a1e82dd45f2d42204cd1a01";
	public static final String AUTH_TOKEN = "a2366af1d684cc14d1d9fa1b9b5efaf3";

	/**
	 * Envoie un SMS d'alerte
	 * @param recipient : destinataire
	 * @param stade : le stade concerné
	 */
	public static void sendSMS(String recipient, String stade) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber(recipient), new PhoneNumber("+18312192098"),
				"Vérifier la température du stade: " + stade + " ASAP !").create();
	}
}
