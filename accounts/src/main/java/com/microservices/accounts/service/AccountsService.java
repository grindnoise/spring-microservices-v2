package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;

public interface AccountsService {

    void createAccount(CustomerDto dto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto dto);


    boolean deleteAccount(String mobileNumber);


}
