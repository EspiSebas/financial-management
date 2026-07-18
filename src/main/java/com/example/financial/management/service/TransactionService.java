package com.example.financial.management.service;

import com.example.financial.management.dto.transaction.TransactionRequest;
import com.example.financial.management.dto.transaction.TransactionResponse;
import com.example.financial.management.entity.Category;
import com.example.financial.management.entity.Transaction;
import com.example.financial.management.enums.TransactionType;
import com.example.financial.management.repository.CategoryRepository;
import com.example.financial.management.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createTransaction(
            String description,
            BigDecimal amount,
            LocalDate date,
            TransactionType type,
            Long categoryId
    ) {
        Transaction transaction = new Transaction();

        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setType(type);
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(()-> new RuntimeException("Category not found"));

        transaction.setCategory(category);

        transactionRepository.save(transaction);
    }


    public void deleteTransaction(Long id){
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Transaction not found"));

        transactionRepository.delete(t);
    }


    public List<TransactionResponse> getAllTransaction(){
        return transactionRepository.findAll()
                .stream()
                .map(t -> new TransactionResponse(
                    t.getId(),
                    t.getDescription(),
                    t.getAmount(),
                    t.getDate(),
                    t.getType(),
                    t.getCategory().getId()
                ))
                .toList();
    }


    public TransactionResponse updateTransaction(Long id, TransactionRequest request) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setCategory(category);

        transactionRepository.save(transaction);
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getCategory().getId()
        );
    }
}
