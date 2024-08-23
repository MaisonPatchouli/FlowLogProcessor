package com.aaronyu.awsflowlogprocessor.model;

import java.util.Map;

public record FlowLogEntry(
        String dstPort,
        String protocol,
        String action,
        String logData
) {
    // Factory method to parse a space-separated line of flow log into a FlowLogEntry
    public static FlowLogEntry fromLogLine(String logLine) {
        String[] parts = logLine.trim().split("\\s+"); // Split by any whitespace
        String dstPort = parts[5];  // Correct index for dstPort
        String protocol = normalizeProtocol(parts[7]); // protocol is at index 7
        String action = parts[12];  // action is at index 12
        return new FlowLogEntry(dstPort, protocol, action, logLine);
    }

    private static String normalizeProtocol(String protocol) {
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