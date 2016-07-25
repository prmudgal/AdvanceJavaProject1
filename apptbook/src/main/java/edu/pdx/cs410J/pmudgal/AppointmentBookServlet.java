package edu.pdx.cs410J.pmudgal;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>AppointmentBook</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple key/value pairs.
 */
public class AppointmentBookServlet extends HttpServlet
{
    private final Map<String, AppointmentBook> appointmentBooks = new HashMap<>();


    /**
     * Handles an HTTP GET request from a client by writing the value of the key
     * specified in the "key" HTTP parameter to the HTTP response.  If the "key"
     * parameter is not specified, all of the key/value pairs are written to the
     * HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        String owner = getParameter( "owner", request );
        AppointmentBook appointmentBook = getAppointmentsBookForOwner(owner);
        String beginTime=getParameter("beginTime",request);
        String endTime=getParameter("endTime",request);
        if(endTime != null && beginTime !=null){

          List<Appointment> appointmentList = new ArrayList<Appointment>(appointmentBook.getAppointments());
            appointmentBook=new AppointmentBook(owner);
            for (Appointment appointment:appointmentList){
                if(appointment.getBeginTimeString().compareTo(beginTime)>=0 && appointment.getEndTimeString().compareTo(endTime)<=0){
                    appointmentBook.addAppointment(appointment);
                }
            }
            System.out.println("sssdd + "+ appointmentBook.getAppointments().size());
        }
        prettyPrint(appointmentBook, response.getWriter());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void prettyPrint(AppointmentBook appointmentBook, PrintWriter pw) throws IOException {
        PrettyPrinter pretty = new PrettyPrinter(pw);
        pretty.dump(appointmentBook);
    }

    private AppointmentBook getAppointmentsBookForOwner(String owner) {
    return this.appointmentBooks.get(owner);
    }

    /**
     * Handles an HTTP POST request by storing the key/value pair specified by the
     * "key" and "value" request parameters.  It writes the key/value pair to the
     * HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        try {
        PrintWriter pw = response.getWriter();

        Appointment appointment =new Appointment();
        response.setContentType( "text/plain" );
        String owner=getParameter("owner", request);
        if (owner == null) {
            missingRequiredParameter(response, "owner");
            return;
        }
        String description = getParameter("description", request);
        if(description ==null){
            missingRequiredParameter(response,"description");
            return;
        }
        String beginTime=getParameter("beginTime",request);
        if(beginTime == null){
            missingRequiredParameter(response,"beginTime");
            return;
        }
        String endTime=getParameter("endTime",request);
        if(endTime == null){
            missingRequiredParameter(response,"endTime");
            return;
        }
        AppointmentBook appointmentBook = getAppointmentsBookForOwner(owner);

        if(appointmentBook ==null){
            appointmentBook= new AppointmentBook(owner);
        }

            if(DateValidator.checkDateTimeFormat(beginTime,pw).equals("false")){
            notFormattedDate(response,"beginTime");
                return;
            }
            if(DateValidator.checkDateTimeFormat(endTime,pw).equals("false")){
            notFormattedDate(response,"endTime");
                return;
            }
        appointment.setDescription(description);
        appointment.setBeginTimeString(beginTime);
        appointment.setEndTimeString(endTime);
        appointmentBook.setOwner(owner);
        appointmentBook.addAppointment(appointment);
        appointmentBooks.put(owner, appointmentBook);

        response.setStatus( HttpServletResponse.SC_OK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*String key = getParameter( "key", request );
        if (key == null) {
            missingRequiredParameter(response, "key");
            return;
        }

        String value = getParameter( "value", request );
        if ( value == null) {
            missingRequiredParameter( response, "value" );
            return;
        }

        this.appointmentBook.put(key, value);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.mappedKeyValue(key, value));
        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK);*/
    }

    /**
     * Handles an HTTP DELETE request by removing all key/value pairs.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        this.appointmentBooks.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allMappingsDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }
    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void notFormattedDate( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.noFormattedDate(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Writes the value of the given key to the HTTP response.
     *
     * The text of the message is formatted with {@link Messages#getMappingCount(int)}
     * and {@link Messages#formatKeyValuePair(String, String)}
     */
    private void writeValue( String key, HttpServletResponse response ) throws IOException
    {
        /*String value = this.appointmentBook.get(key);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount( value != null ? 1 : 0 ));
        pw.println(Messages.formatKeyValuePair(key, value));

        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK );*/
    }

    /**
     * Writes all of the key/value pairs to the HTTP response.
     *
     * The text of the message is formatted with
     * {@link Messages#formatKeyValuePair(String, String)}
     */
    private void writeAllMappings( HttpServletResponse response ) throws IOException
    {
        /*PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount(appointmentBook.size()));

        for (Map.Entry<String, String> entry : this.appointmentBook.entrySet()) {
            pw.println(Messages.formatKeyValuePair(entry.getKey(), entry.getValue()));
        }

        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK );*/
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

    @VisibleForTesting
    void setValueForKey(String key, String value) {
//        this.appointmentBook.put(key, value);
    }

   /* @VisibleForTesting
    String getValueForKey(String key) {
        throw new UnsupportedEncodingException("I dont work anymore!");
    }*/
}
