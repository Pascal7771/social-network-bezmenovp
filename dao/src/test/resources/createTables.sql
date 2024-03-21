create table FRIENDS
(
    IDACCOUNT INTEGER not null,
    IDFRIEND  INTEGER not null
);
create table GROUP_INVITE
(
    IDINVITER INTEGER not null,
    IDGROUP   INTEGER not null
);
create table GROUP_MEMBER
(
    IDGROUP  INTEGER not null,
    IDMEMBER INTEGER not null
);
create table GROUP_MESSAGE
(
    ID          INTEGER auto_increment
        primary key,
    RECIPIENTID INTEGER     not null,
    SENDERID    INTEGER     not null,
    TEXTMESSAGE VARCHAR(500) default NULL,
    TIMESEND    VARCHAR,
    APPOINTMENT VARCHAR(20) not null,
    IMG         BYTEA
);
create table GROUP_TBL
(
    ID           INTEGER auto_increment,
    NAME         VARCHAR(45) not null,
    DESCRIPTION  VARCHAR(120),
    IMG          BYTEA,
    IDCREATOR    INTEGER     not null,
    CREATIONDATE VARCHAR(45),
    constraint GROUPS__PKEY
        primary key (ID)
);
create table INVITES
(
    IDINVITER INTEGER not null,
    IDFRIEND  INTEGER not null
);
create table MESSAGE
(
    ID          INTEGER auto_increment,
    RECIPIENTID INTEGER     not null,
    SENDERID    INTEGER     not null,
    TEXTMESSAGE VARCHAR(500) default NULL,
    TIMESEND    VARCHAR,
    APPOINTMENT VARCHAR(20) not null,
    IMG         BYTEA,
    constraint MESSAGES_PKEY
        primary key (ID)
);
create table PHONES
(
    ACCOUNTID INTEGER     not null,
    PHONE     VARCHAR(12) not null,
    WORKPHONE VARCHAR(12) default NULL
);
create table ACCOUNT
(
    ID           INTEGER auto_increment,
    SURNAME      VARCHAR(45)               not null,
    NAME         VARCHAR(45)               not null,
    PATRONYMIC   VARCHAR(45)  default NULL not null,
    ADDRESS      VARCHAR(45)  default NULL,
    WORKADDRESS  VARCHAR(45)  default NULL,
    EMAIL        VARCHAR(45)               not null,
    ICQ          VARCHAR(45)  default NULL,
    SKYPE        VARCHAR(45)  default NULL,
    ADDITIONALLY VARCHAR(120) default NULL,
    PASSWORD     VARCHAR(90)               not null,
    BIRTHDAY     VARCHAR(45)               not null,
    IMG          BYTEA,
    CREATIONDATE VARCHAR,
    ROLE         VARCHAR                   not null,
    constraint ACCOUNTS_PKEY
        primary key (ID)
);