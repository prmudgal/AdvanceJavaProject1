package edu.pdx.cs410J.pmudgal;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;

/**
 * A helper class for accessing the rest client
 */
public class AppointmentBookRestClient extends HttpRequestHelper
{
    private static final String WEB_APP = "apptbook";
    private static final String SERVLET = "appointments";

    private final String url;

    /**
     * /**
     * Creates a client to the appointment book REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public AppointmentBookRestClient( String hostName, int port )
    {
        this.url = String.format( "http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET );
    }

    /**
     * Returns all keys and values from the server
     * @return : Response
     * @throws IOException : IO Exception
     */
    public Response getAllKeysAndValues() throws IOException
    {
        return get(this.url );
    }

    /**
     *  Returns all values for the given owner
     * @param owner : owner
     * @return Response
     * @throws IOException : Any IO EXception
     */
    public Response getValues( String owner ) throws IOException
    {
        return get(this.url, "owner", owner);
    }

    /**
     * Returns all values for the given owner, beginDate, EndDate
     * @param owner : Owner name
     * @param beginTime : begin Time
     * @param endTime : End Time
     * @return Response
     * @throws IOException : IOException
     */
    public Response getValuesWithSearch( String owner, String beginTime, String endTime ) throws IOException
    {
        return get(this.url, "owner", owner, "beginTime", beginTime, "endTime", endTime);
    }

    /**
     * Add the keyVal pair
     * @param owner : Owner name
     * @param description : Description
     * @param beginTime : beginTime
     * @param endTime : endTime
     * @return : Response
     * @throws IOException : IOException
     */
    public Response addKeyValuePair( String owner, String description, String beginTime, String endTime ) throws IOException
    {
        return postToMyURL("owner", owner, "description", description, "beginTime", beginTime, "endTime", endTime);
    }

    /**
     * Posting to Url
     * @param keysAndValues : All parameters to be added
     * @return : Response
     * @throws IOException : IOException
     */
    @VisibleForTesting
    Response postToMyURL(String... keysAndValues) throws IOException {
        return post(this.url, keysAndValues);
    }

    /**
     * Removes all mapping
     * @return : Response
     * @throws IOException : IOException
     */
    public Response removeAllMappings() throws IOException {
        return delete(this.url);
    }
}
