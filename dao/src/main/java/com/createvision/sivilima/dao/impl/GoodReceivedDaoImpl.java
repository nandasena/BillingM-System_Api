package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.GoodReceivedDao;
import com.createvision.sivilima.tableModel.GoodReceived;
import org.springframework.stereotype.Repository;

@Repository("goodReceivedDao")
public class GoodReceivedDaoImpl extends AbstractDaoImpl<GoodReceived, Long> implements GoodReceivedDao {
}
