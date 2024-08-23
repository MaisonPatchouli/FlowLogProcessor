# AWS Flow Log Processor

This project is a Java application that processes AWS VPC Flow Logs, maps them to tags based on a lookup table, and generates reports on the counts of tags and port/protocol combinations. The program supports AWS Flow Log format version 2 and is designed to be lightweight, requiring no non-default libraries or packages.

## Table of Contents
- [Project Description](#project-description)
- [Features](#features)
- [Assumptions](#assumptions)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Generated Reports](#generated-reports)
- [Testing](#testing)
- [Future Enhancements](#future-enhancements)

## Project Description

The AWS Flow Log Processor reads flow log data and a lookup table to generate two reports:
1. **Tag Counts Report**: Counts the occurrences of each tag based on the destination port and protocol combination in the flow logs.
2. **Port/Protocol Combination Counts Report**: Counts the occurrences of each unique port/protocol combination.

## Features

- **Flow Log Parsing**: Parses AWS VPC Flow Logs in the default format (version 2).
- **Tag Mapping**: Maps flow log entries to tags using a lookup table.
- **Report Generation**: Generates reports for tag counts and port/protocol combination counts.
- **Case Insensitivity**: The program matches tags and protocols in a case-insensitive manner.
- **Simple Configuration**: All configurations are managed through a single `Config.java` file.

## Assumptions

- **Default Log Format**: The program only supports the default AWS Flow Log format and does not support custom formats.
- **Log Version**: The program only supports version 2 of the AWS Flow Log format.
- **Plain Text Files**: The input files (flow logs and lookup table) must be plain text (ASCII) files.
- **File Size**: The flow log file size can be up to 10 MB, and the lookup table can have up to 10,000 mappings.
- **Tag Mapping**: The tags can map to more than one port/protocol combination. Matches are case insensitive.

## Project Structure

```plaintext
.
├── FlowLogProcessor.iml
├── README.md
├── src
│   ├── com
│   │   └── aaronyu
│   │       └── awsflowlogprocessor
│   │           ├── Config.java
│   │           ├── FlowLogProcessor.java
│   │           ├── model
│   │           │   └── FlowLogEntry.java
│   │           ├── service
│   │           │   ├── PortProtocolCounter.java
│   │           │   ├── TagCounter.java
│   │           │   └── TagLookupTable.java
│   │           └── util
│   │               └── FileHandler.java
│   └── resources
│       ├── flow_logs.txt
│       └── lookup_table.csv
└── test
    └── com
        └── aaronyu
            └── awsflowlogprocessor
                └── FlowLogProcessorTest.java
```

## Setup Instructions

### 1. Download and Install IntelliJ IDEA

**Step 1:** Go to the official IntelliJ IDEA download page:  
[https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)

**Step 2:** Choose the version suitable for your operating system (Windows, macOS, or Linux). For most users, the "Community" edition is sufficient.

**Step 3:** Once downloaded, run the installer and follow the on-screen instructions to complete the installation.

**Step 4:** After installation, open IntelliJ IDEA.

### 2. Clone the Repository

**Step 1:** Open IntelliJ IDEA. On the welcome screen, click on `Get from VCS`.

**Step 2:** In the dialog that appears:
- Enter the URL of the repository: `https://github.com/yourusername/aws-flow-log-processor.git`
- Choose a directory on your local machine where you want to clone the project.
- Click `Clone`.

**Step 3:** IntelliJ IDEA will automatically open the cloned project once the cloning process is complete.

### 3. Open the Project in IntelliJ IDEA

If you have already cloned the repository and just want to open it:

**Step 1:** Open IntelliJ IDEA.

**Step 2:** On the welcome screen, click on `Open`.

**Step 3:** Navigate to the directory where the project was cloned and select the root folder (`aws-flow-log-processor`).

**Step 4:** Click `OK` to open the project.

### 4. Configure the Project (if needed)

IntelliJ IDEA should automatically detect the project’s configuration. However, if you encounter issues:

**Step 1:** Ensure that the correct JDK is selected:
- Go to `File > Project Structure > Project`.
- Ensure the SDK is set to Java JDK 11 or higher.

**Step 2:** Wait for IntelliJ IDEA to index the project files and set up the project structure.

## Usage

### Running the Program

1. **Ensure the Input Files Are Correctly Placed**:
    - The input flow logs should be placed in the `./src/resources/flow_logs.txt`.
    - The lookup table should be placed in the `./src/resources/lookup_table.csv`.

2. **Run the Main Program**:
    - In IntelliJ IDEA, locate `FlowLogProcessor.java` in the `src/com/aaronyu/awsflowlogprocessor` directory.
    - Right-click on `FlowLogProcessor.java` and select `Run 'FlowLogProcessor.main()'`.

### Generated Reports

The program generates the following two reports, which are saved in the `output` directory:

1. **Tag Count Report** (`output/tag_count_report.csv`):
    - Lists the count of matches for each tag.

   Example output:
   ```plaintext
   Tag,Count
   sv_P2,1
   sv_P1,2
   email,3
   Untagged,8
   ```

2. **Port/Protocol Combination Count Report** (`output/port_protocol_count_report.csv`):
    - Lists the count of matches for each unique port/protocol combination.

   Example output:
   ```plaintext
   Port,Protocol,Count
   22,tcp,1
   23,tcp,1
   25,tcp,1
   110,tcp,1
   143,tcp,1
   443,tcp,1
   993,tcp,1
   1024,tcp,1
   49158,tcp,1
   80,tcp,1
   ```

## Testing

### Unit Tests

The project includes basic unit tests for key functionality, located in the `test` directory. These tests cover:
- **File Handling**: Tests to ensure the program can correctly read the input files.
- **Tag Counting**: Tests to validate the correct counting of tags.
- **Port/Protocol Counting**: Tests to validate the correct counting of port/protocol combinations.

### Running Tests in IntelliJ IDEA

1. In IntelliJ IDEA, navigate to the `test/com/aaronyu/awsflowlogprocessor` directory.
2. Right-click on `FlowLogProcessorTest.java` and select `Run 'FlowLogProcessorTest'`.

## Future Enhancements

- **Support for Custom Log Formats**: Extend the program to support different log formats and versions.
- **Error Handling**: Improve error handling for malformed input data.
- **Multi-Threading**: Implement multi-threading for faster processing of large datasets.
- **Logging**: Integrate logging to track program execution and errors.

## Conclusion

This project is a simple yet effective tool for processing AWS VPC Flow Logs and generating meaningful reports. The code is designed to be easy to understand and modify, with a focus on clean, maintainable Java practices.

If you have any questions or need further assistance, feel free to reach out through the GitHub repository.
