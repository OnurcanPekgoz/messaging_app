package tr.com.argela.whatsapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tr.com.argela.whatsapp.model.response.ErrorInfo;
import tr.com.argela.whatsapp.model.response.WhatsappResponse;
import tr.com.argela.whatsapp.model.result.WhatsappResult;
import tr.com.argela.whatsapp.service.FriendService;

@RestController
public class FriendController {
    @Autowired
    FriendService friendService;

    @PutMapping("/addFriend/{friendPhone}")
    public ResponseEntity<WhatsappResponse> addFriend(@RequestHeader String sessionId,
            @PathVariable String friendPhone) {
        WhatsappResponse whatsappResponse = new WhatsappResponse();
        HttpStatus httpStatus;
        try {
            whatsappResponse.setSingle(friendService.addFriend(sessionId, friendPhone));
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = handleError(whatsappResponse, e);
        }
        return new ResponseEntity<WhatsappResponse>(whatsappResponse, httpStatus);
    }

    @GetMapping("/friends")
    public ResponseEntity<WhatsappResponse> getFriends(@RequestHeader String sessionId) {
        WhatsappResponse whatsappResponse = new WhatsappResponse();
        HttpStatus httpStatus;
        try {
            List<WhatsappResult> response= new ArrayList<>();
            response.addAll(friendService.getFriends(sessionId));
            whatsappResponse.setList(response);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = handleError(whatsappResponse, e);
        }
        return new ResponseEntity<WhatsappResponse>(whatsappResponse, httpStatus);
    }

    private HttpStatus handleError(WhatsappResponse whatsappResponse, Exception e) {
        HttpStatus httpStatus;
        whatsappResponse.setError(new ErrorInfo(e.getClass().getSimpleName(), e.getMessage()));
        httpStatus = HttpStatus.UNAUTHORIZED;

        return httpStatus;
    }
}
