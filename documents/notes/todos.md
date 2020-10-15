# TODOs
* add spring security regression tests to fake services
* ~~differentiate on domain object level rather than on functionality for at least one service~~ - done
* ~~create service overview in readme.md and more in detail as separate document~~ - done
* ~~create aspect for delaying responses~~ - done
* ~~extract cross cutting concerns in library module~~ - done
* ~~add product service uuid to header by default (via uuid interceptor)~~ - done
* extract validation exceptions in error handler.
* create annotation based random object creation
* switch to existsById queries where applicable, especially for getPricesForProductAndTimeframe in ecommerce service
* switch to JSR354 representation/Java Money/Currency API for price service
* ~~add global api delay on service level by adding some "LatencySimulatorInterceptor" annotated with the regular Delay 
annotations~~ - done
* ~~add probability based probabilisticFailure on API and endpoint level~~ - done
* Resolve base resolvers asynchronously by providing completable futures
* Add deletion mutation for product service
* Add deletion endpoints to other services
* Review current handling of PUT for data changes in integration service.
* Add exception handlers for integration system

# Issues
* Pagination on GET /products endpoint seems to not work correctly
* ~~Base package naming is different for ecommerce: ecommerce instead of ecommerceservice~~ - done
* Config based integration of monotlithic service requires a more streamlined integration based on the properties (API Key or Basic Auth should be decided at startup)