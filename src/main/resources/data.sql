create table joke (
    id bigint not null,
    punchline varchar(255),
    setup varchar(255),
    type varchar(50),
    primary key (id)
);

INSERT INTO joke (id, type, setup, punchline) VALUES ('66', 'general', 'Did you watch the new comic book movie?', 'It was very graphic!');
INSERT INTO joke (id, type, setup, punchline) VALUES ('119', 'general', 'How do locomotives know where they''re going?', 'Lots of training');
INSERT INTO joke (id, type, setup, punchline) VALUES ('158', 'general', 'What did celery say when he broke up with his girlfriend?', 'She wasn''t right for me, so I really do not carrot all.');
INSERT INTO joke (id, type, setup, punchline) VALUES ('175', 'general', 'What did the late tomato say to the early tomato?', 'I’ll ketch up');
INSERT INTO joke (id, type, setup, punchline) VALUES ('180', 'general', 'Why do not you find hippopotamuses hiding in trees?', 'They''re really good at it.');
INSERT INTO joke (id, type, setup, punchline) VALUES ('184', 'general', 'What did the shy pebble wish for?', 'That she was a little boulder.');
INSERT INTO joke (id, type, setup, punchline) VALUES ('289', 'general', 'Where do young cows eat lunch?', 'In the calf-ateria.');
INSERT INTO joke (id, type, setup, punchline) VALUES ('355', 'general', 'Why do you never see elephants hiding in trees?', 'Because they''re so good at it.');
INSERT INTO joke (id, type, setup, punchline) VALUES ('361', 'general', 'Why does Waldo only wear stripes?', 'Because he doesn''t want to be spotted.');
INSERT INTO joke (id, type, setup, punchline) VALUES ('405', 'programming', 'How do you generate a random string?', 'Put a Windows user in front of Vim and tell them to exit.');