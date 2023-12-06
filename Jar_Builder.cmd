@echo off
setlocal enabledelayedexpansion

:: Set the source directory of Java files
set SRC_DIR=src

:: Prompt for the JAR file name
set /p JAR_NAME=Enter the Application name (e.g: myapp): 

:: Create a list of source files with relative paths
(for /r "%SRC_DIR%" %%i in (*.java) do (
    set "FILE_PATH=%%i"
    set "RELATIVE_PATH=!FILE_PATH:%CD%\=!"
    echo !RELATIVE_PATH!
)) > sources.txt

:: Get all Java files in the Source subdirectories
for /r "%SRC_DIR%" %%i in (*.java) do (
    set "CLASSPATH=!CLASSPATH!;%%i"
)

:: Remove the leading semicolon from the classpath
set "CLASSPATH=!CLASSPATH:~1!"

:: Compile all Java files
javac -d classes -classpath "lib\*;!CLASSPATH!" -sourcepath %SRC_DIR% @sources.txt
jar --create --file %JAR_NAME%.jar -C classes .

:: Check if the .jar file was created in the current directory
if not exist "%CD%\%JAR_NAME%.jar" (
    echo Error: Failed to create %JAR_NAME%.jar
    goto :end
)

:: Try to find Main.java in a case-insensitive manner
set MAIN_CLASS_LOCATION=
for /r "%SRC_DIR%" %%i in (*Main.java) do (
    set "LOWER_FILE_NAME=%%~ni"
    set "LOWER_FILE_NAME=!LOWER_FILE_NAME:l=L!"
    set "MAIN_CLASS_LOCATION=%%i"
    set "MAIN_PATH=!MAIN_CLASS_LOCATION:%CD%\%SRC_DIR%\=!"
    set "MAIN_CLASS_NAME=!MAIN_PATH:\=.!"
    set "MAIN_CLASS_NAME=!MAIN_CLASS_NAME:.java=!"
    set "LOWER_MAIN_CLASS_NAME=!MAIN_CLASS_NAME:l=L!"
)

:: If Main.java was not found, prompt for the main class name
if "%MAIN_CLASS_LOCATION%"=="" (
    set /p MAIN_CLASS_NAME="Enter the fully qualified name of the main class (e.g: com.example.Main): "
)

:: If the user didn't specify a main class name, use "Main" as the default
if "%MAIN_CLASS_NAME%"=="" (
    set MAIN_CLASS_NAME=Main
)

:: Remove the sources.txt file.
del sources.txt

:: Create the run.cmd file with the command to run the JAR file
(
    echo @echo off
    echo java -classpath %JAR_NAME%.jar;"lib\*" %MAIN_CLASS_NAME%
    echo pause
) > Run.cmd

:: End of script
echo.
echo %JAR_NAME%.jar file and Run file created!
echo.
echo You can now execute the "run.cmd" in the current folder to execute the application.
echo.
goto :end

:end
pause
