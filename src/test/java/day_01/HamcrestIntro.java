package day_01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

// Hamcrest assertion library already part of
// our RestAssured Dependency in pom file
// No separate dependency needed
public class HamcrestIntro {

    @DisplayName("Test 1 + 3 is 4")
    @Test
    public void test1(){

        assertThat(1+3, is(4));

        assertThat(1+3, equalTo(4));

        // add some nice error message if it fails
        // assertThat("Wrong Result!!", 1+3 , equalTo(5) );

        //test 1+3 is not 5
        assertThat(1+3, not (5));
        assertThat(1+3, is(not(5)));
        assertThat(1+3, not(equalTo(5)));

    }
}
