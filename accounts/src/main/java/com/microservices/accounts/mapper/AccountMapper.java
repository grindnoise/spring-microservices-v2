package com.microservices.accounts.mapper;

import com.microservices.accounts.dto.AccountDto;
import com.microservices.accounts.entity.Account;
import com.microservices.accounts.entity.BaseEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Named("toAccountDto")
    AccountDto toDto(Account account);

    @Named("fromAccountDto")
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Account fromDto(AccountDto accountDto);
    
    @AfterMapping
    default void afterMapping(@MappingTarget Account target) {
//        if (target.getCreatedAt() == null) {
//            target.setCreatedAt(java.time.LocalDateTime.now());
//        }
        target.setUpdatedAt(java.time.LocalDateTime.now());
//        target.setCreatedBy("system");
    }


}
