package com.aaronyu.awsflowlogprocessor.util;

import com.aaronyu.awsflowlogprocessor.model.FlowLogEntry;
import com.aaronyu.awsflowlogprocessor.service.TagLookupTable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileHandler {

    public List<FlowLogEntry> readFlowLogFile(String filePath) throws IOException {
        List<FlowLogEntry> flowLogs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                flowLogs.add(FlowLogEntry.fromLogLine(line));
            }
        }
        return flowLogs;
    }

    public TagLookupTable readLookupTableFile(String filePath) throws IOException {
        TagLookupTable lookupTable = new TagLookupTable();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String dstPort = parts[0];
                String protocol = parts[1];
                String tag = parts[2];
                lookupTable.addMapping(dstPort, protocol, tag);
            }
        }
        return lookupTable;
    }

    public void writeTagCountReport(Map<String, Integer> tagCounts, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Tag,Count\n");
            for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }

    public void writePortProtocolCountReport(Map<String, Integer> portProtocolCounts, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Port,Protocol,Count\n");
            for (Map.Entry<String, Integer> entry : portProtocolCounts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }
}