package com.bankapp.app.controller;

import com.bankapp.app.dto.TransactionDTO;
import com.bankapp.app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/all")
    public List<TransactionDTO> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransactionDTO(@PathVariable("id") String id) {
        String uuidPattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        if (!id.matches(uuidPattern)) {
            throw new IllegalArgumentException("ID is not a valid UUID");
        }
        return transactionService.getTransactionDTO(id);
    }

}
