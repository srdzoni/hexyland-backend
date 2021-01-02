create table if not exists "app_property"
(
    id serial not null
        constraint app_property_pk
            primary key,
    prop_key varchar(128) not null,
    prop_value varchar(1024)
);

create unique index if not exists app_prop_key_uindex
    on "app_property" (prop_key);

insert into app_property(prop_key, prop_value) VALUES ('registrations_enabled', 'false');