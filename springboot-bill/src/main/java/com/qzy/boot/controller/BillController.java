package com.qzy.boot.controller;

import com.qzy.boot.entities.Bill;
import com.qzy.boot.entities.BillProvider;
import com.qzy.boot.entities.Provider;
import com.qzy.boot.mapper.BillMapper;
import com.qzy.boot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class BillController {
    private Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Resource
    private ProviderMapper providerMapper;

    @Resource
    private BillMapper billMapper;

    @GetMapping("/bills")
    public String list(Model model, BillProvider bill){
        logger.info("账单");
        List<BillProvider> all = null;
        if (bill.getBillName()!=null || bill.getProviderName()!=null ||bill.getPay()!=null) {
            if (bill.getProviderName()!=null && !bill.getProviderName().isEmpty()){
                Integer providerPidByName = providerMapper.getProviderPidByName(bill.getProviderName());
                bill.setPid(providerPidByName);
            }
            all = billMapper.getBills(bill);
        }else {
            all = billMapper.getBills(null);
        }
        List<BillProvider> providerList = new ArrayList<>(all);
        model.addAttribute("billList", providerList);
        model.addAttribute("billName", bill.getBillName());
        model.addAttribute("billProviderNames", getBillProviderName());
        return "bill/list";
    }

    public Set getBillProviderName(){
        List<BillProvider> providerList = billMapper.getBills(null);
        Set<String> billProviderNames = new HashSet<>();
        providerList.forEach(x->billProviderNames.add(x.getProviderName()));
        return billProviderNames;


    }

    @GetMapping("/bill/{bid}")
    public String one(@PathVariable(name = "bid") Integer bid, Model model,
                      @RequestParam(value = "type",defaultValue = "view") String type){
        BillProvider billProvider = billMapper.getBillByBid(bid);
        model.addAttribute("billProvider", billProvider);
        model.addAttribute("billProviderNames", providerMapper.getAllProviderName());
        if ("view".equals(type)){
            return "bill/view";
        }else if ("update".equals(type)){
            return "bill/update";
        }else {
            return "redirect:/";
        }
    }

    @Transactional
    @PutMapping("/bill")
    public String update(BillProvider bill){
        bill.setCreateDate(new Date());
        int providerPidByName = providerMapper.getProviderPidByName(bill.getProviderName());
        bill.setPid(providerPidByName);
//        providerDao.save(provider);
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    @GetMapping("/bill")
    public String getAdd(Model model){
        Set billProviderName = providerMapper.getAllProviderName();
        model.addAttribute("billProviderName",billProviderName);
        return "bill/add";
    }

    @Transactional
    @PostMapping("/bill")
    public String add(BillProvider billProvider){
//        providerDao.save(provider);
        billProvider.setCreateDate(new Date());
        int providerPidByName = providerMapper.getProviderPidByName(billProvider.getProviderName());
        billProvider.setPid(providerPidByName);
        billMapper.addBill(billProvider);
        return "redirect:/bills";
    }


    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable("bid") Integer bid){
        billMapper.deleteBillByBid(bid);
        return "redirect:/bills";
    }
}
