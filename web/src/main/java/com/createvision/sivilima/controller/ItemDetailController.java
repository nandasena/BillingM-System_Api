package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.ItemDetailService;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/itemDetail")
public class ItemDetailController {
    @Autowired
    ItemDetailService itemDetailService;

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllItems(@PathVariable("itemId") Long itemId) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            List<ItemDetailsVO> itemVOList = itemDetailService.getItemDetailByItemId(itemId);
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
    public ResponseEntity<Object> createItemDetail(@RequestBody ItemDetailsVO itemVO) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            ItemDetailsVO itemDetailsVO = itemDetailService.createItemDetail(itemVO);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(true);
            returnVO.setResult(itemDetailsVO);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteItemDetails(@PathVariable("itemId") Long itemId) throws Exception {
        ReturnVO returnVO = new ReturnVO();
        try {
            boolean isTrue = itemDetailService.deleteItemDetail(itemId);
            returnVO.setStatusCode(200);
            returnVO.setSuccess(isTrue);
            return ResponseEntity.ok(returnVO);
        } catch (Exception e) {
            returnVO.setResult(e);
            returnVO.setStatusCode(5001);
            returnVO.setSuccess(false);
            return ResponseEntity.ok(returnVO);
        }
    }

}
