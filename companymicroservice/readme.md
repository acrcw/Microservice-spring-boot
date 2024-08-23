# cmd to build the spring boot app with docker and paketo build packs
 ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=acrcw/companymicrosvc:latest"

# to run the docker compose file
 docker compose up -d

# postman collection for api gateway
https://joban4-2295.postman.co/workspace/joban-Workspace~07b447ae-c058-4e93-8619-48f9023330a1/collection/33691534-4a3aa1c8-9468-466a-b990-26683c59b14e?action=share&creator=33691534