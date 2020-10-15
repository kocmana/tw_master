# Structure of the Master Thesis

## Introduction and Motivation

## Background
### Graph QL

#### Background and History

#### Query Syntax

#### Implementation Options
* GraphQL as a service
  * Apollo
  * Hasura
* Language specific Libraries and Frameworks
  * JS reference implementation
  * Implementations for other languages
  * Java Libraries and Frameworks
* Approach and framework used in the thesis

### Microservice Architecture

### GraphQL as Integration Layer

## Problem Definition

### Research Questions
* Management of the n+1 problem
* Management of potential latencies in requests across several platforms
* Tracing and logging of requests
* Access and credential management for downstream services

## Method

### Approach
1. Simulation
2. Real World Approach

### Description of Simulation
* Stack
* Service Description
* Description of additional Monolith Service
* Description of integration layer

## Results

### N+1 and Managing of Response Times

#### Problem definition
  * Definition of the N+1 problem
  * Effect on response times
  * SelctionSet Problem: In heterogeneous architectures, selection of fields is likely only limiting direct transmission to end devices, not load on backend services
  * N+1 Problem and Managing of Response Times are tightly connected, aggravated by heterogeneous architecture

#### Evaluation of the problem
  * *Simulation:* Ratio of Round Trip Times on total response time in microservice architectures
  * *Simulation:* Query response time differences for interdependent queries in MonolithService and Microservice

#### Problem solving approaches

##### Approaches on Reducing Response Time Based Effects
* Solution: Introduction of asynchronous resolvers
  * Description of the approach: Reduces ratio of RTTs to overall response time by parallel requests
  * *Simulation:* Effect of introducing asynchronous `GraphQLQueryResolver`s
  * (optional: *Simulation:* Additional effect of introducing asynchronous `GraphQLResolver<>`s
    * Number of Worker Threads and implications on hosting on virtual infrastructure)
* Solution: Introduction of reactive patterns
  * Description of the approach: Reactive programming reduces/eliminates blocking behaviour, thus similar approach to asynchronous resolver resolution
  * *Simulation:* Effect of Spring Webflux based Integration Service compared to Spring MVC based Integration Service

##### Approaches on Managing Response Numbers/N+1 Problem
* Solution: Batching and Merging
  * Description of approaches
  * Dataloader as best practice for batching
  * *Simulation:* Effects of introducing Dataloaders to intergration service

##### Approaches on Managing Complexity of Consumer Requests
* Background: Limited visibility of actual request complexity to consumers
* Solution: Introduction of maximum request recursion depth
  * Built in functionality of most GraphQL frameworks
* Solution: Introduction of maximum resolver time
  * All resolvers after a certain time limit will automatically fail by throwing an exception (using GraphQL Instrumentation), leading to a plannable max response time of `TimeLimit + Timeout`
  * This way at least some information will be retrieved for large queries before consuming clients close the connection due to a timeout 
  * Presentation of practical implementation approach
  * *Simulaton:* `?`

### Logging and Traceability

#### Problem Definition
* With multiple downstream services applying different means of logging and request IDs, ensuing tracing of requests and responses is non trivial

#### Problem Solving Approach: Mapping of Request IDs
* Approach: 1:n mapping of request tracing using SL4J MDC context
* Strength and Weaknesses of the approach:
  * Strength: 
    * One time implementation, automatic configuration for adding/editing services
    * In cloud services such as GCP Spring Cloud ensures structured logging of MDC context
  * Weakness: 
    * Solution relies on two distinct aspects (RestTemplate Interceptor, GraphQL Instrumentation) 
    * Approach uses MDC as "global variable" which is bad practice
    * *MDC works on thread level, so this may not work for multithreaded/asynch solutions?*

### Access and Credential Management for Downstream Services

#### Problem definition
* In a heterogeneous microservice architecture there is most likely no single point of truth for user rights and both authentication and authorization information
* How to ensure that a consumer of the integraton layer is actually allowed to access the resource he tries to access?

#### Problem Solving Approaches
* Each customer profile is either explicitly or implicitly linked to credentials and hence roles in downstream services

##### Implicit Credential Management: 
* Integration layer accesses all downstream services with elevated rights (superset of all user rights) and manages access based on own user role definition 
* "Virtual" single point of truth
* Non-deniability issues: Obfuscates which user actually accessed data from downstream services

##### Explicit Credential Management:
* 1:n mapping for all user profiles. For each user profile an associated user profile exists in downstream systems.
* Strengths:
  * No maintenance of access rights/authorization needed in integration service. Actual authorization is opaque for integration system.
  * Provides in depth security and non deniability
* Weakness: 
  * Likely not supported by all downstream services (limit to 1 or m<n user accounts or access potentially even limited to a one size fits all API key)
  * High overhead in credential management
  * Integration service is single point of storage for a lot of credentials, even if strongly encrypted

##### Hybrid Approach
* Do mappings where possible
* Do restrictions on each endpoint in addition to downstream restrictions, relay only on those where no downstream restrictions are possible
* Strengths:
  * The hybrid approach potentially provides the best possible solution in terms of security and logging with regards to limitations from downstream services
* Weakness:
  * Prone to mismanagement/disparities between role definitions in integration layer and downstream systems

###### Practical Implementation of Hybrid Approach
* Presentation of a suggested practical solution using the hybrid approach
  * Check authorization on resolver level (using Spring Method Security)
    * *Since this is AOP based and spring proxying is prone to error check if this works*
  * Mapping of credentials using extension of the regular spring security user database
  * For each user and downstream system, store username and credentials
  * If no user credentials are found, use globally managed credentials (via application properties)
  * Inject auth header into the downstream rest calls using RestTemplate Interceptors

### Real World Application

#### Description of Application to Requirements

##### N+1/Response Time issues

##### Downstream request logging

##### Credential Management

## Discussion