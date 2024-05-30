package com.example.CyrsachJava.service;

import com.example.CyrsachJava.dto.TransactionDTO;
import com.example.CyrsachJava.mapper.TransactionMapper;
import com.example.CyrsachJava.model.Transaction;
import com.example.CyrsachJava.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    public TransactionDTO getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDto)
                .orElse(null);
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(savedTransaction);
    }

    public TransactionDTO updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElse(null);
        if (existingTransaction != null) {
            existingTransaction.setType(transactionDTO.getType());
            existingTransaction.setAmount(transactionDTO.getAmount());
            existingTransaction.setDate(transactionDTO.getDate());
            Transaction updatedTransaction = transactionRepository.save(existingTransaction);
            return transactionMapper.toDto(updatedTransaction);
        }
        return null;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}

