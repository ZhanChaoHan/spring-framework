DROP TABLE computer;
CREATE TABLE computer ( computerId varchar(255) NOT NULL, computerName varchar(255), computerPrice bigint, madeTime datetime, PRIMARY KEY (computerId) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
DROP TABLE software;
CREATE TABLE software ( softWareId varchar(255) NOT NULL, cId varchar(255), softWareName varchar(255), PRIMARY KEY (softWareId), INDEX FK3oqxorsi22btxfda69o6avmt6 (cId) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
ALTER TABLE software ADD CONSTRAINT FK3oqxorsi22btxfda69o6avmt6 FOREIGN KEY (cId) REFERENCES computer (computerId);
