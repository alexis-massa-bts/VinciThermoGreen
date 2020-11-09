CREATE TABLE `stade` (
	`id_stade` SERIAL NOT NULL,
	`nom_stade` VARCHAR(100),
	PRIMARY KEY (`id_stade`)
);

CREATE TABLE `mesure` (
	`num_zone` INT,
	`horodate` DATETIME,
	`fahrenheit` FLOAT,
	`id_stade` INT,
	FOREIGN KEY (id_stade) REFERENCES stade(id_stade)
);