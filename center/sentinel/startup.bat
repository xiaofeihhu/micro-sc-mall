@echo off
TITLE sentinel
set JAVA_HOME=%JAVA_HOME%
set PATH=%path%
@echo %PATH%|findstr "%JAVA_HOME%"
if %errorlevel%==0 (
	goto start
) else (
	java -version
	if %errorlevel%==0 (
		goto start
	) else (	
		goto exit
	)
)

:start
java -Dserver.port=8849 -jar sentinel-dashboard-1.4.0.jar
@pause
:exit
@echo "this system has no jdk"
@pause
