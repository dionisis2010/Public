--Likes table

DROP TABLE IF EXISTS "postVotes";
CREATE TABLE "public"."postVotes"
(
    "author" bigint  NOT NULL,
    "post"   bigint  NOT NULL,
    "like"   boolean NOT NULL,
    CONSTRAINT "rating_post_id_fkey" FOREIGN KEY (post) REFERENCES posts (id) NOT DEFERRABLE,
    CONSTRAINT "rating_user_id_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE
) WITH (oids = false);