package com.aaronyu.awsflowlogprocessor;

import com.aaronyu.awsflowlogprocessor.model.FlowLogEntry;
import com.aaronyu.awsflowlogprocessor.service.TagLookupTable;
import com.aaronyu.awsflowlogprocessor.util.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private FileHandler fileHandler;
    private final String flowLogFilePath = "src/resources/flow_logs.txt";
    private final String lookupTableFilePath = "src/resources/lookup_table.csv";

    @BeforeEach
    void setUp() {
        fileHandler = new FileHandler();
    }

    @Test
    void testReadFlowLogFile() throws Exception {
        List<FlowLogEntry> flowLogs = fileHandler.readFlowLogFile(flowLogFilePath);
        assertNotNull(flowLogs, "Flow logs should not be null");
        assertFalse(flowLogs.isEmpty(), "Flow logs should not be empty");

        // Check the first entry based on the actual log data
        FlowLogEntry firstEntry = flowLogs.get(0);
        assertEquals("443", firstEntry.dstPort(), "First entry destination port should be 443");
        assertEquals("tcp", firstEntry.protocol(), "First entry protocol should be 'tcp'");
    }

    @Test
    void testReadLookupTableFile() throws Exception {
        TagLookupTable lookupTable = fileHandler.readLookupTableFile(lookupTableFilePath);
        assertNotNull(lookupTable, "Lookup table should not be null");

        // Check the actual mapping in your lookup table
        String tag = lookupTable.getTag("443", "tcp");
        assertEquals("sv_P2", tag, "Tag for 443/tcp should be 'sv_P2'");
    }

    @Test
    void testNonExistentFiles() {
        assertThrows(Exception.class, () -> fileHandler.readFlowLogFile("nonexistentfile.txt"),
                "Should throw an exception when trying to read a non-existent flow log file");

        assertThrows(Exception.class, () -> fileHandler.readLookupTableFile("nonexistentfile.csv"),
                "Should throw an exception when trying to read a non-existent lookup table file");
    }
}