package com.microservices.accounts.service.impl;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.AccountDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Account;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.exception.CustomerAlreadyExistsException;
import com.microservices.accounts.exception.ResourceNotFoundException;
import com.microservices.accounts.mapper.AccountMapper;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.AccountRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public boolean updateAccount(CustomerDto dto) {
        Assert.notNull(dto, "CustomerDto cannot be null");
        final var customer = customerRepository.findByMobileNumber(dto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", dto.getMobileNumber()));

        // Update customer details
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
//        customer.setUpdatedAt(java.time.LocalDateTime.now());

        // Clear existing accounts and flush to force DELETE operations
        customer.getAccounts().clear();

        // Add new accounts if present in the DTO
        if (dto.getAccounts() != null && !dto.getAccounts().isEmpty()) {
            dto.getAccounts().forEach(accountDto -> {
                Account account = accountMapper.fromDto(accountDto);
                account.setCustomer(customer);
                customer.getAccounts().add(account);
            });
        }

        customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        final var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        customerRepository.delete(customer);
        return true;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {
        if (customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent())
            throw new CustomerAlreadyExistsException("Customer already exists");

        final var customer = customerMapper.fromDto(customerDto);
        customer.setAccounts(List.of(Account.builder()
                .customer(customer)
                .accountNumber(1000000000L + new Random().nextInt(900000000))
                .accountType(AccountsConstants.SAVINGS)
                .branchAddress(AccountsConstants.ADDRESS)
//                .createdBy("system")
                .build()));
        customerRepository.save(customer);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        final var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        return customerMapper.toDto(customer);
    }
}
