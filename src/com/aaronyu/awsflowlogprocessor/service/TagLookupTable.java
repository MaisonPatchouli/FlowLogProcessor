package com.aaronyu.awsflowlogprocessor.service;

import java.util.HashMap;
import java.util.Map;

public class TagLookupTable {
    private final Map<String, String> lookupTable = new HashMap<>();

    public void addMapping(String dstPort, String protocol, String tag) {
        String key = generateKey(dstPort, protocol);
        lookupTable.put(key.toLowerCase(), tag); // Store keys in lowercase
    }

    public String getTag(String dstPort, String protocol) {
        String normalizedProtocol = normalizeProtocol(protocol);
        String key = generateKey(dstPort, normalizedProtocol);
        return lookupTable.getOrDefault(key.toLowerCase(), "Untagged");
    }

    private String generateKey(String dstPort, String protocol) {
        return dstPort + ":" + protocol;
    }

    private String normalizeProtocol(String protocol) {
        // Convert numeric protocol identifiers to their textual equivalents
        switch (protocol) {
            case "6":
                return "tcp";
            case "17":
                return "udp";
            case "1":
                return "icmp";
            default:
                return protocol.toLowerCase(); // Default to lowercase for textual identifiers
        }
    }
}