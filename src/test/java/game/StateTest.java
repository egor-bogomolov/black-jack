package game;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class StateTest {
    @Test
    public void serializationTest() throws Throwable {
        State state = new State(new int[]{1, 2, 3}, false);

        byte[] bytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(state);
            bytes = baos.toByteArray();
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            State state2 = (State)ois.readObject();
            assertEquals(state, state2);
        }
    }
}