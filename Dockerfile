FROM amazoncorretto:19
EXPOSE 8080 8080
ADD build/libs/SmartStaff-0.0.1-SNAPSHOT.jar SmartStaff-0.0.1-SNAPSHOT.jar
ADD entrypoint.sh entrypoint.sh
ENTRYPOINT ["sh","/entrypoint.sh"]