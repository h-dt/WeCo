insert into member ( nickname, password, email, profile_image, role)
values ( "testmemberNickname", "$2a$12$oGJCagk/3f2KaMLluo0PiOjKsi7A.gWdMmK/LqSLFqpUfqXGV/B1a", "testEmail@naver.com", "testProfileimage", "ROLE_USER");

insert into member ( nickname, password, email, profile_image, role)
values ( "testmemberNickname2", "$2a$12$oGJCagk/3f2KaMLluo0PiOjKsi7A.gWdMmK/LqSLFqpUfqXGV/B1a", "testEmail23@naver.com", "testProfileimage2", "ROLE_USER");

insert into member ( nickname, password, email, profile_image, role)
values ("testmemberNickname3", "$2a$12$oGJCagk/3f2KaMLluo0PiOjKsi7A.gWdMmK/LqSLFqpUfqXGV/B1a", "testEmail33333@naver.com", "testProfileimage3", "ROLE_USER");


insert into board (member_id, title, content, reg_date, recruit_type, recruit_cnt, progress_type, duration, contact_type, contact, start_date)
values (1, "test title", "test content", "2022-06-02", "프로젝트", "10명", "온라인", "7개월", "email", "test11234@naver.com", "2022-06-08");

insert into board (member_id, title, content, reg_date, recruit_type, recruit_cnt, progress_type, duration, contact_type, contact, start_date)
values (2, "test title1", "test content1", "2022-06-03", "스터디", "3명", "온라인", "2개월", "email", "love814@naver.com", "2022-11-22");

insert into board (member_id, title, content, reg_date, recruit_type, recruit_cnt, progress_type, duration, contact_type, contact, start_date)
values (1, "test title2", "test content2", "2022-06-04", "프로젝트", "6명", "온라인", "10개월", "email", "emailcc@naver.com", "2022-09-18");

insert into skill (skill_type) value ("Spring");
insert into skill (skill_type) value ("React");
insert into skill (skill_type) value ("Mysql");
insert into skill (skill_type) value ("JPA");
insert into skill (skill_type) value ("Mybatis");
insert into skill (skill_type) value ("Vue");
insert into skill (skill_type) value ("TypeScript");

insert into board_skill (board_id, skill_id) values (1, 1);
insert into board_skill (board_id, skill_id) values (1, 2);
insert into board_skill (board_id, skill_id) values (1, 3);
insert into board_skill (board_id, skill_id) values (1, 5);
insert into board_skill (board_id, skill_id) values (2, 3);
insert into board_skill (board_id, skill_id) values (2, 4);
insert into board_skill (board_id, skill_id) values (3, 1);
insert into board_skill (board_id, skill_id) values (3, 5);



insert into comment (board_id, member_id, content, c_depth) values (1, 2, "board1 comment1", false);
insert into comment (board_id, member_id, content, c_depth) values (1, 2, "board1 comment2", false);
insert into comment (board_id, member_id, content, c_depth) values (1, 2, "board1 comment3", false);
insert into comment (board_id, member_id, content, c_depth) values (2, 2, "board2 comment1", false);
insert into comment (board_id, member_id, content, c_depth) values (2, 2, "board2 comment2", false);
insert into comment (board_id, member_id, content, c_depth) values (2, 2, "board2 comment3", false);
insert into comment (board_id, member_id, content, c_depth) values (2, 2, "board2 comment4", false);
insert into comment (board_id, member_id, content, c_depth) values (3, 2, "board2 comment1", false);
insert into comment (board_id, member_id, content, c_depth) values (3, 2, "board3 comment2", false);
insert into comment (board_id, member_id, content, c_depth) values (3, 2, "board3 comment3", false);
insert into comment (board_id, member_id, content, c_depth) values (3, 2, "board3 comment4", false);
insert into comment (board_id, member_id, content, c_depth) values (3, 2, "board3 comment5", false);


insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 1번 댓글의 대댓글1", true, 1);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 3, "board1의 1번 댓글의 대댓글2", true, 1);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 1, "board1의 1번 댓글의 대댓글3", true, 1);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 2번 댓글의 대댓글1", true, 2);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 2번 댓글의 대댓글2", true, 2);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 2번 댓글의 대댓글3", true, 2);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 2번 댓글의 대댓글4", true, 2);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 3번 댓글의 대댓글1", true, 3);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 3번 댓글의 대댓글2", true, 3);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 3번 댓글의 대댓글3", true, 3);
insert into comment (board_id, member_id, content, c_depth, c_group) values (1, 2, "board1의 3번 댓글의 대댓글4", true, 3);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 1번 댓글의 대댓글1", true, 4);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 1번 댓글의 대댓글2", true, 4);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 1번 댓글의 대댓글3", true, 4);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 2번 댓글의 대댓글1", true, 5);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 2번 댓글의 대댓글2", true, 5);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 2번 댓글의 대댓글3", true, 5);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 2번 댓글의 대댓글4", true, 5);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 2번 댓글의 대댓글5", true, 5);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 2번 댓글의 대댓글6", true, 5);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 2번 댓글의 대댓글7", true, 5);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글1", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글2", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글3", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글4", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글5", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글6", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글7", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (2, 2, "board2의 3번 댓글의 대댓글8", true, 6);
insert into comment (board_id, member_id, content, c_depth, c_group) values (3, 2, "board3의 5번 댓글의 대댓글1", true, 12);
insert into comment (board_id, member_id, content, c_depth, c_group) values (3, 2, "board3의 5번 댓글의 대댓글2", true, 12);
insert into comment (board_id, member_id, content, c_depth, c_group) values (3, 2, "board3의 5번 댓글의 대댓글3", true, 12);
insert into comment (board_id, member_id, content, c_depth, c_group) values (3, 2, "board3의 5번 댓글의 대댓글4", true, 12);

insert into heart ( board_id,member_id) values ( 1,1);
insert into heart (board_id,member_id) values (1,2);
insert into heart ( board_id,member_id) values ( 2,1);
insert into heart ( board_id, member_id) values (2,3)



