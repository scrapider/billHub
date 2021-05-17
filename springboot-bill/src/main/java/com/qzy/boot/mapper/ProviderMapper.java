package com.qzy.boot.mapper;


import com.qzy.boot.entities.Provider;
import org.aopalliance.intercept.Interceptor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

public interface ProviderMapper {

    List<Provider> getProviders(Provider provider);

    Provider getProvider(Integer pid);

    int addProvider(Provider provider);

    int deleteProviderByPid(Integer pid);

    int updateProvider(Provider provider);

    int getProviderPidByName(String providerName);

    Set<String> getAllProviderName();




}
