package com.createvision.sivilima.dao;

import com.createvision.sivilima.tableModel.ItemReceived;

import java.util.List;

public interface ReceivedItemDao extends AbstractDao<ItemReceived, Long> {
    public List<ItemReceived> getReceivedItemById(Long jobId, Long itemId) throws Exception;
}
