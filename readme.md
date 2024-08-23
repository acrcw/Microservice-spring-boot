# Feature of this project
0) Made with spring boot
1) I has 3 microservices namely job , review, company
2) has eureka(netflix) discovery serving for microservice discovery by other microservices(No hardcoded microservice urls for inter service communication) 
3) has zipkin integrated for distributed tracing
4) has APi gateway as single entrypoint for all the microservices
5) has Rabbitmq for (message queues) so that microservice has function even when the other service is offline since the sender will not directly communicate with other microservice if it wants to send and update instead it will publish a message to rabbit mq server and the consumer can fetch it from rabbitmq message queue when it comes online
6) has resilence4j for handling cases(circuit breaking) when the other service may be down. 
7) each microservice(job,company,review) has swagger hub as its front end for proper documentation of that api
8) has mongo express to visualize the database(mongodb)

# limitation
by default all three microservices(job,company,review) are using the same database. If u want each ms to use separate database then just create 3 diffrent copies of mongo-db service in docker compose file each with different port and the provide the url of each mongodb instance to microservice as a environment variable for example:
  - SPRING_DATA_MONGODB_URI=mongodb://<username>:<password>@mongo-db:<port>

# to run the microservices
go inside each microservice and run the cmd mentioned in readme.md of each microservice
this will build a local docker image and then run the docker compose file and then you can use the postman collection to use the acces the api endpoints

# to run the docker compose file
 docker compose up -d

# postman collection for api gateway
https://joban4-2295.postman.co/workspace/joban-Workspace~07b447ae-c058-4e93-8619-48f9023330a1/collection/33691534-4a3aa1c8-9468-466a-b990-26683c59b14e?action=share&creator=33691534