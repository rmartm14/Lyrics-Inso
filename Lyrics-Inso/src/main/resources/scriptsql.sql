CREATE DATABASE IF NOT EXISTS lyrics;
USE lyrics;

CREATE TABLE IF NOT EXISTS users(
	user_id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NULL,
	email varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	grade float NOT NULL,
	fecha_nacimiento date NOT NULL,
	PRIMARY KEY(user_id),
	UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS styles(
	style_id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	characteristics varchar(500) NOT NULL,
	PRIMARY KEY(style_id),
	UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS instruments(
	instrument_id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	instrument_style int NOT NULL,
	price float NULL,
	PRIMARY KEY(instrument_id),
	UNIQUE(name),
	FOREIGN KEY(instrument_style) REFERENCES styles(style_id)
	);

CREATE TABLE IF NOT EXISTS artists(
	artist_id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	artist_style int NOT NULL,
	PRIMARY KEY(artist_id), 
	FOREIGN KEY(artist_style) REFERENCES styles(style_id)
);

CREATE TABLE IF NOT EXISTS groups(
	group_id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	group_style int NOT NULL,
	PRIMARY KEY(group_id),
	FOREIGN KEY(group_style) REFERENCES styles(style_id)
);

CREATE TABLE IF NOT EXISTS SONGS(
	song_id int NOT NULL,
	user_id int NOT NULL,
	group_id int NOT NULL,
	song_style int NOT NULL,
	name varchar(50) NOT NULL,
	lyrics varchar(500) NOT NULL,
	grade float NOT NULL,
	PRIMARY KEY(song_id),
	FOREIGN KEY(user_id) REFERENCES users(user_id),
	FOREIGN KEY(group_id) REFERENCES groups(group_id),
	FOREIGN KEY(song_style) REFERENCES styles(style_id)
);
