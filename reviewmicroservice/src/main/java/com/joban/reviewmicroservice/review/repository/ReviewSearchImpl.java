package com.joban.reviewmicroservice.review.repository;

import com.joban.reviewmicroservice.review.model.Review;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component // this needed is to be declared as a component if u want to auto wire its object
public class ReviewSearchImpl implements ReviewSearch {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter conv;
    public List<Review> searchByTerm(String term){

        final List<Review> searchedItems = new ArrayList<>();
        System.out.println("Seached value is>>> "+ term);

        MongoDatabase db = client.getDatabase("JobPost");
        MongoCollection<Document> collection = db.getCollection("jobs");

        AggregateIterable<Document> res=collection.aggregate(
                Arrays.asList(new Document("$search",
                                new Document("index", "skillsindex")
                                        .append("text",
                                                new Document("query", term)
                                                        .append("path", "skills"))),
                        new Document("$sort",
                                new Document("exp", -1L))));
//
        res.forEach(doc ->searchedItems.add(conv.read(Review.class,doc)));
        return searchedItems;
    }
}
