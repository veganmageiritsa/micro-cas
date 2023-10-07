use connectivity;

CREATE TABLE customer (
	customer_id uuid,
	title text,
	description text,
	release_date timestamp,
	release_year int,
	genres set<text>,

	PRIMARY KEY(customer_id)
);

-- CREATE CUSTOM INDEX videos_by_release_year_idx ON videos(release_year) USING 'org.apache.cassandra.index.sasi.SASIIndex';

CREATE TABLE IF NOT EXISTS customer (
	customer_id int   PRIMARY KEY,
	name text NOT NULL,
	email varchar(100) NOT NULL,
	mobile_number varchar(20) NOT NULL,
	created_at date NOT NULL,
	created_by varchar(20) NOT NULL,
	updated_at date DEFAULT NULL,
	updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
	customer_id int NOT NULL,
	account_number int   PRIMARY KEY,
	account_type varchar(100) NOT NULL,
	branch_address varchar(200) NOT NULL,
	created_at date NOT NULL,
	created_by varchar(20) NOT NULL,
	updated_at date DEFAULT NULL,
	updated_by varchar(20) DEFAULT NULL
);
