package com.qzy.boot.mapper;


import com.qzy.boot.entities.Bill;
import com.qzy.boot.entities.BillProvider;
import com.qzy.boot.entities.Provider;

import java.util.List;

public interface BillMapper {

    List<BillProvider> getBills(Bill bill);

    BillProvider getBillByBid(Integer bid);

    int addBill(Bill bill);

    int updateBill(Bill bill);

    int deleteBillByBid(Integer bid);




}
