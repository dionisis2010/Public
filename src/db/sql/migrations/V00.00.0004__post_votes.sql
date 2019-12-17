--Likes table

DROP TABLE IF EXISTS "post_votes";
DROP SEQUENCE IF EXISTS post_votes_id_seq;
CREATE SEQUENCE post_votes_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."post_votes"
(
    "id"     bigint DEFAULT nextval('post_votes_id_seq') NOT NULL,
    "author" bigint                                      NOT NULL,
    "post"   bigint                                      NOT NULL,
    "vote"   text                                        NOT NULL,
    CONSTRAINT "post_votes_id" PRIMARY KEY ("id"),
    CONSTRAINT "postVotes_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE,
    CONSTRAINT "postVotes_post_fkey" FOREIGN KEY (post) REFERENCES posts (id) NOT DEFERRABLE
) WITH (oids = false);