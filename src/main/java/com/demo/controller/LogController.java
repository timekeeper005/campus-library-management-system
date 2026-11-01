package com.demo.controller;

import com.demo.entity.OperationLog;
import com.demo.service.OperationLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {

    private final OperationLogService operationLogService;

    public LogController(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @GetMapping("/logs")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OperationLog> listLogs() {
        return operationLogService.listAll();
    }
}
