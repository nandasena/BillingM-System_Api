package com.createvision.sivilima.controller;

import com.createvision.sivilima.service.ItemDetailService;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
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

}
