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

    /**
     * Parameterized constructor
     * @param filename : File in which appointments are to to be written.
     */
    public TextDumper(String filename){
        this.filename=filename;
    }

    /**
     * Overridden method of AppointmentBookDumper.
     * This method writes the appointments to the
     * file by calling writeToFile() method.
     * @param abstractAppointmentBook : instance of AppointmentBook
     * @throws IOException : Handles IO Exception in case of file wrting.
     */
    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        try {
            //Whatever the file path is.
            File file = new File(filename);
                FileOutputStream is = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                BufferedWriter w = new BufferedWriter(osw);
                for(Appointment appointment:(new ArrayList<Appointment>(abstractAppointmentBook.getAppointments()))){
                    writeToFile(w, appointment);
                }
                w.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.err.println("Problem writing to the file :" + filename);
        }
    }

    /**
     * This method takes the individual field from appointment
     * and writes it to file using bufferedWriter separated by comma.
     * @param w : BfferedWriter
     * @param appointment : instance of Appointment
     * @throws IOException : handles while writing file.
     */
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
