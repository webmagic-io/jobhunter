create table `JobInfo` (
  `ID` int primary key auto_increment,
  `title` varchar(200) not null default "",
  `salary` varchar(200) not null default "",
  `company` varchar(200) not null default "",
  `description` varchar(6000) not null default "",
  `source` varchar(200) not null default "",
  `url` varchar(5000) not null default "",
  `urlMd5` varchar(100) not null default "",
  key `ix_source` (`source`),
  unique key `un_ix_url_md5` (`urlMd5`)
) default charset 'utf8' ENGINE='innodb';