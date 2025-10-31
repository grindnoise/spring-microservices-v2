package com.microservices.loans.service;

import com.microservices.loans.dto.LoanDto;

public interface LoanService {

    void createLoan(String mobileNumber);

    LoanDto fetchLoan(String mobileNumber);


    boolean updateLoan(LoanDto dto);

    boolean deleteLoan(String mobileNumber);


}
