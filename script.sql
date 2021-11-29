CREATE TABLE IF NOT EXISTS users
(
id UUID NOT NULL,
name VARCHAR(100) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(50) NOT NULL,
created TIMESTAMP NOT NULL,
modified TIMESTAMP NOT NULL,
last_login TIMESTAMP NOT NULL,
active Boolean NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS phones
(
id UUID NOT NULL,
number VARCHAR(7) NOT NULL,
city_code VARCHAR(3) NOT NULL,
country_code VARCHAR(3) NOT NULL,
id_user UUID NOT NULL,
FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE,
PRIMARY KEY(id)
);

insert into users(id, name,email, password, created, modified, last_login, active) 
values('8a59d9547e5b4d9ca0a30804e8a33a94', 'system', 'system@system.cl','system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true);
