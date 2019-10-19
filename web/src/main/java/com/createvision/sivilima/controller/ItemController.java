package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.valuesObject.ItemVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllItems() throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<ItemVO> itemVOList = itemService.getAllItems();
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(itemVOList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addItem(@RequestBody ItemVO itemVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            ItemVO itemVOList = itemService.createNewItem(itemVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(itemVOList);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getItemByItemCode(@PathVariable("itemCode") String itemCode) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            ItemVO itemVO = itemService.getItemByItemCode(itemCode);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(itemVO);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }
}

