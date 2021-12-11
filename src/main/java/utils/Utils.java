package utils;
import java.time.Instant;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static int testCount = 0;

    // Password in base64
    public static String decode64(String encodedString) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedString.getBytes()));
    }

    // Generate a timestamp for report file name
    public static String generateTimestamp() {

        // Create a filename from a format string.
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));

        // Apply date formatting codes.
        return String.format("%1$td-%1$tm-%1$tY--%1$tk-%1$tS%1$tp", cal);
    }
}
