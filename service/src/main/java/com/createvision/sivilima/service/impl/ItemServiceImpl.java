package com.createvision.sivilima.service.impl;

import com.createvision.sivilima.dao.ItemDao;
import com.createvision.sivilima.dao.ItemDetailDao;
import com.createvision.sivilima.dao.MainCategoryDao;
import com.createvision.sivilima.dao.SubCategoryDao;
import com.createvision.sivilima.service.ItemService;
import com.createvision.sivilima.tableModel.Item;
import com.createvision.sivilima.tableModel.ItemDetail;
import com.createvision.sivilima.tableModel.MainCategory;
import com.createvision.sivilima.tableModel.SubCategory;
import com.createvision.sivilima.valuesObject.CategoryVO;
import com.createvision.sivilima.valuesObject.ItemDetailsVO;
import com.createvision.sivilima.valuesObject.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    ItemDetailDao itemDetailDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Autowired
    private MainCategoryDao mainCategoryDao;

    @Autowired
    private CommonFunctionsImpl commonFunctions;

    @Override
    public List<ItemVO> getAllItems() throws Exception {
        List<ItemVO> itemVOList = new ArrayList<>();
        try {
            List<Item> itemList = itemDao.getAll();
            for (Item temp : itemList) {

                List<ItemDetail> itemDetail = temp.getItemDetails();
                ItemVO itemVO = new ItemVO();
                Set<ItemDetailsVO> itemDetailList = new HashSet<>();
                itemVO.setDescription(temp.getDescription());
                itemVO.setItemName(temp.getName());
                itemVO.setItemId(temp.getId());
                itemVO.setSubCategoryId(temp.getSubCategory().getId());
                itemVO.setItemCode(temp.getItemCode());

                for (ItemDetail temItem : itemDetail) {
                    if (!temItem.isDelete()) {
                        ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                        itemDetailsVO.setItemDetailId(temItem.getId());
                        itemDetailsVO.setAvailableQuantity(temItem.getAvailableQuantity());
                        itemDetailsVO.setFabricatorPrice(temItem.getFabricatorPrice());
                        itemDetailsVO.setCustomerPrice(temItem.getCustomerPrice());
                        itemDetailsVO.setMrpPrice(temItem.getMrpPrice());
                        itemDetailsVO.setFabricatorPrice(temItem.getFabricatorPrice());
                        itemDetailsVO.setCostPrice(temItem.getCostPrice());
                        itemDetailsVO.setQuantity(temItem.getQuantity());
                        itemDetailsVO.setDelete(temItem.isDelete());


                        itemDetailList.add(itemDetailsVO);
                    }

                }
                itemVO.setItemDetailList(itemDetailList);
                itemVOList.add(itemVO);

            }
        } catch (Exception e) {
            throw e;
        }
        return itemVOList;
    }

    @Override
    public Item getItemById(long id) throws Exception {
        return itemDao.get(id);
    }

    @Override
    public ItemVO createNewItem(ItemVO itemVO) throws Exception {
        try {
            Date date = commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo");
            Item item = new Item();
            SubCategory subCategory = subCategoryDao.get(itemVO.getSubCategoryId());
            item.setSubCategory(subCategory);
            item.setCreateDate(date);
            item.setDescription(itemVO.getDescription());
            item.setName(itemVO.getItemName());
            item.setItemCode(itemVO.getItemCode());
            Long createItemId = itemDao.save(item);
            itemVO.setItemId(createItemId);

        } catch (Exception e) {
            throw e;
        }
        return itemVO;
    }

    @Override
    public ItemDetailsVO updateItem(ItemDetailsVO itemVo) throws Exception {
        try {

            ItemDetail itemDetail =itemDetailDao.get(itemVo.getItemDetailId());
            itemDetail.setCustomerPrice(itemVo.getCustomerPrice());
            itemDetail.setCostPrice(itemVo.getCostPrice());
            itemDetail.setMrpPrice(itemVo.getMrpPrice());
            itemDetail.setFabricatorPrice(itemVo.getFabricatorPrice());
            itemDetailDao.save(itemDetail);

            return itemVo;
        }catch (Exception e){
            throw e;
        }


    }

    @Override
    public void deleteItem(long id) throws Exception {
        Item item = getItemById(id);

        itemDao.delete(item);
    }

    @Override
    public List<CategoryVO> getAllSubCategory() throws Exception {
        List<SubCategory> subCategoryList = subCategoryDao.getAll();
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (SubCategory tempSubCategory : subCategoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setSubCategoryId(tempSubCategory.getId());
            categoryVO.setMainCategoryId(tempSubCategory.getMainCategory().getId());
            categoryVO.setName(tempSubCategory.getName());
            categoryVOList.add(categoryVO);
        }
        return categoryVOList;
    }

    @Override
    public List<CategoryVO> getAllMainCategory() throws Exception {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        try {
            List<MainCategory> mainCategoryList = mainCategoryDao.getAll();
            for (MainCategory tempMainCategory : mainCategoryList) {
                CategoryVO categoryVO = new CategoryVO();
                categoryVO.setMainCategoryId(tempMainCategory.getId());
                categoryVO.setName(tempMainCategory.getName());
                categoryVOList.add(categoryVO);
            }
        } catch (Exception e) {
            throw e;
        }
        return categoryVOList;
    }

    @Override
    public List<CategoryVO> createMainCategory(List<CategoryVO> categoryVOS) throws Exception {
        try {
            if (!categoryVOS.isEmpty()) {
                for (CategoryVO categoryVO : categoryVOS) {
                    MainCategory mainCategory = new MainCategory();
                    mainCategory.setName(categoryVO.getName());
                    mainCategoryDao.save(mainCategory);
                }
            }

        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<CategoryVO> createSubCategory(List<CategoryVO> categoryVOS) throws Exception {
        try {
            if (!categoryVOS.isEmpty()) {
                for (CategoryVO categoryVO : categoryVOS) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.setName(categoryVO.getName());
                    MainCategory mainCategory = mainCategoryDao.get(categoryVO.getMainCategoryId());
                    subCategory.setMainCategory(mainCategory);
                    subCategoryDao.save(subCategory);
                }
            }

        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public ItemVO getItemByItemCode(String itemCode) throws Exception {
        ItemVO itemVO = null;
        try {
            Item selectedItem = itemDao.getItemByItemCode(itemCode);
            if (selectedItem != null) {

                List<ItemDetail> itemDetail = selectedItem.getItemDetails();
                itemVO = new ItemVO();
                Set<ItemDetailsVO> itemDetailList = new HashSet<>();
                itemVO.setDescription(selectedItem.getDescription());
                itemVO.setItemName(selectedItem.getName());
                itemVO.setItemId(selectedItem.getId());
                itemVO.setSubCategoryId(selectedItem.getSubCategory().getId());
                itemVO.setItemCode(selectedItem.getItemCode());
                for (ItemDetail temItem : itemDetail) {
                    if (!temItem.isDelete()) {
                        ItemDetailsVO itemDetailsVO = new ItemDetailsVO();
                        itemDetailsVO.setItemDetailId(temItem.getId());
                        itemDetailsVO.setAvailableQuantity(temItem.getAvailableQuantity());
                        itemDetailsVO.setFabricatorPrice(temItem.getFabricatorPrice());
                        itemDetailsVO.setCustomerPrice(temItem.getCustomerPrice());
                        itemDetailsVO.setMrpPrice(temItem.getMrpPrice());
                        itemDetailsVO.setCostPrice(temItem.getCostPrice());
                        itemDetailsVO.setQuantity(temItem.getQuantity());
                        itemDetailsVO.setDelete(temItem.isDelete());


                        itemDetailList.add(itemDetailsVO);
                    }

                }
                itemVO.setItemDetailList(itemDetailList);
            }

        } catch (Exception e) {
            throw e;
        }

        return itemVO;
    }

    @Override
    public List<ItemVO> createNewItemList(List<ItemVO> itemVOList) throws Exception {
        try {
            for (ItemVO itemVO:itemVOList) {
                Date date = commonFunctions.getCurrentDateAndTimeByTimeZone("Asia/Colombo");
                Item item = new Item();
                SubCategory subCategory = subCategoryDao.get(itemVO.getSubCategoryId());
                item.setSubCategory(subCategory);
                item.setCreateDate(date);
                item.setDescription(itemVO.getDescription());
                item.setName(itemVO.getItemName());
                item.setItemCode(itemVO.getItemCode());
                Long createItemId = itemDao.save(item);
                itemVO.setItemId(createItemId);
            }


        } catch (Exception e) {
            throw e;
        }
        return itemVOList;
    }
}
