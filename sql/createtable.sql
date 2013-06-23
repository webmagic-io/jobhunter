create table `JobInfo` (
  `ID` int primary key auto_increment,
  `title` varchar(200) not null default "",
  `salary` varchar(200) not null default "",
  `company` varchar(200) not null default "",
  `description` varchar(5000) not null default "",
  `requirement` varchar(5000) not null default "",
  `source` varchar(200) not null default "",
  `url` varchar(5000) not null default "",
  key `ix_source` (`source`)
) default charset 'utf-8' ENGINE='innodb';