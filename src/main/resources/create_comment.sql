CREATE TABLE lecture_comments (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    lecture_id INTEGER DEFAULT NULL,
    content VARCHAR(255) NOT NULL,
    PRIMARY KEY (id), 
    FOREIGN KEY (lecture_id) REFERENCES lectures(id), 
    FOREIGN KEY (username) REFERENCES users(username)
)

CREATE TABLE poll_comments (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    poll_id INTEGER DEFAULT NULL,
    content VARCHAR(255) NOT NULL,
    PRIMARY KEY (id), 
    FOREIGN KEY (poll_id) REFERENCES polls(id), 
    FOREIGN KEY (username) REFERENCES users(username)
)