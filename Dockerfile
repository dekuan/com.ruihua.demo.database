FROM openjdk:8-jdk-alpine

#	change timezone
RUN apk add tzdata
#ENV TZ="Asia/Shanghai"
RUN echo "Asia/Shanghai" > /etc/timezone
#RUN dpkg-reconfigure -f noninteractive tzdata


# 对应pom.xml文件中的dockerfile-maven-plugin插件 buildArgs 配置项JAR_FILE的值
ARG JAR_FILE

# 复制打包完成后的jar文件到/opt目录下
COPY target/demo.database-1.0.jar /app.jar
COPY deploy/agent /agent/

# 启动容器时执行
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/agent/skywalking-agent.jar", "-jar", "/app.jar", "--spring.config.location=classpath:/application.yml,/etc/com.ruihua.demo.database/" ]

# 使用端口
EXPOSE 1910

