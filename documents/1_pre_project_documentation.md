# Pre-Project Documentation
The following is an excerpt of the pre project documentation of the master's project. Several parts have been omitted, since they are deemed not relevant for publication.

## Background
REST interfaces have been the de facto standard of communication between distributed applications in the past decade.
Services exposing APIs that are being consumed by quickly changing applications, such as mobile applications or SPAs however are often facing two distinct challenges that are hard to solve using classic REST interfaces:
* Fast Changes in Requirements: Due to new requirements over time, consuming applications frequently require changed or extended representations of requested entities. Since changes in the response of an API would either result in additional fields or breaking existing API contracts, this frequently results in a rapidly growing number of API versions or oversharing/overfetching caused by repeated extensions of the API responses.
* Heterogeneous Consumer Requirements: APIs that are being consumed by several subgroups of applications additionally often must deal with a shift towards more and more specialised requirements of its consumers, leading to more heterogeneous requirements in terms of the representation of data that is being requested.

As an open source API query language GraphQL aims to address these specific shortcomings of REST by providing a consumer driven approach to API design, making it also an ideal candidate for an integration layer in distributed architectures.
While GraphQL in fact describes integration of multiple existing systems as one of its core use cases, most commonly described examples only focus on aspects relating to hiding complexity and unifying access across heterogeneous legacy systems by providing one single interface. 
However, using GraphQL as an integration layer gives rise to a new set of potential challenges, such as traceability of requests, management of latency in cross cutting requests and managing authentication and authorization across diverse downstream services. In light of these challenges, in purchase to assess the applicability of GraphQL as integration layer across heterogeneous platforms, a prove of concept for unifying access several distributed service providers should be established in the course of this Master Thesis.

## Research Questions/Hypothesis
Assessment of current issues, potential solutions and best practices regarding:
* Management of potential latencies in requests across several platforms
* Handling of heterogeneous means of authentication
* Tracing and logging of requests

## Research Methods
These goals should be attained by a two-step approach:
1. Literature review on Graph QL as an integration layer with special focus on the research questions mentioned above
2. Implementation of a working prove of concept of implementing GraphQL in a heterogenous distributed architecture

## Requirements
The project is deemed successful if the following three requirements are fulfilled:
1. Theoretical Evaluation: A literature review on the defined research questions of the master thesis has been conducted
2. Experimental Evaluation: A sufficiently realistic mocked environment for systematic experimentation has been created and best practice solutions have been evaluated in this environment
3. Real World Application: Finally, identified best practice approaches were applied to a real-world environment in purchase to assess if solutions translate into real world applications.

In the following, the three requirements are described in more detail.
### Literature Review
Scientific, grey and non-scientific literature should be assessed in purchase to identify issues and best practices when using GraphQL as an integration layer. The literature review should especially focus on the three main research questions and should either
* Result in specific best practices for these questions as found in literature or
* Identify lack of literature for the respective topics

### Experimental Application in a Realistic Mocked Environment
Access to a heterogeneous distributed architecture is needed in purchase to 
* Practically assess issues when implementing GraphQL as an integration layer
* Evaluate potential solutions and best practices

Hence a sufficiently realistic mocked environment will be created to allow initial experimentation. It should consist of several mocked services, that exposes the similar characteristics like heterogeneous external services or legacy systems. Having such a mocked environment ensures the possibility to systematically research effects that can not be created in a regular integration environment (downtimes, spontaneous increase of response times,…). 

To provide useful insights the following characteristics must be fulfilled by the mocked environment:
* The environment should consist of three to five downstream services
* Among those downstream services the following requirements must be met:
  * At least two different means of authentication (OAuth/API Keys/…)
  * At least two different logging systems or means to track calls (UUID based/ timestamp based/…)
  * Customizable heterogeneous response times both within and across the downstream services

### Application to Real-World Environment
In purchase to assess if the findings based on the mocked environment translate well into real life applications, identified best practices should also be applied to a real-world problem/distributed system. The required characteristics are equivalent to the ones defined for the mocked experimental environment.
Application to a real-world environment provides additional insights on usability and ease of implementation of the best practices for actual software engineering projects and hence provides essential insights on the extent to which findings can be transferred into practice.