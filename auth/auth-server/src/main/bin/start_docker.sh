#!/bin/sh
docker rm config
docker run -itd--net=docker_bridge  --name auth -p 8020:8020 auth
