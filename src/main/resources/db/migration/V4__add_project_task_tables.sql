create table project
(
    id serial not null,
    name varchar(64) not null,
    description varchar(1024),
    github_url varchar(1024),
    status varchar(32),
    logo_url varchar(2000),
    date_started timestamptz default now() not null,
    last_modified_at timestamptz default now(),
    author_id int not null
        constraint project_hexuser_id_fk
            references hexuser,
    tag_id int
        constraint project_blog_tag_id_fk
            references blog_tag,
    published boolean default false not null
);

create unique index project_id_uindex
	on project (id);

create unique index project_name_uindex
	on project (name);

alter table project
    add constraint project_pk
        primary key (id);

