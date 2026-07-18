package com.example.financial.management.controller;

import com.example.financial.management.dto.transaction.TransactionRequest;
import com.example.financial.management.dto.transaction.TransactionResponse;
import com.example.financial.management.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequest r){
        transactionService.createTransaction(r.getDescription(),r.getAmount(),r.getDate(),r.getType(),r.getCategoryId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/transactions")
    public List<TransactionResponse> getAllTransaction(){
        return transactionService.getAllTransaction();
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        transactionService.deleteTransaction(id);

        return ResponseEntity.ok("Deleted correctly");
    }

    @PatchMapping("/transaction/{id}")
    public ResponseEntity<TransactionResponse> update(
            @PathVariable Long id,
            @RequestBody TransactionRequest request
    ) {
        TransactionResponse updated = transactionService.updateTransaction(id, request);

        return ResponseEntity.ok(updated);
    }


}
