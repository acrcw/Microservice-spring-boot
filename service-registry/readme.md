# cmd to build the spring boot app with docker and paketo build packs
 ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=acrcw/eurekaserver:latest"