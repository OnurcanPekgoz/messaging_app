package tr.com.argela.whatsapp.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.argela.whatsapp.model.result.WhatsappResult;

@Getter
@Setter
@NoArgsConstructor
public class WhatsappResponse {
    WhatsappResult whatsappResult;
    ErrorInfo error;
}
