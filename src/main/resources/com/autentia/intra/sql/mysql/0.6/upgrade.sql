-- -----------------------------------------------------------------------------
-- Script to upgrade from version 0.6 to version 0.7
-- WARNING: Due to limitations in the class MigrationManager, SQL statements 
-- must begin in a new line and no code must exist after semicolons (;).
-- -----------------------------------------------------------------------------

-- Update table Activity to change startTime dataType
ALTER TABLE `Activity` MODIFY `startDate` DATETIME DEFAULT '0000-00-00 00:00:00';


-- Adding a new field to store a customizable id for this type
ALTER TABLE `AccountEntryType` ADD COLUMN customizableId int null;

-- Adding new fields.
ALTER TABLE `AccountEntry` ADD COLUMN entryNumber varchar(16) null;
ALTER TABLE `AccountEntry` ADD COLUMN docNumber varchar(50) null;


-- Update version number
update Version set version='0.7';