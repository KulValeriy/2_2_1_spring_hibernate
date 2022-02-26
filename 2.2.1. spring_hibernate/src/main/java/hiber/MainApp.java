package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User valery = new User("Valery", "Kuleshov", "Valery@mail.ru");
      User ivan = new User("Ivan", "Ivanov", "Ivan@gmail.com");
      User alexey = new User("Alexey", "Alexeev", "Alexey@yandex.ru");

      Car audi = new Car("Audi", 4);
      Car skoda = new Car("Skoda", 5);
      Car opel = new Car("Opel", 7);

      valery.setCar(audi);
      ivan.setCar(skoda);
      alexey.setCar(opel);

      userService.add(valery);
      userService.add(ivan);
      userService.add(alexey);

      for (User user : userService.listUsers()) {
         System.out.println(user.getFirstName() + " have " + user.getCar());
      }


      System.out.println(userService.getUserAndCar("Opel", 7).getFirstName() + " have Opel");


      context.close();
   }
}
