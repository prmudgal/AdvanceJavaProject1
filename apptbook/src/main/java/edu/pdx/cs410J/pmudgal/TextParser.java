package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Priyanka on 7/6/2016.
 */
public class TextParser implements AppointmentBookParser {
    String filename;
    AppointmentBook appointmentBook;
    /**
     * Parameterized Constructor
     * @param filename : File containing appointments
     * @param appointmentBook : ApointmentBook having appointments
     */
    public TextParser(String filename, AppointmentBook appointmentBook){
        this.filename=filename;
        this.appointmentBook=appointmentBook;
    }

    /**
     * This method is reading the contents of file and checking
     * if everything in file is well formatted. And add the content
     * of the file to appointmentBook.
     * @return AbstractAppointmentBook
     * @throws ParserException : Exception if issues in parsing
     * the file.
     */
    @Override
    public AbstractAppointmentBook parse() throws ParserException {
        BufferedReader br =null;
        try {
            File file =new File(filename);
            br=new BufferedReader(new FileReader(file));
            for(String line : Files.readAllLines(Paths.get(filename))){
                Appointment appointment = new Appointment();
                String[] lineFromFile = line.split(",");
//                if(project.equals("Project2")) {
                    if (line != null && !line.isEmpty() && countDelimiters(line) == 3 && lineFromFile.length == 4) {
                        appointment.setOwner(checkNullInsideFile(lineFromFile[0], "owner name"));
                        appointment.setDescription(checkNullInsideFile(lineFromFile[1], "description"));
                        appointment.setBeginTimeString(checkDateTimeFormatWithAmPmInFile(checkNullInsideFile(lineFromFile[2], "beginTime"), "beginTime"));
                        appointment.setEndTimeString(checkDateTimeFormatWithAmPmInFile(checkNullInsideFile(lineFromFile[3], "endTime"), "endTime"));
                        appointmentBook.setOwnerName(appointment.getOwner());
                        appointmentBook.addAppointment(appointment);

                    } else {
                        throw new IOException("The file '"+filename+ "' seems to be malformed." +
                                " The file either has less/more commas or values in it." +
                                " Please correct it and try again.");
                    }
//                }else if(project.equals("Project3")){
//                    if (line != null && !line.isEmpty() && countDelimiters(line) == 4 && lineFromFile.length == 5) {
//                        appointment.setOwner(checkNullInsideFile(lineFromFile[0], "owner name"));
//                        appointment.setDescription(checkNullInsideFile(lineFromFile[1], "description"));
//                        appointment.setBeginTimeString(checkDateTimeFormatWithAmPmInFile(checkNullInsideFile(lineFromFile[2], "beginTime"), "beginTime"));
//                        appointment.setEndTimeString(checkDateTimeFormatWithAmPmInFile(checkNullInsideFile(lineFromFile[3], "endTime"), "endTime"));
//                        appointment.setDuration(Integer.parseInt(checkNullInsideFile(lineFromFile[4], "duration")));
//                        appointmentBook.setOwnerName(appointment.getOwner());
//                        appointmentBook.addAppointment(appointment);
//
//                    } else {
//                        throw new IOException("The file '"+filename+ "'seems to be malformed." +
//                                " The file either has less/more commas or values in it." +
//                                " Please correct it and try again.");
//                    }
//                }

            }
        }  catch (ParseException|IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        return appointmentBook;
    }



    /**
     * This method counts the number of deimiters which is comma.
     * @param line : line from file
     * @return counter: number of comma
     */
    private int countDelimiters(String line){
        int counter = 0;
        for (Character c: line.toCharArray()) {
            if (c.equals(',')) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * This method checks if the passed string is not null
     * and not empty and also make sure that it contains data.
     * @param string : String to be checked as null
     * @param fieldName : The fieldname from appointment
     * @return the correct value or the error message
     */
    public static String checkNullInsideFile(String string, String fieldName){
        if(string!=null && !string.trim().isEmpty() && !string.trim().equals("")){
            return string;
        }else {
            System.out.println( fieldName + " is empty inside the file. Please correct the file and try again.");
            System.exit(1);
            return fieldName + " is empty inside the file. Please correct the file and try again.";

        }
    }

    /**
     * /**
     * *This method checks for the correct format of date and time in the file.
     *The date and time should be in format "mm/dd/yyyy hh:mm' or "m/d/yyyy hh:mm".
     * This method also check for invalid dates e.g. 13/01/2015 or 12/40/2015.
     * Also enforces the year has to pass with 4 digits.
     * @param value : the date and time
     * @param fieldName : beginTime or endTime fieldname
     * @return : the correctly formatted date and time
     * @throws ParseException : Exception while parsing the date and time
     */

    public static String checkDateTimeFormatInFile(String value, String fieldName) throws ParseException {
        if (value == null || !value.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2}$")) {
            System.out.println("The date and time for "+ fieldName+" in file is not in format mm/dd/yyyy hh:mm. Please correct it and try again");
            System.exit(1);
        }else{
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            df.setLenient(false);
            df.parse(value);
        }
        return value;
    }


    /**
     * *This method checks for the correct format of date and time in the file.
     *The date and time should be in format "mm/dd/yyyy hh:mm am/pm' or "m/d/yyyy hh:mm am/pm".
     * This method also check for invalid dates e.g. 13/01/2015 or 12/40/2015.
     * Also enforces the year has to pass with 4 digits.
     * @param value : the date and time
     * @return : the correctly formatted date and time
     * @throws ParseException : Exception while parsing the date and time
     */
    public static String checkDateTimeFormatWithAmPmInFile(String value, String fieldName) throws ParseException {
        if (value == null || !value.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [aApP][mM]$")) {
            System.out.println("The date and time for "+ fieldName+" in file is not in format mm/dd/yyyy hh:mm am/pm. Please correct it and try again");
            System.exit(1);
        }else{
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            df.setLenient(false);
            df.parse(value);
        }
        return value;
    }

   /* public AppointmentBook sortAppointmentBook(AppointmentBook appointmentBook){
        AppointmentBook sortedBook = new AppointmentBook();
        for(Appointment appointment:appointmentBook.getAppointments()){
            appointment.compareTo()
        }
    }*/
}
