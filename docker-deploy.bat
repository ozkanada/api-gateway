@echo off
REM Build the Spring Boot application
echo Cleaning and building the project...
call gradlew.bat clean build

REM Check if the build was successful
IF %ERRORLEVEL% NEQ 0 (
    echo Gradle build failed. Exiting...
    exit /b %ERRORLEVEL%
)

REM Build Docker image
echo Building Docker image...
docker build -t api-gateway:0.0.1 .

REM Check if Docker build was successful
IF %ERRORLEVEL% NEQ 0 (
    echo Docker build failed. Exiting...
    exit /b %ERRORLEVEL%
)

REM Start the container with Docker Compose
echo Starting container with docker-compose...
docker-compose -p rezervin-network-backend -f docker-config.yml up -d

REM Final status
IF %ERRORLEVEL% EQU 0 (
    echo Project started successfully.
) ELSE (
    echo docker-compose failed.
)

pause
