package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.MainCategoryDao;
import com.createvision.sivilima.tableModel.MainCategory;
import org.springframework.stereotype.Repository;

@Repository("mainCategoryDao")
public class MainCategoyDaoImpl extends AbstractDaoImpl<MainCategory,Long> implements MainCategoryDao {
}
