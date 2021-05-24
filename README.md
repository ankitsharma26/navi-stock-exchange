# Stock Exchange Spring Boot with 100% Service Coverage Junit Jupiter Test Cases

StockExchange is a service for Navi.

Question:
https://docs.google.com/document/d/1Z1GO78QLVCNhY_Fw51xmQKHxIIiAbHWQsmGZKgQrl0M/edit

## Assumptions taken

The List of Buy and Sell Orders will have Ascending Time Path (No Duplicate Time)

## Run Application

Go to ./target RUN

```
java -jar stock-0.0.1-SNAPSHOT.jar ../file/test.txt
```

Output Image:


<img width="541" alt="Screenshot 2021-05-25 at 3 55 59 AM" src="https://user-images.githubusercontent.com/36882197/119414451-37017900-bd0d-11eb-9c12-ad79037d48e2.png">



## For Custom File

Go to ./target RUN

```
java -jar stock-0.0.1-SNAPSHOT.jar <file-path>
```

## MVN Build with Test Cases

GO TO ROOT REPOSITORY FOLDER RUN

```
mvn clean install
```
