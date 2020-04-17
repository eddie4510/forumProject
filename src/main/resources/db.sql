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

CREATE TABLE threads(
    thread_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    type VARCHAR(10) NOT NULL,
    title VARCHAR(255) NOT NULL,
    PRIMARY KEY(thread_id)
);

CREATE TABLE posts(
    post_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    thread_id INTEGER NOT NULL,
    thread_seq INTEGER NOT NULL,
    content VARCHAR(255) NOT NULL,
    FOREIGN KEY (thread_id) REFERENCES threads(thread_id),
    FOREIGN KEY (username) REFERENCES users(username),
    PRIMARY KEY(post_id)
);

CREATE TABLE attachment (
        attach_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
        filename VARCHAR(255) DEFAULT NULL,
        content_type VARCHAR(255) DEFAULT NULL,
        content BLOB DEFAULT NULL,
        post_id INTEGER DEFAULT NULL,
        PRIMARY KEY (attach_id),
        FOREIGN KEY (post_id) REFERENCES posts(post_id)
);

CREATE TABLE poll(
    poll_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) NOT NULL,
    PRIMARY KEY(poll_id)
);

CREATE TABLE pollChoice(
    choice_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    choice VARCHAR(255) NOT NULL,
    poll_id INTEGER NOT NULL,
    PRIMARY KEY(choice_id),
    FOREIGN KEY (poll_id) REFERENCES poll(poll_id)
);

CREATE TABLE votes(
    vote_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    choice_id INTEGER NOT NULL,
    PRIMARY KEY(vote_id),
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (choice_id) REFERENCES pollChoice(choice_id)
);


INSERT INTO users VALUES ('abc', '{noop}abc');
INSERT INTO user_roles(username, role) VALUES ('abc', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('abc', 'ROLE_ADMIN');

INSERT INTO users VALUES ('abcd', '{noop}abcd');
INSERT INTO user_roles(username, role) VALUES ('abcd', 'ROLE_USER');


