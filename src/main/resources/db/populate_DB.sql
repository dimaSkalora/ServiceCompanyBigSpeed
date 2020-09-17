DELETE FROM users;


ALTER SEQUENCE global_seq RESTART WITH 100000;

---------------users---------------1
INSERT INTO users (name, email, password, phone) VALUES
 ('Admin', 'admin@gmail.com', 'admin', '+380-00-11-00-111'),
 ('Company', 'company@gmail.com', 'company', '+380-00-11-00-122'),
 ('Boss', 'boss@gmail.com', 'user3', '+380-00-11-00-123'),
 ('User1', 'user1@gmail.com', 'user1', '+380-00-11-00-124'),
 ('User2', 'user2@gmail.com', 'user2', '+380-00-11-00-125'),
 ('User3', 'user3@gmail.com', 'user3', '+380-00-11-00-126');

