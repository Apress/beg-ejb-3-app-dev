Open a Dos Console
Change Directory to sqlscripts directory
issue the following command 
sqlplus system/manager

Note: If you have a database running on remote machine
then add the TNS connect string as well For ex: sqlplus system/manager@TNSString
This readme text assume that system password is manager - Substitute as neccessary

Once you are logged in
run the following command
@runsql.sql