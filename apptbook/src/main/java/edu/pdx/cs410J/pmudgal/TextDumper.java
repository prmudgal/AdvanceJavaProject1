package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Priyanka on 7/6/2016.
 */
public class TextDumper implements AppointmentBookDumper {
    String filename;

    public TextDumper(String filename){
        this.filename=filename;
    }

    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        try {
            //Whatever the file path is.
            File file = new File(filename);
            if(file.createNewFile()) {
                System.out.println("Created new file");
                FileOutputStream is = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                BufferedWriter w = new BufferedWriter(osw);
                for(Appointment appointment:(new ArrayList<Appointment>(abstractAppointmentBook.getAppointments()))){
                    writeToFile(w, appointment);
                }
                w.close();
            }else{
                System.out.println("File already exists");
                FileOutputStream is = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                BufferedWriter w = new BufferedWriter(osw);
                for(Appointment appointment:(new ArrayList<Appointment>(abstractAppointmentBook.getAppointments()))){
                    writeToFile(w, appointment);
                }
                w.close();
            }
        } catch (IOException e) {
            e.getMessage();
            System.err.println("Problem writing to the file :" + filename);
            e.printStackTrace();
        }
    }

    private static void writeToFile(BufferedWriter w, Appointment appointment) throws IOException {
        w.write(appointment.getOwner());
        w.write(",");
        w.write(appointment.getDescription());
        w.write(",");
        w.write(appointment.getBeginTimeString());
        w.write(",");
        w.write(appointment.getEndTimeString());
        w.newLine();
    }

}
