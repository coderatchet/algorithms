CREATE SCHEMA PLAYG;

drop table couser;
drop table cogroup;
drop table cogroupuserlink;

CREATE TABLE COUSER (
    USERID SERIAL,
    USERNAME VARCHAR(128) NOT NULL UNIQUE,
    PASSWORD CHAR(128)
);

CREATE TABLE COGROUP (
    GROUPID SERIAL,
    NAME VARCHAR(64)
);

-- links a user to a group
CREATE TABLE COGROUPUSERLINK (
	USERID BIGINT UNSIGNED NOT NULL
		REFERENCES COUSER (USERID),
	GROUPID BIGINT UNSIGNED NOT NULL
		REFERENCES COGROUP (GROUPID),
  UNIQUE (USERID, GROUPID) -- to ensure no duplicates
);