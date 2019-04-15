CREATE TABLE lectures (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE lecture_notes_attachments (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    lecture_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lecture_id) REFERENCES lectures(id) 
);

CREATE TABLE tutorial_notes_attachments (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    lecture_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lecture_id) REFERENCES lectures(id) 
);

INSERT INTO lectures (title) VALUES ('Lecture 0: Course Guide');

INSERT INTO lectures (title) VALUES ('Lecture 1: Overview, HTML5, CSS, JavaScript');

INSERT INTO lectures (title) VALUES ('Lecture 2: Servlet, XML, JSON');

INSERT INTO lectures (title) VALUES ('Lecture 3: JSP, JavaBean');

INSERT INTO lectures (title) VALUES ('Lecture 4: Session');