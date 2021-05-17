package com.qzy.boot.controller;

import com.qzy.boot.dao.ProviderDao;
import com.qzy.boot.entities.Provider;
import com.qzy.boot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 供应商
 */
@Controller
public class ProviderController {

    @Resource
    private ProviderMapper providerMapper;


    private Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @GetMapping("/providers")
    public String list(Model model, Provider provider){
        logger.info("providers查询");
        Collection<Provider> all = null;
        if (provider!=null && provider.getProviderName()!=null){
            if (!provider.getProviderName().isEmpty())
                all = providerMapper.getProviders(provider);
        }else {
            all = providerMapper.getProviders(null);

        }
        List<Provider> providerList = new ArrayList<>(all);
        model.addAttribute("providerList", providerList);
        model.addAttribute("providerName", provider.getProviderName());
        return "provider/list";
    }

    @GetMapping("/provider/{pid}")
    public String one(@PathVariable(name = "pid") String pid, Model model,
                      @RequestParam(value = "type",defaultValue = "view") String type){
        logger.info("get one provider==="+pid);
        Provider provider = providerMapper.getProvider(Integer.parseInt(pid));
        model.addAttribute("provider", provider);
        if ("view".equals(type)){
            return "provider/view";
        }else if ("update".equals(type)){
            return "provider/update";
        }else {
            return "redirect:/";
        }
    }

    @Transactional
    @PutMapping("/provider")
    public String update(Provider provider){
        provider.setCreateDate(new Date());
//        providerDao.save(provider);
        providerMapper.updateProvider(provider);
        return "redirect:/providers";
    }

    @GetMapping("/provider")
    public String getAdd(){
        return "provider/add";
    }

    @Transactional
    @PostMapping("/provider")
    public String add(Provider provider){
//        providerDao.save(provider);
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }


    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable("pid") String pid){
        providerMapper.deleteProviderByPid(Integer.parseInt(pid));
        return "redirect:/providers";
    }


}
