/*
 *MyTicApp
 */

package messageboard.entities;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Stateless
@Provider
public class MessageListWriter implements MessageBodyWriter<List<Message>> 
{

    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType) 
    {
        return mediaType.toString().equals( MediaType.TEXT_HTML) && verifyGenericType(type);
    }

    private boolean verifyGenericType(Type genericType) 
    {
        if (!(genericType instanceof ParameterizedType)) 
        {
            return false;
        }

        final ParameterizedType pt = (ParameterizedType)genericType;

        if (pt.getActualTypeArguments().length > 1) 
        {
            return false;
        }

        if (!(pt.getActualTypeArguments()[0] instanceof Class)) 
        {
            return false;
        }

        final Class listClass = (Class)pt.getActualTypeArguments()[0];
        return listClass == Message.class;
    }

    public long getSize(List<Message> messages, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType) 
    {
        return -1;
    }

    public void writeTo(List<Message> messages, Class<?> clazz, Type type, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, Object> arg5, OutputStream ostream) 
            throws IOException, WebApplicationException 
    {
        for (Message m : messages) 
        {
            ostream.write(m.toString().getBytes());
            ostream.write((" <a href='./app/messages/" + m.getUniqueId() + "'>link</a><br />").getBytes());
        }
    }
}
