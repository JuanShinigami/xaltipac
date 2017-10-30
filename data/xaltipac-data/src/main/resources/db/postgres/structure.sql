CREATE TABLE actions (
  id serial not null,
  id_profile numeric(11,0) NOT NULL,
  id_resource numeric(11,0) NOT NULL,
  CONSTRAINT actions_pkey PRIMARY KEY (id)
);


CREATE TABLE profiles
(
  id serial NOT NULL,
  name character varying(20) NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT now(),
  last_modified timestamp without time zone NOT NULL DEFAULT '0001-01-01 00:00:00'::timestamp without time zone,
  CONSTRAINT profiles_pkey PRIMARY KEY (id)
);


CREATE TABLE resources (
  id serial not null,
  name character varying(45) DEFAULT NULL,
  path character varying(45) DEFAULT NULL,
  CONSTRAINT resources_pkey PRIMARY KEY (id)
);


CREATE TABLE settings (
  id serial not null,
  name character varying(15) NOT NULL,
  value character varying NOT NULL,
  created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified timestamp NOT NULL DEFAULT '0001-01-01 00:00:00',
  CONSTRAINT settings_pkey PRIMARY KEY (id)
);





CREATE TABLE users
(
  id serial NOT NULL,
  username character varying(16) NOT NULL,
  password character varying(60) NOT NULL,
  id_profile numeric(11,0) NOT NULL,
  id_business numeric(11,0) NOT NULL DEFAULT 0,
  enabled numeric(1,0) NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT now(),
  last_modified timestamp without time zone NOT NULL DEFAULT '0001-01-01 00:00:00'::timestamp without time zone,
  deleted numeric(1,0) NOT NULL DEFAULT 0,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);



CREATE TABLE users_detail
(
  id serial NOT NULL,
  id_user numeric(11,0) NOT NULL,
  name character varying(60) NOT NULL,
  lastname character varying(60) NOT NULL,
  lastname_maternal character varying(60) DEFAULT ''::character varying,
  email character varying(80) NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT now(),
  last_modified timestamp without time zone NOT NULL DEFAULT '0001-01-01 00:00:00'::timestamp without time zone,
  deleted numeric(1,0) NOT NULL DEFAULT 0,
  CONSTRAINT users_detail_pkey PRIMARY KEY (id)
);


CREATE TABLE bands
(
 id serial NOT NULL,
 type character varying(30),
 name character varying(100),
 serie numeric(5,0),
 cost numeric(8,2),
 tab numeric(5,2),
 weight numeric(5,2),
 minimum_mapping numeric(2,2),
 minimum_entered numeric(2,2),
 coefficient_friction numeric(2,2),
 friction_band_profile numeric(2,2),
 minimal_intermediate_section numeric(3,0),
 minimal_section_gone_out numeric(2,2),
 resistance_band numeric(7,2),
 model_band character varying(50),
 material character varying(100),
 CONSTRAINT bands_pkey PRIMARY KEY (id)
);


CREATE TABLE system_retractil
(
 id serial NOT NULL,
 name character varying(100) NOT NULL,
 code character varying(30) NOT NULL,
 pieces numeric(10,0) NOT NULL DEFAULT 0,
 width numeric(10,2) NOT NULL DEFAULT 0.0,
 length numeric(10,2) NOT NULL DEFAULT 0.0,
 si numeric(10,0),
 mt2 numeric(10,10),
 wg numeric(10,2),
 quantity numeric(10,10),
 quantity_total numeric(10,2),
 unit character varying(5),
 cost numeric(20,2),
 cost_current numeric(20,2),
 cost_total numeric(20,2),
 national_currency numeric(20,2),
 CONSTRAINT system_retractil_pkey PRIMARY KEY (id)
);