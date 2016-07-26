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

    /**
     * Parameterized constructor
     * @param owner : Owner name
     */
    public AppointmentBook(String owner){
        this.owner=owner;
    }

    /**
     * No parameterized constructor
     */
    public AppointmentBook(){
    }

    /**
     * Getter for owner name
     * @return :owner
     */
    @Override
    public String getOwnerName() {
        return this.owner;
    }

    /**
     * Getter for appointment
     * @return : appointmentBook
     */
    @Override
    public Collection<Appointment> getAppointments() {
        return appointmentBook;
    }

    /**
     * Adds appointment
     * @param appointment : appointment
     */
    @Override
    public void addAppointment(Appointment appointment) {
    appointmentBook.add(appointment);
    }


}
