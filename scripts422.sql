CREATE TABLE person
(
    "name"  TEXT PRIMARY KEY UNIQUE   NOT NULL,
    age     INTEGER CHECK ( age > 0 ) NOT NULL,
    license BOOLEAN                   NOT NULL,
    idCar   INTEGER
);

CREATE TABLE car
(
    idCar   INTEGER PRIMARY KEY UNIQUE NOT NULL,
    "brand" TEXT,
    "model" TEXT,
    price   INTEGER
);

SELECT person.name, person.age, person.license,
    brand, model, price
FROM person
LEFT JOIN car c on person.idCar = c.idCar;