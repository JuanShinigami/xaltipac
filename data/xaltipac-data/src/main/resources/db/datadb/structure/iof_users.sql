CREATE TABLE iof_users
(
  user_id serial NOT NULL,
  priveteKey numeric(10,0),
  id_company integer,
  user_type character varying(100),
  name character varying(100) NOT NULL,
  surname character varying(100) NOT NULL,
  lastname character varying(100) NOT NULL,
  datebirth date NOT NULL DEFAULT '0001-01-01'::date,
  email character varying(100) NOT NULL,
  numberEmployee character varying(7),
  rfc character varying(35),
  phone numeric(11,0) NOT NULL,
  user_name character varying(50),
  password character varying(100) NOT NULL,
  password_salt character varying(32),
  status character varying(1) NOT NULL DEFAULT 'Y'::character varying,
  created_on time with time zone NOT NULL DEFAULT now(),
  modified_on date NOT NULL,
  isDetailed integer,
  photofilename character varying,
  photofile character varying,
  id_job numeric(4,0),
  canlogin numeric(11,0),
  id_department numeric(11,0),
  user_principal numeric(11,0),
  avatar character varying,
  curp character varying(20) NOT NULL,
  discount_ss numeric(10,2) NOT NULL,
  CONSTRAINT primary_key_iof_users PRIMARY KEY (user_id),
  CONSTRAINT fk_id_company FOREIGN KEY (id_company)
      REFERENCES company (id_company) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE iof_users
  OWNER TO postgres;