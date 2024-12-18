CREATE TABLE IF NOT EXISTS users (
                                     id_user VARCHAR(255) PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password_hash VARCHAR(255) NOT NULL);

CREATE TABLE IF NOT EXISTS chats (
                                     id_chat VARCHAR(255) PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL);

CREATE TABLE IF NOT EXISTS messages (
                                        id_message SERIAL PRIMARY KEY,
                                        id_chat VARCHAR(255) NOT NULL REFERENCES chats(id_chat),
                                        id_from VARCHAR(255) NOT NULL,
                                        content TEXT,
                                        date TIMESTAMP
                                        
                                    );

CREATE TABLE IF NOT EXISTS user_chats (
                                          id_user VARCHAR(255) NOT NULL REFERENCES users(id_user),
                                          id_chat VARCHAR(255) NOT NULL REFERENCES chats(id_chat),
                                          PRIMARY KEY (id_user, id_chat));

