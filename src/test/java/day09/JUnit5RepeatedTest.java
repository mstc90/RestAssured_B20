package day09;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class JUnit5RepeatedTest {

    @RepeatedTest(10)
    public void testRepeating(){

        Faker faker = new Faker();

        //System.out.println("Name is: "+ faker.funnyName().name() );
        System.out.println(faker.chuckNorris().fact());
    }
}
