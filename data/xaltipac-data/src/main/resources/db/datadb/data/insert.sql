
INSERT INTO profiles (name,created,last_modified ) VALUES ('saiyajin','2014-08-27 17:04:01','2014-08-26 20:14:13'),
('Negocio','2014-08-27 17:04:01','2014-08-26 20:14:13'),
('aplicadito','2016-03-05 09:57:20','2016-03-05 09:57:20');

INSERT INTO resources (name, path) VALUES 
('showResources','/recurso'),
('addResource','/recurso/agregar'),
('editResource','/recurso/editar'),
('deleteResource','/recurso/elimina'),
('showProfiles','/perfil/'),
('addProfile','/perfil/agregar'),
('editProfile','/perfil/'),
('deleteProfile','/perfil/eliminar'),
('showUsers','/usuario'),
('addUser','/usuario/agregar'),
('editUser','/usuario/editar'),
('deleteUser','/usuario/elimina');


INSERT INTO actions (id_profile, id_resource) VALUES 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12);

INSERT INTO settings (name, value, created, last_modified) VALUES 
('LOG_USER','YES','2014-08-30 04:09:52','2014-08-30 04:09:52'),('SESSION_TIME','900','2014-09-02 19:43:17','2014-09-02 19:43:17');


INSERT INTO users (username,password,id_profile,id_business,enabled,created,last_modified,deleted) VALUES 
('admin','1cfc4468de86913786303ee169987472',1,0,1,'2014-08-27 17:04:02','2014-08-27 00:51:08',0),('padrino','1cfc4468de86913786303ee169987472',2,0,1,'2016-03-05 22:48:59','2016-03-05 22:48:59',0),('ejemplo','1cfc4468de86913786303ee169987472',1,0,1,'2016-03-06 23:26:25','2016-03-06 23:26:25',0),('segundos','1cfc4468de86913786303ee169987472',1,0,1,'2016-03-07 08:06:21','2016-03-07 08:06:21',0),('tercero','1cfc4468de86913786303ee169987472',1,0,1,'2016-03-07 08:12:10','2016-03-07 08:12:10',0);


INSERT INTO users_detail (id_user,name,lastname,lastname_maternal,email,created,last_modified,deleted) VALUES (1,'Administrador','Kraken',NULL,'juanbusines@gmail.com','2014-08-27 17:04:02','2014-08-26 19:15:10',0),(2,'asda','dasdasd','dads','pumas@hola.com','2016-03-05 22:48:59','2016-03-05 22:48:59',0),(3,'juan','gomez','fabela','juan@juan.com','2016-03-06 23:26:25','2016-03-06 23:26:25',0),(4,'ejemplo','gomez','fabela','pumas@hola.com','2016-03-07 08:06:21','2016-03-07 08:06:21',0),(5,'ejemplo','gomez','fabela','pumas@hola.com','2016-03-07 08:12:10','2016-03-07 08:12:10',0);


