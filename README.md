# Installation

 - Prerequisite - Java 11(or higher)
 - Start Tick Service:
	 - `cd tick-service`
	 - `./gradlew clean build`
	 - `java -jar build/libs/tick-service-0.0.1-SNAPSHOT.jar`
- Start Export Service:
	- `cd export-service`
	- `./gradlew clean build`
	- `java -jar build/libs/export-service-0.0.1-SNAPSHOT.jar`
- Tick service endpoint: http://localhost:8080
- Export service endpoint: http://localhost:8081

 

# Tick Service
The service exposes two apis:

 - **Consume Ticks**:  POST - /ticks/consume
 
   eg: `curl --location --request POST 'http://localhost:8080/ticks/consume' \
--header 'Content-Type: application/json' \
--data-raw '[
    "TIMESTAMP=1614398881|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=AAPL.OQ",
    "TIMESTAMP=1614398885|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=IBM.N",
    "TIMESTAMP=1614398887|PRICE=|CLOSE_PRICE=7.5|CURRENCY=EUR|RIC=AAPL.OQ"
]'`
- **List Ticks for RIC**:  GET - /ticks?ric={name}
	eg: `curl --location --request GET 'http://localhost:8080/ticks?ric=AAPL.OQ'`

# Export Service
The service internally polls the Tick Service and downloads the files as CSVs.  It exposes an endpoint to download the CSVs for a given RIC.

 - **Export Endpoint**:   http://localhost:8081/csv-export?ric=AAPL.OQ
