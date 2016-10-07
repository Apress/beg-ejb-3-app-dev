drop user wineappst cascade;
create user wineappst identified by wineappst;
grant connect, resource, dba to wineappst;
connect wineappst/wineappst

@ejb3book_st.sql
@PopulateIdGenTable.sql
@PopulateWines.sql
@PopulateWineItemsst.sql


