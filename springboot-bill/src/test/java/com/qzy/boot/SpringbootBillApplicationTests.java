package com.qzy.boot;

import com.qzy.boot.dao.ProviderDao;
import com.qzy.boot.entities.Bill;
import com.qzy.boot.entities.BillProvider;
import com.qzy.boot.entities.Provider;
import com.qzy.boot.entities.User;
import com.qzy.boot.mapper.BillMapper;
import com.qzy.boot.mapper.ProviderMapper;
import com.qzy.boot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@SpringBootTest
class SpringbootBillApplicationTests {

    @Resource
    ProviderMapper providerMapper;

    @Resource
    BillMapper billMapper;

    @Resource
    UserMapper userMapper;
    @Test
    void contextLoads() {
        Provider provider = new Provider();
        provider.setProviderName("A货");
        List<Provider> providers = providerMapper.getProviders(provider);
        providers.forEach(System.out::println);

        Provider provider1 = providerMapper.getProvider(2);
        System.out.println(provider1);
        provider1.setProviderName("B货域名供应商");
        int i = providerMapper.updateProvider(provider1);
        System.out.println(i);
//        int i1 = providerMapper.addProvider(new Provider(null,"PR-AA", "梦学谷供应商111", "小张", "18888666981", "深圳软件园", "0911-0123456", "品质A"));
//        providerMapper.deleteProviderByPid(9);
//        providerMapper.deleteProviderByPid(8);
    }

    @Test
    void test1(){
//        BillProvider billByBid = billMapper.getBillByBid(2);
//        System.out.println(billByBid.getN);
//        Bill bill = new Bill();
//        bill.setBillName("com");
//        List<BillProvider> bills = billMapper.getBills(bill);
//        bills.forEach(System.out::println);
//        Bill billByBid1 = billByBid;
//        billByBid1.setBillName("cn域名。。。。。");
//        billMapper.updateBill(billByBid1);
//
//        billMapper.addBill(billByBid1);

//        billMapper.deleteBillByBid(7);
//        List<BillProvider> bills = billMapper.getBills(new Bill());
//        bills.forEach(System.out::println);
    }

    @Test
    void test2(){
        User user = new User();
        user.setBirthday(new Date());
        user.setId(1);
        user.setRealName("李三");
        user.setUserType(3);
        user.setGender(1);
        userMapper.updateUser(user);
    }


}
