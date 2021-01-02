create table if not exists "hexuser"
(
    id serial not null
        constraint author_pk
            primary key,
    username varchar(64) not null,
    password varchar(64) not null,
    email varchar(256) not null,
    full_name varchar(256) not null,
    registration_date timestamp with time zone default now() not null,
    deleted boolean default false not null,
    active boolean default false
);

create unique index if not exists author_email_uindex
    on "hexuser" (email);

create unique index if not exists author_id_uindex
    on "hexuser" (id);

create unique index if not exists author_username_uindex
    on "hexuser" (username);

create table if not exists blog_post
(
    id serial not null
        constraint blog_posts_pk
            primary key,
    title varchar(2000),
    description varchar(2000),
    plaintext text,
    slug varchar(200),
    post_type_id integer,
    created_by varchar(64) not null,
    created_at timestamp with time zone default CURRENT_TIMESTAMP,
    last_modified_by varchar(64),
    last_modified_at timestamp with time zone,
    published_by varchar(64),
    published_at timestamp with time zone,
    published boolean default false not null,
    deleted boolean default false not null,
    author_id integer not null
        constraint blog_post_user_id_fk
            references "hexuser"
);

create unique index if not exists blog_posts_id_uindex
    on blog_post (id);

create table if not exists blog_category
(
    id serial not null
        constraint blog_category_pk
            primary key,
    name varchar(64) not null
);

create unique index if not exists blog_category_id_uindex
    on blog_category (id);

create unique index if not exists blog_category_name_uindex
    on blog_category (name);

create table if not exists blog_tag
(
    id serial not null
        constraint blog_tag_pk
            primary key,
    name varchar(64) not null
);

create unique index if not exists blog_tag_id_uindex
    on blog_tag (id);

create table if not exists blog_post_category
(
    id serial not null
        constraint blog_post_category_pk
            primary key,
    post_id integer not null
        constraint blog_post_category_blog_post_id_fk
            references blog_post,
    category_id integer not null
        constraint blog_post_category_blog_category_id_fk
            references blog_category
);

create unique index if not exists blog_post_category_id_uindex
    on blog_post_category (id);

create table if not exists blog_post_type
(
    id serial not null
        constraint blog_post_type_pk
            primary key,
    name integer not null
);

create unique index if not exists blog_post_type_id_uindex
    on blog_post_type (id);

create table if not exists blog_post_tag
(
    id serial not null
        constraint blog_post_tag_pk
            primary key,
    post_id integer not null
        constraint blog_post_tag_blog_post_id_fk
            references blog_post,
    tag_id integer not null
        constraint blog_post_tag_blog_tag_id_fk
            references blog_tag
);

create unique index if not exists blog_post_tag_id_uindex
    on blog_post_tag (id);

create table if not exists hexauthority
(
    id serial not null
        constraint authorities_pk
            primary key,
    name varchar(64) not null
);
create unique index if not exists authorities_id_uindex
    on hexauthority (id);

create unique index if not exists authorities_name_uindex
    on hexauthority (name);

create table if not exists user_authorities
(
    id serial not null
        constraint user_authorities_pk
            primary key,
    user_id integer not null
        constraint user_authorities_user_id_fk
            references "hexuser",
    authority_id integer not null
        constraint user_authorities_authority_id_fk
            references hexauthority
);

create unique index if not exists user_authorities_id_uindex
    on user_authorities (id);

