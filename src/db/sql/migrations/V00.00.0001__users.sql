-- Users table.

DROP TABLE IF EXISTS "users";
DROP SEQUENCE IF EXISTS users_id_seq;
CREATE SEQUENCE users_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."users"
(
    "id"               bigint DEFAULT nextval('users_id_seq') NOT NULL,
    "username"         character varying                      NOT NULL,
    "password"         character(32)                          NOT NULL,
    "last_login"       timestamp                              NOT NULL,
    "registration_time" timestamp                             NOT NULL,
    "age"              smallint                               NOT NULL,
    "gender"           boolean                                NOT NULL,
    "rating"           bigint                                 NOT NULL,
    CONSTRAINT "users_id" PRIMARY KEY ("id")
) WITH (oids = false);