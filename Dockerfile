FROM maven:3-amazoncorretto-17
COPY  . /root/app/
WORKDIR /root/app
ARG PROTOC_ZIP=protoc-21.5-linux-x86_64.zip
RUN yum -y install unzip &&\
    curl -OL https://github.com/protocolbuffers/protobuf/releases/download/v21.5/${PROTOC_ZIP} &&\
    unzip -o ${PROTOC_ZIP} -d /usr/local bin/protoc &&\
    unzip -o ${PROTOC_ZIP} -d /usr/local 'include/*' &&\
    rm ${PROTOC_ZIP}
RUN mvn compile && mvn schema-registry:register; exit 0
ENTRYPOINT mvn -Dschema-registry-url=${SCHEMA_REGISTRY_URL} -o schema-registry:register && mvn exec:java