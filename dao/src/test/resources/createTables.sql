CREATE TABLE accounts
(
    account_id                     SERIAL PRIMARY KEY,
    account_password               VARCHAR,
    account_surname                VARCHAR,
    account_name                   VARCHAR,
    account_patronymic             VARCHAR,
    account_birthday               DATE,
    account_home_address           VARCHAR,
    account_business_address       VARCHAR,
    account_email                  VARCHAR,
    account_icq                    VARCHAR,
    account_skype                  VARCHAR,
    account_additional_information VARCHAR
);
CREATE TABLE account_phones
(
    phone_id           SERIAL PRIMARY KEY,
    account_phone      VARCHAR,
    account_work_phone VARCHAR,
    account_id         INT,
    FOREIGN KEY (account_id) REFERENCES accounts (account_id)
);
CREATE TABLE groups
(
    group_id          SERIAL PRIMARY KEY,
    group_name        VARCHAR,
    group_description VARCHAR
);
CREATE TABLE accounts_groups
(
    account_id INT REFERENCES accounts (account_id),
    group_id   INT REFERENCES groups (group_id),
    PRIMARY KEY (account_id, group_id)
);
CREATE TABLE friends
(
    friendship_id SERIAL PRIMARY KEY,
    account_id_1  INT,
    account_id_2  INT,
    FOREIGN KEY (account_id_1) REFERENCES accounts (account_id),
    FOREIGN KEY (account_id_2) REFERENCES accounts (account_id)
);