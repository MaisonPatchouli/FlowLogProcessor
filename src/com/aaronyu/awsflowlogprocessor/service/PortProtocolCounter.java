package com.aaronyu.awsflowlogprocessor.service;

import com.aaronyu.awsflowlogprocessor.model.FlowLogEntry;

import java.util.HashMap;
import java.util.Map;

public class PortProtocolCounter {
    private final Map<String, Integer> portProtocolCounts = new HashMap<>();

    public void countPortProtocol(FlowLogEntry logEntry) {
        String key = logEntry.dstPort() + "," + logEntry.protocol();
        portProtocolCounts.put(key, portProtocolCounts.getOrDefault(key, 0) + 1);
    }

    public Map<String, Integer> getPortProtocolCounts() {
        return portProtocolCounts;
    }
}