# Message Processing Application
[![GitHub issues](https://img.shields.io/github/issues/asrinivas61/message-processor)](https://github.com/asrinivas61/message-processor/issues)
[![GitHub forks](https://img.shields.io/github/forks/asrinivas61/message-processor)](https://github.com/asrinivas61/message-processor/network)
[![GitHub stars](https://img.shields.io/github/stars/asrinivas61/message-processor)](https://github.com/asrinivas61/message-processor/stargazers)
[![GitHub license](https://img.shields.io/github/license/asrinivas61/message-processor)](https://github.com/asrinivas61/message-processor/blob/main/LICENSE)

A message processing application which accepts a predefined set of incoming messages in 3 standard formats and processes those messages according to their type.

### Assumptions & Capabilities
 1. This application was built in such a way that it can be configured for 2 main processing properties and by default their values are set as below.
    - LOG_COUNTER - 3
    - ADJUSTMENT_COUNTER - 5
 2. Made the application compatible to accept incoming messages from 3 different sources.
    - Command line input.
    - A plain text file containing messages as strings lines.
    - A JSON file with predefined message properties.
 3. Assumption was like the incoming messages must be in one of the below 3 predefined formats
    - Type 1 - `apple at 10p`
    - Type 2 - `20 apple at 30p`
    - Type 3 - `add 10p apple`

### Pre-Requisites 🔧
* Should be installed Java version 8 or higher in the machine.
* Pre installation of Intellij IDEA or any other Java IDE.

### Steps to run the application
 1. clone the application using the below link.
    ```java
    https://github.com/asrinivas61/message-processor.git
    ```
 2. Import project as existing Maven project in your IDE (Eclipse, IntelliJ, etc.)
 3. Locate the Main.main() method and run it using maven.
 4. To run the application processing a default/sample `text source file - input.txt` from resource folder, navigate to `MessageServiceTest.java` which is under test folder and 
    execute the method `processTxtFile` inside your IDE.
