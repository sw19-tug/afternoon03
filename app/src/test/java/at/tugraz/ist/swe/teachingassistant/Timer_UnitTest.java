package at.tugraz.ist.swe.teachingassistant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

@RunWith(MockitoJUnitRunner.class)
public class Timer_UnitTest {
    public int seconds = 0;

    @Test
    public void checkCorrectTime() throws Exception {
        Timer timer = new Timer();
        int seconds = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runTimer();
            }
        }, 0, 1000);
        sleep(4000);
        timer.cancel();
        assert (seconds < 3);
    }

    private void runTimer() {
        seconds++;
    }
}




