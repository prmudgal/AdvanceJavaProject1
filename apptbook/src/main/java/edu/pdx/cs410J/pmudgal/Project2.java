package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Priyanka on 7/6/2016.
 */
public class Project2 {
    /**
     * This is main method
     * @param args : Command line arguments which were passed while running program.
     */
    public static void main(String[] args){
        parseCommandLineArgs(args);

    }

    /**
     * This method checks if there is "-textfile" option present in
     * command line arguments. If yes, then it checks if the filename
     * passed in command line args, exists. If no, it created the file.
     * If yes, it writes the content of appointment in that file by calling
     * dump() method of TextDumper.
     * If command line arguments does not have "-textfile" option,
     * the project processes same as Project 1.
     * @param args : Command line arguments passed.
     */
    public static AppointmentBook parseCommandLineArgs(String[] args) {
        Project1 project1=new Project1();
        AppointmentBook appointmentBook = new AppointmentBook();

        try {
            if(args.length==0){
                System.out.println("You did not provide any arguments. Please provide the missing arguments in format: "+Constants.usage);
            }else {
                ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(args));
                if (!arrayList.contains(Constants.README)) {
                    if (arrayList.contains(Constants.TEXTFILE)) {
                        int index = arrayList.indexOf(Constants.TEXTFILE);
                        String filename = arrayList.get(index + 1);
                        arrayList.remove(index + 1);
                        arrayList.remove(Constants.TEXTFILE);

                        String[] stockArr = new String[arrayList.size()];
                        stockArr = arrayList.toArray(new String[arrayList.size()]);
                        File file = new File(filename);
                        if (file.createNewFile()) {
                            appointmentBook = parseAndPrepareTheContentsOfFile(filename, stockArr);
                            TextDumper textDumper = new TextDumper(filename);
                            textDumper.dump(appointmentBook);
                            System.out.println("Created new file : " + filename);
                        } else {
                            appointmentBook = parseAndPrepareTheContentsOfFile(filename, stockArr);
                            TextDumper textDumper = new TextDumper(filename);
                            textDumper.dump(appointmentBook);
                            System.out.println("Written appointment to file : " + filename);
                        }
                    } else {
                        project1.prepareAppointmentBook(args, appointmentBook);
                    }
                } else {
                    System.out.println(Constants.PROJECT2_README_DESC);
                }
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }  catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return appointmentBook;
    }

    /**
     * This method calls the parse() method of TextParser by passing
     * filename and command line args. After parsing, it also calls
     * prepareAppointmentBook() method of Project1.
     * @param filename : Filename which contains appointments
     * @param args : command line args
     * @return : AppointmentBook
     * @throws Exception : In case of any exception, regarding parsing the file.
     */
    public static AppointmentBook parseAndPrepareTheContentsOfFile(String filename, String[] args) throws Exception {
        Project1 project1 =new Project1();
        AbstractAppointmentBook appointmentBook = new AppointmentBook();
        TextParser textParser = new TextParser(filename, (AppointmentBook) appointmentBook, "Project2");
        appointmentBook = textParser.parse();
        appointmentBook = project1.prepareAppointmentBook(args, (AppointmentBook) appointmentBook);
        return (AppointmentBook) appointmentBook;
    }



}

