CREATE TABLE polls (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) NOT NULL,
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE poll_responses (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    response CHAR(1) NOT NULL,
    poll_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (poll_id) REFERENCES polls(id) 
);

INSERT INTO polls (question, option1, option2, option3, option4) VALUES ('Which date do you prefer for the mid-term test?', 'Week 6: 2019/03/01', 'Week 7: 2019/03/08', 'Week 8: 2019/03/15', 'No Mid-term, Group Project 100% for OCAS');
INSERT INTO poll_responses (username, response, poll_id) VALUES ('tsli', '2', 1);
INSERT INTO poll_responses (username, response, poll_id) VALUES ('user1', '1', 1);
INSERT INTO poll_responses (username, response, poll_id) VALUES ('user2', '4', 1);

INSERT INTO polls (question, option1, option2) VALUES ('Do you think this course is useful for your future career?', 'Yes', 'No');
INSERT INTO poll_responses (username, response, poll_id) VALUES ('tsli', '1', 2);
INSERT INTO poll_responses (username, response, poll_id) VALUES ('user1', '1', 2);
INSERT INTO poll_responses (username, response, poll_id) VALUES ('user2', '1', 2);

INSERT INTO polls (question, option1, option2, option3) VALUES ('Do you think this course is difficult?', 'Yes, the code is difficult to understand...', 'Yes, the concept is difficult to understand...', 'No');
INSERT INTO poll_responses (username, response, poll_id) VALUES ('user2', '1', 3);
INSERT INTO poll_responses (username, response, poll_id) VALUES ('user1', '2', 3);
INSERT INTO poll_responses (username, response, poll_id) VALUES ('tsli', '3', 3);
