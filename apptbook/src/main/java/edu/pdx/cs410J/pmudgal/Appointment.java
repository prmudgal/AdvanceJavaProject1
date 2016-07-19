package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.*;
import java.util.Comparator;
import java.util.Date;

/**
 * This class contains the properties required for an appointment
 * namely: description, owner, beginTimeString, endTimeString and their getter and setters
 */
public class Appointment extends AbstractAppointment implements Comparable<Appointment>{

  /**
   * The properties: description, owner, beginTimeString, endTimeString
   */
  private String description;

  private String owner;

  private String beginTimeString;

  private String endTimeString;

  private int duration;

  SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy hh:mm a");

  /**
   * getter for beginTimeString
   * @return beginTimeString
     */
  @Override
  public String getBeginTimeString() {
    String formattedBeginDate = null;
    try {
      formattedBeginDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(sdf.parse(beginTimeString));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return formattedBeginDate;
  }



  /**
   * getter for endTimeString
   * @return endTimeString
     */
  @Override
  public String getEndTimeString() {
    String formattedEndDate = null;
    try {
      formattedEndDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(sdf.parse(endTimeString));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return formattedEndDate;
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
   * setter for description
   * @param description : Description for appointment
     */
  public void setDescription(String description) {
    this.description = description;
  }


  /**
   * Getter for Owner
   * @return owner : Owner name
     */
  public String getOwner() {
    return owner;
  }

  /**
   * Setter for Owner
   * @param owner : Owner name
     */
  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * Setter for beginTimeString
   * @param beginTimeString : Begin date and time
     */
  public void setBeginTimeString(String beginTimeString) {
    this.beginTimeString = beginTimeString;
  }

  /**
   * Setter for endTimeString
   * @param endTimeString :End Time and date
     */
  public void setEndTimeString(String endTimeString) {
    this.endTimeString = endTimeString;
  }

  /**
   * This method converts the beginTimeString to beginTime of Date format
   * @return begindate
     */
  @Override
  public Date getBeginTime() {
    Date date=null;
    try {

       date= sdf.parse(getBeginTimeString());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * This method converts the endTimeString to endTime of Date format
   * @return enddate
     */
  @Override
  public Date getEndTime() {
    Date date=null;
    try {
      date= sdf.parse(getEndTimeString());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * Overridden method to compare the values
   * @param o param1 to compare
   * @return status 1/-1/0 if param1 is greater than param2 / param1 is less than param2 / param1 is equal to param2
   */
  @Override
  public int compareTo(Appointment o) {
       if(this.getBeginTime().compareTo(o.getBeginTime())>0){
        return 1;
      }else if(this.getBeginTime().compareTo(o.getBeginTime())<0){
        return -1;
      } else if(this.getEndTime().compareTo(o.getEndTime())>0){
        return 1;
      } else if(this.getEndTime().compareTo(o.getEndTime())<0){
        return  -1;
      } else if (this.getDescription().compareTo(o.getDescription()) > 0) {
        return 1;
      } else if (this.getDescription().compareTo(o.getDescription()) < 0) {
        return -1;
      } else{
        return 0;
      }
  }

    /**
     * This is overridden method from Object class
     * @param o : Object
     * @return true or false
     */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Appointment that = (Appointment) o;

    if (!description.equals(that.description)) return false;
    if (!owner.equals(that.owner)) return false;
    if (!beginTimeString.equals(that.beginTimeString)) return false;
    if (!endTimeString.equals(that.endTimeString)) return false;
    return sdf.equals(that.sdf);

  }

    /**
     * This is overridden method from Object class
     * @return the hashcode for the object
     */
  @Override
  public int hashCode() {
    int result = description.hashCode();
    result = 31 * result + owner.hashCode();
    result = 31 * result + beginTimeString.hashCode();
    result = 31 * result + endTimeString.hashCode();
    result = 31 * result + sdf.hashCode();
    return result;
  }




    /**
     * Getter for duration or meeting
     * @return the no of minutes
     */
  public int getDuration() {
    return duration;
  }

    /**
     * Setter for duration
     * @param duration : duration of appointment in minutes
     */
  public void setDuration(int duration) {
    this.duration = duration;
  }
}
