--Likes table

DROP TABLE IF EXISTS "comment_votes";
DROP SEQUENCE IF EXISTS comment_votes_id_seq;
CREATE SEQUENCE comment_votes_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."comment_votes"
(
    "id"      bigint DEFAULT nextval('comment_votes_id_seq') NOT NULL,
    "author"  bigint                                         NOT NULL,
    "comment" bigint                                         NOT NULL,
    "vote"    text                                           NOT NULL,
    CONSTRAINT "comment_votes_id" PRIMARY KEY ("id"),
    CONSTRAINT "commentVotes_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE,
    CONSTRAINT "commentVotes_comment_fkey" FOREIGN KEY (comment) REFERENCES comments (id) NOT DEFERRABLE
) WITH (oids = false);