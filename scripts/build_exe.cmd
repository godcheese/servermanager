CHCP 65001
@echo off
echo.author godcheese [godcheese@outlook.com]
set "CURRENT_DIR=%cd%"
cd %CURRENT_DIR%
cd ..
echo.开始构建...
call javapackager -deploy -native image -appclass com.gioov.MainWindow -srcfiles %cd% -outdir %cd%\out -outfile servermanager -name ServerManager
cd %CURRENT_DIR%
echo.构建完毕...
