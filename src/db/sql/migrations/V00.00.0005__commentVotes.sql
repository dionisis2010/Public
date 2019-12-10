--Likes table

DROP TABLE IF EXISTS "commentVotes";
CREATE TABLE "public"."commentVotes"
(
    "author"  bigint  NOT NULL,
    "comment" bigint  NOT NULL,
    "vote"    boolean NOT NULL,
    CONSTRAINT "commentVotes_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE,
    CONSTRAINT "commentVotes_comment_fkey" FOREIGN KEY (comment) REFERENCES comments (id) NOT DEFERRABLE
) WITH (oids = false);