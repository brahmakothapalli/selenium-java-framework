package helper;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class DataGeneratorHelper {

    private DataGeneratorHelper(){
    }

    private static Logger logger = Logger.getLogger(DataGeneratorHelper.class);

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Exception when creating obj to Random() ",e);
        }
    }

    private static Fairy fairy = Fairy.create(Locale.ENGLISH);

    private static Person person = fairy.person();



    public static String getFirstName(){

        return person.getFirstName();
    }

    public static String getLastName(){

        return person.getLastName();
    }

    public static Address getAddress(){

        return person.getAddress();
    }

    public static String getRandomAlphaNumeric() {
        int count =8;
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String getMobileNumber() {

        logger.info("Getting the mobile number:getMobileNumber");

        StringBuilder sb = new StringBuilder();
        int start = random.ints(7, 10).findFirst().getAsInt();
        sb.append(start);
        for(int i=1; i<=9;i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    public static String getRandomNum(int length) {

        logger.info("Getting the registration number :: getRegistrationNum");
        StringBuilder sb = new StringBuilder();
        int start = random.ints(0, 10).findFirst().getAsInt();
        sb.append(start);
        for(int i=1; i<=length-1;i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String getRandomString(int length) {

        logger.info("Getting the random string number :: getRandomString");
        int count =length;
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
