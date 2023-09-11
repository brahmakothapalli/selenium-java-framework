package helper;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//import static PageObjects.PaymentPageObjects.*;


public class DateHelper {

    private static Logger logger = Logger.getLogger(DateHelper.class);

    private static final String DATE_FORMAT = "dd-MMM-yyyy";

    private static LocalDate currentDate;

    private static DateTimeFormatter dateTimeFormatter;

    private DateHelper() {

    }

    public static String getSystemDate() {

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

            java.util.Date date = new java.util.Date();

            logger.info(dateFormat.format(date));

            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error("Failed to return the back date", e);
            return null;
        }
    }

    public static String getSystemDateWithFormat(String format) {

        try {
            DateFormat dateFormat = new SimpleDateFormat(format);

            java.util.Date date = new java.util.Date();

            logger.info(dateFormat.format(date));

            return dateFormat.format(date);
        } catch (Exception e) {
            logger.error("Failed to return the back date", e);
            return null;
        }
    }


    public static String getBackDate(long days) {

        logger.info("Returning the back date");

        try {

            currentDate = LocalDate.now();

            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

            return currentDate.minusDays(days).format(dateTimeFormatter);

        } catch (Exception e) {

            logger.error("Failed to return the back date", e);

            return null;
        }

    }

    public static String getFutureDate(long days) {

        logger.info("Returning the future date::getFutureDate");

        try {

            currentDate = LocalDate.now();

            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

            logger.info("Future date: " + currentDate.plusDays(days).format(dateTimeFormatter));

            return currentDate.plusDays(days).format(dateTimeFormatter);

        } catch (Exception e) {

            logger.error("Failed to return the future date", e);

            return null;
        }
    }


    public static String getYear(long years, String period) {

        logger.info("Returning the future date::getFutureDate");

        try {
            currentDate = LocalDate.now();

            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

            if (period.equalsIgnoreCase("Future")) {

                return currentDate.plusYears(years).format(dateTimeFormatter).split("-")[2];

            } else {
                return currentDate.minusYears(years).format(dateTimeFormatter).split("-")[2];
            }

        } catch (Exception e) {
            logger.error("Failed to return the year by given number of years ", e);
            return null;
        }
    }


    public static String getInceptionDateReDerived() {

        logger.info("Returning the Re Derived Inception Date::getInceptionDate");

        try {
            currentDate = LocalDate.now();

            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return currentDate.plusDays(2).format(dateTimeFormatter);

        } catch (Exception e) {

            logger.error("Failed to return the Re Derived Inception Date ", e);

            return null;
        }
    }




}

