package io.reactivex.rxjavafx.operators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjavafx.transformers.FxFlowableTransformers;
import io.reactivex.rxjavafx.transformers.FxObservableTransformers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public final class OperatorsTest {

    @Test
    public void testDoOnNextCountObservable() {

        final List<Integer> onNextCounts = new ArrayList<>();

        Observable.just("Alpha", "Beta", "Gamma")
                .compose(FxObservableTransformers.doOnNextCount(onNextCounts::add))
                .subscribe();

        assertTrue(onNextCounts.containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testDoOnCompleteCountObservable() {
        AtomicInteger onCompleteCount = new AtomicInteger();


        Observable.just("Alpha", "Beta", "Gamma")
                .compose(FxObservableTransformers.doOnCompleteCount(onCompleteCount::set))
                .subscribe();

      assertEquals(3, onCompleteCount.get());
    }

    @Test
    public void testDoOnErrorCountObservable() {
        AtomicInteger onErrorCount = new AtomicInteger();

        Observable.just(5, 10, 15, 0, 20)
                .map(i -> 5 / i)
                .compose(FxObservableTransformers.doOnErrorCount(onErrorCount::set))
                .subscribe();

      assertEquals(3, onErrorCount.get());
    }


    @Test
    public void testDoOnNextCountFlowable() {

        final List<Integer> onNextCounts = new ArrayList<>();

        Flowable.just("Alpha", "Beta", "Gamma")
                .compose(FxFlowableTransformers.doOnNextCount(onNextCounts::add))
                .subscribe();

        assertTrue(onNextCounts.containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testDoOnCompleteCountFlowable() {
        AtomicInteger onCompleteCount = new AtomicInteger();

        Flowable.just("Alpha", "Beta", "Gamma")
                .compose(FxFlowableTransformers.doOnCompleteCount(onCompleteCount::set))
                .subscribe();

      assertEquals(3, onCompleteCount.get());
    }

    @Test
    public void testDoOnErrorCountFlowable() {
        AtomicInteger onErrorCount = new AtomicInteger();

        Flowable.just(5, 10, 15, 0, 20)
                .map(i -> 5 / i)
                .compose(FxFlowableTransformers.doOnErrorCount(onErrorCount::set))
                .subscribe();

      assertEquals(3, onErrorCount.get());
    }
}
