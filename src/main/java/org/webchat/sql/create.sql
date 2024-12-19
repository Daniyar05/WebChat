CREATE TABLE IF NOT EXISTS users (
                                     id_user VARCHAR(255) PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS chats (
                                     id_chat VARCHAR(255) PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL
);
ALTER TABLE chats ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE IF NOT EXISTS messages (
                                        id_message SERIAL PRIMARY KEY,
                                        id_chat VARCHAR(255) NOT NULL,
                                        id_from VARCHAR(255) NOT NULL,
                                        content TEXT,
                                        date TIMESTAMP,
                                        CONSTRAINT fk_chat FOREIGN KEY (id_chat) REFERENCES chats(id_chat) ON DELETE CASCADE,
                                        CONSTRAINT fk_user FOREIGN KEY (id_from) REFERENCES users(id_user) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_chats (
                                          id_user VARCHAR(255) NOT NULL,
                                          id_chat VARCHAR(255) NOT NULL,
                                          PRIMARY KEY (id_user, id_chat),
                                          CONSTRAINT fk_user_chat_user FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE,
                                          CONSTRAINT fk_user_chat_chat FOREIGN KEY (id_chat) REFERENCES chats(id_chat) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS user_mood (
                                         id BIGSERIAL PRIMARY KEY,
                                         id_user VARCHAR(255) NOT NULL,
                                         mood VARCHAR(255) NOT NULL,
                                         CONSTRAINT fk_user_mood_user FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);
