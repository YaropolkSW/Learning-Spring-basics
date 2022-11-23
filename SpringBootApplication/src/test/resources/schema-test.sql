DROP TABLE IF EXISTS shop CASCADE;

CREATE TABLE IF NOT EXISTS shop(
    shop_id INT PRIMARY KEY AUTO_INCREMENT,
    shop_name VARCHAR(50) NOT NULL
    );

INSERT INTO shop(shop_name)
VALUES('Subaru Official Dealer'),
      ('Chevrolet Official Dealer'),
      ('Kia Official Dealer'),
      ('Trade-In Dealer');

DROP TABLE IF EXISTS car CASCADE;

CREATE TABLE IF NOT EXISTS car(
    car_id INT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(15) NOT NULL,
    model VARCHAR(15) NOT NULL,
    age_of_produce INT NOT NULL,
    price INT NOT NULL CHECK (price > 0)
    );

INSERT INTO car(brand, model, age_of_produce, price)
VALUES('Subaru', 'Outback', 2022, 6000000),
      ('Subaru', 'Forester', 2021, 4000000),
      ('Chevrolet', 'Camaro', 2014, 2000000),
      ('Chevrolet', 'Lacetti', 2015, 400000),
      ('Kia', 'Rio', 2022, 1500000),
      ('Kia', 'Ceed', 2020, 1800000),
      ('Daewoo', 'Gentra', 2016, 250000),
      ('Nissan', 'Juke', 2014, 1000000);

DROP TABLE IF EXISTS client;

CREATE TABLE IF NOT EXISTS client(
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(20) NOT NULL,
    city VARCHAR(20) NOT NULL,
    car_id INT,
    FOREIGN KEY (car_id) REFERENCES car (car_id)
    );

INSERT INTO client(client_name, city, car_id)
VALUES('Василий Ф.И.', 'Москва', 1),
      ('Надежда К.Ю.', 'Самара', 3),
      ('Леонид М.В.', 'Санкт-Петербург', 5),
      ('Вероника Л.К.', 'Тольятти', 7);

DROP TABLE IF EXISTS shop_car;

CREATE TABLE IF NOT EXISTS shop_car(
    shop_id INT NOT NULL,
    car_id INT,
    FOREIGN KEY (shop_id) REFERENCES shop (shop_id),
    FOREIGN KEY (car_id) REFERENCES car (car_id)
    );

INSERT INTO shop_car(shop_id, car_id)
VALUES(1, 1), (1, 2),
      (2, 3), (2, 4),
      (3, 5), (3, 6),
      (4, 7), (4, 8),
      (4, 1), (3, 3),
      (2, 7), (1, 8);