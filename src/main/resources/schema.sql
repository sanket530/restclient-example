create table joke (
    id bigint not null,
    punchline varchar(255),
    setup varchar(255),
    type varchar(50),
    primary key (id)
);