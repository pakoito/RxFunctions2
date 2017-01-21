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

import rx.Observable;
import rx.functions.*;

public final class RxFunctions {
    private static final Func2<Boolean, Boolean, Boolean> AND = new Func2<Boolean, Boolean, Boolean>() {
        @Override
        public Boolean call(Boolean first, Boolean second) {
            return first && second;
        }
    };

    private static final Func2<Boolean, Boolean, Boolean> OR = new Func2<Boolean, Boolean, Boolean>() {
        @Override
        public Boolean call(Boolean first, Boolean second) {
            return first || second;
        }
    };

    private RxFunctions() {
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func2.call(func1.call(t));
            }
        };
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2,
            final Func1<T, T> func3) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func3.call(func2.call(func1.call(t)));
            }
        };
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2,
            final Func1<T, T> func3, final Func1<T, T> func4) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func4.call(func3.call(func2.call(func1.call(t))));
            }
        };
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2,
            final Func1<T, T> func3, final Func1<T, T> func4, final Func1<T, T> func5) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func5.call(func4.call(func3.call(func2.call(func1.call(t)))));
            }
        };
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2,
            final Func1<T, T> func3, final Func1<T, T> func4, final Func1<T, T> func5,
            final Func1<T, T> func6) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func6.call(func5.call(func4.call(func3.call(func2.call(func1.call(t))))));
            }
        };
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2,
            final Func1<T, T> func3, final Func1<T, T> func4, final Func1<T, T> func5,
            final Func1<T, T> func6, final Func1<T, T> func7) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func7.call(
                        func6.call(func5.call(func4.call(func3.call(func2.call(func1.call(t)))))));
            }
        };
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2,
            final Func1<T, T> func3, final Func1<T, T> func4, final Func1<T, T> func5,
            final Func1<T, T> func6, final Func1<T, T> func7, final Func1<T, T> func8) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func8.call(func7.call(
                        func6.call(func5.call(func4.call(func3.call(func2.call(func1.call(t))))))));
            }
        };
    }

    public static <T> Func1<T, T> same(final Func1<T, T> func1, final Func1<T, T> func2,
            final Func1<T, T> func3, final Func1<T, T> func4, final Func1<T, T> func5,
            final Func1<T, T> func6, final Func1<T, T> func7, final Func1<T, T> func8,
            final Func1<T, T> func9) {
        return new Func1<T, T>() {
            @Override
            public T call(T t) {
                return func9.call(func8.call(func7.call(func6
                        .call(func5.call(func4.call(func3.call(func2.call(func1.call(t)))))))));
            }
        };
    }

    public static <T, U, R> Func1<T, R> chain(final Func1<T, U> func1, final Func1<U, R> func2) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return func2.call(func1.call(t));
            }
        };
    }

    public static <T, U, V, R> Func1<T, R> chain(final Func1<T, U> func1, final Func1<U, V> func2,
            final Func1<V, R> func3) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return func3.call(func2.call(func1.call(t)));
            }
        };
    }

    public static <T, U, V, W, R> Func1<T, R> chain(final Func1<T, U> func1,
            final Func1<U, V> func2, final Func1<V, W> func3, final Func1<W, R> func4) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return func4.call(func3.call(func2.call(func1.call(t))));
            }
        };
    }

    public static <T, U, V, W, X, R> Func1<T, R> chain(final Func1<T, U> func1,
            final Func1<U, V> func2, final Func1<V, W> func3, final Func1<W, X> func4,
            final Func1<X, R> func5) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return func5.call(func4.call(func3.call(func2.call(func1.call(t)))));
            }
        };
    }

    public static <T, U, V, W, X, Y, R> Func1<T, R> chain(final Func1<T, U> func1,
            final Func1<U, V> func2, final Func1<V, W> func3, final Func1<W, X> func4,
            final Func1<X, Y> func5, final Func1<Y, R> func6) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return (func6.call(func5.call(func4.call(func3.call(func2.call(func1.call(t)))))));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, R> Func1<T, R> chain(final Func1<T, U> func1,
            final Func1<U, V> func2, final Func1<V, W> func3, final Func1<W, X> func4,
            final Func1<X, Y> func5, final Func1<Y, Z> func6, final Func1<Z, R> func7) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return func7.call(
                        func6.call(func5.call(func4.call(func3.call(func2.call(func1.call(t)))))));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, R> Func1<T, R> chain(final Func1<T, U> func1,
            final Func1<U, V> func2, final Func1<V, W> func3, final Func1<W, X> func4,
            final Func1<X, Y> func5, final Func1<Y, Z> func6, final Func1<Z, A> func7,
            final Func1<A, R> func8) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return func8.call(func7.call(
                        func6.call(func5.call(func4.call(func3.call(func2.call(func1.call(t))))))));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, B, R> Func1<T, R> chain(final Func1<T, U> func1,
            final Func1<U, V> func2, final Func1<V, W> func3, final Func1<W, X> func4,
            final Func1<X, Y> func5, final Func1<Y, Z> func6, final Func1<Z, A> func7,
            final Func1<A, B> func8, final Func1<B, R> func9) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return func9.call(func8.call(func7.call(func6
                        .call(func5.call(func4.call(func3.call(func2.call(func1.call(t)))))))));
            }
        };
    }

    public static <T, U, V, R> Func1<T, R> combine(final Func1<T, U> func1, final Func1<T, V> func2,
            final Func2<U, V, R> funcResult) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return funcResult.call(func1.call(t), func2.call(t));
            }
        };
    }

    public static <T, U, V, W, R> Func1<T, R> combine(final Func1<T, U> func1,
            final Func1<T, V> func2, final Func1<T, W> func3, final Func3<U, V, W, R> funcResult) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return funcResult.call(func1.call(t), func2.call(t), func3.call(t));
            }
        };
    }

    public static <T, U, V, W, X, R> Func1<T, R> combine(final Func1<T, U> func1,
            final Func1<T, V> func2, final Func1<T, W> func3, final Func1<T, X> func4,
            final Func4<U, V, W, X, R> funcResult) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return funcResult.call(func1.call(t), func2.call(t), func3.call(t), func4.call(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, R> Func1<T, R> combine(final Func1<T, U> func1,
            final Func1<T, V> func2, final Func1<T, W> func3, final Func1<T, X> func4,
            final Func1<T, Y> func5, final Func1<T, Z> func6,
            final Func6<U, V, W, X, Y, Z, R> funcResult) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return funcResult.call(func1.call(t), func2.call(t), func3.call(t), func4.call(t),
                        func5.call(t), func6.call(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, R> Func1<T, R> combine(final Func1<T, U> func1,
            final Func1<T, V> func2, final Func1<T, W> func3, final Func1<T, X> func4,
            final Func1<T, Y> func5, final Func1<T, Z> func6, final Func1<T, A> func7,
            final Func7<U, V, W, X, Y, Z, A, R> funcResult) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return funcResult.call(func1.call(t), func2.call(t), func3.call(t), func4.call(t),
                        func5.call(t), func6.call(t), func7.call(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, B, R> Func1<T, R> combine(final Func1<T, U> func1,
            final Func1<T, V> func2, final Func1<T, W> func3, final Func1<T, X> func4,
            final Func1<T, Y> func5, final Func1<T, Z> func6, final Func1<T, A> func7,
            final Func1<T, B> func8, final Func8<U, V, W, X, Y, Z, A, B, R> funcResult) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return funcResult.call(func1.call(t), func2.call(t), func3.call(t), func4.call(t),
                        func5.call(t), func6.call(t), func7.call(t), func8.call(t));
            }
        };
    }

    public static <T, U, V, W, X, Y, Z, A, B, C, R> Func1<T, R> combine(final Func1<T, U> func1,
            final Func1<T, V> func2, final Func1<T, W> func3, final Func1<T, X> func4,
            final Func1<T, Y> func5, final Func1<T, Z> func6, final Func1<T, A> func7,
            final Func1<T, B> func8, final Func1<T, C> func9,
            final Func9<U, V, W, X, Y, Z, A, B, C, R> funcResult) {
        return new Func1<T, R>() {
            @Override
            public R call(T t) {
                return funcResult.call(func1.call(t), func2.call(t), func3.call(t), func4.call(t),
                        func5.call(t), func6.call(t), func7.call(t), func8.call(t), func9.call(t));
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2) {
        return reduceInternal(initial, compose, func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2, final Func1<T, R> func3) {
        return reduceInternal(initial, compose, func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2, final Func1<T, R> func3,
            final Func1<T, R> func4) {
        return reduceInternal(initial, compose, func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2, final Func1<T, R> func3,
            final Func1<T, R> func4, final Func1<T, R> func5) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2, final Func1<T, R> func3,
            final Func1<T, R> func4, final Func1<T, R> func5, final Func1<T, R> func6) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2, final Func1<T, R> func3,
            final Func1<T, R> func4, final Func1<T, R> func5, final Func1<T, R> func6,
            final Func1<T, R> func7) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2, final Func1<T, R> func3,
            final Func1<T, R> func4, final Func1<T, R> func5, final Func1<T, R> func6,
            final Func1<T, R> func7, final Func1<T, R> func8) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6, func7,
                func8);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Func1<T, R> reduce(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R> func1, final Func1<T, R> func2, final Func1<T, R> func3,
            final Func1<T, R> func4, final Func1<T, R> func5, final Func1<T, R> func6,
            final Func1<T, R> func7, final Func1<T, R> func8, final Func1<T, R> func9) {
        return reduceInternal(initial, compose, func1, func2, func3, func4, func5, func6, func7,
                func8, func9);
    }

    public static <T> Func1<T, Boolean> not(final Func1<T, Boolean> func1) {
        return new Func1<T, Boolean>() {
            @Override
            public Boolean call(T t) {
                return !func1.call(t);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2) {
        return reduceInternal(true, AND, func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3) {
        return reduceInternal(true, AND, func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4) {
        return reduceInternal(true, AND, func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6, final Func1<T, Boolean> func7) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6, final Func1<T, Boolean> func7,
            final Func1<T, Boolean> func8) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6, func7, func8);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> and(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6, final Func1<T, Boolean> func7,
            final Func1<T, Boolean> func8, final Func1<T, Boolean> func9) {
        return reduceInternal(true, AND, func1, func2, func3, func4, func5, func6, func7, func8,
                func9);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2) {
        return reduceInternal(false, OR, func1, func2);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3) {
        return reduceInternal(false, OR, func1, func2, func3);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4) {
        return reduceInternal(false, OR, func1, func2, func3, func4);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6, final Func1<T, Boolean> func7) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6, func7);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6, final Func1<T, Boolean> func7,
            final Func1<T, Boolean> func8) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6, func7, func8);
    }

    @SuppressWarnings("unchecked")
    public static <T> Func1<T, Boolean> or(final Func1<T, Boolean> func1,
            final Func1<T, Boolean> func2, final Func1<T, Boolean> func3,
            final Func1<T, Boolean> func4, final Func1<T, Boolean> func5,
            final Func1<T, Boolean> func6, final Func1<T, Boolean> func7,
            final Func1<T, Boolean> func8, final Func1<T, Boolean> func9) {
        return reduceInternal(false, OR, func1, func2, func3, func4, func5, func6, func7, func8,
                func9);
    }

    private static <T, R> Func1<T, R> reduceInternal(final R initial, final Func2<R, R, R> compose,
            final Func1<T, R>... actions) {
        return new Func1<T, R>() {
            @Override
            public R call(final T t) {
                return Observable.from(actions).reduce(initial, new Func2<R, Func1<T, R>, R>() {
                    @Override
                    public R call(R r, Func1<T, R> ttFunc1) {
                        return compose.call(r, ttFunc1.call(t));
                    }
                }).toBlocking().first();
            }
        };
    }
}
