create table board (
    idx int not null auto_increment primary key,
    subject varchar(100) not null,
    writer varchar(50) not null,
    contents text not null,
    ip varchar(15),
    hit int,
    reg_date datetime,
    mod_date datetime);
    