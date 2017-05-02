/*
 *MyTicApp
 */
package messageboard.resources;

import messageboard.entities.Message;
import messageboard.exceptions.NotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
public class MessageBoardResourceBean 
{

    @Context
    private UriInfo ui;
    @EJB
    MessageHolderSingletonBean singleton;

    @GET
    @Produces({"application/xml", "text/html"})
    public List<Message> getMessages() 
    {
        return singleton.getMessages();
    }

    @POST
    public Response addMessage(String msg) 
            throws URISyntaxException 
    {
        Message m = singleton.addMessage(msg);

        URI msgURI = ui.getRequestUriBuilder().path(Integer.toString(m.getUniqueId())).build();

        return Response.created(msgURI).build();
    }

    @Path("{msgNum}")
    @GET
    @Produces({"application/xml", "text/html"})
    public Message getMessage(@PathParam("msgNum") int msgNum) 
            throws NotFoundException 
    {
        Message m = singleton.getMessage(msgNum);

        if (m == null) 
        {
            throw new NotFoundException();
        }

        return m;

    }

    @Path("{msgNum}")
    @DELETE
    public void deleteMessage(@PathParam("msgNum") int msgNum) 
            throws NotFoundException 
    {
        boolean deleted = singleton.deleteMessage(msgNum);

        if (!deleted) 
        {
            throw new NotFoundException();
        }
    }
}
