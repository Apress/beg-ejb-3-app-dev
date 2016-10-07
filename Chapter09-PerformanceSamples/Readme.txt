This chapter compares JOINED and SINGLE_TABLE inheritance strategies
in Java Persistence Architecture (JPA). JOINED inheritance strategy
uses the wineapp schema that is used for other chapter samples. 
We have a different schema to test SINGLE_TABLE strategy. 
You can install the SINGLE_TABLE wineapp schema by 

Login to SQLPlus as system user.

Execute runsql.sql using SQLPlus.

runsql.sql and other dependent sql scripts are available
in z:\chapter09-Sample\PersistenceUnit-ST\sqlscripts directory. 

Deploying JOINED inheritance strategy winestore application

To deploy the winestore application which is using JOINED inheritance strategy, execute the following Ant task.

Open a command shell.

Change directory to chapter08-Sample which is Chapter 8 directory in the downloaded source code.

%ANT_HOME%/bin/ant DeployWineStoreApplication

After sucessful deployment, you can run the application using the following URL.
http://localhost:8080/wineapp/faces/winestore.jsp

Running SINGLE_TABLE inheritance strategy winestore application

To deploy the winestore application which is using SINGLE_TABLE inheritance 
strategy, execute the following Ant task.

Open a command shell.

Change directory to chapter08-Sample which is Chapter 8 directory in 
the downloaded source code.

%ANT_HOME%/bin/ant DeployWineStoreApplication-ST

The client application is a Java class that is executed from the Grinder. 
After setting up Grinder environment you can use the python script JEE.py 
in chapter08-Sample/J2SECompareClientWithFacadeAccess directory. 