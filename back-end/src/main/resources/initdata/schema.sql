drop table if exists board_skill;
drop table if exists comment;
drop table if exists skill;
drop table if exists heart;
drop table if exists board;
drop table if exists member;



CREATE TABLE member
(
    member_id       int primary key AUTO_INCREMENT comment '사용자 식별 번호',
    username        VARCHAR(100)                    NOT NULL UNIQUE comment '아이디',
    nickname        VARCHAR(50)                     NOT NULL UNIQUE comment '닉네임',
    password        VARCHAR(100)                    NOT NULL comment '비밀번호',
    email           VARCHAR(50)                     NOT NULL UNIQUE comment '이메일',
    profile_image   VARCHAR(250)                    NOT NULL comment '프로필 이미지',
    role            ENUM ('ROLE_USER','ROLE_ADMIN') NOT NULL DEFAULT 'ROLE_USER' comment '권한',
    reg_date        TIMESTAMP                                default now() comment '작성일',
    mod_date        TIMESTAMP comment '수정일',
    withdrawal_yn   ENUM ('Y','N')                           default 'N' comment '탈퇴여부',
    withdrawal_date TIMESTAMP comment '탈퇴날짜',
    social_type     VARCHAR(20)
) comment '멤버 정보';

create table board
(
    board_id       int(10) AUTO_INCREMENT PRIMARY KEY,
    member_id      int(10)      NOT NULL,
    title          VARCHAR(100) NOT NULL,
    content        TEXT         NOT NULL,
    view_cnt       BIGINT                DEFAULT 0,
    recruit_status CHAR(1)      NOT NULL DEFAULT 'Y',
    reg_date       TIMESTAMP             DEFAULT NOW(),
    mod_date       TIMESTAMP,
    recruit_type   VARCHAR(10)  NOT NULL,
    recruit_cnt    VARCHAR(10)  NOT NULL,
    progress_type  VARCHAR(10)  NOT NULL,
    duration       VARCHAR(10)  NOT NULL,
    contact_type   VARCHAR(20)  NOT NULL,
    contact        VARCHAR(100) NOT NULL,
    start_date     TIMESTAMP    NOT NULL,
    foreign key (member_id) references member (member_id)
);

create table skill
(
    skill_id   int(10) AUTO_INCREMENT PRIMARY KEY,
    skill_type VARCHAR(20) NOT NULL
);

create table board_skill
(
    board_skill_id int(10) AUTO_INCREMENT PRIMARY KEY,
    board_id       int(10) NOT NULL,
    skill_id       int(10) NOT NULL,
    foreign key (board_id) references board (board_id) on delete cascade,
    foreign key (skill_id) references skill (skill_id) on delete cascade
);

create table comment
(
    comment_id int(10) AUTO_INCREMENT PRIMARY KEY,
    board_id   int(10)      NOT NULL,
    member_id   int(10)     NOT NULL,
    content    VARCHAR(200) NOT NULL,
    reg_date   TIMESTAMP DEFAULT NOW(),
    mod_date   TIMESTAMP,
    c_depth    TINYINT,
    c_group    int(10),
    foreign key (board_id) references board (board_id) on delete cascade,
    foreign key (member_id) references member (member_id) on delete cascade
);

create table heart(
    board_id int not null ,
    member_id int not null,
    constraint heart_pk primary key (board_id,member_id),
    foreign key (board_id) references board(board_id) on delete cascade,
    foreign key (member_id) references member(member_id) on delete cascade
);



