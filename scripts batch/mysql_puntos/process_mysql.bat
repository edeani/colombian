REM Script para backup de base de datos MYSQL
setlocal EnableDelayedExpansion

@ECHO OFF
set backupvar=process.properties
for /f "tokens=1,2 delims==" %%a in (%backupvar%) do (
    set %%a=%%b
)   

ECHO Iniciando backup de base de datos...
ECHO Path de destino para el achivo: %mysqlbackupdestiny%

set DATETIME=%date:~-4%-%date:~-7,2%-%date:~-10,2%_%time:~-11,2%-%time:~-8,2%-%time:~-5,2%


cd %path_mysql%
for %%a in (%database%) do (
   SET full_destiny_path=%mysqlbackupdestiny%\%point_name%_%%a_%DATETIME%.sql
   mysqldump --defaults-extra-file="%path_mycnf%\.my.cnf" %%a >  !full_destiny_path!
   timeout /t 1
)


ECHO Backup finalizado

ECHO Copiando archivos al servidor...
for %%a in (%database%) do (
   SET full_destiny_path=%mysqlbackupdestiny%\%point_name%_%%a_%DATETIME%.sql
   scp -i %rsa_key% !full_destiny_path!  %user_server%@%ip_server%:%path_server% 
   timeout /t 1
)

ECHO Borrar archivos...
for %%a in (%database%) do (
   SET full_destiny_path=%mysqlbackupdestiny%\%point_name%_%%a_%DATETIME%.sql
   del !full_destiny_path!
)


ECHO Proceso finalizado.





