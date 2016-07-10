package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
     * if evryting in file is well formatted. And add the content
     * of the file to appointmentBook.
     * @return AbstractAppointmentBook
     * @throws ParserException : Exception if issues in parsing
     * the file.
     */
    @Override
    public AbstractAppointmentBook parse() throws ParserException {
        BufferedReader br =null;
        try {
           br=new BufferedReader(new FileReader(filename));
            for(String line : Files.readAllLines(Paths.get(filename))){
                if(line!=null && !line.isEmpty())
                    System.out.println(" inside for : "+ line);
                StringTokenizer stringTokenizer = new StringTokenizer(line,",");
                Appointment appointment =new Appointment();
                while(stringTokenizer.hasMoreTokens()){
                    appointment.setOwner(stringTokenizer.nextToken());
                    appointment.setDescription(stringTokenizer.nextToken());
                    appointment.setBeginTimeString(Project1.checkDateTimeFormat(stringTokenizer.nextToken()));
                    appointment.setEndTimeString(Project1.checkDateTimeFormat(stringTokenizer.nextToken()));
                    appointmentBook.setOwnerName(appointment.getOwner());
                    appointmentBook.addAppointment(appointment);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(appointmentBook.getAppointments().size() + " Size");
        return appointmentBook;
    }
}
