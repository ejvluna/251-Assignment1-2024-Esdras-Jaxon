# Project Title: Java TextEditor

## Group Members
- **Name:** Esdras Luna
  - **ID:** 23015971
- **Name:** Jaxon Goss
  - **ID:** 22001841

## Instructions to Run the Program
This project is containerized using Docker for easy deployment and execution.

### Prerequisites
- Docker installed on your system

### Steps to Run
1. Clone the repository: e.g.: `git clone <url to repository>`
2. Navigate to the project directory: e.g.: `cd 251-Assignment1-2024-Esdras-Jaxon`
3. Build the Docker image: e.g.: `docker build -t java-texteditor .`
4. Run the Docker container: e.g.: `docker run -it --rm java-texteditor`

### Running Maven Commands
To run Maven commands within the Docker container:

1. Start an interactive shell in the container: e.g.: `docker run -it --rm java-texteditor /bin/bash`
2. Once inside the container, you can run Maven commands, e.g.: `mvn test`

### Dockerfile
The Dockerfile is included in the root directory of the project. It sets up the necessary environment to build and run the Java TextEditor application.

## Directory Structure
- `src/main/java`: Contains the main Java source files.
- `src/test/java`: Contains the test Java source files.
- `target`: Contains the compiled classes and packaged application.
- `resources`: Contains the `config.yaml` file.

## Significant GitHub Commits

### Esdras Luna:  
- `32ad00d`: Added the initial project files: TextEditor.java, pom.xml, config.yaml
- `1251fdd`: Added the main menu bar with required Menus.
- `90e4ec8`: Added the 'Open' menu item, and methods/dependencies to read/open .txt and .odt files.  
- `6a2ee96`: Added the 'Save' menu item and methods to save to a .txt file
- `be38b51`: Added a 'Print' menu item and class/methods to enable this function. 
- `c9ca284`: Added an 'Export to PDF' menu item and methods/dependency to enable this function. 
- `dde0d80`: Refactored the 'TextEditor' class into separate classes to improve SRP practice.
- `50f7c0a`: Added a 'About' menu item and class/methods to enable required functionality. 
- `e5c48da`: Added a 'Time/Date' menu item and class/methods to enable required functionality.
- `21bdf3b`: Added method to enable read/open .rtf files
- `fad5c73`: Added method/dependency to enable read/open source code files with syntax highlighting.
- `31d1117`: Added methods to enable SCPC (Select, Copy, Paste, Cut) functionality.
- `e515919`: Added class/methods/dependency to enable applying settings from 'config.yaml' at run-time

### Jaxon Goss:
- `1cd6a88`: Updated GUI to a more modern look using FlatLaf
- `879afbf`: Added PMD Maven Goal To Generate PMD Report
- `de4e85c`: Added Metrics Report .txt file Generated by MetricsReloaded

## GitHub Repository

https://github.com/ejvluna/251-Assignment1-2024-Esdras-Jaxon
