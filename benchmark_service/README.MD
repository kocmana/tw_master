# Benchmark Service

The Benchmark Service is a tool that allows measuring execution times of repeated GraphQL schema calls.
It is intended for internal response time measurements, error handling is limited.

## Prerequisites
* Java 11
* Maven

## Using Benchmark Service

### Configuration
The following settings need to be adjusted to the GraphQL Service in question:
```
service:
  url: localhost
  port: 8080
  endpoint: /graphql
  username: integrationservice_user
  password: integrationservice_user_password
```
The above snippet shows the settings for the `local` profile, intended for local assessment with the Integration 
Service which is part of this repository.

Currently, the application only supports basic auth.

### Running a benchmark

* Start the application, if using local profile, the application will use port 9000
* Once the application is up and running, perform a post request:
  * `schema` refers to a schema stored in the application resources
  * `numberOfCalls` indicates the amount of calls to be performed. Calls will be done in a serial manner.
```
curl --location --request POST 'localhost:9000/benchmark' \
--header 'Content-Type: application/json' \
--data-raw '{
    "schema": "/schemas/benchmark.graphqls",
    "numberOfCalls": 20
}'
```
* The response will provide a UUID that is needed to obtain the results:
```
HTTP/1.1 200 OK
...
Content-Type: application/json
{"id":"619c2b58-e661-4cda-b197-3b28fd7db5a8"}
```
* Once the benchmark are running, results can be obtained under
`http://localhost:9000/results` using two path parameters:
  * `uuid` (required): the uuid of the benchmark that was provided in the response of the post request
  * `bucketSize` (optional): the size of the buckets used to display the call response time distribution, defaults to 1000ms
  * Example: `http://localhost:9000/results?uuid=6f3c4c4f-be7b-4c40-987e-5ea65d04a5&bucketSize=100`
