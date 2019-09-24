package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.CategoryDao;
import com.createvision.sivilima.TableModel.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDaoImpl<Category,Long> implements CategoryDao {


}
