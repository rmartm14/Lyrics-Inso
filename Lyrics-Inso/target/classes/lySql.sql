CREATE DATABASE IF NOT EXISTS lyrics;
USE lyrics;

CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER(50) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    grade FLOAT not null DEFAULT 0.0,
    fecha_nacimiento datetime not null,
    role BIT(1) not null,
    PRIMARY KEY(user_id),
    UNIQUE(name)
);
DELETE FROM `users`;
INSERT INTO `users` (`user_id`, `name`, `password`, `grade`, `fecha_Nacimiento`, `role`) VALUES
	(1, 'maria', 'pass', '0.0', '2018-02-10 00:00:00', 1),
	(2, 'Antonio', 'pass', '0.0', '2018-01-27 00:03:33', 0),
	(3, 'Gabriel', 'pass', '0.0', '2018-01-31 17:34:31', 0);
CREATE TABLE IF NOT EXISTS styles(
	style_id INTEGER(50) not null AUTO_INCREMENT,
    name varchar(50) not null,
    characteristics varchar(500) null,
    PRIMARY KEY(style_id),
    UNIQUE(name)
);
DELETE FROM `styles`;
INSERT INTO `styles` (`style_id`, `name`, `characteristics`) VALUES
	(1, 'Pop', 'intenso'),
	(2, 'Rock', 'para coche'),
	(3, 'Dance', 'para bailar');
CREATE TABLE IF NOT EXISTS instruments(
	instrument_id INTEGER(50) not null AUTO_INCREMENT,
    name varchar(50) not null,
    price float null DEFAULT 0.0,
    instrument_style varchar(50) null,
    PRIMARY KEY(instrument_id),
    UNIQUE(name)
);
DELETE FROM `instruments`;
INSERT INTO `instruments` (`instrument_id`, `name`, `price`, `instrument_style`) VALUES
	(1, 'Piano', '400.0', 'Cuerda percutida'),
	(2, 'Pandereta', '20.0', 'Percusion'),
   (3, 'Guitarra clasica', '40.0', 'Cuerda'),
	(4, 'Guitarra Electrica', '90.0', 'Electrico'),
	(5, 'Bateria', '120.0', 'Percusion'),
	(6, 'Laud', '34.0', 'Cuerda'),
	(7, 'Arpa', '178.0', 'Cuerda'),
	(8, 'Platillos', '45.0', 'Percusion'),
	(9, 'Xilofono', '45.0', 'Percusion'),
	(10, 'Pandero', '39.5', 'Percusion'),
	(11, 'Triangulo', '29.75', 'Percusion'),
	(12, 'Bajo', '221.0', 'Electrico'),
	(13, 'Teclado', '87.0', 'Electrico'),
	(14, 'Caja de ritmos', '145.0', 'Electrico'),
	(15, 'Theremin', '257.9', 'Electrico'),
	(16, 'Sintetizdor', '135.9', 'Electrico'),
	(17, 'Gong', '323.75', 'Percusion'),
	(18, 'Bongo', '147.85', 'Percusion'),
	(19, 'Sitar', '193.85', 'Cuerda'),
	(20, 'Ukelele', '96.42', 'Cuerda'),
	(21, 'Violonchelo', '278.9', 'Cuerda'),
	(22, 'Violin', '234.85', 'Cuerda'),
	(23, 'Viola', '259.85', 'Cuerda'),
	(24, 'Whamola', '310.85', 'Cuerda'),
	(25, 'Citara', '178.85', 'Cuerda'),
	(26, 'Mandolina de Octava', '93.99', 'Cuerda'),
	(27, 'Lira', '159.99', 'Cuerda'),
	(28, 'Guitarra de Cuello Multiple', '356.99', 'Cuerda'),
	(29, 'Contrabajo', '482.99', 'Cuerda'),
	(30, 'Banjo', '65.99', 'Cuerda'),
	(31, 'Saxofon', '263.89', 'Viento'),
	(32, 'Tubax', '149.99', 'Viento'),
	(33, 'Sacabuche', '90.99', 'Viento'),
	(34, 'Didgeridoo', '325.56', 'Viento'),
	(35, 'Trombon', '367.99', 'Viento'),
	(36, 'Trompeta', '159.99', 'Viento'),
	(37, 'Tuba', '199.99', 'Viento'),
	(38, 'Flauta', '40.0', 'Viento'),
	(39, 'Flauta de pan', '69.0', 'Viento'),
	(40, 'Flautin', '56.0', 'Viento'),
	(41, 'Organo', '568.0', 'Viento'),
	(42, 'Ocarina', '25.0', 'Viento'),
	(43, 'Oboe', '163.7', 'Viento'),
	(44, 'Armonica', '39.8', 'Viento'),
	(45, 'Trompa', '174.8', 'Viento'),
	(46, 'Corneta', '98.8', 'Viento'),
	(47, 'Clarinete', '263.69', 'Viento'),
	(48, 'Fagot', '176.93', 'Viento'),
	(49, 'Gaita', '249.59', 'Viento'),
	(50, 'Acordeon', '347.93', 'Viento');
CREATE TABLE IF NOT EXISTS groups1(
	group_id INTEGER(50) not null AUTO_INCREMENT,
    name varchar(50) not null,
    PRIMARY KEY(group_id),
    UNIQUE(name)
);
DELETE FROM `groups1`;
INSERT INTO `groups1` (`group_id`, `name`) VALUES
	(1, 'ACPC'),
	(2, 'Parco'),
	(3, 'Intror');
CREATE TABLE IF NOT EXISTS artists (
	artist_id INTEGER(50) not null AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    group_id INTEGER(50) not null,
    PRIMARY KEY(artist_id),
    FOREIGN KEY(group_id) REFERENCES groups1(group_id) ON DELETE CASCADE,
    UNIQUE(NAME)
);
DELETE FROM `artists`;
INSERT INTO `artists` (`artist_id`, `name`,`group_id`) VALUES
	(1, 'Hasst',1),
	(2, 'Tranm',1),
	(3, 'Menis',2),
	(4, 'Wirt',3);
CREATE TABLE IF NOT EXISTS songs (
	song_id INTEGER(50) NOT NULL AUTO_INCREMENT,
    user_id INTEGER(50) NOT NULL ,
    group_id INTEGER(50) NOT NULL ,
    style INTEGER(50) NOT NULL ,
    original bit(1) not null,
    visit_counter INTEGER not null DEFAULT 0,
    name varchar(100) not null,
    lyrics varchar(1000) null,
    grade float null DEFAULT 0.0,
    PRIMARY KEY(song_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY(group_id) REFERENCES groups1(group_id) ON DELETE CASCADE,
    FOREIGN KEY(style) REFERENCES styles(style_id) ON DELETE CASCADE
);
DELETE FROM `songs`;
INSERT INTO `songs` (`song_id`, `user_id`,`group_id`,style,original, visit_counter, NAME, lyrics, grade) VALUES
	(1, 2, 1, 2, 0, 0, 'Cancion1', '1 2 3 arriba', 0.0 ),
	(2, 3, 1, 3, 1, 2, 'Oda a la vida',' vida feliz aqui',5.0 ),
	(3, 1, 2, 1, 0, 0, 'Surcando el mar', 'Bajo el sol del verano',0.0 ),
	(4, 2, 3, 3, 0, 0, 'Caminando descalzo', 'sin dinero en el bolsillo', 0.0 );
CREATE TABLE IF NOT EXISTS instruArtist(
	artist_id INTEGER(50) not null ,
    instrument_id INTEGER(50) not null ,
    FOREIGN KEY(artist_id) REFERENCES artists(artist_id) ON DELETE CASCADE,
    FOREIGN KEY(instrument_id) REFERENCES instruments(instrument_id) ON DELETE CASCADE
);
DELETE FROM `instruArtist`;
INSERT INTO `instruArtist` (`artist_id`, `instrument_id`) VALUES
	(1, 1),
	(2, 3),
	(3, 2),
	(4, 1);
CREATE TABLE IF NOT EXISTS stylesGroups(
	style_id INTEGER(50) not null ,
    group_id INTEGER(50) not null ,
    FOREIGN KEY(style_id) REFERENCES styles(style_id) ON DELETE CASCADE,
    FOREIGN KEY(group_id) REFERENCES groups1(group_id) ON DELETE CASCADE
);
DELETE FROM `stylesGroups`;
INSERT INTO `stylesGroups` (`style_id`, `group_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 2),
	(2, 3);
CREATE TABLE IF NOT EXISTS foros(
	foro_id INTEGER(50) not null AUTO_INCREMENT,
    song_id INTEGER(50) not null,
    PRIMARY KEY(foro_id),
    FOREIGN KEY(song_id) REFERENCES songs(song_id) ON DELETE CASCADE
);
DELETE FROM `foros`;
INSERT INTO `foros` (`foro_id`, `song_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4);
CREATE TABLE IF NOT EXISTS comments(
    comment_id INTEGER(50) not null AUTO_INCREMENT,
    user_id INTEGER(50) not null ,
    foro_id INTEGER(50) not null ,
    commentContent varchar(500) null,
    fecha_creacion datetime not null DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(comment_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY(foro_id) REFERENCES foros(foro_id) ON DELETE CASCADE
);
DELETE FROM `comments`;
INSERT INTO `comments` (`comment_id`, `user_id`, `foro_id`,`commentContent`, `fecha_creacion`) VALUES
	(1, 2, 2, 'Me ha gustado la cancion', '2018-04-10 09:00:00'),
	(2, 2, 3, 'Creo que hay algunas letras mal puestas', '2019-05-10 12:00:00'),
	(3, 1, 4, 'La odio le doy 0', '2019-06-14 7:00:00'),
	(4, 3, 1, 'Siempre quise la cancion gracias', '2019-07-10 09:00:00');