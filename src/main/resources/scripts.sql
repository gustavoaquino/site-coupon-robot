CREATE SEQUENCE cupom.company_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE cupom.coupon_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE cupom.log_error_generate_coupon_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE cupom.log_job_find_coupon_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE cupom.company (
	id int8 NOT NULL DEFAULT nextval('cupom.company_sequence'::regclass),
	uuid uuid NOT NULL,
	uri_social_soul varchar(100) NOT NULL,
	url_image_icon varchar(1000) NOT NULL,
	name_company varchar(100) NOT NULL,
	date_created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	date_excluded timestamp NULL,
	principal bool NULL DEFAULT false,
	CONSTRAINT pk_company_id PRIMARY KEY (id)
);


CREATE TABLE cupom.coupon (
	id int8 NOT NULL DEFAULT nextval('cupom.coupon_sequence'::regclass),
	uuid uuid NOT NULL,
	company_id int8 NULL,
	link_coupon varchar(5000) NULL,
	code_coupon varchar(100) NULL,
	description varchar(1000) NOT NULL,
	date_expiration timestamp NOT NULL,
	date_created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	date_excluded timestamp NULL,
	CONSTRAINT pk_coupon_id PRIMARY KEY (id)
);

ALTER TABLE cupom.coupon ADD CONSTRAINT fk_coupon_company_id FOREIGN KEY (company_id) REFERENCES cupom.company(id);

CREATE TABLE cupom.log_error_generate_coupon (
	id int8 NULL DEFAULT nextval('cupom.log_error_generate_coupon_sequence'::regclass),
	date_created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	company_id int8 NULL,
	type_error varchar(5000) NULL
);

CREATE TABLE cupom.log_job_find_coupon (
	id int8 NULL DEFAULT nextval('cupom.log_job_find_coupon_sequence'::regclass),
	date_created timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	message varchar(255) NULL
);



