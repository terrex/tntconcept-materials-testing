--
-- Script to create initial tables
-- Include here the default initial values for this version.
--

set names utf8;

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------

-- Create table
create table `Version` (
  `version` varchar(32) not null
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='version de la base de datos';

-- Insert version number
insert into Version values ('0.1');


-- -----------------------------------------------------------------------------
-- Organization
-- -----------------------------------------------------------------------------

-- Create table
create table `Organization` (
  `id` int not null auto_increment,
  `name` varchar(128) not null,
  `cif` varchar(50),
  `phone` varchar(15),
  `street` varchar(256),
  `number` varchar(16) comment 'Building number in street',
  `locator` varchar(256) comment 'Location information inside building',
  `postalCode` varchar(32),
  `city` varchar(256),
  `provinceId` int not null,
  `state` varchar(256),
  `country` varchar(256),
  `fax` varchar(16),
  `email` varchar(256),
  `website` varchar(256),
  `ftpsite` varchar(256),
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='los clientes';

-- Insert default own company
insert into Organization(id,name,provinceId) values ( 1, 'Nuestra empresa', 28 );


-- -----------------------------------------------------------------------------
-- Project
-- -----------------------------------------------------------------------------

-- Create table
create table `Project` (
  `id` int not null auto_increment,
  `organizationId` int not null,
  `startDate` date not null,
  `endDate` date,
  `name` varchar(128) not null,
  `description` varchar(2048),
  primary key  (`id`),
  index `ndx_project_organizationId` (`organizationId`),
  constraint `fk_project_organizationId` foreign key (`organizationId`) references `Organization` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Projects';

-- Insert generic projects
insert into Project(id,organizationId,startDate,name) values (1, 1, CURDATE(),'Vacaciones');
insert into Project(id,organizationId,startDate,name) values (2, 1, CURDATE(),'Permiso extraordinario');
insert into Project(id,organizationId,startDate,name) values (3, 1, CURDATE(),'Baja por enfermedad');
insert into Project(id,organizationId,startDate,name) values (4, 1, CURDATE(),'Auto-formación');


-- -----------------------------------------------------------------------------
-- Role
-- -----------------------------------------------------------------------------

-- Create table
create table `Role` (
  `id` int not null auto_increment,
  `name` varchar(64) not null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Roles de la aplicacin';

-- Add default roles
insert into Role values (1, 'Administrador');
insert into Role values (2, 'Supervisor');
insert into Role values (3, 'Usuario');


-- -----------------------------------------------------------------------------
-- Province
-- -----------------------------------------------------------------------------

-- Create table
create table `Province` (
  `id` int not null comment 'El id no es autoincremental porque ya tienen unos códigos fijos',
  `name` varchar(64) not null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Tabla con las Provinces';

-- Insert default provinces
insert into Province values (01, 'Alava');
insert into Province values (02, 'Albacete');
insert into Province values (03, 'Alicante');
insert into Province values (04, 'Almería');
insert into Province values (33, 'Asturias');
insert into Province values (05, 'Avila');
insert into Province values (06, 'Badajoz');
insert into Province values (07, 'Balears, Illes');
insert into Province values (08, 'Barcelona');
insert into Province values (09, 'Burgos');
insert into Province values (10, 'Cáceres');
insert into Province values (11, 'Cádiz');
insert into Province values (39, 'Cantabria');
insert into Province values (12, 'Castellón');
insert into Province values (51, 'Ceuta');
insert into Province values (13, 'Ciudad Real');
insert into Province values (14, 'Córdoba');
insert into Province values (15, 'Coruña, A');
insert into Province values (16, 'Cuenca');
insert into Province values (17, 'Girona');
insert into Province values (18, 'Granada');
insert into Province values (19, 'Guadalajara');
insert into Province values (20, 'Guipúzcoa');
insert into Province values (21, 'Huelva');
insert into Province values (22, 'Huesca');
insert into Province values (23, 'Jaén');
insert into Province values (24, 'León');
insert into Province values (25, 'Lleida');
insert into Province values (27, 'Lugo');
insert into Province values (28, 'Madrid');
insert into Province values (29, 'Málaga');
insert into Province values (52, 'Melilla');
insert into Province values (30, 'Murcia');
insert into Province values (31, 'Navarra');
insert into Province values (32, 'Ourense');
insert into Province values (34, 'Palencia');
insert into Province values (35, 'Palmas, Las');
insert into Province values (36, 'Pontevedra');
insert into Province values (26, 'Rioja, La');
insert into Province values (37, 'Salamanca');
insert into Province values (38, 'Santa Cruz de Tenerife');
insert into Province values (40, 'Segovia');
insert into Province values (41, 'Sevilla');
insert into Province values (42, 'Soria');
insert into Province values (43, 'Tarragona');
insert into Province values (44, 'Teruel');
insert into Province values (45, 'Toledo');
insert into Province values (46, 'Valencia');
insert into Province values (47, 'Valladolid');
insert into Province values (48, 'Vizcaya');
insert into Province values (49, 'Zamora');
insert into Province values (50, 'Zaragoza');


-- -----------------------------------------------------------------------------
-- UserCategory
-- -----------------------------------------------------------------------------

-- Create table
create table `UserCategory` (
  `id` int not null auto_increment,
  `name` varchar(64) not null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Se almacenan las categorias';

-- Add generic user categories
insert into UserCategory values (1, 'Socio');
insert into UserCategory values (2, 'Becario');
insert into UserCategory values (3, 'Administrativo');
insert into UserCategory values (4, 'Gerente');


-- -----------------------------------------------------------------------------
-- User
-- -----------------------------------------------------------------------------

-- Create table
create table `User` (
  `id` int not null auto_increment,
  -- Datos de la aplicacion
  `login` varchar(50) not null,
  `password` varchar(50) not null,
  `roleId` int not null,
  `active` tinyint(1) not null default '1' comment 'User activo',

  -- Datos personales
  `name` varchar(200) not null,
  `nif` varchar(9),
  `birthDate` date,
  `academicQualification` varchar(200),
  -- XXX Habrá que unificar el tratamiento de documentos externos
  `curriculumPath` varchar(200) comment 'Curriculum',
  `curriculumType` varchar(100),
  `phone` varchar(12),
  `mobile` varchar(12),
  -- XXX Habría que normalizar las direcciones, o por lo menos ponerlas todas igual. Aquí faltaría el pais
  `street` varchar(100),
  `city` varchar(100),
  `postalCode` varchar(5),
  `provinceId` int,
  `married` tinyint(1) not null comment 'Casado (1) o no (0)',
  `childrenNumber` tinyint not null,
  `drivenLicenseType` varchar(10),
  `vehicleType` varchar(50),
  `licensePlate` varchar(45),

  -- Datos de la empresa
  -- XXX falta el campo de fecha de baja (endDate)
  `startDate` date not null comment 'fecha de alta en la empresa',
  `categoryId` int not null,
  `socialSecurityNumber` varchar(45),
  `bank` varchar(100),
  `account` varchar(34),
  `travelAvailability` varchar(128) comment 'Disponibilidad para viajar',
  `workingInClient` tinyint(1) not null comment 'Si esta tabajando en el cliente',
  `email` varchar(128),
  primary key (`id`),
  index ndx_user_roleId (`roleId`),
  index ndx_user_provinceId (`provinceId`),
  index ndx_user_categoryId (`categoryId`),
  constraint `fk_user_roleId` foreign key (`roleId`) references `Role` (`id`),
  constraint `fk_user_provinceId` foreign key (`provinceId`) references `Province` (`id`),
  constraint `fk_user_categoryId` foreign key (`categoryId`) references `UserCategory` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Users de la aplicacin';

-- Create administrator user (login=admin, password=adminadmin)
insert into User set name='Administrador', login='admin', password='dd94709528bb1c83d08f3088d4043f4742891f4f', roleId=1, categoryId=1, startDate=now(), workingInClient=0, married=0, childrenNumber=0;


-- -----------------------------------------------------------------------------
-- Activity
-- -----------------------------------------------------------------------------

-- Create table
create table `Activity` (
  `id` int not null auto_increment,
  `projectId` int not null,
  `userId` int not null,
  `startDate` date not null,
  `duration` int not null comment 'Duración en minutos',
  `description` varchar(2048),
  primary key (`id`),
  index `ndx_activity_userId` (`userId`),
  index `ndx_activity_projectId` (`projectId`),
  constraint `fk_activity_userId` foreign key (`userId`) references `User` (`id`),
  constraint `fk_activity_projectId` foreign key (`projectId`) references `Project` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Activityes de los Users';


-- -----------------------------------------------------------------------------
-- Bill
-- -----------------------------------------------------------------------------

-- Create table
create table `Bill` (
  `id` int not null auto_increment,
  `creationDate` date not null,
  `expiration` smallint,
  `organizationId` int not null,
  `paymentMode` varchar(16),
  `state` varchar(16) not null,
  `amount` decimal(10,2) not null,
  `number` varchar(64) not null,
  `name` varchar(128) not null,
  `file` varchar(512),
  `fileMime` varchar(64),
  primary key (`id`),
  index `ndx_bill_organizationId` (`organizationId`),
  constraint `fk_bill_organizationId` foreign key (`organizationId`) references `Organization` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci;


-- -----------------------------------------------------------------------------
-- BulletinBoardCategory
-- -----------------------------------------------------------------------------

-- Create table
create table `BulletinBoardCategory` (
  `id` int not null auto_increment,
  `name` varchar(64) not null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='almacenan las categoras';

-- Add default bulletin board categories
insert into BulletinBoardCategory values (null, 'Pública');
insert into BulletinBoardCategory values (null, 'General');


-- -----------------------------------------------------------------------------
-- Interaction
-- -----------------------------------------------------------------------------

-- Create table
create table `Interaction` (
  `id` int not null auto_increment,
  `creationDate` datetime not null,
  `organizationId` int not null,
  `interest` varchar(16) not null comment 'enum InteractionInterest',
  `why` varchar(500) not null,
  `description` varchar(2048) not null,
  `file` varchar(400),
  `fileMime` varchar(128),
  primary key (`id`),
  index `ndx_interaction_organizationId` (`organizationId`),
  constraint `fk_interaction_organizationId` foreign key (`organizationId`) references `Organization` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='contactos que se mantienen con ';


-- -----------------------------------------------------------------------------
-- Contact
-- -----------------------------------------------------------------------------

-- Create table
create table `Contact` (
  `id` int not null auto_increment,
  `organizationId` int not null,
  `name` varchar(150) not null,
  `email` varchar(128),
  `phone` varchar(15),
  `mobile` varchar(15),
  `position` varchar(128) default null comment 'Cargo de la persona de contacto',
  primary key (`id`),
  index `ndx_contact_organizationId` (`organizationId`),
  constraint `fk_contact_organizationId` foreign key (`organizationId`) references `Organization` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='contactos de las Organizationes';


-- -----------------------------------------------------------------------------
-- CompanyState
-- -----------------------------------------------------------------------------

-- Create table
create table `CompanyState` (
  `id` int not null auto_increment,
  `userId` int not null comment 'aplicacin que manda el mail',
  `creationDate` datetime not null,
  `description` longtext not null,
  primary key (`id`),
  index `ndx_companystate_userId` (`userId`),
  constraint `fk_companystate_userId` foreign key (`userId`) references `User` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='comentario del director de empresa';


-- -----------------------------------------------------------------------------
-- Idea
-- -----------------------------------------------------------------------------

-- Create table
create table `Idea` (
  `id` int not null auto_increment,
  `userId` int not null,
  `creationDate` datetime not null,
  `description` varchar(2048) not null,
  `cost` varchar(500),
  `benefits` varchar(2048),
  `name` varchar(300) not null,
  primary key (`id`),
  index `ndx_idea_userId` (`userId`),
  constraint `fk_idea_userId` foreign key (`userId`) references `User` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Ideas de los empleados';


-- -----------------------------------------------------------------------------
-- Inventory
-- -----------------------------------------------------------------------------

-- Create table
create table `Inventory` (
  `id` int not null auto_increment,
  `buyDate` date,
  `asignedToId` int,
  `renting` tinyint(1) not null default 0 comment 'De renting (1) o no (0)',
  `cost` decimal(10,2),
  `amortizable` tinyint(1) not null default 0 comment 'Amortizable (1) o no (0)consumible',
  `serialNumber` varchar(30) not null,
  `type` varchar(16) not null,
  `provider` varchar(128),
  `trademark` varchar(128),
  `model` varchar(128),
  `speed` varchar(10),
  `storage` varchar(10),
  `ram` varchar(10),
  `location` varchar(128),
  `description` varchar(256),
  primary key (`id`),
  index `ndx_invetory_userId` (`asignedToId`),
  constraint `fk_inventory_userId` foreign key (`asignedToId`) references `User` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Inventory de mquinas';


-- -----------------------------------------------------------------------------
-- Objective
-- -----------------------------------------------------------------------------

-- Create table
create table `Objective` (
  `id` int not null auto_increment,
  `userId` int not null,
  `projectId` int not null,
  `startDate` date not null,
  `endDate` date,
  `state` varchar(16),
  `name` varchar(512) not null,
  `log` longtext,
  primary key (`id`),
  index `ndx_objective_userId` (`userId`),
  index `ndx_objective_projectId` (`projectId`),
  constraint `fk_objective_projectId` foreign key (`userId`) references `User` (`id`),
  constraint `fk_objective_userId` foreign key (`projectId`) references `Project` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Se almacenan los Objectives';


-- -----------------------------------------------------------------------------
-- Magazine
-- -----------------------------------------------------------------------------

-- Create table
create table `Magazine` (
  `id` int not null auto_increment,
  `name` varchar(128) not null,
  `description` varchar(2048),
  primary key  (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Magazines a las que se envan';


-- -----------------------------------------------------------------------------
-- Tutorial
-- -----------------------------------------------------------------------------

-- Create table
create table `Tutorial` (
  `id` int not null auto_increment,
  `userId` int not null,
  `maxDeliveryDate` datetime not null,
  `endDate` datetime default null comment 'Fecha de finalizacin del Tutorial',
  `name` varchar(128) not null,
  `description` varchar(2048),
  primary key (`id`),
  index `ndx_tutorial_userId` (`userId`),
  constraint `fk_tutorial_userId` foreign key (`userId`) references `User` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='tutoriales que son enviado a la';


-- -----------------------------------------------------------------------------
-- Publication
-- -----------------------------------------------------------------------------

-- Create table
create table `Publication` (
  `id` int not null auto_increment,
  `name` varchar(128) not null,
  `magazineId` int not null,
  `magazineDeliveryDate` datetime default null comment 'Fecha de entrega a la Magazine',
  `magazinePublicationDate` date,
  `ownPublicationDate` date comment 'Fecha de publicacin en Adictos',
  `accepted` tinyint(1) comment 'Indicador de si el Tutorial ha sido aceptado (1) o no (0)',
  primary key  (`id`),
  index `ndx_publication_tutorialId` (`id`),
  constraint `fk_publicacion_tutorialId` foreign key (`id`) references `Tutorial` (`id`),
  constraint `fk_publication_magazineId` foreign key (`magazineId`) references `Magazine` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='publicaciones de tutoriales';


-- -----------------------------------------------------------------------------
-- BulletinBoard
-- -----------------------------------------------------------------------------

-- Create tables
create table `BulletinBoard` (
  `id` int not null auto_increment,
  `categoryId` int not null,
  `userId` int not null,
  `creationDate` datetime not null,
  `message` varchar(2048) not null,
  `title` varchar(128) not null,
  `documentPath` varchar(128),
  `documentContentType` varchar(128),
  primary key  (`id`),
  index `ndx_bulletinboard_categoryId` (`categoryId`),
  index `ndx_bulletinboard_userId` (`userId`),
  constraint `fk_bulletinboard_categoryId` foreign key (`categoryId`) references `BulletinBoardCategory` (`id`),
  constraint `fk_bulletinboard_userId` foreign key (`userId`) references `User` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='mensajes del tabln';


-- -----------------------------------------------------------------------------
-- AccountType
-- -----------------------------------------------------------------------------

-- Create table
create table `AccountType` (
  `id` int not null auto_increment,
  `name` varchar(128) not null comment 'Account type descriptive name',
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='lmacenan las cuentas';

-- Default account types
insert into AccountType values (1, 'Caja');
insert into AccountType values (2, 'Cuenta corriente');
insert into AccountType values (3, 'Cuenta de crédito');
insert into AccountType values (4, 'Depósito');


-- -----------------------------------------------------------------------------
-- Account
-- -----------------------------------------------------------------------------

-- Create table
create table `Account` (
  `id` int not null auto_increment,
  `name` varchar(128) not null comment 'Account descriptive name',
  `number` varchar(20) not null comment 'Account number',
  `accountTypeId` int not null comment 'Account type',
  primary key (`id`),
  index `ndx_account_accountTypeId` (`accountTypeId`),
  constraint `fk_account_accountTypeId` foreign key (`accountTypeId`) references `AccountType` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='almacenan las cuentas';

-- Default account for cash
insert into Account(id,name,number,accountTypeId) VALUES (1,'Caja','0000000000000000000',1);


-- -----------------------------------------------------------------------------
-- AccountEntryGroup
-- -----------------------------------------------------------------------------

-- Create table
create table `AccountEntryGroup` (
  `id` int not null auto_increment,
  `name` varchar(128) not null comment 'Account entry group descriptive name',
  `description` varchar(1024),
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='grupos de movimiento';

-- The default values for AccountEntryGroup
insert into AccountEntryGroup values (1, 'Ingreso', 'Ingresos en cuenta');
insert into AccountEntryGroup values (2, 'Gasto', 'Gastos en cuenta');
insert into AccountEntryGroup values (3, 'Traspaso', 'Traspasos');
insert into AccountEntryGroup values (4, 'Arranque anual', 'Movimiento que representa al arranque anual');


-- -----------------------------------------------------------------------------
-- AccountEntryType
-- -----------------------------------------------------------------------------

-- Create table
create table `AccountEntryType` (
  `id` int not null auto_increment,
  `accountEntryGroupId` int not null comment 'Account entry group',
  `name` varchar(256) not null comment 'Account descriptive name',
  `observations` varchar(1024),
  primary key (`id`),
  index `ndx_accountEntryType_accountEntryGroupId` (`accountEntryGroupId`),
  constraint `fk_accountEntryType_accountEntryGroupId` foreign key (`accountEntryGroupId`) references `AccountEntryGroup` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='tipos de movimientos';

-- Insert initial entry type
insert into `AccountEntryType` ( `id`, `accountEntryGroupId`, `name`, observations )
  values( 1, 4, 'Arranque inicial', 'Tipo de asiento que representa el arranque inicial de un año' );


-- -----------------------------------------------------------------------------
-- AccountEntry
-- -----------------------------------------------------------------------------

-- Create table
create table `AccountEntry` (
  `id` int not null auto_increment,
  `accountId` int not null comment 'account where the entry is charged',
  `accountEntryTypeId` int not null comment 'Account entry type',
  `entryDate` date not null comment 'account entry date',
  `entryAmountDate` date not null comment 'account entry amount date (fecha valor)',
  `concept` varchar(1024) not null,
  `amount` decimal(10,2) not null comment 'account entry amount',
  `observations` varchar(1024),
  `review` tinyint(1) not null default 0 comment 'Si se desea revisar el movimiento 1 si, 0 no',
  primary key (`id`),
  index `ndx_accountEntry_accountId` (`accountId`),
  index `ndx_accountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  constraint `fk_accountEntry_accountId` foreign key (`accountId`) references `Account` (`id`),
  constraint `fk_accountEntry_accountEntryTypeId` foreign key (`accountEntryTypeId`) references `AccountEntryType` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='los movimientos';


-- -----------------------------------------------------------------------------
-- Bill_AccountEntry
-- -----------------------------------------------------------------------------

-- Create tables
create table `Bill_AccountEntry` (
  `billId` int not null comment 'bill id',
  `accountEntryId` int not null comment 'account entry id',
  `observations` varchar(2048),
  primary key (`billId`,`accountEntryId`),
  index `ndx_bill_AccountEntry_billId` (`billId`),
  index `ndx_bill_AccountEntry_accountEntryId` (`accountEntryId`),
  constraint `fk_billAccountEntry_billId` foreign key (`billId`) references `Bill` (`id`),
  constraint `fk_billAccountEntry_accountEntryId` foreign key (`accountEntryId`) references `AccountEntry`(`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='relaciona n m Facturas y movimientos';





-- Añadimos proyecto histórico.
insert into Project(id,organizationId,startDate,name) values (5, 1, CURDATE(),'Histórico');


-- -----------------------------------------------------------------------------
-- ProjectRole
-- -----------------------------------------------------------------------------

-- Create table
create table `ProjectRole` (
  `id` int not null auto_increment,
  `projectId` int not null,
  `name` varchar(128) not null,
  `costPerHour` decimal(10,2) not null,
  `expectedHours` int not null,
  primary key (`id`),
  index `ndx_projectRole_projectId` (`projectId`),
  constraint `fk_projectRole_projectId` foreign key (`projectId`) references `Project` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci;

-- Create one default role per old project
INSERT INTO ProjectRole( projectId, name, costPerHour, expectedHours )
  SELECT id, name, 0, 0 FROM Project;


-- -----------------------------------------------------------------------------
-- ProjectCost
-- -----------------------------------------------------------------------------

-- Create table
create table `ProjectCost` (
  `id` int  NOT NULL AUTO_INCREMENT,
  `projectId` int  NOT NULL,
  `name` varchar(128)  NOT NULL,
  `cost` decimal(10,2)  NOT NULL,
  `billable` boolean  NOT NULL DEFAULT true,
  PRIMARY KEY(`id`),
  CONSTRAINT `fk_projectCost_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB default charset=utf8 collate=utf8_spanish_ci;


-- -----------------------------------------------------------------------------
-- Activity
-- -----------------------------------------------------------------------------

-- Add columns: billable, roleId
ALTER TABLE `Activity`
  ADD COLUMN `billable` BOOLEAN  NOT NULL DEFAULT true AFTER `description`,
  ADD COLUMN `roleId` INTEGER  AFTER `billable`,
  ADD CONSTRAINT `fk_activity_roleId` FOREIGN KEY `fk_activity_roleId` (`roleId`)
    REFERENCES `ProjectRole` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

-- Set old Activities to non billable
UPDATE `Activity` SET billable=false;

-- Migrate projectId field from Activity table to roleId field
UPDATE `Activity` a SET roleId=(
  SELECT r.id FROM Project p, ProjectRole r WHERE
    r.projectId=p.id AND p.id=a.projectId
);

-- Drop projectId field from Activity table as we can navigate to it through the roleId field
ALTER TABLE `Activity` DROP COLUMN `projectId`,
  DROP INDEX `ndx_activity_projectId`,
  DROP FOREIGN KEY `fk_activity_projectId`;


-- -----------------------------------------------------------------------------
-- Document
-- -----------------------------------------------------------------------------

-- Create table
CREATE TABLE  `Document` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `creationDate` datetime default NULL,
  `name` varchar(256) collate utf8_spanish_ci default NULL,
  `description` varchar(4096) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


-- -----------------------------------------------------------------------------
-- DocumentCategory
-- -----------------------------------------------------------------------------

-- Create table
CREATE TABLE  `DocumentCategory` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(255) collate utf8_spanish_ci NOT NULL,
  `description` varchar(4096) collate utf8_spanish_ci default NULL,
  `code` varchar(45) collate utf8_spanish_ci default NULL,
  `categoryid` int(10) unsigned default NULL,
  `documentslastupdate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


-- default values for categories
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (1,'Documentos de Calidad', 'Documentos de calidad', 'CALIDAD', null, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (2,'Otros Documentos', 'Otros documentos no clasificados', ' ', null, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (3,'Curriculum Vitae', '', '', null, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (4,'Documentos de Usuarios', '', '', null, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (10,'(I1-PC01) Lista de distribución de la documentación', '', 'I1-PC01', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (11,'(I1-PC02) Acta de reunión de revisión del sistema', '', 'I1-PC02', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (12,'(I1-PC08) Informe de auditoría interna', '', 'I1-PC08', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (13,'(I2-PC02) Planificación de objetivos de calidad', '', 'I2-PC02', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (14,'(I2-PC08) Informe de no conformidad/reclamación del cliente', '', 'I2-PC08', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (15,'(I3-PC08) Informe de acción correctiva/preventiva', '', 'I3-PC08', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (16,'(PC01) Sistema de Gestión de la Calidad', 'Documento inicial de descripción', 'PC01', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (17,'(PC02) Responsabilidad de la Dirección', '', 'PC02', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (18,'(PC03) Gestión de los Recursos', '', 'PC03', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (19,'(PC04) Procesos relacionados con los clientes', '', 'PC04', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (20,'(PC05) Gestión de compras', '', 'PC05', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (21,'(PC06) Evaluación de proveedores y subcontratistas', '', 'PC06', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (22,'(PC07) Prestación del servicio', '', 'PC07', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (23,'(PC08) Medición análisis y mejora', '', 'PC08', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (24,'Control documentación entregada y recibida', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (25,'Criterio de evaluación y seguimiento de procesos', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (26,'Cuestionario de satisfacción del cliente', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (27,'E-mail aprobación documentación', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (28,'E-mail de comunicaciones', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (29,'Ficha de mantenimiento de equipos', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (30,'Índice de ediciones de documentos', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (31,'Índice de no conformidades', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (32,'Inventario de recursos', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (33,'Listado de documentación externa en vigor', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (34,'Listado de proveedores y subcontratistas evaluados', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (35,'Listado de registros', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (36,'Manual de Gestión de la Calidad (MGC)', '', 'MGC', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (37,'Perfil del empleado', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (38,'Perfil puesto trabajo', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (39,'Plan de auditoría anual', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (40,'Plan de formación anual', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (41,'Política de Calidad', '', '', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (42,'(I5-PC03) Registro perfil del empleado', '', 'I5-PC03', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (43,'(I8-PC03) Cuestionario de satisfacción laboral', '', 'I8-PC03', 1, null);
insert into DocumentCategory (id, name, description, code, categoryid, documentsLastUpdate) values (44,'(I6-PC08) Evaluación de satisfacción del cliente', '', 'I6-PC08', 1, null);

ALTER TABLE `DocumentCategory` AUTO_INCREMENT = 100;



CREATE TABLE  `DocumentVersion` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `documentPath` varchar(255) collate utf8_spanish_ci NOT NULL,
  `creationDate` datetime NOT NULL,
  `version` varchar(255) collate utf8_spanish_ci NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_DocumentVersion_document_id` (`documentid`),
  CONSTRAINT `FK_DocumentVersion_document_id` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


-- -----------------------------------------------------------------------------
-- DocumentCategoryDoc
-- -----------------------------------------------------------------------------

-- Create table
CREATE TABLE  `DocumentCategoryDoc` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `categoryid` int(10) unsigned NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_DocumentCategorydoc_category` (`categoryid`),
  KEY `FK_DocumentCategorydoc_docu` (`documentid`),
  CONSTRAINT `FK_DocumentCategorydoc_category` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `FK_DocumentCategorydoc_docu` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------------------------------
-- Book
-- -----------------------------------------------------------------------------

-- Create table
CREATE TABLE  `Book` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(255) collate utf8_spanish_ci NOT NULL,
  `author` varchar(255) collate utf8_spanish_ci default NULL,
  `ISBN` varchar(13) collate utf8_spanish_ci default NULL,
  `URL` varchar(255) collate utf8_spanish_ci default NULL,
  `price` decimal(10,2) default NULL,
  `purchaseDate` datetime default NULL,
  `userId` int default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Book_userId` (`userId`),
  CONSTRAINT `FK_Book_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


-- -----------------------------------------------------------------------------
-- Bill
-- -----------------------------------------------------------------------------

ALTER TABLE `Bill` DROP COLUMN `organizationId`, DROP INDEX `ndx_bill_organizationId`, DROP FOREIGN KEY `fk_bill_organizationId`;

-- Add columns: observations, projectId, rappel
ALTER TABLE `Bill` ADD COLUMN `observations` varchar(2048) AFTER `fileMime`;
ALTER TABLE `Bill` ADD COLUMN `projectId` int not null default 5 comment 'project id' AFTER `observations`;
ALTER TABLE `Bill` ADD COLUMN `rappel` decimal(4,2) comment 'rappel' AFTER `projectId`;

-- Modify indices and constraints
ALTER TABLE `Bill` ADD index `ndx_bill_projectId` (`projectId`);
ALTER TABLE `Bill` ADD constraint `fk_bill_projectId` foreign key (`projectId`) references `Project` (`id`);


-- -----------------------------------------------------------------------------
-- Account
-- -----------------------------------------------------------------------------

-- Add column: description
ALTER TABLE `Account` ADD COLUMN `description` varchar(2048) comment 'Account description' AFTER `accountTypeId`;


-- -----------------------------------------------------------------------------
-- Frequency
-- -----------------------------------------------------------------------------

-- Create table
CREATE TABLE  `Frequency` (
  `id` int not null auto_increment,
  `name` varchar(255) collate utf8_spanish_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci comment='Frecuencia de los asientos contables';

-- Insert default frequencies
insert into Frequency values (1, 'Mensual');
insert into Frequency values (2, 'Trimestral');
insert into Frequency values (3, 'Semestral');
insert into Frequency values (4, 'Anual');


-- -----------------------------------------------------------------------------
-- PeriodicalAccountEntry
-- -----------------------------------------------------------------------------

-- Create table
CREATE TABLE  `PeriodicalAccountEntry` (
  `id` int not null auto_increment,
  `accountId` int not null comment 'account where the entry is charged',
  `accountEntryTypeId` int not null comment 'entry type',
  `frequencyId` int not null comment 'entry frequency',
  `concept` varchar(1024) not null,
  `entryDate` date not null comment 'entry date',
  `amount` decimal(10,2) not null comment 'entry amount',
  `rise` decimal(4,2) comment 'account entry rise',
  `observations` varchar(1024),
  PRIMARY KEY  (`id`),
  index `ndx_periodicalAccountEntry_accountId` (`accountId`),
  index `ndx_periodicalAccountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  index `ndx_periodicalAccountEntry_frequencyId` (`frequencyId`),
  constraint `fk_periodicalAccountEntry_accountId` foreign key (`accountId`) references `Account` (`id`),
  constraint `fk_periodicalAccountEntry_accountEntryTypeId` foreign key (`accountEntryTypeId`) references `AccountEntryType` (`id`),
  constraint `fk_periodicalAccountEntry_frequencyId` foreign key (`frequencyId`) references `Frequency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci comment='asientos contables periodicos';


-- -----------------------------------------------------------------------------
-- InteractionType
-- -----------------------------------------------------------------------------

-- Create table
create table `InteractionType` (
  `id` int not null auto_increment,
  `name` varchar(128) not null comment 'Interaction type descriptive name',
  `description` varchar(1024) comment 'Interaction type description',
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='tipos de interacciones';

-- Insert default interaction types
insert into InteractionType values (1, 'No conformidad','');
insert into InteractionType values (2, 'Accion comercial','');
insert into InteractionType values (3, 'Envío de oferta','');
insert into InteractionType values (4, 'Envío de factura','');
insert into InteractionType values (5, 'Accion administrativa','');
insert into InteractionType values (6, 'No definida','');


-- -----------------------------------------------------------------------------
-- Interaction
-- -----------------------------------------------------------------------------

ALTER TABLE `Interaction` DROP COLUMN `organizationId`, DROP INDEX `ndx_interaction_organizationId`, DROP FOREIGN KEY `fk_interaction_organizationId`;


-- Add columns: observations, projectId, userId, interactionTypeId
ALTER TABLE `Interaction` ADD COLUMN `observations` varchar(2048) AFTER `fileMime`;
ALTER TABLE `Interaction` ADD COLUMN `projectId` int not null default 5 comment 'project id' AFTER `id`;
ALTER TABLE `Interaction` ADD COLUMN `userId` int not null default 1 comment 'user id' AFTER `projectId`;
ALTER TABLE `Interaction` ADD COLUMN `interactionTypeId` int not null default 6 comment 'type id' AFTER `userId`;

-- Modify indices and constraints
ALTER TABLE `Interaction` ADD index `ndx_interaction_projectId` (`projectId`);
ALTER TABLE `Interaction` ADD index `ndx_interaction_userId` (`userId`);
ALTER TABLE `Interaction` ADD index `ndx_interaction_interactionTypeId` (`interactionTypeId`);
ALTER TABLE `Interaction` ADD constraint `fk_interaction_projectId` foreign key (`projectId`) references `Project` (`id`);
ALTER TABLE `Interaction` ADD constraint `fk_interaction_userId` foreign key (`userId`) references `User` (`id`);
ALTER TABLE `Interaction` ADD constraint `fk_interaction_interactionTypeId` foreign key (`interactionTypeId`) references `InteractionType` (`id`);


-- -----------------------------------------------------------------------------
-- OrganizationType
-- -----------------------------------------------------------------------------

-- Create table
create table `OrganizationType` (
  `id` int not null auto_increment,
  `name` varchar(128) not null comment 'Organization type descriptive name',
  `description` varchar(1024) comment 'Organization type description',
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='tipos de organizaciones';

-- Insert default organization types
insert into OrganizationType values (1, 'Cliente','');
insert into OrganizationType values (2, 'Proveedor','');
insert into OrganizationType values (3, 'Cliente y proveedor','');
insert into OrganizationType values (4, 'Prospecto','Posible cliente');


-- -----------------------------------------------------------------------------
-- OrganizationISOCategory
-- -----------------------------------------------------------------------------

-- Create table
create table `OrganizationISOCategory` (
  `id` int not null auto_increment,
  `name` varchar(128) not null comment 'ISO Organization Category descriptive name',
  `description` varchar(1024) comment 'ISO Organization Category description',
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='tipos de organizaciones segun ISO';

-- Inserting default ISO categories
insert into OrganizationISOCategory values (1, 'A','Proveedor / Subcontratista habitual.');
insert into OrganizationISOCategory values (2, 'B','Proveedor / Subcontratista recomendado.');
insert into OrganizationISOCategory values (3, 'C','Proveedor / Subcontratista no habitual.');
insert into OrganizationISOCategory values (4, 'D','Proveedor / Subcontratista que haya tenido disconformidades.');


-- -----------------------------------------------------------------------------
-- Organization
-- -----------------------------------------------------------------------------

-- Add columns: organizationTypeId, organizationISOCategoryId
ALTER TABLE `Organization` ADD COLUMN `organizationTypeId` int not null default 1 comment 'organization type id' AFTER `id`;
ALTER TABLE `Organization` ADD COLUMN `organizationISOCategoryId` int not null default 1 comment 'iso category id' AFTER `organizationTypeId`;

-- Modify indices and constraints
ALTER TABLE `Organization` ADD index `ndx_organization_organizationTypeId` (`organizationTypeId`);
ALTER TABLE `Organization` ADD index `ndx_organization_isoOrganizationCategoryId` (`organizationISOCategoryId`);
ALTER TABLE `Organization` ADD constraint `fk_organization_organizationTypeId` foreign key (`organizationTypeId`) references `OrganizationType` (`id`);
ALTER TABLE `Organization` ADD constraint `fk_organization_isoOrganizationCategoryId` foreign key (`organizationISOCategoryId`) references `OrganizationISOCategory` (`id`);

-- Set our company to provider
update Organization set organizationTypeId=2 where id=1;




-- -----------------------------------------------------------------------------
-- User
-- -----------------------------------------------------------------------------

-- Add columns: genre, salary, salaryExtras
ALTER TABLE `User`
  ADD COLUMN `genre` VARCHAR(16)  AFTER `email`,
  ADD COLUMN `salary` DECIMAL(10,2)  AFTER `genre`,
  ADD COLUMN `salaryExtras` DECIMAL(10,2)  AFTER `salary`,
  ADD COLUMN `documentCategoryId` int(10) unsigned  AFTER `salaryExtras`,
  ADD COLUMN `securityCard` VARCHAR(64) AFTER `documentCategoryId`,
  ADD COLUMN `healthInsurance` VARCHAR(64) AFTER `securityCard`;

ALTER TABLE `User` ADD index `ndx_user_documentCategoryId` (`documentCategoryId`);
ALTER TABLE `User` ADD constraint `fk_user_documentCategoryId` foreign key (`documentCategoryId`) references `DocumentCategory` (`id`);

-- User: add document
ALTER TABLE `User` DROP COLUMN `curriculumPath`,
 DROP COLUMN `curriculumType`;
-- -----------------------------------------------------------------------------
-- Contact
-- -----------------------------------------------------------------------------

-- Add columns: notified
ALTER TABLE `Contact`
  ADD COLUMN `notified` BOOLEAN  NOT NULL DEFAULT false AFTER `position`;

CREATE TABLE  `Holiday` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `description` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


CREATE TABLE  `RequestHoliday` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `beginDate` datetime NOT NULL,
  `finalDate` datetime NOT NULL,
  `state` varchar(16) collate utf8_spanish_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `observations` varchar(1024) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_requestHoliday_userId` (`userId`),
  CONSTRAINT `fk_requestHoliday_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
