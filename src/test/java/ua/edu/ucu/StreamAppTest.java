package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class StreamAppTest {

    private IntStream emptyStream;
    private IntStream intStream;


    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
        int[] zero = {};
        emptyStream = AsIntStream.of(zero);

    }

    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void test_average() {
        assertEquals(1, intStream.average(), 0.00001);
    }

    @Test
    public void test_sum() {
        assertEquals(5, intStream.sum(), 0.00001);
    }

    @Test
    public void test_count() {
        assertEquals(5, intStream.count(), 0.00001);
    }

    @Test
    public void test_min() {
        int[] intArr = {0, -1, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
        assertEquals(-1, intStream.min(), 0.00001);
    }

    @Test
    public void test_max() {
        assertEquals(3, intStream.max(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyException() {
        int total = emptyStream.sum();
    }


}