package edu.pdx.cs410J.pmudgal;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Vijay on 7/23/2016.
 */
public class DateValidator {
    /**
     * *This method checks for the correct format of date and time.
     *The date and time should be in format "mm/dd/yyyy hh:mm am/pm' or "m/d/yyyy hh:mm am/pm".
     * This method also check for invalid dates e.g. 13/01/2015 or 12/40/2015.
     * Also enforces the year has to pass with 4 digits.
     * @param value : the date and time
     * @param pw
     * @return : the correctly formatted date and time
     * @throws ParseException : Exception while parsing the date and time
     */
    public static String checkDateTimeFormat(String value, PrintWriter pw) throws ParseException {
        if (value == null || !value.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [aApP][mM]$")) {
            pw.println("Please provide the date and time in format mm/dd/yyyy hh:mm");
            return "false";
        }else{
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            df.setLenient(false);
            df.parse(value);
        }
        return value;
    }
}
