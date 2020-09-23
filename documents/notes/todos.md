# TODOs
* add spring security regression tests to fake services
* differentiate on domain object level rather than on functionality for at least one service - done
* create service overview in readme.md and more in detail as separate document - done
* create aspect for delaying responses - done
* extract cross cutting concerns in library module
* add product service uuid to header by default (via uuid interceptor) - done
* extract validation exceptions in error handler.
* create annotation based random object creation
* switch to existsById queries where applicable, especially for getPricesForProductAndTimeframe in ecommerce service
* switch to JSR354 representation/Java Money/Currency API for price service
* add global api delay on service level by adding some "LatencySimulatorInterceptor" annotated with the regular Delay 
annotations

# Issues
* Pagination on GET /products endpoint seems to not work correctly
* Autowiring differences for RequestLoggingInterceptor in ecommerce_service