--Likes table

DROP TABLE IF EXISTS "postVotes";
CREATE TABLE "public"."post_votes"
(
    "author" bigint  NOT NULL,
    "post"   bigint  NOT NULL,
    "vote"   boolean NOT NULL,
    CONSTRAINT "postVotes_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE,
    CONSTRAINT "postVotes_post_fkey" FOREIGN KEY (post) REFERENCES posts (id) NOT DEFERRABLE
) WITH (oids = false);