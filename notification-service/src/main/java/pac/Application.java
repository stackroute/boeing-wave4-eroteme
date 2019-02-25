package pac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pac.controllers.NotificationController;


@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    NotificationController notificationController;


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... strings) throws Exception {

//        String[] user=new String[]{"julu@fmail.com","sita"};
//        String Question="What is Angular?";
        notificationController.generateQuestionNotification();
        notificationController.generateAnswerNotification();
        notificationController.generateLikedNotification();
    }

}
