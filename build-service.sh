#!/bin/bash

export $(cat .env | xargs) && 
mvn clean package &&
mvn spring-boot:run