drop user wineapp cascade;
create user wineapp identified by wineapp;
grant connect, resource, dba to wineapp;
connect wineapp/wineapp@JA9

@WineStoreSchema.sql
@PopulateIdGenTable.sql
@PopulateWines.sql
@PopulateWineItems.sql
@PopulateInvetoryitems.sql

