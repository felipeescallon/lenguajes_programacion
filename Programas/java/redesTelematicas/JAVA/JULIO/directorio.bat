cls
set J2EE_HOME=C:\j2sdkee1.3.1
set JAVA_HOME=C:\j2sdk1.4.1_01
rem CODEDIR donde tengo mi codigo
set CODEDIR=C:\julio
set classpath=.;%J2EE_HOME%\lib\j2ee.jar;%CODEDIR%\holaComponentes\holaMundoClient.jar
set path=.;%JAVA_HOME%\bin;%path%
j2ee -verbose
pause
pause