package com.stackroute.searchservice;

import com.stackroute.searchservice.domain.Question;
import com.stackroute.searchservice.domain.Topic;
import com.stackroute.searchservice.domain.User;
import com.stackroute.searchservice.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SearchServiceApplication  {
	@Autowired
	SearchRepository searchRepository;


	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}

BufferedReader br;
//	BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"))
	{
		try {
			br = new BufferedReader(new FileReader("Questions.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length >= 3)
					System.out.println(values[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}






//	@Override
//	public void run(String... args) throws Exception {
//
//
//			List<Topic> topics= readQuestionsFromCSV("Questions.txt");
//			for (Topic q : topics) {
//			System.out.println(searchRepository.save(q));
//			}
//		}
//	private static List<Topic> topics = new ArrayList<>();
//		public static List<Topic> readQuestionsFromCSV(String fileName){
//
//			Path pathToFile = Paths.get(fileName);
//
//			// create an instance of BufferedReader
//			// using try with resource, Java 7 feature to close resources
//			try (BufferedReader br = Files.newBufferedReader(pathToFile,
//					StandardCharsets.US_ASCII)) {
//
//				// read the first line from the text file
//				String line = br.readLine();
//
//				// loop until all lines are read
//				while (line != null) {
//
//					// use string.split to load a string array with the values from
//					// each line of
//					// the file, using a comma as the delimiter
//					String[] attributes = line.split(",");
//
//					Topic topic = createTopic(attributes);
//
//					// adding book into ArrayList
//					topics.add(topic);
//
//					// read next line before looping
//					// if end of file reached, line would be null
//					line = br.readLine();
//				}
//
//			} catch (IOException ioe) {
//				System.out.println(ioe);
//			}
//
//			return topics;
//		}
//	private static  List<Question> questionList=new ArrayList<>();
//
//		private  static Topic createTopic(String[] metadata){
//
//			String question = metadata[0];
//			String topics=metadata[1];
////			if(metadata[1].contains("$")){
////				String topic[]=metadata[1].split("$");
////				topics = Arrays.asList(topic);
////			}
////			else {
////				topics = new ArrayList<>();
////				topics.add(metadata[1]);
////			}
//			long timestamp = Long.parseLong(metadata[2]);
//			User userDTO = new User(metadata[3],metadata[4],metadata[5]);
////		int price = Integer.parseInt(metadata[1]);
////		String author = metadata[2];
////    public Question(final String question, final String description, final List<String> topics, final int upvotes, final long timestamp, final int downvotes, final User userDTO, final List<Comment> comment, final List<Answer> answerDTO) {
//			Question questions=new Question(question,null,0,timestamp,0,null,null);
//			// create and return question of this metadata
////			private String question;
////			private String description;
////			private int upvotes;
////			private long timestamp;
////			private int downvotes;
////			List<Answers> answers;
////			List<Comments> comments;
//			questionList.add(questions);
//			return new Topic(topics,questionList);
//		}




