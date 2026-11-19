# Museums SOAP CRUD Service

A contract-first SOAP web service built with **Spring Boot**, **Java 25**, **Spring Web Services**, and **Spring Data JPA** to perform CRUD operations on a `museums` table.

## Tech Stack

- **Java:** 25
- **Framework:** Spring Boot (`spring-boot-starter-web-services`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`)
- **SOAP & Contract:** Spring WS, `wsdl4j`, Jakarta XML Binding (JAXB), XSD (`museum.xsd`)
- **Build Tool:** Maven

## Project Structure

- `src/main/resources/museums.xsd` — XML schema defining CRUD request/response payloads and `museumInfo`.
- `src/main/java/.../config/WebServiceConfig.java` — Registers `MessageDispatcherServlet` (`/ws/*`) and exposes the WSDL at `/ws/museums.wsdl`.
- `src/main/java/.../entity/Museum.java` — JPA entity mapped to the `museums` table.
- `src/main/java/.../repository/MuseumRepository.java` — Spring Data JPA repository for `Museum`.
- `src/main/java/.../service/MuseumService.java` — Business logic and mapping between JPA entities and JAXB types.
- `src/main/java/.../endpoint/MuseumEndpoint.java` — SOAP endpoint handling `@PayloadRoot` requests.

## Running the Application

1. Configure your database connection in `src/main/resources/application.yml` (or `application.properties`).
2. Generate JAXB sources and compile the project:
   ```bash
   ./mvnw clean generate-sources test-compile
3. Run the application:
   ```Bash
    ./mvnw spring-boot:run
4. Inspect the generated WSDL at: http://localhost:8080/ws/museums.wsdl

## SOAP Operations
Send POST requests to http://localhost:8080/ws with Content-Type: text/xml; charset=utf-8 using namespace http://jhon0206.dev/museums/soap:

Create: `CreateMuseumRequest` -> `CreateMuseumResponse`

Read by ID: `GetMuseumRequest` -> `GetMuseumResponse`

Read all: `ListMuseumsRequest` -> `ListMuseumsResponse`

Update: `UpdateMuseumRequest` -> `UpdateMuseumResponse`

Delete: `DeleteMuseumRequest` -> `DeleteMuseumResponse`

## Example Request (`CreateMuseumRequest`)
```XML
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:soap="http://jhon0206.dev/museums/soap">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:CreateMuseumRequest>
         <soap:name>Museo Larco</soap:name>
         <soap:city>Lima</soap:city>
         <soap:address>Av. Simón Bolívar 1515, Pueblo Libre</soap:address>
         <soap:openingHours>Lun-Dom 09:00 - 20:00</soap:openingHours>
         <soap:admissionPrice>40.00</soap:admissionPrice>
      </soap:CreateMuseumRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

## Example Response (`CreateMuseumRequest`)
```XML
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:CreateMuseumResponse xmlns:ns2="http://jhon0206.dev/museums/soap">
            <ns2:museum>
                <ns2:id>1</ns2:id>
                <ns2:name>Museo Larco</ns2:name>
                <ns2:city>Lima</ns2:city>
                <ns2:address>Av. Simón Bolívar 1515, Pueblo Libre</ns2:address>
                <ns2:openingHours>Lun-Dom 09:00 - 20:00</ns2:openingHours>
                <ns2:admissionPrice>40.00</ns2:admissionPrice>
            </ns2:museum>
        </ns2:CreateMuseumResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
