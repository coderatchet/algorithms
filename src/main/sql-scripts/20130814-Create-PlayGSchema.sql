CREATE SCHEMA PLAYG;

drop table couser;
drop table cogroup;
drop table cogroupuserlink;

CREATE TABLE COUSER (
    USERID BIGINT UNSIGNED NOT NULL UNIQUE PRIMARY KEY,
    USERNAME VARCHAR(128) NOT NULL UNIQUE,
    PASSWORD CHAR(128)
);

CREATE TABLE COGROUP (
    GROUPID BIGINT UNSIGNED NOT NULL UNIQUE PRIMARY KEY,
    NAME VARCHAR(64)
);

-- links a user to a group
CREATE TABLE COGROUPUSERLINK (
	USERID BIGINT UNSIGNED NOT NULL
		REFERENCES COUSER (USERID),
	GROUPID BIGINT UNSIGNED NOT NULL
		REFERENCES COGROUP (GROUPID),
  PRIMARY KEY (USERID, GROUPID) -- to ensure no duplicates
);

-- this table will be used to generate next numbers
CREATE TABLE openjpa_sequence_table (
	ID tinyint(4) NOT NULL,
	SEQUENCE_VALUE bigint(20) default NULL,
	PRIMARY KEY  (ID)
);