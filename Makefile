SHELL := /bin/bash

run:
	mvn spring-boot:run

install:
	mvn clean install

build:
	mvn clean compile