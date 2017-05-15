@echo off
if not exist target\classes (
 mkdir target\classes
)

set test=%1%
if "%test%"=="test" (goto testcompile)

echo "COMPILE..."
javac -d target\classes -cp lib\*;src\main\java src\main\java\Main.java -encoding utf-8
echo "COMPILE SUCCESS"
echo "RUN..."
java -cp target\classes;lib\*; Main 
goto end

:testcompile
 echo "COMPILE TEST FILE..."
 javac -d target\classes -cp lib\*;src\test\java;src\main\java src\test\java\org\lbchild\util\Base64ContentTest.java -encoding utf-8
 java -cp target\classes;lib\*; org.junit.runner.JUnitCore org.lbchild.util.Base64ContentTest

:end