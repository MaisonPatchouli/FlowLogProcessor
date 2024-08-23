package com.aaronyu.awsflowlogprocessor;

import com.aaronyu.awsflowlogprocessor.model.FlowLogEntry;
import com.aaronyu.awsflowlogprocessor.service.PortProtocolCounter;
import com.aaronyu.awsflowlogprocessor.service.TagCounter;
import com.aaronyu.awsflowlogprocessor.service.TagLookupTable;
import com.aaronyu.awsflowlogprocessor.util.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FlowLogProcessor {
    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler();

            // Load the flow logs and lookup table
            List<FlowLogEntry> flowLogs = fileHandler.readFlowLogFile(Config.FLOW_LOG_FILE_PATH);
            TagLookupTable tagLookupTable = fileHandler.readLookupTableFile(Config.LOOKUP_TABLE_FILE_PATH);

            // Count tags and port/protocol combinations
            TagCounter tagCounter = new TagCounter(tagLookupTable);
            PortProtocolCounter portProtocolCounter = new PortProtocolCounter();

            for (FlowLogEntry logEntry : flowLogs) {
                tagCounter.countTag(logEntry);
                portProtocolCounter.countPortProtocol(logEntry);
            }

            // Ensure output directories exist and write the output reports
            File tagCountReportDir = new File(Config.TAG_COUNT_REPORT_FILE_PATH).getParentFile();
            if (!tagCountReportDir.exists()) {
                tagCountReportDir.mkdirs();
            }
            fileHandler.writeTagCountReport(tagCounter.getTagCounts(), Config.TAG_COUNT_REPORT_FILE_PATH);

            File portProtocolCountReportDir = new File(Config.PORT_PROTOCOL_COUNT_REPORT_FILE_PATH).getParentFile();
            if (!portProtocolCountReportDir.exists()) {
                portProtocolCountReportDir.mkdirs();
            }
            fileHandler.writePortProtocolCountReport(portProtocolCounter.getPortProtocolCounts(), Config.PORT_PROTOCOL_COUNT_REPORT_FILE_PATH);

            System.out.println("Reports generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}