package ru.example;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class GameTests {


    @Test
    public void game_Core_1() throws Exception {
        assertThat(Main.gameCore("house","mouse"), is(equalTo(false)));
    }

    @Test
    public void game_Core_2() throws Exception {
        Main.gameCore("goose","mouse");
        assertThat(Main.bulls, is(equalTo(3)));
    }

    @Test
    public void game_Core_3() throws Exception {
        Main.gameCore("close","mouse");
        assertThat(Main.bulls, is(equalTo(2)));
        assertThat(Main.cows, is(equalTo(1)));
    }

    @Test
    public void game_Core_4() throws Exception {
        Main.gameCore("aval","java");
        assertThat(Main.cows, is(equalTo(3)));
    }
}