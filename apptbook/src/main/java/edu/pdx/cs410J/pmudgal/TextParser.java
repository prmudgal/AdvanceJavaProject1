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
import java.util.StringTokenizer;

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
                System.out.println(" inside for : " + line);
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                if(line!=null && !line.isEmpty() && countDelimiters(line) ==3) {
                    Appointment appointment = new Appointment();
                    while (stringTokenizer.hasMoreTokens()) {
                        appointment.setOwner(Project1.checkNull(stringTokenizer.nextToken(),"owner name"));
                        appointment.setDescription(Project1.checkNull(stringTokenizer.nextToken(),"description"));
                        appointment.setBeginTimeString(Project1.checkDateTimeFormat(stringTokenizer.nextToken()));
                        appointment.setEndTimeString(Project1.checkDateTimeFormat(stringTokenizer.nextToken()));
                        appointmentBook.setOwnerName(appointment.getOwner());
                        appointmentBook.addAppointment(appointment);
                    }
                } else{
                    throw new IOException("The file seems to be malformed." +
                            " The file either has more commas or values in it." +
                            " Please correct it and try again.");

                }
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
        System.out.println(appointmentBook.getAppointments().size() + " Size");
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
}
