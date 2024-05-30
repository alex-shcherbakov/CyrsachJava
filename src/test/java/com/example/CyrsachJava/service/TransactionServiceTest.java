package com.example.CyrsachJava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.CyrsachJava.dto.TransactionDTO;
import com.example.CyrsachJava.mapper.TransactionMapper;
import com.example.CyrsachJava.model.Transaction;
import com.example.CyrsachJava.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;
    private TransactionDTO transactionDTO;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setType("EXPENSE");
        transaction.setAmount(100.0);
        transaction.setDate(new Date()); // Using java.util.Date

        transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setType("EXPENSE");
        transactionDTO.setAmount(100.0);
        transactionDTO.setDate(new Date()); // Using java.util.Date
    }

    @Test
    void testGetAllTransactions() {
        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(transaction));
        when(transactionMapper.toDto(transaction)).thenReturn(transactionDTO);

        List<TransactionDTO> result = transactionService.getAllTransactions();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getType()).isEqualTo("EXPENSE");
        verify(transactionRepository, times(1)).findAll();
        verify(transactionMapper, times(1)).toDto(transaction);
    }

    @Test
    void testGetTransactionById() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionMapper.toDto(transaction)).thenReturn(transactionDTO);

        TransactionDTO result = transactionService.getTransactionById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("EXPENSE");
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionMapper, times(1)).toDto(transaction);
    }

    @Test
    void testGetTransactionByIdNotFound() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        TransactionDTO result = transactionService.getTransactionById(1L);

        assertNull(result);
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionMapper, never()).toDto(any(Transaction.class));
    }

    @Test
    void testCreateTransaction() {
        when(transactionMapper.toEntity(transactionDTO)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.toDto(transaction)).thenReturn(transactionDTO);

        TransactionDTO result = transactionService.createTransaction(transactionDTO);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("EXPENSE");
        verify(transactionMapper, times(1)).toEntity(transactionDTO);
        verify(transactionRepository, times(1)).save(transaction);
        verify(transactionMapper, times(1)).toDto(transaction);
    }

    @Test
    void testUpdateTransaction() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.toDto(transaction)).thenReturn(transactionDTO);

        TransactionDTO result = transactionService.updateTransaction(1L, transactionDTO);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("EXPENSE");
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, times(1)).save(transaction);
        verify(transactionMapper, times(1)).toDto(transaction);
    }

    @Test
    void testUpdateTransactionNotFound() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        TransactionDTO result = transactionService.updateTransaction(1L, transactionDTO);

        assertNull(result);
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, never()).save(any(Transaction.class));
        verify(transactionMapper, never()).toDto(any(Transaction.class));
    }

    @Test
    void testDeleteTransaction() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionService.deleteTransaction(1L);

        verify(transactionRepository, times(1)).deleteById(1L);
    }
}

