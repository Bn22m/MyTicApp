/*
 *MyTicApp
 */

package messageboard.entities;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ejb.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Singleton
@Provider
public class MessageWriter implements MessageBodyWriter<Message> 
{

    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType) 
    {
        return mediaType.toString().equals( MediaType.TEXT_HTML) && clazz == Message.class;
    }

    public long getSize(Message message, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType) 
    {
        return -1;
    }

    public void writeTo(Message message, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, Object> arg5, OutputStream ostream) 
            throws IOException, WebApplicationException 
    {
        ostream.write(message.toString().getBytes());
    }
}
