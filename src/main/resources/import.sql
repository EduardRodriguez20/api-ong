INSERT INTO users (email, pwd) VALUES ('admin@example.com', 'passAdmin');
INSERT INTO users (email, pwd) VALUES ('admin2@example.com', 'passAdmin2');
INSERT INTO users (email, pwd) VALUES ('admin3@example.com', 'passAdmin3');

INSERT INTO roles (role_name, id_user) VALUES ('ROLE_ADMIN', 1);
INSERT INTO roles (role_name, id_user) VALUES ('ROLE_ADMIN', 2);
INSERT INTO roles (role_name, id_user) VALUES ('ROLE_ADMIN', 3);

INSERT INTO `cities` (`id`, `department`, `name`) VALUES (1,'Santander','Bucaramanga'),(2,'Santander','Floridablanca'),(3,'Cundinamarca','Bogota');
INSERT INTO `professions` (`id`, `code_pr`, `name`) VALUES (1,'PR-1','Doctor'),(2,'PR-2','Enfermero');
INSERT INTO `users` (`id`, `email`, `pwd`) VALUES (1,'admin@example.com','passAdmin'),(2,'admin5@example.com','passAdmin5'),(3,'aux1@example.com','passAux1');
INSERT INTO `roles` (`id`, `role_name`, `id_user`) VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_ADMIN',2),(3,'ROLE_ASSISTANT',3);

