package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Priyanka on 7/15/2016.
 */
public class PrettyPrinter implements AppointmentBookDumper {
    String filename;

    public PrettyPrinter(String filename){
        this.filename=filename;
    }


    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        try {
            //Whatever the file path is.
            File file = new File(filename);
            FileOutputStream is = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            BufferedWriter w = new BufferedWriter(osw);

           /* Set<Appointment> toRetain = new HashSet<Appointment>();
            toRetain.addAll(abstractAppointmentBook.getAppointments());
            abstractAppointmentBook=new AppointmentBook();
            for(Appointment app:toRetain) {
                abstractAppointmentBook.addAppointment(app);
            }*/

            Collections.sort((ArrayList<Appointment>)(abstractAppointmentBook.getAppointments()));
//            System.out.println(toRetain.size()+ " sadasdd");
            for(Appointment appointment:( (ArrayList<Appointment>)(abstractAppointmentBook.getAppointments()))){
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
        w.write(",");
        w.write(String.valueOf(getDuration(appointment)));
        w.newLine();
    }

    public static long getDuration(Appointment appointment){
        System.out.println(" time : "+ (appointment.getEndTime().getTime() - appointment.getBeginTime().getTime())/(60 * 1000));
       return  (appointment.getEndTime().getTime() - appointment.getBeginTime().getTime())/(60 * 1000);
    }
}
