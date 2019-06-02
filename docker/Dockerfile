FROM openjdk:11.0.3-jre-slim

ENV WORKING_DIR="/usr/local/happy-fridge-1.0.jar" \
    JAVA_OPTS="-Xmx128m -XX:+UseG1GC"


COPY happy-fridge-1.0.jar  $WORKING_DIR
COPY email_user.html  "/tmp/"
COPY email_owner.html  "/tmp/"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar ${WORKING_DIR}" ]