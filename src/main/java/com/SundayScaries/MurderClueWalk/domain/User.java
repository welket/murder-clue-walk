package com.SundayScaries.MurderClueWalk.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    Long userID;

    @NonNull
    @Email
    @NotEmpty
    String loginName;

    @NonNull
    @Size(min = 5)
    String password;

//    @NonNull
////    @OneToOne(fetch = FetchType.EAGER) // Låt denna vara på default EAGER men ändra till explicit EAGER om den inte fungerar
//    Rank rank;

    @NonNull
    double distance;

    @NonNull
    double score;

//    @NonNull
//    @ManyToOne(fetch = FetchType.EAGER) // Låt denna vara på default EAGER men ändra till explicit EAGER om den inte fungerar
//            int userMysteryID;

    @NonNull
    int age;

    @NonNull
    @NotEmpty
    String gender;

    @NonNull
    @NotEmpty
    String country;

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", distance=" + distance +
                ", age=" + age +
                ", gender=" + gender +
                ", country='" + country + '\'' +
                '}';
    }
}