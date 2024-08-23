package com.joban.companymicroservice.company.repository;

import com.joban.companymicroservice.company.model.Company;
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
public class CompanySearchImpl implements CompanySearch {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter conv;
    public List<Company> searchByTerm(String term){

        final List<Company> searchedItems = new ArrayList<>();
        System.out.println("Seached value is>>> "+ term);

        MongoDatabase db = client.getDatabase("JobPost");
        MongoCollection<Document> collection = db.getCollection("company");

        AggregateIterable<Document> res=collection.aggregate(
                Arrays.asList(new Document("$search",
                                new Document("index", "skillsindex")
                                        .append("text",
                                                new Document("query", term)
                                                        .append("path", "skills"))),
                        new Document("$sort",
                                new Document("exp", -1L))));
//
        res.forEach(doc ->searchedItems.add(conv.read(Company.class,doc)));
        return searchedItems;
    }
}
