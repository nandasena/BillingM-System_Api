package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.CategoryVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllItems() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CategoryVO> categoryList = itemService.getAllCategory();
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(categoryList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }
}
