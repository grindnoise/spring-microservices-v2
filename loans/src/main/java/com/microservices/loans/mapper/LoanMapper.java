package com.microservices.loans.mapper;

import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.entity.Loan;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    Loan fromDto(LoanDto loanDto);

    LoanDto toDto(Loan loan);
}
