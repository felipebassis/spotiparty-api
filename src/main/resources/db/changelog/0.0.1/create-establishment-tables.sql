--liquibase formatted sql

--changeset felipe:1
create table if not exists "establishment"(
    "id" uuid not null,
    "name" varchar(255) not null,
    "latitude" NUMERIC(9, 6) not null,
    "longitude" NUMERIC(10, 6) not null,
    "deleted" bool not null,
    constraint "establishment_pk" primary key ("id")
);

--rollback drop table if exists "establishment";

--changeset felipe:2
create table if not exists "spotify_credential"(
    "id" uuid not null,
    "account_name" varchar(50) null,
    "default" bool not null,
    "authentication_protocol" varchar(15) not null,
    "client_id" bytea not null,
    "client_secret" bytea not null,
    "access_token" bytea null,
    "refresh_token" bytea null,
    "expires_at" timestamp null,
    "establishment_id" uuid not null,
    "deleted" bool not null,
    constraint "spotify_credential_pk" primary key ("id"),
    constraint "spotify_credential_establishment_fk" foreign key ("establishment_id") references "establishment" ("id")
);

create unique index if not exists "spotify_credential_establishment_default_unique_idx" on "spotify_credential" ("establishment_id", "default");

alter table "spotify_credential"
  	add constraint "spotify_credential_establishment_default_uk"
   	unique using index "spotify_credential_establishment_default_unique_idx";

--rollback drop table if exists "spotify_credential";