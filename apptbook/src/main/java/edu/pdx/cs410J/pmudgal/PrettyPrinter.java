package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Priyanka on 7/21/2016.
 */
public class PrettyPrinter implements AppointmentBookDumper {

    private final PrintWriter writer;

    public PrettyPrinter(PrintWriter writer){
        this.writer=writer;
    }
    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        if (abstractAppointmentBook != null && abstractAppointmentBook.getAppointments().size()!=0) {
            writer.println(abstractAppointmentBook.getAppointments());
//            System.out.println(abstractAppointmentBook.getAppointments());
        }else{
            writer.println(Messages.getMappingCount(0));
//            System.out.println(Messages.getMappingCount(0));
        }
    }
}
