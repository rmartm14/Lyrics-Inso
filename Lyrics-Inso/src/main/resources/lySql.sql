CREATE DATABASE IF NOT EXISTS lyrics;
USE lyrics;

CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER(50) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NULL,
    password VARCHAR(50) NULL,
    grade FLOAT not null,
    fecha_nacimiento date not null,
    role BIT(1) not null,
    PRIMARY KEY(user_id)
);
CREATE TABLE IF NOT EXISTS styles(
	style_id INTEGER(50) not null AUTO_INCREMENT,
    name varchar(50) null,
    characteristics varchar(500) null,
    PRIMARY KEY(style_id)
);
CREATE TABLE IF NOT EXISTS instruments(
	instrument_id INTEGER(50) not null AUTO_INCREMENT,
    style INTEGER(50) not null,
    name varchar(50) null,
    price float null,
    PRIMARY KEY(instrument_id),
    FOREIGN KEY(style) REFERENCES styles(style_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS groups1(
	group_id INTEGER(50) not null AUTO_INCREMENT,
    name varchar(50) null,
    style INTEGER(50) not null,
    PRIMARY KEY(group_id),
    FOREIGN KEY(style) REFERENCES styles(style_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS artists (
	artist_id INTEGER(50) not null AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    group_id INTEGER(50) not null,
    PRIMARY KEY(artist_id),
    FOREIGN KEY(group_id) REFERENCES groups1(group_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS songs (
	song_id INTEGER(50) NOT NULL AUTO_INCREMENT,
    user_id INTEGER(50) NOT NULL ,
    group_id INTEGER(50) NOT NULL ,
    style INTEGER(50) NOT NULL ,
    original bit(1) not null,
    visit_counter INTEGER not null,
    name varchar(50) not null,
    lyrics varchar(1000) not null,
    grade float not null,
    PRIMARY KEY(song_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY(group_id) REFERENCES groups1(group_id) ON DELETE CASCADE,
    FOREIGN KEY(style) REFERENCES styles(style_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS instruArtist(
	artist_id INTEGER(50) not null ,
    instrument_id INTEGER(50) not null ,
    FOREIGN KEY(artist_id) REFERENCES artists(artist_id) ON DELETE CASCADE,
    FOREIGN KEY(instrument_id) REFERENCES instruments(instrument_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS stylesGroups(
	style_id INTEGER(50) not null ,
    group_id INTEGER(50) not null ,
    FOREIGN KEY(style_id) REFERENCES styles(style_id) ON DELETE CASCADE,
    FOREIGN KEY(group_id) REFERENCES groups1(group_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS songInstr(
	song_id INTEGER(50) not null ,
    instrument_id INTEGER(50) not null ,
    FOREIGN KEY(song_id) REFERENCES songs(song_id) ON DELETE CASCADE,
    FOREIGN KEY(instrument_id) REFERENCES instruments(instrument_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS foros(
	foro_id INTEGER(50) not null AUTO_INCREMENT,
    song_id INTEGER(50) not null,
    PRIMARY KEY(foro_id),
    FOREIGN KEY(song_id) REFERENCES songs(song_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments(
    comment_id INTEGER(50) not null AUTO_INCREMENT,
    user_id INTEGER(50) not null ,
    foro_id INTEGER(50) not null ,
    commentContent varchar(500) null,
    PRIMARY KEY(comment_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY(foro_id) REFERENCES foros(foro_id) ON DELETE CASCADE
);

