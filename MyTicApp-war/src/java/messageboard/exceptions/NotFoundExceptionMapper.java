/*
 *MyTicApp
 */

package messageboard.exceptions;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> 
{

    public Response toResponse(NotFoundException exception) 
    {
        try 
        {
            URI location = new URI("index.html");
            Response.seeOther(location);
            return Response.ok().build();
            //return Response.status(404).build();
        } 
        catch (URISyntaxException ex) 
        {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
