/*
 * Copyright (c) pakoito 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pacoworks.rxfunctions2;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;
import io.reactivex.functions.Function8;
import io.reactivex.functions.Function9;
import io.reactivex.functions.Predicate;

public final class RxFunctions {
    private static final BiFunction<Boolean, Boolean, Boolean> AND = new BiFunction<Boolean, Boolean, Boolean>() {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return first && second;
        }
    };

    private static final BiFunction<Boolean, Boolean, Boolean> OR = new BiFunction<Boolean, Boolean, Boolean>() {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return first || second;
        }
    };

    private RxFunctions() {
        // No instances
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func2.apply(func1.apply(t));
            }
        };
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2,
            final Function<T, T> func3) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func3.apply(func2.apply(func1.apply(t)));
            }
        };
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2,
            final Function<T, T> func3, final Function<T, T> func4) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func4.apply(func3.apply(func2.apply(func1.apply(t))));
            }
        };
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2,
            final Function<T, T> func3, final Function<T, T> func4, final Function<T, T> func5) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t)))));
            }
        };
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2,
            final Function<T, T> func3, final Function<T, T> func4, final Function<T, T> func5,
            final Function<T, T> func6) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func6.apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t))))));
            }
        };
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2,
            final Function<T, T> func3, final Function<T, T> func4, final Function<T, T> func5,
            final Function<T, T> func6, final Function<T, T> func7) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func7.apply(
                        func6.apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t)))))));
            }
        };
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2,
            final Function<T, T> func3, final Function<T, T> func4, final Function<T, T> func5,
            final Function<T, T> func6, final Function<T, T> func7, final Function<T, T> func8) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func8.apply(func7.apply(
                        func6.apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t))))))));
            }
        };
    }

    public static <T> Function<T, T> same(final Function<T, T> func1, final Function<T, T> func2,
            final Function<T, T> func3, final Function<T, T> func4, final Function<T, T> func5,
            final Function<T, T> func6, final Function<T, T> func7, final Function<T, T> func8,
            final Function<T, T> func9) {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return func9.apply(func8.apply(func7.apply(func6
                        .apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t)))))))));
            }
        };
    }

    public static <T, U, R> Function<T, R> chain(final Function<T, U> func1, final Function<U, R> func2) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return func2.apply(func1.apply(t));
            }
        };
    }

    public static <T, U, V, R> Function<T, R> chain(final Function<T, U> func1, final Function<U, V> func2,
            final Function<V, R> func3) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return func3.apply(func2.apply(func1.apply(t)));
            }
        };
    }

    public static <T, U, V, W, R> Function<T, R> chain(final Function<T, U> func1,
            final Function<U, V> func2, final Function<V, W> func3, final Function<W, R> func4) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return func4.apply(func3.apply(func2.apply(func1.apply(t))));
            }
        };
    }

    public static <T, U, V, W, X, R> Function<T, R> chain(final Function<T, U> func1,
            final Function<U, V> func2, final Function<V, W> func3, final Function<W, X> func4,
            final Function<X, R> func5) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t)))));
            }
        };
    }

    public static <T, U, V, W, X, Y, R> Function<T, R> chain(final Function<T, U> func1,
            final Function<U, V> func2, final Function<V, W> func3, final Function<W, X> func4,
            final Function<X, Y> func5, final Function<Y, R> func6) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return (func6.apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t)))))));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, R> Function<T, R> chain(final Function<T, U> func1,
            final Function<U, V> func2, final Function<V, W> func3, final Function<W, X> func4,
            final Function<X, Y> func5, final Function<Y, Z> func6, final Function<Z, R> func7) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return func7.apply(
                        func6.apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t)))))));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, R> Function<T, R> chain(final Function<T, U> func1,
            final Function<U, V> func2, final Function<V, W> func3, final Function<W, X> func4,
            final Function<X, Y> func5, final Function<Y, Z> func6, final Function<Z, A> func7,
            final Function<A, R> func8) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return func8.apply(func7.apply(
                        func6.apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t))))))));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, B, R> Function<T, R> chain(final Function<T, U> func1,
            final Function<U, V> func2, final Function<V, W> func3, final Function<W, X> func4,
            final Function<X, Y> func5, final Function<Y, Z> func6, final Function<Z, A> func7,
            final Function<A, B> func8, final Function<B, R> func9) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return func9.apply(func8.apply(func7.apply(func6
                        .apply(func5.apply(func4.apply(func3.apply(func2.apply(func1.apply(t)))))))));
            }
        };
    }

    public static <T, U, V, R> Function<T, R> combine(final Function<T, U> func1, final Function<T, V> func2,
            final BiFunction<U, V, R> funcResult) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return funcResult.apply(func1.apply(t), func2.apply(t));
            }
        };
    }

    public static <T, U, V, W, R> Function<T, R> combine(final Function<T, U> func1,
            final Function<T, V> func2, final Function<T, W> func3, final Function3<U, V, W, R> funcResult) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return funcResult.apply(func1.apply(t), func2.apply(t), func3.apply(t));
            }
        };
    }

    public static <T, U, V, W, X, R> Function<T, R> combine(final Function<T, U> func1,
            final Function<T, V> func2, final Function<T, W> func3, final Function<T, X> func4,
            final Function4<U, V, W, X, R> funcResult) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return funcResult.apply(func1.apply(t), func2.apply(t), func3.apply(t), func4.apply(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, R> Function<T, R> combine(final Function<T, U> func1,
            final Function<T, V> func2, final Function<T, W> func3, final Function<T, X> func4,
            final Function<T, Y> func5, final Function<T, Z> func6,
            final Function6<U, V, W, X, Y, Z, R> funcResult) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return funcResult.apply(func1.apply(t), func2.apply(t), func3.apply(t), func4.apply(t),
                        func5.apply(t), func6.apply(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, R> Function<T, R> combine(final Function<T, U> func1,
            final Function<T, V> func2, final Function<T, W> func3, final Function<T, X> func4,
            final Function<T, Y> func5, final Function<T, Z> func6, final Function<T, A> func7,
            final Function7<U, V, W, X, Y, Z, A, R> funcResult) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return funcResult.apply(func1.apply(t), func2.apply(t), func3.apply(t), func4.apply(t),
                        func5.apply(t), func6.apply(t), func7.apply(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, B, R> Function<T, R> combine(final Function<T, U> func1,
            final Function<T, V> func2, final Function<T, W> func3, final Function<T, X> func4,
            final Function<T, Y> func5, final Function<T, Z> func6, final Function<T, A> func7,
            final Function<T, B> func8, final Function8<U, V, W, X, Y, Z, A, B, R> funcResult) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return funcResult.apply(func1.apply(t), func2.apply(t), func3.apply(t), func4.apply(t),
                        func5.apply(t), func6.apply(t), func7.apply(t), func8.apply(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, B, C, R> Function<T, R> combine(final Function<T, U> func1,
            final Function<T, V> func2, final Function<T, W> func3, final Function<T, X> func4,
            final Function<T, Y> func5, final Function<T, Z> func6, final Function<T, A> func7,
            final Function<T, B> func8, final Function<T, C> func9,
            final Function9<U, V, W, X, Y, Z, A, B, C, R> funcResult) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                return funcResult.apply(func1.apply(t), func2.apply(t), func3.apply(t), func4.apply(t),
                        func5.apply(t), func6.apply(t), func7.apply(t), func8.apply(t), func9.apply(t));
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2) {
        return reduceInternal(initial, compose, func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2, final Function<T, R> func3) {
        return reduceInternal(initial, compose, func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2, final Function<T, R> func3,
            final Function<T, R> func4) {
        return reduceInternal(initial, compose, func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2, final Function<T, R> func3,
            final Function<T, R> func4, final Function<T, R> func5) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2, final Function<T, R> func3,
            final Function<T, R> func4, final Function<T, R> func5, final Function<T, R> func6) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2, final Function<T, R> func3,
            final Function<T, R> func4, final Function<T, R> func5, final Function<T, R> func6,
            final Function<T, R> func7) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2, final Function<T, R> func3,
            final Function<T, R> func4, final Function<T, R> func5, final Function<T, R> func6,
            final Function<T, R> func7, final Function<T, R> func8) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6, func7,
                func8);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> reduce(final R initial, final BiFunction<R, R, R> compose,
            final Function<T, R> func1, final Function<T, R> func2, final Function<T, R> func3,
            final Function<T, R> func4, final Function<T, R> func5, final Function<T, R> func6,
            final Function<T, R> func7, final Function<T, R> func8, final Function<T, R> func9) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6, func7,
                func8, func9);
    }

    public static <T> Function<T, Boolean> not(final Function<T, Boolean> func1) {
        return new Function<T, Boolean>() {
            @Override
            public Boolean apply(T t) throws Exception {
                return !func1.apply(t);
            }
        };
    }

    public static <T> Predicate<T> not(final Predicate<T> func1) {
        return new Predicate<T>() {
            @Override
            public boolean test(T t) throws Exception {
                return !func1.test(t);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2) {
        return reduceInternal(true, AND, func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3) {
        return reduceInternal(true, AND, func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4) {
        return reduceInternal(true, AND, func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6, final Function<T, Boolean> func7) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6, final Function<T, Boolean> func7,
            final Function<T, Boolean> func8) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6, func7, func8);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> and(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6, final Function<T, Boolean> func7,
            final Function<T, Boolean> func8, final Function<T, Boolean> func9) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6, func7, func8,
                func9);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2) {
        return predicateAndInternal(func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2, final Predicate<T> func3) {
        return predicateAndInternal(func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2, final Predicate<T> func3,
                                       final Predicate<T> func4) {
        return predicateAndInternal(func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2, final Predicate<T> func3,
                                       final Predicate<T> func4, final Predicate<T> func5) {
        return predicateAndInternal(func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2, final Predicate<T> func3,
                                       final Predicate<T> func4, final Predicate<T> func5,
                                       final Predicate<T> func6) {
        return predicateAndInternal(func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2, final Predicate<T> func3,
                                       final Predicate<T> func4, final Predicate<T> func5,
                                       final Predicate<T> func6, final Predicate<T> func7) {
        return predicateAndInternal(func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2, final Predicate<T> func3,
                                       final Predicate<T> func4, final Predicate<T> func5,
                                       final Predicate<T> func6, final Predicate<T> func7,
                                       final Predicate<T> func8) {
        return predicateAndInternal(func1, func2, func3, func4, func5, func6, func7, func8);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(final Predicate<T> func1,
                                       final Predicate<T> func2, final Predicate<T> func3,
                                       final Predicate<T> func4, final Predicate<T> func5,
                                       final Predicate<T> func6, final Predicate<T> func7,
                                       final Predicate<T> func8, final Predicate<T> func9) {
        return predicateAndInternal(func1, func2, func3, func4, func5, func6, func7, func8,
                func9);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2) {
        return reduceInternal(false, OR, func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3) {
        return reduceInternal(false, OR, func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4) {
        return reduceInternal(false, OR, func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6, final Function<T, Boolean> func7) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6, final Function<T, Boolean> func7,
            final Function<T, Boolean> func8) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6, func7, func8);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, Boolean> or(final Function<T, Boolean> func1,
            final Function<T, Boolean> func2, final Function<T, Boolean> func3,
            final Function<T, Boolean> func4, final Function<T, Boolean> func5,
            final Function<T, Boolean> func6, final Function<T, Boolean> func7,
            final Function<T, Boolean> func8, final Function<T, Boolean> func9) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6, func7, func8,
                func9);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2) {
        return predicateOrInternal(func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2, final Predicate<T> func3) {
        return predicateOrInternal(func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2, final Predicate<T> func3,
                                      final Predicate<T> func4) {
        return predicateOrInternal(func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2, final Predicate<T> func3,
                                      final Predicate<T> func4, final Predicate<T> func5) {
        return predicateOrInternal(func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2, final Predicate<T> func3,
                                      final Predicate<T> func4, final Predicate<T> func5,
                                      final Predicate<T> func6) {
        return predicateOrInternal(func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2, final Predicate<T> func3,
                                      final Predicate<T> func4, final Predicate<T> func5,
                                      final Predicate<T> func6, final Predicate<T> func7) {
        return predicateOrInternal(func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2, final Predicate<T> func3,
                                      final Predicate<T> func4, final Predicate<T> func5,
                                      final Predicate<T> func6, final Predicate<T> func7,
                                      final Predicate<T> func8) {
        return predicateOrInternal(func1, func2, func3, func4, func5, func6, func7, func8);
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(final Predicate<T> func1,
                                      final Predicate<T> func2, final Predicate<T> func3,
                                      final Predicate<T> func4, final Predicate<T> func5,
                                      final Predicate<T> func6, final Predicate<T> func7,
                                      final Predicate<T> func8, final Predicate<T> func9) {
        return predicateOrInternal(func1, func2, func3, func4, func5, func6, func7, func8,
                func9);
    }

    private static <T, R> Function<T, R> reduceInternal(final R initial, final BiFunction<R, R, R> compose,
                                                        final Function<T, R>... actions) {
        return new Function<T, R>() {
            @Override
            public R apply(final T t) {
                return Observable.fromArray(actions).reduce(initial, new BiFunction<R, Function<T, R>, R>() {
                    @Override
                    public R apply(R r, Function<T, R> ttFunction) throws Exception {
                        return compose.apply(r, ttFunction.apply(t));
                    }
                }).blockingGet();
            }
        };
    }

    private static <T> Predicate<T> predicateOrInternal(final Predicate<T>... predicates) {
        return new Predicate<T>() {
            @Override
            public boolean test(T t) throws Exception {
                for (Predicate<T> predicate: predicates) {
                    if (predicate.test(t)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private static <T> Predicate<T> predicateAndInternal(final Predicate<T>... predicates) {
        return new Predicate<T>() {
            @Override
            public boolean test(T t) throws Exception {
                for (Predicate<T> predicate: predicates) {
                    if (!predicate.test(t)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }
}
