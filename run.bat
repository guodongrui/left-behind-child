@echo off
mkdir target\classes
echo "COMPILE..."
javac -d target\classes -cp lib\*;src\main\java src\main\java\Main.java -encoding utf-8
echo "COMPILE SUCCESS"
echo "RUN..."
java -cp target\classes;lib\*; Main 
