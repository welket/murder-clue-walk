package com.SundayScaries.MurderClueWalk.bootstrap;

import com.SundayScaries.MurderClueWalk.domain.*;
import com.SundayScaries.MurderClueWalk.domain.User;

import com.SundayScaries.MurderClueWalk.repository.RoleRepository;
import com.SundayScaries.MurderClueWalk.repository.UserRepository;
import com.SundayScaries.MurderClueWalk.service.ClueService;
import com.SundayScaries.MurderClueWalk.service.MysteryService;
import com.SundayScaries.MurderClueWalk.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataBaseLoader implements CommandLineRunner {

    UserService userService;
    UserRepository userRepository;
    RoleRepository roleRepository;


    public DataBaseLoader(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        addUsersAndRoles();


        //Allt under är OK
        String pw = BCrypt.hashpw("password", BCrypt.gensalt());
        int ranking = 0;

        User user1 = new User("random@email.com", pw, 10.15, 100, 2, 20, "male", "sweden", true);
        UserService.save(user1);
        System.out.println(user1.toString());

        String pw2 = BCrypt.hashpw("qwerty", BCrypt.gensalt());

        User user2 = new User("slump@email.com", pw2, 42.18, 500, 1, 35, "female", "norway", true);
        UserService.save(user2);
        System.out.println(user2.toString());

        String pw3 = BCrypt.hashpw("123456789", BCrypt.gensalt());

        User user3 = new User("blabla@email.com", pw3, 0.75, 5, 4, 20, "male", "denmark", true);
        UserService.save(user3);
        System.out.println(user3.toString());

        String pw4 = BCrypt.hashpw("admin", BCrypt.gensalt());

        User user4 = new User("blaha@email.com", pw4, 2.73, 20, 3, 57, "female", "finland", true);
        UserService.save(user4);
        System.out.println(user4.toString());

        //FAKE LEDTRÅDAR OCH MYSTERIUM, BEHÅLL!!!!!
//        Mystery mystery1 = new Mystery("Death @ Kista", "N/A", "Random person is found dead somewhere....", "59.408, 17.943", 100);
//        MysteryService.save(mystery1);
//        System.out.println(mystery1.toString());
//
//
//        Clue clue1 = new Clue("You found a bowtie, it looks weird.....", "59.41, 17.95", "N/A", 10);
//        ClueService.save(clue1);
//        System.out.println(clue1.toString());
//
//        Clue clue2 = new Clue("You found a trench coat, it looks tacky.....", "59.411, 17.95", "N/A", 10);
//        ClueService.save(clue2);
//        System.out.println(clue2.toString());
//
//        Clue clue3 = new Clue("You found a bloody knife, and it was in your gut.....", "59.407, 17.947", "N/A", 10);
//        ClueService.save(clue3);
//        System.out.println(clue3.toString());
    }

    private void addUsersAndRoles() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User user = new User("user@gmail.com", secret, 10.15, 100, 2, 20, "male", "sweden", true);
        user.addRole(userRole);
        userRepository.save(user);

//        User admin = new User("admin@gmail.com", secret, 42.18, 500, 1, 35, "female", "norway", true);
//        admin.addRole(adminRole);
//        userRepository.save(admin);

        User master = new User("super@gmail.com", secret, 0.75, 5, 4, 20, "male", "denmark", true);
        master.addRoles(new HashSet<>(Arrays.asList(userRole,adminRole)));
        userRepository.save(master);

    }
}

//SQL kod för H2
//SELECT *
//FROM MYSTERY
//INNER JOIN Clue on Mystery.mysteryID = Clue.mysteryID;

//SQL kod för rank
//SELECT *
//FROM USER
//ORDER BY score DESC;