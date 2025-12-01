-- =====================
--        BANCOS
-- =====================
INSERT INTO banco (nombre, pais, activo) VALUES ('Santander', 'Argentina', true);
INSERT INTO banco (nombre, pais, activo) VALUES ('Santander', 'Uruguay', true);
INSERT INTO banco (nombre, pais, activo) VALUES ('Santander', 'España', true);
INSERT INTO banco (nombre, pais, activo) VALUES ('Santander', 'Portugal', true);

-- password: password
-- https://bcrypt-generator.com/
INSERT INTO app_user (username, password, role) VALUES ('user1', '$2a$12$IiWpvJfwH/HvE3dC7ltL2.KbAn7HaiVeJGvDXMZlxjjuW6VdteFby', 'USER');
INSERT INTO app_user (username, password, role) VALUES ('admin', '$2a$12$IiWpvJfwH/HvE3dC7ltL2.KbAn7HaiVeJGvDXMZlxjjuW6VdteFby', 'ADMIN');