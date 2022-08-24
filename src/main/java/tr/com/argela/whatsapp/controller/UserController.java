package tr.com.argela.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tr.com.argela.whatsapp.entity.User;
import tr.com.argela.whatsapp.model.response.ErrorInfo;
import tr.com.argela.whatsapp.model.response.WhatsappResponse;
import tr.com.argela.whatsapp.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/register")
    public ResponseEntity<WhatsappResponse> register(@RequestParam String name,@RequestParam String telephone, @RequestParam String password) {
        WhatsappResponse whatsappResponse = new WhatsappResponse();
        HttpStatus httpStatus;
        try {
            User user= new User();
            user.setName(name);
            user.setTelephone(telephone);
            user.setPassword(password);
           whatsappResponse.setSingle(userService.register(user));
           httpStatus=HttpStatus.OK;
        } catch (Exception e) {
            httpStatus=handleError(whatsappResponse, e);
        }
        return new ResponseEntity<WhatsappResponse>(whatsappResponse,httpStatus);
    }

    @PutMapping("/login/{telephone}/{password}")
    public ResponseEntity<WhatsappResponse> login(@PathVariable String telephone, @PathVariable String password) {
        WhatsappResponse whatsappResponse = new WhatsappResponse();
        HttpStatus httpStatus;
        try {
           whatsappResponse.setSingle(userService.login(telephone, password));
           httpStatus=HttpStatus.OK;
        } catch (Exception e) {
            httpStatus=handleError(whatsappResponse, e);
        }
        return new ResponseEntity<WhatsappResponse>(whatsappResponse,httpStatus);
    }



    private HttpStatus handleError(WhatsappResponse whatsappResponse, Exception e) {
        HttpStatus httpStatus;
        whatsappResponse.setError(new ErrorInfo(e.getClass().getSimpleName(), e.getMessage()));
            httpStatus = HttpStatus.UNAUTHORIZED;

        return httpStatus;
    }
}
