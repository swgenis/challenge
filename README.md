# challenge
A fun code challenge.

## Description
A production ready, maintainable, testable command-line application that
will calculate the ranking table for a league.

## Prerequisites
- Java 17 or higher
- Maven 3.9.9

## Building the Application

To build the application, run:

```sh
mvn clean install
```

## Running the Application

Navigate to the target folder and execute the following command:

```sh
java -jar challenge.jar ..\championship.txt
```

where `championship.txt` is the input file.

alternative use the following line to execute the provided example.

```sh
java -jar challenge.jar ..\src\test\resources\example.txt
```

## Input File Format
Lions 3, Snakes 3<br>
Tarantulas 1, FC Awesome 0<br>
Lions 1, FC Awesome 1<br>
Tarantulas 3, Snakes 1<br>
Lions 4, Grouches 0<br>
