package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.CategoryVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/subCategory/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllSubCategory() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CategoryVO> categoryList = itemService.getAllSubCategory();
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

    @RequestMapping(value = "/mainCategory/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllMainCategory() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CategoryVO> categoryList = itemService.getAllMainCategory();
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

    @RequestMapping(value = "/mainCategory/",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object>createMainCategory(@RequestBody List<CategoryVO> categoryVO)throws Exception{
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CategoryVO> categoryList = itemService.createMainCategory(categoryVO);
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

    @RequestMapping(value = "/subCategory/",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object>createSubCategory(@RequestBody List<CategoryVO> categoryVO)throws Exception{
        ReturnVO returnVO = new ReturnVO();
        try {
            List<CategoryVO> categoryList = itemService.createSubCategory(categoryVO);
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
