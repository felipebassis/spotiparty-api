--liquibase formatted sql

--changeset felipe:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--rollback drop extension if exists "uuid-ossp";

--changeset felipe:2
create table if not exists "user"(
	"id" uuid not null,
	"username" varchar(40) not null,
	"password" varchar(60) not null,
	"email" varchar(255) null,
	"cellphone" varchar(20) null,
	"banned" bool not null,
	"deleted" bool not null,
	constraint "user_pk" primary key ("id")
);

create index if not exists "user_login_idx" on "user"("username", "email", "cellphone");

create unique index if not exists "user_username_unique_idx" on "user"("username");

alter table "user"
    add constraint "user_username_uk"
        unique using index "user_username_unique_idx";

--rollback drop table if exists "user";

--changeset felipe:3
create table "role"(
	"id" uuid not null default uuid_generate_v4(),
	"name" varchar(255) not null,
	constraint "role_pk" primary key ("id")
);

create unique index if not exists "role_name_unique_idx" on "role"("name");

alter table "role"
	add constraint "role_name_uk"
	unique using index "role_name_unique_idx";

insert into "role"("name") values ('SYSTEM_ACCESS');
--rollback drop table if exists "role";

--changeset felipe:4
create table "user_role"(
	"user_id" uuid not null,
	"role_id" uuid not null,
    "deleted" bool not null default false,
	constraint "user_role_pk" primary key ("user_id", "role_id"),
	constraint "user_role_user_table_fk" foreign key ("user_id") references "user"("id"),
	constraint "user_role_role_table_fk" foreign key ("role_id") references "role"(id)
);

--rollback drop table "user_role";