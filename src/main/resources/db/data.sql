/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM m_user;
DELETE FROM task;

/* ユーザマスタのデータ（ADMIN権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role)
VALUES('yamada@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '山田太郎', false, 'ROLE_ADMIN');
/* ユーザマスタのデータ（ADMIN権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role)
VALUES('s3a208katou@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '加藤聖貴', false, 'ROLE_ADMIN');
/* ユーザマスタのデータ（一般権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role)
VALUES('tamura@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '田村', false, 'ROLE_GENERAL');
/* ユーザマスタのデータ（一般権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, darkmode, role)
VALUES('suzuki@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '鈴木', true, 'ROLE_GENERAL');

/* タスクテーブルのデータ */
INSERT INTO task (id, user_id, comment, limitday,task_name) VALUES (1, 'user', 'これやる', '2020-03-23','a');
INSERT INTO task (id, user_id, comment, limitday,task_name) VALUES (2, 'owner', 'あれやる', '2020-03-24','b');
INSERT INTO task (id, user_id, comment, limitday,task_name) VALUES (3, 'tester', 'それやる', '2020-03-31','c');
INSERT INTO task (id, user_id, comment, limitday,task_name) VALUES (4, 'developer', 'どれやる', '2020-03-25','d');
INSERT INTO task (id, user_id, comment, limitday,task_name) VALUES (5, 'master', 'もっとやる', '2020-04-20','e');