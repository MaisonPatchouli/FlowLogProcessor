package com.aaronyu.awsflowlogprocessor.service;

import com.aaronyu.awsflowlogprocessor.model.FlowLogEntry;

import java.util.HashMap;
import java.util.Map;

public class TagCounter {
    private final TagLookupTable lookupTable;
    private final Map<String, Integer> tagCounts = new HashMap<>();

    public TagCounter(TagLookupTable lookupTable) {
        this.lookupTable = lookupTable;
    }

    public void countTag(FlowLogEntry logEntry) {
        String tag = lookupTable.getTag(logEntry.dstPort(), logEntry.protocol());
        tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
    }

    public Map<String, Integer> getTagCounts() {
        return tagCounts;
    }
}