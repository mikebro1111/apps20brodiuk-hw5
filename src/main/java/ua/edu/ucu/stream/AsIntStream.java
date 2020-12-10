package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {

    private ArrayList<Integer> stream;

    private AsIntStream() {
        this.stream = new ArrayList<Integer>();
    }

    private AsIntStream(ArrayList<Integer> stream) {
        this.stream = stream;
    }

    public static IntStream of(int... values) {
        AsIntStream st = new AsIntStream();
        for (int val: values) {
            st.stream.add(val);
        }
        return st;
    }


    @Override
    public Double average() {
        emptyStream();
        return ((double) sum()) / count();
    }

    @Override
    public Integer max() {
        emptyStream();
        int max_value = stream.get(0);
        for (int i: stream)
            if ( max_value < i)
                max_value = i;
        return max_value;
    }

    @Override
    public Integer min() {
        emptyStream();
        int min_value = stream.get(0);
        for (int i: stream)
            if (min_value > i)
                min_value = i;
        return min_value;
    }

    @Override
    public long count() {
        return stream.size();
    }

    @Override
    public Integer sum() {
        emptyStream();
        int counter = 0;
        for (int i: stream)
            counter += i;
        return counter;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream stream1 = new AsIntStream();
        for (int i: stream)
            if (predicate.test(i))
                stream1.stream.add(i);
        return stream1;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i: stream)
            action.accept(i);
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        AsIntStream stream1 = new AsIntStream();
        for (int i: stream)
            stream1.stream.add(mapper.apply(i));
        return stream1;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<IntStream> stream1 = new ArrayList<>();
        for (Integer i: stream)
            stream1.add(func.applyAsIntStream(i));

        ArrayList<Integer> res = new ArrayList<>();
        for (IntStream i: stream1)
            for (int j: i.toArray())
                res.add(j);
        return new AsIntStream(res);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int i: stream)
            result = op.apply(result, i);
        return result;
    }

    @Override
    public int[] toArray() {
        int[] array = new int[stream.size()];
        for (int i = 0; i < stream.size(); i++)
            array[i] = stream.get(i);
        return array;
    }

    private void emptyStream() {
        if (stream.isEmpty()){
            throw new IllegalArgumentException("Empty stream");
        }
    }

}