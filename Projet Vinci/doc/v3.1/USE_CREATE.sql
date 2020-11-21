create table user (
    login VARCHAR(15) not null UNIQUE,
    password VARCHAR(15) not null,
    name VARCHAR(50),
    surname VARCHAR(50),
    email VARCHAR(50) UNIQUE,

    CONSTRAINT pk_user PRIMARY KEY(login)
);