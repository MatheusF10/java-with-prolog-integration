@echo off

REM Find the absolute path of swipl.exe from the system environment variables
for /f "delims=" %%i in ('where swipl.exe 2^>nul') do set "SWIPL_EXE=%%i"

REM Check if swipl was found in the PATH
if "%SWIPL_EXE%"=="" (
    echo [ERROR] SWI-Prolog (swipl.exe) was not found in your system's PATH.
    echo Please ensure SWI-Prolog is installed and added to your Environment Variables.
    pause
    exit /b
)

REM Extract the bin folder path (where swipl.exe resides)
for %%A in ("%SWIPL_EXE%") do set "SWIPL_BIN=%%~dpA"

REM Remove the trailing backslash from the bin path
set "SWIPL_BIN=%SWIPL_BIN:~0,-1%"

REM Extract the parent directory to find the lib folder
for %%A in ("%SWIPL_BIN%") do set "SWIPL_HOME=%%~dpA"
set "SWIPL_LIB=%SWIPL_HOME%lib"

REM Run the Java application using the dynamically discovered paths
cd bin
java -cp ".;%SWIPL_LIB%\jpl.jar" -Djava.library.path="%SWIPL_BIN%" application.Aplicacao
cd ..