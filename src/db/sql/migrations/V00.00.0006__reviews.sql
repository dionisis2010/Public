--Likes table

DROP TABLE IF EXISTS "reviews";
DROP SEQUENCE IF EXISTS reviews_id_seq;
CREATE SEQUENCE reviews_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."reviews"
(
    "id"          bigint DEFAULT nextval('reviews_id_seq') NOT NULL,
    "author"      bigint                                   NOT NULL,
    "email"       text                                     NOT NULL,
    "title"       text                                     NOT NULL,
    "body"        text                                     NOT NULL,
    "posted_time" timestamp                                NOT NULL,
    "is_deleted"  boolean                                  NOT NULL,
    CONSTRAINT "reviews_id" PRIMARY KEY ("id"),
    CONSTRAINT "reviews_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE
) WITH (oids = false);