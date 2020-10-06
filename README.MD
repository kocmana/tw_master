# Master Project - Graph QL as Integration Layer
## Problem description
REST interfaces have been the de facto standard of communication between distributed applications in the past decade.
Services exposing APIs that are being consumed by quickly changing applications, such as mobile applications or SPAs however are often facing two distinct challenges that are hard to solve using classic REST interfaces:
* **Fast Changes in Requirements:** Due to new requirements over time, consuming applications frequently require changed or extended representations of requested entities. Since changes in the response of an API would either result in additional fields or breaking existing API contracts, this frequently results in a rapidly growing number of API versions or oversharing/overfetching caused by repeated extensions of the API responses.
* **Heterogeneous Consumer Requirements:** APIs that are being consumed by several subgroups of applications additionally often must deal with a shift towards more and more specialised requirements of its consumers, leading to more heterogeneous requirements in terms of the representation of data that is being requested.

As an open source API query language GraphQL aims to address these specific shortcomings of REST by providing a consumer driven approach to API design, making it also an ideal candidate for an integration layer in distributed architectures.

While GraphQL in fact describes integration of multiple existing systems as one of its core use cases, most commonly described examples only focus on aspects relating to hiding complexity and unifying access across heterogeneous legacy systems by providing one single interface. 
However, using GraphQL as an integration layer gives rise to a new set of potential challenges, such as traceability of requests, management of latency in cross cutting requests and managing authentication and authorization across diverse downstream services.
 
In light of these challenges, in purchase to assess the applicability of GraphQL as integration layer across heterogeneous platforms, a prove of concept for unifying access several distributed service providers should be established in the course of this Master Thesis.

The main research topics should be:
* Management of potential latencies in requests across several platforms
* Access and credential management for downstream services
* Tracing and logging of requests

##  Expected Results
Results should focus around these three core aspects:
* A proof of concept of implementing GraphQL in a heterogeneous distributed architecture
* Insights into potential issues and shortcomings of GraphQL when used as integration layer as well as potential solutions to those issues
* Recommendations for use cases where GraphQL should and should not be applied as integration layer

## Stack
### Main Technologies Used
* Java 11 as latest LTS Version
* Spring Boot 2.3.3
* Graph QL ([via GraphQL Java Kickstart Spring Boot](https://www.graphql-java-kickstart.com/spring-boot/))
* Maven 

### Additional Technologies/Frameworks/Libraries
* [Lombok](https://projectlombok.org/) to reduce boilerplate code
* [Springfox](https://springfox.github.io/springfox/) for endpoint documentation
* [Mapstruct](https://mapstruct.org/) for DTO mappings

## Mock Service Overview
| Service | Interface | Authentication | Request ID for Logging | Latency | Other Challenges |
|---------|-----------|----------------|-----------------|---------|------------------|
| Customer Service | REST | API Key | Request Epoch as Request ID | constant, low | none |
| Product Service | REST | Basic Auth | UUID | normally distributed, medium to high, dependent on endpoint | Pagination in API response |
| ECommerce Service | REST | OAuth | UID | Spikes of unpredictable high latency (i.e. service warm up behavior) | none |
| OAuth 2 Authentication Server | REST | OAuth 2 | none | none | none |

### Service Description

#### Customer Service
Provides two distinct services:
* **Customer Information:** Allowing CRUD operations focussed on personal data of customers
* **Customer Network:** Storing and querying of relationships between customers

#### Product Service
Product Service also provides services on two distinct domains:
* **Product Information:** Provides REST APIs for retrieval, editing and creation of product information.
* **Product Reviews:** Exposes endpoints for basic functionality to review products.

#### eCommerce Service
Again, eCommerce Service provides services for two interacting domains:
* **Price Information:** Information on current and historic pricings can be managed.
* **Purchase Information:** Past purchases can be retrieved and new purchases can be made.

### Simulation Support
In order to support simulation, two additional functionalities are provided:

#### Overall Service Delay and API Specific Delay
Services can be configured in the respective application properties to incorporate "lag" based on three distinct approaches:
* **Fixed Delay:** Calls to all endpoints exposed by this service exhibit a fixed delay.
* **Normally Distributed Random Delay:** Calls to all endpoints exposed by this service exhibit a random delay following a gaussian distribution with configurable mean and standard deviation.
* **Probabilistic Delay:** Calls to all endpoints to this service either exhibit no or a fixed amount of delay, based on a probability that can be set. This is intended to simulate wake up behavior or less reliable downstream services.

Similar to the overall delay approaches, all endpoints can be used to simulate similar delays by adding annotation to the respective controller functions:
* **Fixed Delay**: `@FixedDelay` 
* **Normally Distributed Random Delay**: `@NormallyDistributedDelay` 
* **Probabilistic Delay:** `@ProbabilisticDelay`
These endpoint-specific delays can be used to simulate different service response times with respect to server loads and average query response times.

#### Probabilistic Failure
*TODO*

Based on controller or rest endpoint level, probabilities for failures (as represented by 4** and 5** status code responses) can be set.