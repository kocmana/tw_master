# Evaluation of the Applicability of Consumer Driven API Design for Distributed Heterogeneous Architectures

## Problem description / Problemstellung
REST interfaces have been the de facto standard of communication between distributed applications in the past decade.
Services exposing APIs that are being consumed by quickly changing applications, such as mobile applications or SPAs however are often facing two distinct challenges that are hard to solve using classic REST interfaces:
* Fast Changes in Requirements: Due to new requirements over time, consuming applications frequently require changed or extended representations of requested entities. Since changes in the response of an API would either result in additional fields or breaking existing API contracts, this frequently results in a rapidly growing number of API versions or oversharing/overfetching caused by repeated extensions of the API responses.
* Heterogeneous Consumer Requirements: APIs that are being consumed by several subgroups of applications additionally often must deal with a shift towards more and more specialised requirements of its consumers, leading to more heterogeneous requirements in terms of the representation of data that is being requested.

As an open source API query language GraphQL aims to address these specific shortcomings of REST by providing a consumer driven approach to API design, making it also an ideal candidate for an integration layer in distributed architectures.

While GraphQL in fact describes integration of multiple existing systems as one of its core use cases, most commonly described examples only focus on aspects relating to hiding complexity and unifying access across heterogeneous legacy systems by providing one single interface. 
However, using GraphQL as an integration layer gives rise to a new set of potential challenges, such as traceability of requests, management of latency in cross cutting requests and managing authentication and authorization across diverse downstream services.
 
In light of these challenges, in order to assess the applicability of GraphQL as integration layer across heterogeneous platforms, a prove of concept for unifying access several distributed service providers should be established in the course of this Master Thesis.

The main research topics should be:
* Management of potential latencies in requests across several platforms
* Access and credential management for downstream services
* Tracing and logging of requests

##  Expected Results/ Geplante Ergebnisse
Results should focus around these three core aspects:
* A prove of concept of implementing GraphQL in a heterogenous distributed architecture
* Insights into potential issues and shortcomings of GraphQL when used as integration layer as well as potential solutions to those issues
* Recommendations for use cases where GraphQL should and should not be applied as integration layer

## Planned Methods / Wie soll die Fragestellung gelöst werden
The result should be yielded using two methodological approaches:
* Implementation of a working prove of concept
* Literature review on Graph QL as an integration layer

## Target Group / Zielgruppe
* System Architects facing the challenges described in the problem description
* Software Developers implementing GraphQL solutions