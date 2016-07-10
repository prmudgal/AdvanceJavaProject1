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

    public TextParser(String filename, AppointmentBook appointmentBook){
        this.filename=filename;
        this.appointmentBook=appointmentBook;
    }

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
            e.getMessage();
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
