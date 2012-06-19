CREATE TABLE Permission(
	PKPermission	VARCHAR(36) PRIMARY KEY,
	Description	VARCHAR(512) NOT NULL
) engine=InnoDB;

CREATE TABLE ProfilePermission(
	FKProfile VARCHAR(36),
	FKPermission VARCHAR(36),
	PRIMARY KEY (FKProfile, FKPermission)
) engine=InnoDB;

CREATE TABLE Profile(
	PKProfile VARCHAR(36) PRIMARY KEY,
	Name VARCHAR(64) NOT NULL
) engine=InnoDB;

CREATE TABLE Account(
	ID	VARCHAR(36) PRIMARY KEY,
	Password TEXT NOT NULL,
	UserName	VARCHAR(512) NOT NULL,
	FKProfile	VARCHAR(36)
) engine=InnoDB;

CREATE TABLE Area(
	PKArea	VARCHAR(36) PRIMARY KEY,
	FKMainArea VARCHAR(36),
	Name VARCHAR(512) NOT NULL,
	Description VARCHAR(512)
) engine=InnoDB;

CREATE TABLE Advisor(
	PKAdvisor	VARCHAR(36) PRIMARY KEY,
	Name	VARCHAR(512) NOT NULL,
	Email VARCHAR (512) NOT NULL
) engine=InnoDB;

CREATE TABLE Status(
	PKStatus	VARCHAR(36) PRIMARY KEY,
	Description	VARCHAR(512) NOT NULL
) engine=InnoDB;

CREATE TABLE Student(
	PKStudent	VARCHAR(36) PRIMARY KEY,
	Name	VARCHAR(512) NOT NULL,
	Email VARCHAR (512) NOT NULL
) engine=InnoDB;

CREATE TABLE StudentCourse(
	FKStudent	VARCHAR(36),
	FKCourse	VARCHAR(36),
	PRIMARY KEY (FKStudent, FKCourse)
) engine=InnoDB;

CREATE TABLE Documentation(
	PKDocumentation	VARCHAR(36) PRIMARY KEY,
	Filename	VARCHAR(64) NOT NULL,
	Data	BLOB NOT NULL
) engine=InnoDB;

CREATE TABLE HandIn(
	PKHandIn	VARCHAR(36) PRIMARY KEY,
	FKMonograph	VARCHAR(36),
	FKActivity	VARCHAR(36),
	FKDocumentation	VARCHAR(36),
	HandedOn	DATE NOT NULL,
	Grade	VARCHAR(512),
	Remark	VARCHAR(512)
) engine=InnoDB;

CREATE TABLE Activity(
	PKActivity	VARCHAR(36) PRIMARY KEY,
	Title	VARCHAR(512) NOT NULL,
	Description	VARCHAR(512),
	DueDate	DATE,
	FKStage	VARCHAR(36)
) engine=InnoDB;

CREATE TABLE Stage(
	PKStage	VARCHAR(36) PRIMARY KEY,
	Name VARCHAR(512) NOT NULL,
	Year VARCHAR (5) NOT NULL
) engine=InnoDB;

CREATE TABLE MonographStage(
	FKMonograph	VARCHAR(36),
	FKStage	VARCHAR(36),
	PRIMARY KEY (FKMonograph, FKStage)
) engine=InnoDB;

CREATE TABLE Monograph(
	PKMonograph	VARCHAR(36) PRIMARY KEY,
	FKArea	VARCHAR(36),
	FKStudent VARCHAR(36),
	FKCourse	VARCHAR(36),
	FKAdvisor	VARCHAR(36),
	FKCoadvisor	VARCHAR(36),
	FKStatus	VARCHAR(36),
	Title	VARCHAR(512) NOT NULL
) engine=InnoDB;

CREATE TABLE Course(
	PKCourse	VARCHAR(36) PRIMARY KEY,
	Name	VARCHAR(512) NOT NULL,
	Turn VARCHAR (512) NOT NULL,
	Year VARCHAR (5) NOT NULL
) engine=InnoDB;



ALTER TABLE ProfilePermission
	ADD CONSTRAINT fk_profilePermission_profile FOREIGN KEY (FKProfile) REFERENCES Profile(PKProfile),
	ADD CONSTRAINT fk_profilePermission_permission FOREIGN KEY (FKPermission) REFERENCES Permission(PKPermission);

ALTER TABLE Account
	ADD CONSTRAINT fk_account_profile FOREIGN KEY (FKProfile) REFERENCES Profile(PKProfile);

ALTER TABLE Area
	ADD CONSTRAINT fk_area_mainArea FOREIGN KEY (FKMainArea) REFERENCES Area(PKArea);

ALTER TABLE StudentCourse
	ADD CONSTRAINT fk_studentCourse_student FOREIGN KEY (FKStudent) REFERENCES Student(PKStudent),
	ADD CONSTRAINT fk_studentCourse_course FOREIGN KEY (FKCourse) REFERENCES Course(PKCourse);

ALTER TABLE HandIn
	ADD CONSTRAINT fk_handIn_monograph FOREIGN KEY (FKMonograph) REFERENCES Monograph(PKMonograph),
	ADD CONSTRAINT fk_handIn_activity FOREIGN KEY (FKActivity) REFERENCES Activity(PKActivity),
	ADD CONSTRAINT fk_handIn_documentation FOREIGN KEY (FKDocumentation) REFERENCES Documentation(PKDocumentation);

ALTER TABLE Activity
	ADD CONSTRAINT fk_activity_stage FOREIGN KEY (FKStage) REFERENCES Stage(PKStage);

ALTER TABLE MonographStage
	ADD CONSTRAINT fk_monographStage_monograph FOREIGN KEY (FKMonograph) REFERENCES Monograph(PKMonograph),
	ADD CONSTRAINT fk_monographStage_stage FOREIGN KEY (FKStage) REFERENCES Stage(PKStage);

ALTER TABLE Monograph
	ADD CONSTRAINT fk_monograph_area FOREIGN KEY (FKArea) REFERENCES Area(PKArea),
	ADD CONSTRAINT fk_monograph_student FOREIGN KEY (FKStudent) REFERENCES Student(PKStudent),
	ADD CONSTRAINT fk_monograph_course FOREIGN KEY (FKCourse) REFERENCES Course(PKCourse),
	ADD CONSTRAINT fk_monograph_advisor FOREIGN KEY (FKAdvisor) REFERENCES Advisor(PKAdvisor),
	ADD CONSTRAINT fk_monograph_coadvisor FOREIGN KEY (FKCoadvisor) REFERENCES Advisor(PKAdvisor),
	ADD CONSTRAINT fk_monograph_status FOREIGN KEY (FKStatus) REFERENCES Status(PKStatus);
