package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Vijay on 7/15/2016.
 */
public class Project3 {

    public static void main(String[] args){

        parseCommandLineArgs(args);
    }

    private static void parseCommandLineArgs(String[] args) {
            Project1 project1=new Project1();
            Project2 project2 =new Project2();
            AppointmentBook appointmentBook = new AppointmentBook();


            try {
                if(args.length==0){
                    System.out.println("You did not provide any arguments. Please provide the missing arguments in format: "+Constants.usage);
                }else {
                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(args));
                    if (!arrayList.contains(Constants.README)) {
                        if (arrayList.contains(Constants.PRETTYPRINT)) {
                            int index = arrayList.indexOf(Constants.PRETTYPRINT);
                            String prettyFilename = arrayList.get(index + 1);
                            arrayList.remove(index + 1);
                            arrayList.remove(Constants.PRETTYPRINT);
                            String[]  stockArr = new String[arrayList.size()];
                            stockArr = arrayList.toArray(new String[arrayList.size()]);
                            if(!prettyFilename.equals("-")) {
                                File file = new File(prettyFilename);
                                if (file.createNewFile()) {
                                    appointmentBook = parseAndPrepareTheContentsOfFile(prettyFilename, stockArr);
                                    PrettyPrinter prettyPrinter =new PrettyPrinter(prettyFilename);
                                    prettyPrinter.dump(appointmentBook);
                                    System.out.println("Created new file : " + prettyFilename);
                                } else {
                                    appointmentBook = parseAndPrepareTheContentsOfFile(prettyFilename, stockArr);
                                    PrettyPrinter prettyPrinter =new PrettyPrinter(prettyFilename);
                                    prettyPrinter.dump(appointmentBook);
                                    System.out.println("Written appointment to file : " + prettyFilename);
                                }
                            }else{
                                System.out.println("Printing to standard out");
                                appointmentBook = parseAndPrepareTheContentsOfFile(prettyFilename, stockArr);
                                for(Appointment appointment:appointmentBook.getAppointments()) {
                                    System.out.println(" Owner name : " + appointment.getOwner());
                                    System.out.println(" Description : "+ appointment.getDescription());
                                    System.out.println(" Begin Time : "+ appointment.getBeginTime());
                                    System.out.println(" End Time : "+ appointment.getEndTime());
                                    System.out.println(" Duration of meeting : "+ (appointment.getEndTime().getTime() - appointment.getBeginTime().getTime())/(60 * 1000)  + " minutes.");
                                }

                            }
//                            appointmentBook= Project2.parseCommandLineArgs(stockArr);


                        } else {
                            Project2.parseCommandLineArgs(args);
                        }
                    } else {
                        System.out.println(Constants.PROJECT3_README_DESC);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        /**
         * This method calls the parse() method of TextParser by passing
         * filename and command line args. After parsing, it also calls
         * prepareAppointmentBook() method of Project1.
         * @param prettyFilename : Filename which contains appointments
         * @param args : command line args
         * @return : AppointmentBook
         * @throws Exception : In case of any exception, regarding parsing the file.
         */
    public static AppointmentBook parseAndPrepareTheContentsOfFile(String prettyFilename, String[] args) throws Exception {
        Project1 project1 =new Project1();
        AbstractAppointmentBook appointmentBook = new AppointmentBook();
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(args));
        if (arrayList.contains(Constants.TEXTFILE)) {
            Project2.parseCommandLineArgs(args);
            int index = arrayList.indexOf(Constants.TEXTFILE);
            String textFilename = arrayList.get(index + 1);
            arrayList.remove(index + 1);
            arrayList.remove(Constants.TEXTFILE);
            String[] stockArr = new String[arrayList.size()];
            stockArr = arrayList.toArray(new String[arrayList.size()]);
            if(!prettyFilename.equals("-")) {
                TextParser textParser = new TextParser(prettyFilename, (AppointmentBook) appointmentBook, "Project3");
                appointmentBook = textParser.parse();
                appointmentBook = project1.prepareAppointmentBook(stockArr, (AppointmentBook) appointmentBook);
            }else{
                appointmentBook=project1.prepareAppointmentBook(stockArr,(AppointmentBook )appointmentBook);
            }

        }else{
            if(!prettyFilename.equals("-")) {
                TextParser textParser = new TextParser(prettyFilename, (AppointmentBook) appointmentBook, "Project3");
                appointmentBook = textParser.parse();
                appointmentBook = project1.prepareAppointmentBook(args, (AppointmentBook) appointmentBook);
            }else{
                appointmentBook=project1.prepareAppointmentBook(args,(AppointmentBook )appointmentBook);
            }
        }
        return (AppointmentBook) appointmentBook;
    }


    /**
     * *This method checks for the correct format of date and time.
     *The date and time should be in format "mm/dd/yyyy hh:mm am/pm' or "m/d/yyyy hh:mm am/pm".
     * This method also check for invalid dates e.g. 13/01/2015 or 12/40/2015.
     * Also enforces the year has to pass with 4 digits.
     * @param value : the date and time
     * @return : the correctly formatted date and time
     * @throws ParseException : Exception while parsing the date and time
     */
    public static String checkDateTimeFormat(String value) throws ParseException {
        if (value == null || !value.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [aApP][mM]$")) {
            System.out.println("Please provide the date and time in format mm/dd/yyyy hh:mm");
            System.exit(1);
        }else{
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            df.setLenient(false);
            df.parse(value);
        }
        return value;
    }
}
