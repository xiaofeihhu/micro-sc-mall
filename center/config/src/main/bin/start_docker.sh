#!/bin/sh
docker rm config
docker run -itd --net=docker_bridge --name config -p 8100:8100 config
