@ECHO OFF
ECHO Starting geckodriver: %0 %*
geckodriver.exe --log fatal %* > NUL 2>&1