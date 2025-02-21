@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  growstone-server startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GROWSTONE_SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Duser.timezone=UTC"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\growstone-server.jar;%APP_HOME%\lib\growstone-game-core-1.0-SNAPSHOT.jar;%APP_HOME%\lib\growstone-redis-1.0-SNAPSHOT.jar;%APP_HOME%\lib\growstone-database-1.0-SNAPSHOT.jar;%APP_HOME%\lib\growstone-resource-1.0-SNAPSHOT.jar;%APP_HOME%\lib\growstone-core-1.0-SNAPSHOT.jar;%APP_HOME%\lib\growstone-network-1.0-SNAPSHOT.jar;%APP_HOME%\lib\netty-all-4.1.87.Final.jar;%APP_HOME%\lib\redisson-3.20.0.jar;%APP_HOME%\lib\netty-resolver-dns-native-macos-4.1.87.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-resolver-dns-native-macos-4.1.87.Final-osx-aarch_64.jar;%APP_HOME%\lib\netty-resolver-dns-classes-macos-4.1.87.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.89.Final.jar;%APP_HOME%\lib\netty-handler-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-4.1.112.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.87.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.87.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.87.Final-linux-aarch_64.jar;%APP_HOME%\lib\netty-transport-native-kqueue-4.1.87.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-transport-native-kqueue-4.1.87.Final-osx-aarch_64.jar;%APP_HOME%\lib\netty-transport-classes-epoll-4.1.87.Final.jar;%APP_HOME%\lib\netty-transport-classes-kqueue-4.1.87.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-4.1.112.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.112.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.112.Final.jar;%APP_HOME%\lib\netty-common-4.1.112.Final.jar;%APP_HOME%\lib\protobuf-java-util-3.21.12.jar;%APP_HOME%\lib\mysql-connector-j-8.0.32.jar;%APP_HOME%\lib\protobuf-java-3.21.12.jar;%APP_HOME%\lib\logback-classic-1.4.11.jar;%APP_HOME%\lib\jedis-4.3.1.jar;%APP_HOME%\lib\HikariCP-5.0.1.jar;%APP_HOME%\lib\slf4j-api-2.0.7.jar;%APP_HOME%\lib\google-api-client-1.32.1.jar;%APP_HOME%\lib\google-oauth-client-1.32.1.jar;%APP_HOME%\lib\google-http-client-jackson2-1.41.5.jar;%APP_HOME%\lib\java-jwt-4.2.1.jar;%APP_HOME%\lib\jackson-annotations-2.15.1.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.15.1.jar;%APP_HOME%\lib\jackson-databind-2.15.1.jar;%APP_HOME%\lib\jackson-core-2.15.1.jar;%APP_HOME%\lib\jdom2-2.0.6.jar;%APP_HOME%\lib\google-http-client-gson-1.39.2.jar;%APP_HOME%\lib\google-http-client-apache-v2-1.39.2.jar;%APP_HOME%\lib\google-http-client-1.41.5.jar;%APP_HOME%\lib\opencensus-contrib-http-util-0.31.0.jar;%APP_HOME%\lib\guava-32.1.2-jre.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\mybatis-3.5.10.jar;%APP_HOME%\lib\netty-codec-haproxy-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-memcache-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-mqtt-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-redis-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-smtp-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-stomp-4.1.87.Final.jar;%APP_HOME%\lib\netty-codec-xml-4.1.87.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.87.Final.jar;%APP_HOME%\lib\netty-handler-ssl-ocsp-4.1.87.Final.jar;%APP_HOME%\lib\netty-transport-rxtx-4.1.87.Final.jar;%APP_HOME%\lib\netty-transport-sctp-4.1.87.Final.jar;%APP_HOME%\lib\netty-transport-udt-4.1.87.Final.jar;%APP_HOME%\lib\error_prone_annotations-2.18.0.jar;%APP_HOME%\lib\j2objc-annotations-2.8.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\gson-2.8.9.jar;%APP_HOME%\lib\logback-core-1.4.11.jar;%APP_HOME%\lib\httpclient-4.5.13.jar;%APP_HOME%\lib\httpcore-4.4.15.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-qual-3.33.0.jar;%APP_HOME%\lib\commons-pool2-2.11.1.jar;%APP_HOME%\lib\json-20220320.jar;%APP_HOME%\lib\cache-api-1.1.1.jar;%APP_HOME%\lib\reactor-core-3.5.3.jar;%APP_HOME%\lib\rxjava-3.1.6.jar;%APP_HOME%\lib\reactive-streams-1.0.4.jar;%APP_HOME%\lib\jboss-marshalling-river-2.0.11.Final.jar;%APP_HOME%\lib\jboss-marshalling-2.0.11.Final.jar;%APP_HOME%\lib\kryo-5.4.0.jar;%APP_HOME%\lib\byte-buddy-1.14.0.jar;%APP_HOME%\lib\snakeyaml-2.0.jar;%APP_HOME%\lib\jodd-bean-5.1.6.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\opencensus-api-0.31.0.jar;%APP_HOME%\lib\reflectasm-1.11.9.jar;%APP_HOME%\lib\objenesis-3.3.jar;%APP_HOME%\lib\minlog-1.3.1.jar;%APP_HOME%\lib\jodd-core-5.1.6.jar;%APP_HOME%\lib\grpc-context-1.27.2.jar


@rem Execute growstone-server
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GROWSTONE_SERVER_OPTS%  -classpath "%CLASSPATH%" org.supercat.growstone.Server %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable GROWSTONE_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%GROWSTONE_SERVER_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
