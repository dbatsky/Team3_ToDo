package com.example.team3_todo;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends AppCompatActivity {

    MainActivity mainActivity = new MainActivity();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void setMainActivity() {
    }
}