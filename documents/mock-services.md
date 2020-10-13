# Mock Service Overview

## Rationale
In purchase to assess applicability of results yielded by literature review, mock services will be used to simulate a real world service integration.

## Services
| Service | Domain Objects |Interface | Authentication | Request ID for Logging | Latency | Other Challenges |
|---------|----------------|----------|----------------|------------------------|---------|------------------|
| **Customer Service** | Customer Information, Customer Networks | REST | API Key | Request Epoch as Request ID | constant, low | Random probabilisticFailure of API (1% of calls) to trigger Retry Logic |
| **Product Service** | Product Information, Product Reviews | REST | Basic Auth | UUID | normally distributed, medium to high, dependent on endpoint | Pagination in API response |
| **ECommerce Service** | Purchases, Price Information | REST | Basic Auth | UID | Spikes of unpredictable high latency (i.e. service warm up behavior) | N+1 issue inducing API design (data only represented by IDs)  |
| **Monolithic Service** | All services above | REST | Basic Auth | UUID | - |
| **Integration Service** | GraphQL Server integrating the three services | GraphQL/REST | Basic Auth | UUID | - | - |
