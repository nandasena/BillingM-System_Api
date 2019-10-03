package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.IUserService;
import com.createvision.sivilima.valuesObject.ReturnVO;
import com.createvision.sivilima.valuesObject.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createNewUser(@RequestBody UserVO userVO) {
        ReturnVO returnVO = new ReturnVO();
        try {
        UserVO insertUser = iUserService.createNewUser(userVO);
            if (insertUser!=null) {
                returnVO.setStatusCode(200);
                returnVO.setSuccess(true);
                returnVO.setResult(insertUser);
                return ResponseEntity.ok(returnVO);
            } else {
                returnVO.setStatusCode(301);
                returnVO.setSuccess(false);
                returnVO.setMessage("user is there.....");
                return ResponseEntity.ok(returnVO);
            }
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }
}
