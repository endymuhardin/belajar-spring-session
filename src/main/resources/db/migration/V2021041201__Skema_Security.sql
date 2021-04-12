create table s_role (
    id   varchar(36),
    nama varchar(100) not null,
    primary key (id),
    unique (nama)
);


create table s_user (
    id              varchar(36),
    id_role         varchar(36)  not null,
    username        varchar(100) not null,
    hashed_password varchar(255) not null,
    nama            varchar(255) not null,
    primary key (id),
    unique (username),
    foreign key (id_role) references s_role (id)
);
