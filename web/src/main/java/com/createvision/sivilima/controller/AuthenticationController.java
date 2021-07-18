package com.createvision.sivilima.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.createvision.sivilima.service.ISecurityService;
import com.createvision.sivilima.service.IUserService;
import com.createvision.sivilima.tableModel.User;
import com.createvision.sivilima.valuesObject.ErrorModel;
import com.createvision.sivilima.valuesObject.LoginModalVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/userAuth")
public class AuthenticationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ISecurityService securityService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> doLogin(@RequestBody LoginModalVO loginModel) throws Exception {

        try {
//            LOGGER.info("User name ========", loginModel.getUserName());
            securityService.autologin(loginModel.getEmail(), loginModel.getPassword());
            User user = userService.getUserByUserName(loginModel.getEmail());

            if (!user.isDelete()) {

                String userRole = "admin";
                userRole = userRole.replaceFirst("\\s", "");
                Algorithm algorithm = Algorithm.HMAC256("sivilima-secret");

                String jwtToken = JWT.create()
                        .withClaim("email", user.getUserName())
                        .withClaim("name", user.getName())
                        .withClaim("role", userRole)
                        .withClaim("picture", "assets/images/avatar.png")
                        .withClaim("userId", user.getId())
                        .withClaim("roleId", user.getUserRoleId().getRoleId())
                        .sign(algorithm);

                String jsonResponseString = "{\r\n" +
                        "	\"success\": true,\r\n" +
                        "	\"messages\": \"Successful Login...\",\r\n" +
                        "	\"data\": {\r\n" +
                        "		\"token\": \"" + jwtToken + "\"\r\n" +
                        "	}\r\n" +
                        "}";
                return ResponseEntity.ok(jsonResponseString);
            } else if (user.isDelete()) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setErrorCode("5002");
                errorModel.setErrorMsg("User still not invited");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);

            }
            else {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setErrorCode("5003");
                errorModel.setErrorMsg("User unregistered");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
            }

        } catch (UsernameNotFoundException e) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrorCode("4001");
            errorModel.setErrorMsg("Username Not Found Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);

        } catch (AuthenticationException e) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrorCode("4002");
            errorModel.setErrorMsg("Authentication Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);

        } catch (JWTCreationException e) {
            e.printStackTrace();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrorCode("4003");
            errorModel.setErrorMsg("JWT Error" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);

        } catch (Exception e) {
            LOGGER.info("Exception occured. ", e);
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrorCode("5001");
            errorModel.setErrorDesc(e.getMessage());
            errorModel.setErrorMsg(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorModel);
        }
    }
}
