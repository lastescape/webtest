FROM tomcat:8-jre8

MAINTAINER qdh <bust_nakoruru@163.com>

COPY  ./lib/mvc.war /usr/local/tomcat/webapps

