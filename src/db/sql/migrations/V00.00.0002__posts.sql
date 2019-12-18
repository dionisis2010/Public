-- Posts table.

DROP TABLE IF EXISTS "posts";
DROP SEQUENCE IF EXISTS posts_id_seq;
CREATE SEQUENCE posts_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."posts"
(
    "id"          bigint DEFAULT nextval('posts_id_seq') NOT NULL,
    "author"      bigint                                 NOT NULL,
    "body"        text                                   NOT NULL,
    "posted_time" timestamp                              NOT NULL,
    "title"       text                                   NOT NULL,
    "is_deleted"  boolean                                NOT NULL,
    CONSTRAINT "posts_id" PRIMARY KEY ("id"),
    CONSTRAINT "posts_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE
) WITH (oids = false);