CREATE TABLE user_details
(
  id_details serial NOT NULL,
  acl_users_id integer NOT NULL,
  countbanc numeric(20,0) DEFAULT NULL::numeric,
  clabe character varying(45) DEFAULT NULL::character varying,
  branch character varying(45) DEFAULT NULL::character varying,
  namebanc character varying(45) DEFAULT NULL::character varying,
  cost numeric(10,2) DEFAULT NULL::numeric,
  period numeric(5,0) DEFAULT NULL::numeric,
  mannerofpayment numeric(5,0) DEFAULT NULL::numeric,
  birthday date DEFAULT '0001-01-01'::date,
  local_phone character varying(45) DEFAULT NULL::character varying,
  cellphone character varying(45) DEFAULT NULL::character varying,
  date_admission date,
  contract_type numeric(2,0) NOT NULL,
  number_ss character varying(50) NOT NULL,
  photofile_ife character varying NOT NULL,
  photofile_license character varying NOT NULL,
  photofile_certification character varying NOT NULL,
  CONSTRAINT primary_key_user_details PRIMARY KEY (id_details),
  CONSTRAINT fk_details_to_acl_users FOREIGN KEY (acl_users_id)
      REFERENCES iof_users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_details
  OWNER TO postgres;
