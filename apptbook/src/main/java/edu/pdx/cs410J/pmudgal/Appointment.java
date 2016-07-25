package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointment;

/**
 * Created by Priyanka on 7/21/2016.
 */
public class Appointment extends AbstractAppointment{
    private String beginTimeString;
    private String endTimeString;
    private String description;

    @Override
    public String getBeginTimeString() {
        return beginTimeString;
    }

    @Override
    public String getEndTimeString() {
        return endTimeString;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setBeginTimeString(String beginTimeString) {
        this.beginTimeString = beginTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
