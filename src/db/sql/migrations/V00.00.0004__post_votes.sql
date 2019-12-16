--Likes table

DROP TABLE IF EXISTS "postVotes";
CREATE TABLE "public"."post_votes"
(
    "id"     bigint DEFAULT nextval('users_id_seq') NOT NULL,
    "author" bigint                                 NOT NULL,
    "post"   bigint                                 NOT NULL,
    "vote"   text                                   NOT NULL,
    CONSTRAINT "postVotes_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE,
    CONSTRAINT "postVotes_post_fkey" FOREIGN KEY (post) REFERENCES posts (id) NOT DEFERRABLE,
    CONSTRAINT "users_id" PRIMARY KEY ("id")
) WITH (oids = false);