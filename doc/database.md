#   database

#
#   create database
#
CREATE DATABASE ruihua;
CREATE USER 'ruihua'@'%' IDENTIFIED BY 'ruihua';
GRANT ALL PRIVILEGES ON ruihua.* TO 'ruihua'@'%';
FLUSH PRIVILEGES;
