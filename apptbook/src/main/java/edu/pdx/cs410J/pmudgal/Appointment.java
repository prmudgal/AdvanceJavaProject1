package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointment;

/**
 * Created by Priyanka on 7/21/2016.
 */
public class Appointment extends AbstractAppointment{
    private String beginTimeString;
    private String endTimeString;
    private String description;

    /**
     * Getter for begin time
     * @return beginTimeString
     */
    @Override
    public String getBeginTimeString() {
        return beginTimeString;
    }

    /**
     * getter for EndTime
     * @return endTimeString
     */
    @Override
    public String getEndTimeString() {
        return endTimeString;
    }

    /**
     * getter for description
     * @return description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Setter for begin Time
     * @param beginTimeString :beginTimeString
     */
    public void setBeginTimeString(String beginTimeString) {
        this.beginTimeString = beginTimeString;
    }

    /**
     * Setter for end time
     * @param endTimeString : endTimeString
     */
    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    /**
     * Setter for description
     * @param description : description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
