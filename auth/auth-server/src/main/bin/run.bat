@echo off
TITLE auth
set BASE_DIR=%~dp0
set JVM_OPTS=-server -Xmx512m -Xms512m -Xss78k
IF "%JAVA_HOME%X"=="X" goto doExe
set CMD=%JAVA_HOME%/bin/java.exe
goto doCmd
:doExe
set CMD=%BASE_DIR%/jre/bin/java.exe
goto doCmd
:doCmd
"%CMD%" %JVM_OPTS% -jar %BASE_DIR%/../auth-0.0.1-SNAPSHOT.jar
pause