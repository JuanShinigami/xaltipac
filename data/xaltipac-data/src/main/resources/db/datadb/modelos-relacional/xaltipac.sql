SET SESSION FOREIGN_KEY_CHECKS=0;


/* Create Tables */

CREATE TABLE actions
(
	id int NOT NULL AUTO_INCREMENT,
	id_resource int NOT NULL,
	id_profile int NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE days
(
	id int NOT NULL AUTO_INCREMENT,
	day date NOT NULL,
	total numeric(10,2) DEFAULT 0.00 NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE groups
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);


CREATE TABLE offerings
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(60) NOT NULL,
	description varchar(500) NOT NULL,
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);


CREATE TABLE offering_details
(
	id int NOT NULL AUTO_INCREMENT,
	rode numeric(8,2) NOT NULL,
	id_offering int NOT NULL,
	id_day int NOT NULL,
	id_user_detail int NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE profiles
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);


CREATE TABLE resources
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	path varchar(100) NOT NULL,
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);


CREATE TABLE settings
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	value varchar(50) NOT NULL,
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);


CREATE TABLE users
(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	password varchar(200) NOT NULL,
	enabled int(1) DEFAULT 1 NOT NULL,
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	deleted int(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (username)
);


CREATE TABLE users_detail
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	lastname varchar(50) NOT NULL,
	lastname_maternal varchar(50) NOT NULL,
	email varchar(100) NOT NULL,
	age int(3) NOT NULL,
	gender char(1) NOT NULL,
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	deleted int(1) DEFAULT 0 NOT NULL,
	id_user int NOT NULL,
	id_group int NOT NULL,
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE offering_details
	ADD FOREIGN KEY (id_day)
	REFERENCES days (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE users_detail
	ADD FOREIGN KEY (id_group)
	REFERENCES groups (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE offering_details
	ADD FOREIGN KEY (id_offering)
	REFERENCES offerings (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE actions
	ADD FOREIGN KEY (id_profile)
	REFERENCES profiles (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE actions
	ADD FOREIGN KEY (id_resource)
	REFERENCES resources (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE users_detail
	ADD FOREIGN KEY (id_user)
	REFERENCES users (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE offering_details
	ADD FOREIGN KEY (id_user_detail)
	REFERENCES users_detail (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



