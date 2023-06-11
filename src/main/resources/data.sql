INSERT INTO users (username, password, email, url_image, names, surnames)
VALUES
    ('username1', '000', 'user1@email.com', 'http//:localhost/user1', 'namesuser1', 'surnameuser1'),
    ('username2', '001', 'user2@email.com', 'http//:localhost/user2', 'namesuser2', 'surnameuser2');

INSERT INTO pins (title, description, url_image, url_owner, user_id)
VALUES
    ('pin1', 'description de pin 1', 'http//:localhost/pin1', 'http//:localhost/user', 1),
    ('pin2', 'description de pin 2', 'http//:localhost/pin2', 'http//:localhost/user', 1),
    ('pin3', 'description de pin 3', 'http//:localhost/pin3', 'http//:localhost/user', 1),
    ('pin4', 'description de pin 4', 'http//:localhost/pin4', 'http//:localhost/user', 1),
    ('pin5', 'description de pin 5', 'http//:localhost/pin5', 'http//:localhost/user', 2),
    ('pin6', 'description de pin 6', 'http//:localhost/pin6', 'http//:localhost/user', 2),
    ('pin7', 'description de pin 7', 'http//:localhost/pin7', 'http//:localhost/user', 2),
    ('pin8', 'description de pin 8', 'http//:localhost/pin8', 'http//:localhost/user', 2),
    ('pin9', 'description de pin 9', 'http//:localhost/pin9', 'http//:localhost/user', 2);

INSERT INTO boards (name, description, user_id)
VALUES
    ('board1', 'description de board 1', 1),
    ('board2', 'description de board 2', 2),
    ('board3', 'description de board 3', 1);

INSERT INTO child_boards (name, board_id)
VALUES
    ('childBoard1', 1),
    ('childBoard2', 2),
    ('childBoard3', 3);
