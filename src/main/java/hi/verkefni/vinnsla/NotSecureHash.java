/**
 * Flugbókanakerfi HBV401G Hópur 1F
 * Alda, Ármann, Halldór og Hrólfur
 */

package hi.verkefni.vinnsla;

import java.util.Random;

/**
 * Hjálparklasi fyrir staðfestingarnúmer
 */
public class NotSecureHash {
    /**
     * Fall sem skilar sirka-hashi f. staðfestingarnúmer
     * @param len int lengd númers
     * @return staðfestingarnúmer
     */
    public static String generateHash(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
