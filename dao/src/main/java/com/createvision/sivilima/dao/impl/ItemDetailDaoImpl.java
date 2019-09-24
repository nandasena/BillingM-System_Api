package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.ItemDetailDao;
import com.createvision.sivilima.TableModel.ItemDetail;
import org.springframework.stereotype.Repository;

@Repository("itemDetailDao")
public class ItemDetailDaoImpl extends AbstractDaoImpl<ItemDetail, Long> implements ItemDetailDao {
}
