package com.microservices.loans.service;

import com.microservices.loans.constants.LoanConstants;
import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.entity.Loan;
import com.microservices.loans.exception.LoanAlreadyExistsException;
import com.microservices.loans.exception.ResourceNotFoundException;
import com.microservices.loans.mapper.LoanMapper;
import com.microservices.loans.repository.LoanRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private static final LoanMapper loanMapper = LoanMapper.INSTANCE;

    @Override
    public void createLoan(String mobileNumber) {
        if (loanRepository.findByMobileNumber(mobileNumber).isPresent())
            throw new LoanAlreadyExistsException("Loan already exists for mobile number: " + mobileNumber);

        final var loan = Loan.builder()
                .mobileNumber(mobileNumber)
                .loanNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000000L, 999999999999L)))
                .loanType(LoanConstants.HOME_LOAN)
                .totalLoan(LoanConstants.NEW_LOAN_LIMIT)
                .amountPaid(0)
                .outstandingAmount(LoanConstants.NEW_LOAN_LIMIT)
                .build();
        loanRepository.save(loan);
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        final var loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan not found for mobile number: " + mobileNumber));
        return loanMapper.toDto(loan);
    }


    @Override
    public boolean updateLoan(LoanDto dto) {
        final var loan = loanRepository.findByMobileNumber(dto.mobileNumber()).orElseThrow(() -> new ResourceNotFoundException("Loan not found for mobile number: " + dto.mobileNumber()));

        var hasChanges = false;
        if (!dto.loanNumber().equals(loan.getLoanNumber())) {
            loan.setLoanNumber(dto.loanNumber());
            hasChanges = true;
        }

        if (!dto.loanType().equals(loan.getLoanType())) {
            loan.setLoanType(dto.loanType());
            hasChanges = true;
        }

        if (!dto.totalLoan().equals(loan.getTotalLoan())) {
            loan.setTotalLoan(dto.totalLoan());
            hasChanges = true;
        }

        if (!dto.amountPaid().equals(loan.getAmountPaid())) {
            loan.setAmountPaid(dto.amountPaid());
            hasChanges = true;
        }

        if (!dto.outstandingAmount().equals(loan.getOutstandingAmount())) {
            loan.setOutstandingAmount(dto.outstandingAmount());
            hasChanges = true;
        }

        if (hasChanges) {
            loanRepository.save(loan);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        final var loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan not found for mobile number: " + mobileNumber));
        loanRepository.delete(loan);
        return true;
    }
}
