#!/bin/sh
SH_DIR=$(cd `dirname $0`;pwd)
java_home=env|grep "JAVA_HOME";
latestjar=`ls $SH_DIR/.. -lt | grep '.jar'| awk '{print $9}' | sed -n '1p'`
echo 'stop:'$latestjar
process_exists=`ps -ef|grep $latestjar|grep -v grep|awk '{print $2}'`
if [ -n "${process_exists}" ];then
	kill -9 ${process_exists}
fi
echo "------application stop sucess---------"
