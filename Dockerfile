FROM openjdk:11
EXPOSE 8080
ADD target/jenkins-docker-kubernetes-gke.jar jenkins-docker-kubernetes-gke.jar
ENTRYPOINT ["java","-jar","/jenkins-docker-kubernetes-gke.jar"]