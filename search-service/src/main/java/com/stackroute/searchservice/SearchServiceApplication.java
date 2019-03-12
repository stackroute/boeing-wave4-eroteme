package com.stackroute.searchservice;

import com.stackroute.searchservice.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchServiceApplication {
    @Autowired
    SearchRepository searchRepository;


    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        BufferedReader br = null;
//        Gson gson = new Gson();
//
//        try {
//            br = new BufferedReader(new FileReader(("resources/SearchService5.json")));
//            // convert json string to object
//            Topic topic = gson.fromJson(br, Topic.class);
//            System.out.println("Topic Name: " + topic.getTopic());
//            searchRepository.save(topic);
//            System.out.println("Question Name: " + topic.getTopic());
//            System.out.println(topic.getQuestions());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}











