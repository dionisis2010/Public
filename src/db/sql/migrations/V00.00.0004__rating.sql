--Likes table

DROP TABLE IF EXISTS "rating";
DROP SEQUENCE IF EXISTS rating_id_seq;
CREATE SEQUENCE rating_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."rating"
(
    "id"      bigint DEFAULT nextval('rating_id_seq') NOT NULL,
    "author"  bigint                                  NOT NULL,
    "post"    bigint                                  NOT NULL,
    "like"    boolean                                 NOT NULL,
    "dislike" boolean                                 NOT NULL,
    CONSTRAINT "rating_id" PRIMARY KEY ("id"),
    CONSTRAINT "rating_post_id_fkey" FOREIGN KEY (post) REFERENCES posts (id) NOT DEFERRABLE,
    CONSTRAINT "rating_user_id_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE
) WITH (oids = false);