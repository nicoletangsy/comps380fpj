CREATE TABLE lecture_comments (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    lecture_id INTEGER DEFAULT NULL,
    content VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lecture_id) REFERENCES lectures(id)
);

CREATE TABLE poll_comments (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    poll_id INTEGER DEFAULT NULL,
    content VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (poll_id) REFERENCES polls(id)
);

INSERT INTO lecture_comments (username, content, lecture_id) VALUES ('keith', 'Welcome to COMPS380F! This course aims to teach you how to develop web applications based on mainly Java technologies. Hope you enjoy studying this course!', 1);
INSERT INTO lecture_comments (username, content, lecture_id) VALUES ('user1', 'Why do we need to learn Spring Framework instead of AngularJS?', 1);

INSERT INTO poll_comments (username, content, poll_id) VALUES ('keith', 'I will cancel the mid-term test if more than 50% of you think that it is not neccessary, but the group project will count 100% in OCAS' , 1);
INSERT INTO poll_comments (username, content, poll_id) VALUES ('user2', 'Not difficult to understand but the implementation is troublesome! I made mistakes easily!!' , 3);
INSERT INTO poll_comments (username, content, poll_id) VALUES ('user1', 'Me too!' , 3);