package edu.pdx.cs410J.pmudgal;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{
    /**
     * Mapping Count
     * @param count : Int
     * @return Message
     */
    public static String getMappingCount( int count )
    {
        return String.format( "Server contains %d Appointments", count );
    }

    /**
     * Format the key value
     * @param key : Key
     * @param value : Value
     * @return : Message
     */
    public static String formatKeyValuePair( String key, String value )
    {
        return String.format("  %s -> %s", key, value);
    }

    /**
     * Missing required Paramateres
     * @param parameterName : parameter Name
     * @return : Message
     */
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    /**
     * Date not formatted
     * @param parameterName : parameter name
     * @return : Message
     */
    public static String noFormattedDate( String parameterName )
    {
        return String.format("The dateTime parameter \"%s\" is not in format mm/dd/yyyy hh:mm a", parameterName);
    }

    /**
     * Mapped key value
     * @param key : Key
     * @param value : Value
     * @return Message
     */
    public static String mappedKeyValue( String key, String value )
    {
        return String.format( "Mapped %s to %s", key, value );
    }

    /**
     * Mapped owner to Appointment Book
     * @param owner : Owner name
     * @param appointmentBook : app book
     * @return Message
     */
    public static String mappedOwnerToAppointmentBook( String owner, String appointmentBook )
    {
        return String.format( "Added appointment :  %s has %s", owner, appointmentBook );
    }

    /**
     * Delete all mapping
     * @return : Message
     */
    public static String allMappingsDeleted() {
        return "All mappings have been deleted";
    }
}
