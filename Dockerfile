ADD file:f278386b0cef68136129f5f58c52445590a417b624d62bca158d4dc926c340df in / 
CMD ["/bin/sh"]
/bin/sh -c apk add --no-cache
ENV JAVA_HOME=/opt/openjdk-17
ENV PATH=/opt/openjdk-17/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
ENV JAVA_VERSION=17-ea+14
/bin/sh -c set -eux; arch="$(apk
CMD ["jshell"]
EXPOSE map[8081/tcp:{}]
ADD target/*.jar app.jar # buildkit
ENTRYPOINT ["java" "-jar" "/app.jar"]