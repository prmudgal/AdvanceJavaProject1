package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.util.*;

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
//        AbstractAppointmentBook<Appointment> abstractAppointmentBook1 =new AppointmentBook();
        try {

            Set<Appointment> toRetain = new TreeSet<>();
            toRetain.addAll(abstractAppointmentBook.getAppointments());
//            for(Appointment app:toRetain) {
//                abstractAppointmentBook1.addAppointment(app);
//            }
//            Collections.sort((ArrayList<Appointment>)(abstractAppointmentBook1.getAppointments()));
            if(!filename.equals("-")) {
                //Whatever the file path is.
                File file = new File(filename);
                FileOutputStream is = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                BufferedWriter w = new BufferedWriter(osw);
                for (Appointment appointment : (toRetain)) {
                    writeToFile(w, appointment);
                }
                w.close();
            }else{
                for (Appointment appointment : (toRetain)) {
                    System.out.println("'"+appointment.getOwner() + "' has '"+ appointment.getDescription() + "' from '"+ appointment.getBeginTime()+ "' to '"+ appointment.getEndTime() + "' for "+ (appointment.getEndTime().getTime() - appointment.getBeginTime().getTime())/(60 * 1000)  + " minutes.");
                }
            }
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
        w.write("'");
        w.write(appointment.getOwner());
        w.write("' has '");
        w.write(appointment.getDescription());
        w.write("' from '");
        w.write(String.valueOf(appointment.getBeginTime()));
        w.write("' to '");
        w.write(String.valueOf(appointment.getEndTime()));
        w.write("' for ");
        w.write(String.valueOf(getDuration(appointment)));
        w.write(" minutes.");
        w.newLine();
    }

    public static long getDuration(Appointment appointment){
       return  (appointment.getEndTime().getTime() - appointment.getBeginTime().getTime())/(60 * 1000);
    }
}
