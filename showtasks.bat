call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo There was an error running runcrud.bat file
goto fail

:runchrome
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo There was an error

:end
echo Finishing work.