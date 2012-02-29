-- -----------------------------------------------------------------------------
-- Script to upgrade from version 0.2 to version 0.3
-- WARNING: Due to limitations in the class MigrationManager, SQL statements 
-- must begin in a new line and no code must exist after semicolons (;).
-- -----------------------------------------------------------------------------


-- ----------------------------------------------------------------------------
-- Bill
-- ----------------------------------------------------------------------------
ALTER TABLE `Bill` ADD COLUMN `providerId` int;
ALTER TABLE `Bill` ADD index `ndx_bill_providerId` (`providerId`);
ALTER TABLE `Bill` ADD constraint `fk_bill_providerId` foreign key (`providerId`) references `Organization` (`id`);


-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------

-- Update version number
update Version set version='0.3';
