package com.aaronyu.awsflowlogprocessor;

public class Config {
    // File paths for the flow log and lookup table files
    public static final String FLOW_LOG_FILE_PATH = "./src/resources/flow_logs.txt";
    public static final String LOOKUP_TABLE_FILE_PATH = "./src/resources/lookup_table.csv";

    // File paths for the output reports in the output directory
    public static final String TAG_COUNT_REPORT_FILE_PATH = "output/tag_count_report.csv";
    public static final String PORT_PROTOCOL_COUNT_REPORT_FILE_PATH = "output/port_protocol_count_report.csv";

    // Ensure the output directory exists
    static {
        // Create output directory if it doesn't exist
        java.io.File outputDir = new java.io.File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }
}