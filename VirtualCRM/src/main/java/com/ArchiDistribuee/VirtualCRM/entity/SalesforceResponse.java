package com.ArchiDistribuee.VirtualCRM.entity;

import java.util.Set;

public record SalesforceResponse<T>(
        int totalSize,
        boolean done,
        Set<T> records) {
}
