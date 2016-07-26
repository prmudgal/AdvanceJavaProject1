package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Priyanka on 7/21/2016.
 */
public class AppointmentBook extends AbstractAppointmentBook<Appointment>{

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private String owner;

    private List<Appointment> appointmentBook=new ArrayList<Appointment>();

    public AppointmentBook(String owner){
        this.owner=owner;
    }
    public AppointmentBook(){
    }
    @Override
    public String getOwnerName() {
        return this.owner;
    }

    @Override
    public Collection<Appointment> getAppointments() {
        return appointmentBook;
    }

    @Override
    public void addAppointment(Appointment appointment) {
    appointmentBook.add(appointment);
    }


}
