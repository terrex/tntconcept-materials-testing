-- -----------------------------------------------------------------------------
-- Script to upgrade from version 0.7 to version 0.8
-- WARNING: Due to limitations in the class MigrationManager, SQL statements 
-- must begin in a new line and no code must exist after semicolons (;).
-- -----------------------------------------------------------------------------

ALTER TABLE `RequestHoliday` ADD COLUMN userComment varchar(1024) null;

ALTER TABLE `User` ADD COLUMN `dayDuration` int not null default 480 comment 'Duración en minutos';


-- Create table
create table `Occupation` (
  `id` int not null auto_increment,
  `projectId` int not null,
  `userId` int not null,
  `startDate` date not null,
  `endDate` date not null,  
  `description` varchar(1024),
  `duration` int not null comment 'Duración en minutos',
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,  
  primary key (`id`),
  index `ndx_occupation_userId` (`userId`),
  index `ndx_occupation_projectId` (`projectId`),
  constraint `fk_occupation_userId` foreign key (`userId`) references `User` (`id`),
  constraint `fk_occupation_projectId` foreign key (`projectId`) references `Project` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Future occupations of Users';


ALTER TABLE `Project` ADD COLUMN `billable` boolean  NOT NULL DEFAULT true;


-- Update version number
update Version set version='0.8';