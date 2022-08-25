FROM openjdk:17
EXPOSE 8080
RUN  mkdir -p /onurcan
WORKDIR /onurcan
COPY target/whatsapp-1.0.jar ./whatsapp-1.0.jar
ENTRYPOINT ["java","-jar","/onurcan/whatsapp.jar"]