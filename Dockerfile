FROM tomcat:8-jre8

MAINTAINER qdh <bust_nakoruru@163.com>

COPY  ./target/mvc.war /usr/local/tomcat/webapps

