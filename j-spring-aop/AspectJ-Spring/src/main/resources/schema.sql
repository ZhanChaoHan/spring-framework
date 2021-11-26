create table t_user(
    uId varchar(50) NOT NULL DEFAULT '0',
    uName varchar(50),
    uPwd varchar(50),
    PRIMARY KEY (uId)
)

insert into t_user values('tI1','tOne','pJK');
insert into t_user values('tI2','Ta','Mkl');
