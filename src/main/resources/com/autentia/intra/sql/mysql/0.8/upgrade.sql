-- -----------------------------------------------------------------------------
-- Script to upgrade from version 0.8 to version 0.9
-- WARNING: Due to limitations in the class MigrationManager, SQL statements 
-- must begin in a new line and no code must exist after semicolons (;).
-- -----------------------------------------------------------------------------

ALTER TABLE `Offer` ADD COLUMN observations varchar(1024) null;

ALTER TABLE `RequestHoliday` ADD COLUMN chargeYear date not null default '2007-01-01';

CREATE TABLE `WorkingAgreement` (
`id` int not null auto_increment,
`name` varchar(128) not null,
`description` varchar(2048),
`holidays` int not null default 22,
`ownerId` int null,
`departmentId` int unsigned null,
`insertDate` datetime null,
`updateDate` datetime null,
primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Convenios laborales';

INSERT INTO WorkingAgreement (id,name,description,holidays) values (1,'Convenio Nuestra Empresa','Este es el convenio de nuestra empresa',22);

ALTER TABLE `User` ADD COLUMN `agreementId` int not null default 1;
ALTER TABLE `User` ADD index `ndx_user_agreementId` (`agreementId`);
ALTER TABLE `User` ADD constraint `fk_user_agreementId` foreign key (`agreementId`) references `WorkingAgreement` (`id`);

-- TTNConcept changes below:

-- Fix charsets and collations
ALTER TABLE `DocumentCategoryDoc` DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Add updatedById fields to current tables
ALTER TABLE `Account` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `AccountEntry` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `AccountEntryGroup` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `AccountEntryType` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `AccountType` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Activity` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Bill` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `BillBreakDown` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Book` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `BulletinBoard` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `BulletinBoardCategory` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `CompanyState` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Contact` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `ContractType` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Department` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Document` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `DocumentCategory` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `DocumentCategoryDoc` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `DocumentVersion` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `FinancialRatio` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Frequency` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Holiday` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Idea` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Interaction` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `InteractionType` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Inventory` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Magazine` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Objective` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Occupation` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Offer` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `OfferCost` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `OfferRejectReason` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `OfferRole` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Organization` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `OrganizationISOCategory` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `OrganizationType` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `PeriodicalAccountEntry` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Project` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `ProjectCost` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `ProjectRole` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Province` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Publication` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `RequestHoliday` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Role` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Setting` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `Tutorial` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `User` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `UserCategory` ADD COLUMN `updatedById` INTEGER;
ALTER TABLE `WorkingAgreement` ADD COLUMN `updatedById` INTEGER;

-- New Table Ensayo prior to new table ProjectEnsayo
CREATE TABLE `Ensayo` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL COMMENT 'Ensayo descriptive name',
`cost` DECIMAL(10,2),
`perHour` BOOLEAN DEFAULT FALSE,
`description` VARCHAR(2048),
`nameIngles` VARCHAR(255),
`dimensional` BOOLEAN,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='ensayos';

-- New Table NotaSalida prior to new table ProjectEnsayo
CREATE TABLE `NotaSalida` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL UNIQUE,
`fecha` DATE NOT NULL,
`peticionarioId` INTEGER,
`clienteOrigenId` INTEGER,
`observaciones` TEXT,
`unico` BOOLEAN,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_notaSalida_peticionarioId` (`peticionarioId`),
KEY `ndx_notaSalida_clienteOrigenId` (`clienteOrigenId`),
CONSTRAINT `fk_notaSalida_peticionarioId` FOREIGN KEY (`peticionarioId`) REFERENCES `Organization` (`id`),
CONSTRAINT `fk_notaSalida_clienteOrigenId` FOREIGN KEY (`clienteOrigenId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table ProjectEnsayo prior to changes to Activity
CREATE TABLE `ProjectEnsayo` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`aprobadoFecha` DATETIME,
`aprobadoPor` INTEGER,
`conformidad` VARCHAR(800),
`cost` DECIMAL(10,2) NOT NULL,
`ensayoId` INTEGER NOT NULL,
`fechaRecepcionMuestra` DATETIME,
`identificacionCliente` VARCHAR(255),
`identificacionLab` VARCHAR(255),
`idTitMatEns` VARCHAR(255),
`notaSalidaId` INTEGER,
`observaciones` TEXT,
`photo` VARCHAR(255),
`plantillaDim` VARCHAR(255),
`plantillaLab` VARCHAR(255),
`plantillaReq` VARCHAR(255),
`plantillaTit` VARCHAR(255),
`plantilla` VARCHAR(255),
`procedimiento` VARCHAR(500),
`projectId` INTEGER NOT NULL,
`realizadoFecha` DATETIME,
`realizadoPor` INTEGER,
`requerimiento` VARCHAR(800),
`revisadoFecha` DATETIME,
`revisadoPor` INTEGER,
`valor` VARCHAR(800),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_projectEnsayo_aprobadoPor` (`aprobadoPor`),
KEY `ndx_projectEnsayo_ensayoId` (`ensayoId`),
KEY `ndx_projectEnsayo_notaSalidaId` (`notaSalidaId`),
KEY `ndx_projectEnsayo_projectId` (`projectId`),
KEY `ndx_projectEnsayo_realizadoPor` (`realizadoPor`),
KEY `ndx_projectEnsayo_revisadoPor` (`revisadoPor`),
CONSTRAINT `fk_projectEnsayo_aprobadoPor` FOREIGN KEY (`aprobadoPor`) REFERENCES `User` (`id`),
CONSTRAINT `fk_projectEnsayo_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
CONSTRAINT `fk_projectEnsayo_notaSalidaId` FOREIGN KEY (`notaSalidaId`) REFERENCES `NotaSalida` (`id`),
CONSTRAINT `fk_projectEnsayo_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
CONSTRAINT `fk_projectEnsayo_realizadoPor` FOREIGN KEY (`realizadoPor`) REFERENCES `User` (`id`),
CONSTRAINT `fk_projectEnsayo_revisadoPor` FOREIGN KEY (`revisadoPor`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Changes to Activity model
ALTER TABLE `Activity`
ADD COLUMN `projectEnsayoId` INTEGER,
ADD COLUMN `projectId` INTEGER,
ADD KEY `ndx_activity_projectEnsayoId` (`projectEnsayoId`),
ADD KEY `ndx_activity_projectId` (`projectId`),
ADD CONSTRAINT `fk_activity_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`),
ADD CONSTRAINT `fk_activity_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`);

-- Changes to Bill model
ALTER TABLE `Bill`
MODIFY COLUMN `projectId` INTEGER DEFAULT 5 COMMENT 'project id',
ADD COLUMN `clientId` INTEGER,
ADD COLUMN `lineaTrabajoId` INTEGER UNSIGNED,
ADD COLUMN `pendienteDePedido` BOOLEAN DEFAULT FALSE,
ADD UNIQUE KEY `uniq_bill_number` (`number`),
ADD KEY `ndx_bill_clientId` (`clientId`),
ADD KEY `ndx_bill_lineaTrabajoId` (`lineaTrabajoId`),
ADD CONSTRAINT `fk_bill_clientId` FOREIGN KEY (`clientId`) REFERENCES `Organization` (`id`),
ADD CONSTRAINT `fk_bill_lineaTrabajoId` FOREIGN KEY (`lineaTrabajoId`) REFERENCES `Department` (`id`);

-- Changes to Book model
ALTER TABLE `Book`
ADD KEY `ndx_book_userId` (`userId`),
ADD CONSTRAINT `fk_bill_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`);

-- Changes to DocumentCategory
ALTER TABLE `DocumentCategory`
ADD KEY `ndx_documentCategory_categoryId` (`categoryId`),
ADD CONSTRAINT `fk_documentCategory_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `DocumentCategory` (`id`);

-- Changes to Interaction model
ALTER TABLE `Interaction`
MODIFY COLUMN `projectId` INTEGER DEFAULT 5 COMMENT 'project id',
MODIFY COLUMN `why` VARCHAR(5000) NOT NULL,
MODIFY COLUMN `observations` VARCHAR(5000),
ADD COLUMN `medio` VARCHAR(16) NOT NULL DEFAULT 'EMAIL' COMMENT 'enum InteractionMedio',
ADD COLUMN `organizationId` INTEGER,
ADD KEY `ndx_interaction_organizationId` (`organizationId`),
ADD CONSTRAINT `fk_interaction_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`);

-- Changes to Offer model
ALTER TABLE `Offer`
ADD COLUMN `billId` INTEGER,
ADD COLUMN `informesCreados` BOOLEAN DEFAULT FALSE,
ADD COLUMN `iva` DECIMAL(4,2) DEFAULT 16.00,
ADD COLUMN `lineaTrabajoId` INTEGER UNSIGNED,
ADD COLUMN `orderNumber` VARCHAR(64),
ADD KEY `ndx_offer_billId` (`billId`),
ADD KEY `ndx_offer_lineaTrabajoId` (`lineaTrabajoId`),
ADD CONSTRAINT `fk_offer_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
ADD CONSTRAINT `fk_offer_lineaTrabajoId` FOREIGN KEY (`lineaTrabajoId`) REFERENCES `Department` (`id`);

-- Changes to OfferCost model
ALTER TABLE `OfferCost`
DROP COLUMN `iva`;

-- Changes to OfferRole model
ALTER TABLE `OfferRole`
MODIFY COLUMN `expectedHours` DECIMAL(10,2) NOT NULL,
DROP COLUMN `iva`;

-- Changes to Organization model
ALTER TABLE `Organization`
ADD COLUMN `acronimo` VARCHAR(15),
ADD COLUMN `certificado` VARCHAR(16),
ADD COLUMN `columnaRefCli` VARCHAR(64),
ADD COLUMN `direccionEnvioFactura` TEXT,
ADD COLUMN `direccionEnvioInformes` TEXT,
ADD COLUMN `numeroProveedor` VARCHAR(255),
ADD COLUMN `estado` VARCHAR(16),
ADD COLUMN `fechaAlta` DATE,
ADD COLUMN `fechaCaducidadCertificado` DATE,
ADD COLUMN `procedimientoAdministrativo` TEXT,
ADD KEY `ndx_organization_provinceId` (`provinceId`),
ADD CONSTRAINT `fk_organization_provinceId` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`);

-- New table Albaran prior to add a fk from Project to this
CREATE TABLE `Albaran` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(128) NOT NULL UNIQUE,
`fecha` DATE NOT NULL,
`devuelto` BOOLEAN DEFAULT FALSE,
`client` INTEGER,
`billId` INTEGER,
`observaciones` TEXT,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_albaran_billId` (`billId`),
KEY `ndx_albaran_client` (`client`),
CONSTRAINT `fk_albaran_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
CONSTRAINT `fk_albaran_client` FOREIGN KEY (`client`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Changes to Project model
ALTER TABLE `Project`
ADD COLUMN `albaranId` INTEGER,
ADD COLUMN `billId` INTEGER,
ADD COLUMN `costeAFacturar` DECIMAL(19,2),
ADD COLUMN `descripcion` VARCHAR(500),
ADD COLUMN `ensayosSolicitados` VARCHAR(2048),
ADD COLUMN `especificacion` VARCHAR(255),
ADD COLUMN `estadoDeRecepcion` VARCHAR(2048),
ADD COLUMN `fabricante` VARCHAR(64),
ADD COLUMN `fechaCaducidad` DATETIME,
ADD COLUMN `loteFabricante` VARCHAR(64),
ADD COLUMN `lote` VARCHAR(64),
ADD COLUMN `motivoDevolucion` VARCHAR(256),
ADD COLUMN `numMuestras` INTEGER,
ADD COLUMN `objetivosDelInforme` VARCHAR(1024),
ADD COLUMN `observaciones` VARCHAR(2048),
ADD COLUMN `offerId` INTEGER,
ADD COLUMN `orderNumber` VARCHAR(64),
ADD COLUMN `plantillaLab` VARCHAR(255),
ADD COLUMN `plantilla` VARCHAR(255),
ADD COLUMN `proveedor` VARCHAR(64),
ADD COLUMN `realizadoFecha` DATETIME,
ADD COLUMN `realizadoPor` INTEGER,
ADD COLUMN `recibidoPor` INTEGER,
ADD COLUMN `referenciaCliente` VARCHAR(64),
ADD COLUMN `referenciaLaboratorio` VARCHAR(64) UNIQUE,
ADD COLUMN `supervisadoPor` INTEGER,
ADD COLUMN `tiempoRespuesta` INTEGER,
ADD COLUMN `titulo` VARCHAR(255),
ADD KEY `ndx_project_albaranId` (`albaranId`),
ADD KEY `ndx_project_billId` (`billId`),
ADD KEY `ndx_project_offerId` (`offerId`),
ADD KEY `ndx_project_realizadoPor` (`realizadoPor`),
ADD KEY `ndx_project_recibidoPor` (`recibidoPor`),
ADD KEY `ndx_project_supervisadoPor` (`supervisadoPor`),
ADD CONSTRAINT `fk_project_albaranId` FOREIGN KEY (`albaranId`) REFERENCES `Albaran` (`id`),
ADD CONSTRAINT `fk_project_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
ADD CONSTRAINT `fk_project_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
ADD CONSTRAINT `fk_project_realizadoPor` FOREIGN KEY (`realizadoPor`) REFERENCES `User` (`id`),
ADD CONSTRAINT `fk_project_recibidoPor` FOREIGN KEY (`recibidoPor`) REFERENCES `User` (`id`),
ADD CONSTRAINT `fk_project_supervisadoPor` FOREIGN KEY (`supervisadoPor`) REFERENCES `User` (`id`);

-- Fix to Publication table
ALTER TABLE `Publication`
ADD COLUMN tutorialId INTEGER;

-- Changes to User model
ALTER TABLE `User`
ADD COLUMN `cargo` VARCHAR(255),
ADD COLUMN `organizationId` INTEGER NOT NULL DEFAULT 1,
ADD CONSTRAINT `fk_user_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`);

-- New Table EnsayoCost
CREATE TABLE `EnsayoCost` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`organizationId` INTEGER NOT NULL,
`cost` DECIMAL(10,2) NOT NULL,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
PRIMARY KEY (`id`),
KEY `ndx_ensayoCost_organizationId` (`organizationId`),
CONSTRAINT `fk_ensayoCost_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `ProjectCost` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table EnsayoPrecio
CREATE TABLE `EnsayoPrecio` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`organizationId` INTEGER NOT NULL,
`ensayoId` INTEGER NOT NULL,
`cost` DECIMAL(10,2),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_ensayoPrecio_ensayoId` (`ensayoId`),
KEY `ndx_ensayoPrecio_organizationId` (`organizationId`),
CONSTRAINT `fk_ensayoPrecio_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
CONSTRAINT `fk_ensayoPrecio_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table EnsayoProcedimiento
CREATE TABLE `EnsayoProcedimiento` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`ensayoId` INTEGER NOT NULL,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_ensayoProcedimiento_ensayoId` (`ensayoId`),
CONSTRAINT `fk_ensayoProcedimiento_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table Historial
CREATE TABLE `Historial` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`idObjeto` INTEGER,
`fechaHora` DATETIME,
`klazz` VARCHAR(255),
`tipoCambio` VARCHAR(255),
`usuario` INTEGER,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_historial_usuario` (`usuario`),
CONSTRAINT `fk_historial_usuario` FOREIGN KEY (`usuario`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table OfferEnsayo
CREATE TABLE `OfferEnsayo` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`offerId` INTEGER NOT NULL,
`ensayoId` INTEGER NOT NULL,
`cost` DECIMAL(10,2) NOT NULL,
`unidades` INTEGER NOT NULL DEFAULT 1,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER NOT NULL,
PRIMARY KEY (`id`),
KEY `ndx_offerEnsayo_ensayoId` (`ensayoId`),
KEY `ndx_offerEnsayo_offerId` (`offerId`),
CONSTRAINT `fk_offerEnsayo_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
CONSTRAINT `fk_offerEnsayo_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table Pauta
CREATE TABLE `Pauta` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`agrupacion` VARCHAR(255),
`clienteId` INTEGER,
`cost` DECIMAL(10,2),
`description` VARCHAR(2048),
`ensayosSolicitados` VARCHAR(2048),
`familia` VARCHAR(255),
`material` VARCHAR(255),
`name` VARCHAR(255) NOT NULL COMMENT 'Ensayo descriptive name',
`referenciaCliente` VARCHAR(64),
`tiempoRespuesta` INTEGER,
`titulo` VARCHAR(255),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `fk_pauta_clienteId` (`clienteId`),
CONSTRAINT `fk_pauta_clienteId` FOREIGN KEY (`clienteId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='ensayos';

-- New table OfferPauta
CREATE TABLE `OfferPauta` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`cost` DECIMAL(10,2) NOT NULL,
`offerId` INTEGER NOT NULL,
`pautaId` INTEGER NOT NULL,
`unidades` INTEGER NOT NULL DEFAULT 1,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER NOT NULL,
PRIMARY KEY (`id`),
KEY `ndx_offerPauta_offerId` (`offerId`),
KEY `ndx_offerPauta_pautaId` (`pautaId`),
CONSTRAINT `fk_offerPauta_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
CONSTRAINT `fk_offerPauta_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table PautaEnsayo
CREATE TABLE `PautaEnsayo` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`ensayoId` INTEGER NOT NULL,
`procedimiento` VARCHAR(500),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`pautaId` INTEGER NOT NULL,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_pautaEnsayo_ensayoId` (`ensayoId`),
KEY `ndx_pautaEnsayo_pautaId` (`pautaId`),
CONSTRAINT `fk_pautaEnsayo_ensayoId` FOREIGN KEY (`ensayoId`) REFERENCES `Ensayo` (`id`),
CONSTRAINT `fk_pautaEnsayo_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table ParametroString
-- XXX: TODO: Cambiar el nombre de este modelo y tabla por algo mas descriptivo
CREATE TABLE `ParametroString` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`incertidumbre` VARCHAR(255),
`nombreIngles` VARCHAR(255),
`nombre` VARCHAR(255),
`pautaEnsayoId` INTEGER,
`requerimiento` VARCHAR(800),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER NOT NULL,
PRIMARY KEY (`id`),
KEY `ndx_parametroString_pautaEnsayoId` (`pautaEnsayoId`),
CONSTRAINT `fk_parametroString_pautaEnsayoId` FOREIGN KEY (`pautaEnsayoId`) REFERENCES `PautaEnsayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table PautaEnsayoDimension
CREATE TABLE `PautaEnsayoDimension` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(255),
`pautaEnsayoId` INTEGER NOT NULL,
`valor` VARCHAR(255),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_pautaEnsayoDimension_pautaEnsayoId` (`pautaEnsayoId`),
CONSTRAINT `fk_pautaEnsayoDimension_pautaEnsayoId` FOREIGN KEY (`PautaEnsayoId`) REFERENCES `PautaEnsayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table PautaIdentificacion
CREATE TABLE `PautaIdentificacion` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`pautaId` INTEGER NOT NULL,
`valor` VARCHAR(255) NOT NULL,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_pautaIdentificacion_pautaId` (`pautaId`),
CONSTRAINT `fk_pautaIdentificacion_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table PautaPrecio
CREATE TABLE `PautaPrecio` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`cost` DECIMAL(10,2),
`organizationId` INTEGER NOT NULL,
`pautaId` INTEGER NOT NULL,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_pautaPrecio_organizationId` (`organizationId`),
KEY `ndx_paitaPrecio_pautaId` (`pautaId`),
CONSTRAINT `fk_pautaPrecio_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
CONSTRAINT `fk_pautaPrecio_pautaId` FOREIGN KEY (`pautaId`) REFERENCES `Pauta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table ProductoPrecio
CREATE TABLE `ProductoPrecio` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`cost` DECIMAL(19,2),
`organizationId` INTEGER NOT NULL,
`producto` VARCHAR(255) NOT NULL,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_produtoPrecio_organizationId` (`organizationId`),
CONSTRAINT `fk_productoPrecio_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table ProjectEnsayoDimension
CREATE TABLE `ProjectEnsayoDimension` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(255),
`projectEnsayoId` INTEGER,
`valor` VARCHAR(255),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_projectEnsayoDimension_projectEnsayoId` (`projectEnsayoId`),
CONSTRAINT `ndx_projectEnsayoDimension_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table ProjectEnsayoIdentificacion
CREATE TABLE `ProjectEnsayoIdentificacion` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`projectEnsayoId` INTEGER,
`valor` VARCHAR(255) NOT NULL,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_projectEnsayoIdentificacion_projectEnsayoId` (`projectEnsayoId`),
CONSTRAINT `fk_projectEnsayoIdentificacion_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table ProjectEnsayoRequerimiento
CREATE TABLE `ProjectEnsayoRequerimiento` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`conformidad` VARCHAR(255),
`nombreIngles` VARCHAR(255),
`nombre` VARCHAR(255),
`projectEnsayoId` INTEGER,
`requerimiento` VARCHAR(800),
`valor` VARCHAR(255),
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_projectEnsayoRequerimiento_projectEnsayoId` (`projectEnsayoId`),
CONSTRAINT `fk_projectEnsayoRequerimiento_projectEnsayoId` FOREIGN KEY (`projectEnsayoId`) REFERENCES `ProjectEnsayo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- New Table ProjectIdentificacion
CREATE TABLE `ProjectIdentificacion` (
`id` INTEGER NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`projectId` INTEGER,
`valor` VARCHAR(255) NOT NULL,
`ownerId` INTEGER,
`departmentId` INTEGER UNSIGNED,
`insertDate` DATETIME,
`updateDate` DATETIME,
`updatedById` INTEGER,
`listindex` INTEGER,
PRIMARY KEY (`id`),
KEY `ndx_projectIdentification_projectId` (`projectId`),
CONSTRAINT `fk_projectIdentificacion_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Data changes ::
-- Empresa laboratorio
INSERT INTO `Organization` (`organizationTypeId`, `organizationISOCategoryId`, `name`, `provinceId`)
VALUES (2, 1, 'Nuestro laboratorio', 28);

-- Helpers functions for reports and so on
DELIMITER $$

DROP FUNCTION IF EXISTS `coste_project_sin_iva`$$
CREATE FUNCTION `coste_project_sin_iva` (project_Id int) RETURNS DECIMAL(19,2)
BEGIN
return (select coalesce(sum(pc.cost),0)+
coalesce(sum(pr.costPerHour*pr.expectedHours),0)+
coalesce(sum(pe.cost),0)
from Project p
left join ProjectCost pc on pc.projectId = p.id
left join ProjectRole pr on pr.projectId = p.id
left join ProjectEnsayo pe on pe.projectId = p.id
where p.id = project_Id
group by p.id);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `coste_albaran_sin_iva`$$
CREATE FUNCTION `coste_albaran_sin_iva` (albaran_Id int) RETURNS DECIMAL(19,2)
BEGIN
return (select sum(coalesce(p.costeAFacturar, coste_project_sin_iva(p.id), 0))
from Albaran a
left join Project p on p.albaranId = a.id
where a.id = albaran_Id
group by a.id);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `coste_presupuesto_sin_ensayos_ni_projectos_sin_iva`$$
CREATE FUNCTION `coste_presupuesto_sin_ensayos_ni_projectos_sin_iva` (offer_Id int) RETURNS DECIMAL(19,2)
BEGIN
return (select coalesce(sum(ofr.costPerHour*ofr.expectedHours),0)+
coalesce(sum(oc.cost*oc.billable),0)
From Offer o
left join OfferRole ofr on ofr.offerId = o.id
left join OfferCost oc on oc.offerId = o.id
where o.id = offer_Id
group by o.id);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `coste_factura_sin_iva`$$
CREATE FUNCTION `coste_factura_sin_iva` (bill_Id int) RETURNS DECIMAL(19,2)
BEGIN
return (SELECT sum(coalesce(bb.units*bb.amount,0))+
sum(coalesce(coste_albaran_sin_iva(a.id),0))+
sum(coalesce(coste_presupuesto_sin_ensayos_ni_projectos_sin_iva(o.id),0))
FROM Bill b
left join BillBreakDown bb on b.id=bb.billId
left join Albaran a on b.id=a.billId
left join Offer o on b.id=o.billId
where b.id=bill_Id
group by b.id);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `coste_project_sin_costes_ni_tareas_sin_iva`$$
CREATE FUNCTION `coste_project_sin_costes_ni_tareas_sin_iva` (project_Id int) RETURNS DECIMAL(19,2)
BEGIN
return (select coalesce(p.costeAFacturar,sum(pe.cost),0)
from Project p
left join ProjectEnsayo pe on pe.projectId = p.id
where p.id = project_Id
group by p.id);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `nulo`$$
CREATE FUNCTION `nulo` (texto text) RETURNS BOOL
BEGIN
return (select case when coalesce(texto,'')='' then 1 else 0 end);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `informe_tiene_pauta`$$
CREATE FUNCTION `informe_tiene_pauta` (project_Id int) RETURNS BOOL
BEGIN
return (select case when exists (select * from Pauta pa where pa.name=p.especificacion and not nulo(pa.name)) then 1 else 0 end
from Project p where p.id = project_Id);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `informe_tiene_familia`$$
CREATE FUNCTION `informe_tiene_familia` (project_Id int) RETURNS BOOL
BEGIN
return (select case when exists (select * from Pauta pa where pa.name=p.especificacion and not nulo(pa.name) and not nulo(pa.familia)) then 1 else 0 end
from Project p where p.id = project_Id);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `informe_estado`$$
CREATE FUNCTION `informe_estado` (project_Id int) RETURNS TEXT
BEGIN
return (select
case

when exists (select *
from Project p join Albaran a on (p.albaranId=a.id)
join Bill b on (a.billId=b.id)
where p.id=project_Id)
then 'ALBARAN_FACTURADO'

when exists (select *
from Project p join Albaran a on (p.albaranId=a.id)
where p.id=project_Id
and a.billId is null)
then 'EN_ALBARAN'

when not exists (select *
from ProjectEnsayo pe
where pe.projectId=id
and pe.notaSalidaId is null)
then 'TOTAL_NS'

when exists (select *
from ProjectEnsayo pe
where pe.projectId=id
and pe.notaSalidaId is not null)
then 'PARCIAL_NS'

else 'PENDIENTE'

end
collate utf8_spanish_ci
);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `bill_lineatrabajo_sugerida`$$
CREATE FUNCTION `bill_lineatrabajo_sugerida` (bill_Id int) RETURNS INT
BEGIN
return (select coalesce(
(select distinct b.lineaTrabajoId from Bill b
where b.id = bill_Id limit 1),
(select distinct o.lineaTrabajoId from Albaran a join Project p on p.albaranId = a.id join Offer o on p.offerId = o.id
where a.billId = bill_Id limit 1),
(select distinct o.lineaTrabajoId from Offer o
where o.billId = bill_Id limit 1),
1) as lineaTrabajoId);
END$$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `estado_de_informe`$$
CREATE FUNCTION `estado_de_informe` (projectId int) RETURNS VARCHAR(30) CHARSET utf8
BEGIN
return (
select (case

when (not con_albaran
and not con_factura
and alguno_sin_realizar
) then 'PENDIENTE'

when (not con_albaran
and not con_factura
and ninguno_sin_realizar
and todos_sin_nota_salida
) then 'PEND_ENV_LABORATORIO'

when (informe.billable
and not con_albaran
and not con_factura
and ninguno_sin_realizar
and not todos_sin_nota_salida
and not alguno_sin_nota_salida
) then 'PEND_ENV_EMPRESA'

when (not con_albaran
and not con_factura
and ninguno_sin_realizar
and not todos_sin_nota_salida
and alguno_sin_nota_salida
) then 'PARCIAL_NS'

when (billable
and con_albaran
and not con_factura
) then 'EN_ALBARAN'

when (billable
and con_factura
and pendienteDePedido
) then 'EN_FACTURA_PEND_PEDIDO'

when (billable
and con_factura
) then 'FACTURADO'

when (not billable
) then 'NO_FACTURABLE'

else 'DESCONOCIDO'

end)
from (
select
informe.billable,
informe.albaranId is not null as con_albaran,
factura.id is not null as con_factura,
factura.pendienteDePedido,
-1 = ANY (select coalesce(pe.realizadoFecha, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as alguno_sin_realizar,
-1 != ALL (select coalesce(pe.realizadoFecha, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as ninguno_sin_realizar,
-1 = ALL (select coalesce(pe.notaSalidaId, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as todos_sin_nota_salida,
-1 = ANY (select coalesce(pe.notaSalidaId, -1) from ProjectEnsayo pe where pe.projectId = informe.id) as alguno_sin_nota_salida

from Project informe
left join Albaran albaran on (informe.albaranId = albaran.id)
left join Bill factura on (informe.billId = factura.id or albaran.billId = factura.id)
where informe.id = projectId
) informe
);
END$$

DELIMITER ;

-- Helper views

CREATE VIEW Department_hijos AS
SELECT d0.id as padre, d0.id as hijo
FROM Department d0
union
SELECT d0.id as padre, d1.id as hijo
FROM Department d0
join Department d1 on d1.parentId = d0.id
union
SELECT d0.id as padre, d2.id as hijo
FROM Department d0
join Department d1 on d1.parentId = d0.id
join Department d2 on d2.parentId = d1.id
union
SELECT d0.id as padre, d3.id as hijo
FROM Department d0
join Department d1 on d1.parentId = d0.id
join Department d2 on d2.parentId = d1.id
join Department d3 on d3.parentId = d2.id
union
SELECT d0.id as padre, d4.id as hijo
FROM Department d0
join Department d1 on d1.parentId = d0.id
join Department d2 on d2.parentId = d1.id
join Department d3 on d3.parentId = d2.id
join Department d4 on d4.parentId = d3.id
union
SELECT d0.id as padre, d5.id as hijo
FROM Department d0
join Department d1 on d1.parentId = d0.id
join Department d2 on d2.parentId = d1.id
join Department d3 on d3.parentId = d2.id
join Department d4 on d4.parentId = d3.id
join Department d5 on d5.parentId = d4.id
union
SELECT d0.id as padre, d6.id as hijo
FROM Department d0
join Department d1 on d1.parentId = d0.id
join Department d2 on d2.parentId = d1.id
join Department d3 on d3.parentId = d2.id
join Department d4 on d4.parentId = d3.id
join Department d5 on d5.parentId = d4.id
join Department d6 on d6.parentId = d5.id
;

CREATE VIEW Project_Estado AS (SELECT id AS id, estado_de_informe(id) COLLATE utf8_spanish_ci AS estado FROM Project);

-- Update version number
update Version set version='0.9';
