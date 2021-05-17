package com.qzy.boot.entities;


import lombok.Data;


public class BillProvider extends Bill{
    private String providerName;


    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

}
