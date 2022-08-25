package tr.com.argela.whatsapp.model;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDeserializer implements Deserializer {

    public Message deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Message message = null;
        try {
            message = mapper.readValue(arg1, Message.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
