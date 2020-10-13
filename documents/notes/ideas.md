* Use downstream tracing with [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth#samples)
* ~~Generate alternative monolithic application combining all existing microservices to compare effect of distributed systems (with lag simulation) to monolithic backend architecture: *this could show that distributed architecutres aggravate shortcomings/potential issues of GraphQL*~~ - done
* Impact of reactive application design (using spring webflux)
  * generate single Webflux based REST downstream service, measure difference in performance
  * generate alternative Webflux based integration service, measure differences in performance for non blocking approach