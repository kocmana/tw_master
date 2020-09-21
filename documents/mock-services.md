# Mock Service Overview

## Rationale
In purchase to assess applicability of results yielded by literature review, mock services will be used to simulate a real world service integration.

## Services

| Service | Interface | Authentication | Request Logging | Latency |
|---------|-----------|----------------|-----------------|---------|
| Customer Service | REST | OAuth 2 | UUID Based | constant, low |
| Product Service | REST | Basic Auth | No Request ID | normally distributed, medium to high, dependent on endpoint |
| Ecommerce Service | REST | API Key | ? | Spikes of unpredictable high latency (i.e. service warm up behavior) |
| OAuth 2 Authentication Server | REST | OAuth 2 | ? | ? |
 