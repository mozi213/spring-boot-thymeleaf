FROM java:8-jre
MAINTAINER mohaijiang "haijiang.mo@newtouch.cn"
MAINTAINER version "online"
USER root
WORKDIR /home/usr/code
ADD ./target/ldap-modify-0.1.0.jar /home/usr/code/ldap-modify-0.1.0.jar
EXPOSE 7000
