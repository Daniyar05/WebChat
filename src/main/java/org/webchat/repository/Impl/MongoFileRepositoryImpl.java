package org.webchat.repository.Impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.Binary;
import org.webchat.repository.MongoFileRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class MongoFileRepositoryImpl implements MongoFileRepository {

    private final MongoCollection<Document> mongoCollection;

    public MongoFileRepositoryImpl(MongoClient mongoClient) {
        mongoCollection = mongoClient.getDatabase("chat_mongo_db")
                .getCollection("files");
    }


    @Override
    public String saveFile(String id, InputStream inputStream) {
        Binary data = null;
        try {
            data = new Binary(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Document document = new Document("file", data)
                .append("id", id);
        delete(id);
        mongoCollection.insertOne(document);
        return id;
    }

    @Override
    public byte[] getFile(String id) throws FileNotFoundException {
        Document selector = new Document("id", id);
        Document result = mongoCollection.find(selector).first();
        if(result != null) {
            return result.get("file", Binary.class).getData();
        }
        throw new FileNotFoundException();
    }

    @Override
    public void delete(String chatId) {
        Document selector = new Document("id", chatId);

        mongoCollection.deleteOne(selector);
    }
}