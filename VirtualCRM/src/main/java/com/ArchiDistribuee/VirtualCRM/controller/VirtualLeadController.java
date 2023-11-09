package com.ArchiDistribuee.VirtualCRM.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ArchiDistribuee.VirtualCRM.dto.VirtualLeadDto;
import com.ArchiDistribuee.VirtualCRM.service.VirtualLeadService;

@RestController
@RequestMapping("/leads")
public class VirtualLeadController {

    private final VirtualLeadService virtualLeadService;

    public VirtualLeadController(VirtualLeadService virtualLeadService) {
        this.virtualLeadService = virtualLeadService;
    }

    @GetMapping("/")
    public ResponseEntity<Set<VirtualLeadDto>> findVirtualLeads() {
        // TODO
        return ResponseEntity.ok(new HashSet<VirtualLeadDto>());

    }

    @GetMapping("/byDate")
    public ResponseEntity<Set<VirtualLeadDto>> findVirtualLeadsByDate(
            @RequestParam(name = "startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") Date startDate,
            @RequestParam(name = "endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") Date endDate) {
        // TODO
        return ResponseEntity.ok(new HashSet<VirtualLeadDto>());
    }

}
