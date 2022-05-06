# Simple microservices project

Based on tutorials from [Daily Code Buffer](https://www.youtube.com/channel/UC4VZwhJ4T42SVHy9QbZ5rKw), with some variations:
- bootsrap.yml file for cloud config are now deprecated, configuration is in application.yml
- Hystrix is no logner developed, resilience4j is used instead

Two services
- student
  - get students with grades
- grades
  - get grade for student

Eureka service registry

API Gateway

Cloud config server

resilience4j fallback

Running on Docker + K8S

Sleuth logging
