#!/bin/sh
SH_DIR=$(cd `dirname $0`;pwd)
java_home=env|grep "JAVA_HOME";
latestjar=`ls $SH_DIR/.. -lt | grep '.jar'| awk '{print $9}' | sed -n '1p'`
echo 'start:'$latestjar
process_exists=`ps -ef|grep $latestjar|grep -v grep|awk '{print $2}'`
if [ -n "${process_exists}" ];then
	kill -9 ${process_exists}
fi
if [ ! -n "$java_home" ]; then
        cd $SH_DIR/../;
        nohup java -jar $SH_DIR/../$latestjar > /dev/null 2>&1 &
	echo " application is starting......";
        cd -;
        exit;
else
	java -version;
        cd $SH_DIR/../;
	if [ $? -eq 0]; then
		nohup java -jar $SH_DIR/../$latestjar > /dev/null 2>&1 &
	else	
		echo "this system has no jdk";
                cd -;
		exit;
	fi
fi
