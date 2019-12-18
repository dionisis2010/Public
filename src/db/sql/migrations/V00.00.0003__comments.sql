-- Comments table.

DROP TABLE IF EXISTS "comments";
DROP SEQUENCE IF EXISTS comments_id_seq;
CREATE SEQUENCE comments_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."comments"
(
    "id"          bigint DEFAULT nextval('comments_id_seq') NOT NULL,
    "author"      bigint                                    NOT NULL,
    "post"        bigint                                    NOT NULL,
    "body"        text                                      NOT NULL,
    "posted_time" timestamp                                 NOT NULL,
    "is_deleted"  boolean                                   NOT NULL,
    CONSTRAINT "comments_id" PRIMARY KEY ("id"),
    CONSTRAINT "comments_author_fkey" FOREIGN KEY (author) REFERENCES users (id) NOT DEFERRABLE,
    CONSTRAINT "comments_post_fkey" FOREIGN KEY (post) REFERENCES posts (id) NOT DEFERRABLE
) WITH (oids = false);