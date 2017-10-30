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


