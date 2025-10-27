package com.microservices.accounts.mapper;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.AccountDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Account;
import com.microservices.accounts.entity.BaseEntity;
import com.microservices.accounts.entity.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                AccountMapper.class
        })
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "accounts", source = "accounts", qualifiedByName = "toAccountDto")
    CustomerDto toDto(Customer entity);

    @Mapping(target = "accounts", source = "accounts", qualifiedByName = "fromAccountDto")
    Customer fromDto(CustomerDto dto);

    @AfterMapping
    default void afterMapping(@MappingTarget Customer target) {
//        target.setCreatedBy("system");
        if (target.getAccounts() != null)
            target.getAccounts().forEach(account -> account.setCustomer(target));
    }

}
