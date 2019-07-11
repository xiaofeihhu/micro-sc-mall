#!/bin/sh
docker run -itd --net=docker_bridge  --name eureka -p 8761:8761 eureka
