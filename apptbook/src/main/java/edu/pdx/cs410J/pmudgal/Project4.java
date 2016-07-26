package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The main class that parses the command line and communicates with the
 * Appointment Book server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    /**
     * Main method
     * @param args : Command line args
     */
    public static void main(String[] args) {
        String hostName = null;
        String portString = null;
        String key = null;
        String value = null;

        try {
            prepareAppointmentBook(args);
        } catch ( Exception ex ) {
            error("While contacting server: " + ex);
            return;
        }


       /* for (String arg : args) {
            if (hostName == null) {
                hostName = arg;

            } else if ( portString == null) {
                portString = arg;

            } else if (key == null) {
                key = arg;

            } else if (value == null) {
                value = arg;

//            }  else {
//                    usage("Extraneous command line argument: " + arg);
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );

        } else if ( portString == null) {
            usage( "Missing port" );
        }

        int port;
        try {
            port = Integer.parseInt( portString );
            
        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }*/

            /*AppointmentBookRestClient client = new AppointmentBookRestClient(hostName, port);

        HttpRequestHelper.Response response;
        try {
            if (key == null) {
                // Print all key/value pairs
                response = client.getAllKeysAndValues();

            } else if (value == null) {
                // Print all values of key
                response = client.getValues(key);

            } else {
                // Post the key/value pair
//                response = client.addKeyValuePair(key, value);
                     }

//            checkResponseCode( HttpURLConnection.HTTP_OK, response);

        } catch ( IOException ex ) {
            error("While contacting server: " + ex);
            return;
        }

//        System.out.println(response.getContent());

        System.exit(0);   */
    }

    /**
     *This method takes the command line args, parses them and sends them to RestClient.
     * @param args : Command line args
     * @throws Exception : Any exception thrown
     */
    private static void prepareAppointmentBook(String[] args) throws Exception {
        try {
            ArrayList<String> argList = new ArrayList<String>(Arrays.asList(args));
            String host = null;
            Integer port = null;
            String owner = null;
            String description = null;
            String beginTime = null;
            String endTime = null;
            boolean searchFlag = false;
            if (argList.contains(Constants.README)) {
                displayReadMe();
            } else {
                if (!argList.contains(Constants.HOST)) {
                    usage("Missing Host");

                } else if (!argList.contains(Constants.PORT)) {
                    usage("Missing port");
                }
                int hostIndex = argList.indexOf(Constants.HOST);
                host = argList.get(hostIndex + 1);
                argList.remove(hostIndex + 1);
                argList.remove(hostIndex);
                int portIndex = argList.indexOf(Constants.PORT);
                String portString = argList.get(portIndex + 1);
                argList.remove(portIndex + 1);
                argList.remove(portIndex);
                if (argList.contains(Constants.SEARCH)) {
                    searchFlag = true;
                    argList.remove(argList.indexOf(Constants.SEARCH));
                }
                try {
                    port = Integer.parseInt(portString);
                } catch (NumberFormatException ex) {
                    usage("Port \"" + portString + "\" must be an integer");
                    return;
                }
                AppointmentBookRestClient client = new AppointmentBookRestClient(host, port);
                String[] modifiedArgs = argList.toArray(new String[argList.size()]);
                for (int i = 0; i < modifiedArgs.length; i++) {
                    if (modifiedArgs[i].startsWith("-") && searchFlag == false) {
                        if (modifiedArgs[i].contains(Constants.PRINT)) {
                            System.out.println("Printing the Appointment : " + (modifiedArgs.length > 2 ? modifiedArgs[2] : "No description provided in command line."));
                            i++;
                        } else {
                            error(modifiedArgs[i] + ": This is not a correct option. Please provide the correct option.");
                            System.exit(1);
                        }
                    }
                    if (searchFlag == false) {
                        if (modifiedArgs.length - i == 8) {
                            owner = validateOwnerName(modifiedArgs[i]);//Checks if the argument starts with "-", it is not considered as owner name
                            description = (checkNull(modifiedArgs[++i], "description")); //
                            beginTime = (checkDateTimeFormatWithAmPm(checkNull(modifiedArgs[++i], "beginDateTime").concat(" ").concat(checkNull(modifiedArgs[++i], "beginDateTime")).concat(" ").concat(checkNull(modifiedArgs[++i], "beginDateTime")),"beginDateTime"));
                            endTime = (checkDateTimeFormatWithAmPm(checkNull(modifiedArgs[++i], "endDateTime").concat(" ").concat(checkNull(modifiedArgs[++i], "endDateTime")).concat(" ").concat(checkNull(modifiedArgs[++i], "endDateTime")),"endDateTime"));
                        } else if (modifiedArgs.length - i < 8) {
                            usage(MISSING_ARGS);
                        } else if (modifiedArgs.length - i > 8) {
                            usage("Extraneous command line argument: " + modifiedArgs);
                        }

                        HttpRequestHelper.Response response = client.addKeyValuePair(owner, description, beginTime, endTime);
                    } else {
                        if (modifiedArgs.length - i == 7) {
                            owner = validateOwnerName(modifiedArgs[i]);//Checks if the argument starts with "-", it is not considered as owner name
                            beginTime = (checkDateTimeFormatWithAmPm(checkNull(modifiedArgs[++i], "beginDateTime").concat(" ").concat(checkNull(modifiedArgs[++i], "beginDateTime")).concat(" ").concat(checkNull(modifiedArgs[++i], "beginDateTime")),"beginDateTime"));
                            endTime = (checkDateTimeFormatWithAmPm(checkNull(modifiedArgs[++i], "endDateTime").concat(" ").concat(checkNull(modifiedArgs[++i], "endDateTime")).concat(" ").concat(checkNull(modifiedArgs[++i], "endDateTime")),"endDateTime"));
                        } else if (modifiedArgs.length - i < 7) {
                            usage(MISSING_ARGS);
                        } else if (modifiedArgs.length - i > 7) {
                            usage("Extraneous command line argument: " + modifiedArgs);
                        }
                        HttpRequestHelper.Response response = client.getValuesWithSearch(owner, beginTime, endTime);
                        System.out.println(response.getContent());
                    }
                }
            }
        }catch ( IOException ex ) {
            error("While contacting server: " + ex);
            return;
        }
    }

    /**
     *  This method prints the README once called.
     */
    private static void displayReadMe() {
        System.out.println(Constants.PROJECT4_README_DESC);
        System.exit(1);
    }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getCode(), response.getContent()));
        }
    }

    /**
     * This method prints the error message
     * @param message : Error message
     */
    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [key] [value]");
        err.println("  host    Host of web server");
        err.println("  port    Port of web server");
        err.println("  key     Key to query");
        err.println("  value   Value to add to server");
        err.println();
        err.println("This simple program posts key/value pairs to the server");
        err.println("If no value is specified, then all values are printed");
        err.println("If no key is specified, all key/value pairs are printed");
        err.println();

        System.exit(1);
    }

    /**
     * *This method checks for the correct format of date and time.
     *The date and time should be in format "mm/dd/yyyy hh:mm am/pm' or "m/d/yyyy hh:mm am/pm".
     * This method also check for invalid dates e.g. 13/01/2015 or 12/40/2015.
     * Also enforces the year has to pass with 4 digits.
     * @param value : the date and time
     * @param fieldName : fieldName
     * @return : the correctly formatted date and time
     * @throws ParseException : Exception while parsing the date and time
     */
    public static String checkDateTimeFormatWithAmPm(String value, String fieldName) throws ParseException {
        if (value == null || !value.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [aApP][mM]$")) {
            System.out.println(Messages.noFormattedDate(fieldName));
            System.exit(1);
        }else{
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            df.setLenient(false);
            df.parse(value);
        }
        return value;
    }


    /**
     * This method checks if the passed string is not null
     * and not empty and also make sure that it contains data.
     * @param string : String to be checked as null
     * @param fieldName : The fieldname from appointment
     * @return the correct value or he error message
     */
    public static String checkNull(String string, String fieldName){
        if(string!=null && !string.trim().isEmpty() && !string.trim().equals("")){
            return string;
        }else {
            System.out.println("Please provide " + fieldName);
            System.exit(1);
            return "Please specify " + fieldName;

        }
    }

    /**
     * This method checks if the passed argument is valid Owner
     * or it is some option and not arg.
     * @param arg : ownerName of the appointment
     * @return owner name
     */
    private static String validateOwnerName(String arg) {
        if(!checkNull(arg, "owner name").startsWith("-")){
            checkNull(arg, "owner name");
        }else{
            System.out.println(arg+ " : This does not seem to be a valid owner name as it starts with '-'.\n"+Constants.PROVIDE_ARG);
            System.exit(1);
        }
        return arg;
    }
}