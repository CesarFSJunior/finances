CREATE TABLE USERS (
ID UNIQUEIDENTIFIER PRIMARY KEY,
NAME VARCHAR(100),
PASSWORD VARCHAR(255),
EMAIL VARCHAR(100) UNIQUE NOT NULL,
BIRTHDAY DATE,
CREATED_AT DATETIME,
ROLE VARCHAR(40)
)