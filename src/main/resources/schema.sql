create table if not exists users (
    id integer not null GENERATED ALWAYS AS identity PRIMARY KEY,
    username varchar not null,
    fio varchar not null,
    UNIQUE (username, fio)
);

create table if not exists logins (
    id integer not null GENERATED ALWAYS AS identity PRIMARY KEY,
    access_date timestamp,
    user_id integer not null,
    application varchar,
    constraint fk_logins foreign key (user_id) references users(id)
);