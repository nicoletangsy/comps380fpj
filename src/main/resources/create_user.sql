CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE user_roles (
    user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id),
    FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO users VALUES ('keith', 'keithpw');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_ADMIN');

INSERT INTO users VALUES ('tsli', 'tslipw');
INSERT INTO user_roles(username, role) VALUES ('tsli', 'ROLE_ADMIN');
INSERT INTO user_roles(username, role) VALUES ('tsli', 'ROLE_USER');

INSERT INTO users VALUES ('user1', 'user1pw');
INSERT INTO user_roles(username, role) VALUES ('user1', 'ROLE_USER');

INSERT INTO users VALUES ('user2', 'user2pw');
INSERT INTO user_roles(username, role) VALUES ('user2', 'ROLE_USER');