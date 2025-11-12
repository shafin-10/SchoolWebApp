CREATE TABLE IF NOT EXISTS holidays (
  day varchar(20) NOT NULL,
  reason varchar(100) NOT NULL,
  type varchar(20) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by varchar(50) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by varchar(50) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS roles (
  role_id SERIAL PRIMARY KEY,
  role_name VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at TIMESTAMP DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL
);


