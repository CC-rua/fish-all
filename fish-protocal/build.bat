@echo off
cd /d %~dp0
SET JAVA_PATH=src\main\java
SET SOURCE_FOLDER=proto
SET protoc=tool\protoc.exe
del %JAVA_PATH%\*.* /f /s /q
for /f "delims=" %%i in ('dir /b "%SOURCE_FOLDER%\*.proto"') do (
	if exist %JAVA_PATH% (
		call %protoc% --proto_path=. --java_out=%JAVA_PATH% %SOURCE_FOLDER%\%%i
	) else (
		md %JAVA_PATH%
		call %protoc% --proto_path=. --java_out=%JAVA_PATH% %SOURCE_FOLDER%\%%i
	)
)
pause

