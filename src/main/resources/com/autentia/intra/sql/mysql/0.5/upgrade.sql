-- -----------------------------------------------------------------------------
-- Script to upgrade from version 0.5 to version 0.6
-- WARNING: Due to limitations in the class MigrationManager, SQL statements 
-- must begin in a new line and no code must exist after semicolons (;).
-- -----------------------------------------------------------------------------

-- Create table Setting
CREATE TABLE  `Setting` (
  `id` int NOT NULL auto_increment,
  `type` varchar(64) collate utf8_spanish_ci NOT NULL,
  `name` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `value` varchar(4096) collate utf8_spanish_ci default NULL,
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,  
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='User settings';

-- Update version number
update Version set version='0.6';