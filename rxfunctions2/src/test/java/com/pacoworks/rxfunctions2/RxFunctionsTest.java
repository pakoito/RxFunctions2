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

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Predicate;

public class RxFunctionsTest {

    @Test
    public void testSame() throws Exception {
        final int start = 0;
        int separate = INCREMENT.apply(INCREMENT.apply(INCREMENT.apply(INCREMENT.apply(start))));
        int chain = RxFunctions.same(INCREMENT, INCREMENT, INCREMENT, INCREMENT).apply(start);
        Assert.assertEquals(separate, chain);
    }

    @Test
    public void testChain() throws Exception {
        final int start = 0;
        int separate = TO_INT.apply(TO_STRING.apply(TO_INT.apply(TO_STRING.apply(start))));
        int chain = RxFunctions.chain(TO_STRING, TO_INT, TO_STRING, TO_INT).apply(start);
        Assert.assertEquals(separate, chain);
    }

    @Test
    public void testCombine() throws Exception {
        User separate = TO_USER.apply(TO_STRING.apply(55), TO_STRING.apply(55), IDENTITY.apply(55), IDENTITY.apply(55));
        User chain = RxFunctions.combine(TO_STRING, TO_STRING, IDENTITY, IDENTITY, TO_USER)
                .apply(55);
        Assert.assertEquals(separate, chain);
    }

    @Test
    public void testReduce() throws Exception {
        for (int i = 0; i < ITERATIONS; i++) {
            final Observable<Integer> value = Observable.just(0).cache();
            Integer separated = value.map(INCREMENT).map(INCREMENT).map(INCREMENT).blockingFirst();
            final Function<Integer, Integer> mergeIncrement = RxFunctions.reduce(0, BI_SUM, INCREMENT,
                    INCREMENT, INCREMENT);
            Integer chained = value.map(mergeIncrement).blockingFirst();
            Assert.assertEquals(separated, chained);
        }
    }

    @Test
    public void testAnd() throws Exception {
        for (int i = 0; i < ITERATIONS; i++) {
            Collections.shuffle(USER_NAMES);
            final Observable<User> userList = Observable.fromIterable(USER_NAMES).map(NAME_TO_USER).cache();
            List<User> separated = userList.filter(CORRECT_NAME).filter(IS_RETIRED)
                    .filter(IS_HIGH_INCOME).toList().blockingGet();
            final Predicate<User> mergeFilter = RxFunctions.and(CORRECT_NAME, IS_RETIRED,
                    IS_HIGH_INCOME);
            List<User> chained = userList.filter(mergeFilter).toList().blockingGet();
            Assert.assertEquals(separated, chained);
        }
    }

    @Test
    public void testOr() throws Exception {
        for (int i = 0; i < ITERATIONS; i++) {
            Collections.shuffle(USER_NAMES);
            final Observable<User> userList = Observable.fromIterable(USER_NAMES).map(NAME_TO_USER).cache();
            Set<User> separated = userList.toList()
                    .flatMap(new Function<List<User>, Single<Set<User>>>() {
                        @Override
                        public Single<Set<User>> apply(List<User> users) {
                            final Observable<User> from = Observable.fromIterable(users);
                            return Observable
                                    .merge(from.filter(RxFunctions.not(CORRECT_NAME)),
                                            from.filter(IS_RETIRED), from.filter(IS_HIGH_INCOME))
                                    .collect(NEW_SET, SET_COLLECTOR);
                        }
                    }).blockingGet();
            final Predicate<User> mergeFilter =
                    RxFunctions.or(RxFunctions.not(CORRECT_NAME), IS_RETIRED, IS_HIGH_INCOME);
            Set<User> chained = userList.filter(mergeFilter).collect(NEW_SET, SET_COLLECTOR)
                    .blockingGet();
            Assert.assertEquals(separated, chained);
        }
    }

    @Test
    public void testNot() throws Exception {
        User retired = new User("bla", "bla", 90, 10);
        Assert.assertEquals(!IS_RETIRED.test(retired), RxFunctions.not(IS_RETIRED).test(retired));
    }

    public static final class User {
        public final String name;

        public final String surname;

        public final int age;

        public final float income;

        public User(String name, String surname, int age, float income) {
            this.name = name;
            this.surname = surname;
            this.age = age;
            this.income = income;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof User))
                return false;
            User user = (User)o;
            if (age != user.age)
                return false;
            if (Float.compare(user.income, income) != 0)
                return false;
            if (!name.equals(user.name))
                return false;
            return surname.equals(user.surname);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + surname.hashCode();
            result = 31 * result + age;
            result = 31 * result + (income != +0.0f ? Float.floatToIntBits(income) : 0);
            return result;
        }
    }

    public static final int ITERATIONS = 100;

    private static final Function<Integer, Integer> INCREMENT = new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer integer) {
            return integer + 1;
        }
    };

    public static final Function<String, User> NAME_TO_USER = new Function<String, User>() {
        @Override
        public User apply(String s) {
            return new User(s, s, (int)(Math.random() * 120), (float)Math.random() * 100000);
        }
    };

    private static final BiFunction<Boolean, Boolean, Boolean> AND = new BiFunction<Boolean, Boolean, Boolean>() {
        @Override
        public Boolean apply(Boolean aBoolean, Boolean aBoolean2) {
            return aBoolean && aBoolean2;
        }
    };

    private static final Predicate<User> CORRECT_NAME = new Predicate<User>() {
        @Override
        public boolean test(User user) {
            return user.name.length() < 200 && user.surname.length() < 200;
        }
    };

    private static final Predicate<User> IS_RETIRED = new Predicate<User> () {
        @Override
        public boolean test(User user) {
            return user.age > 65;
        }
    };

    private static final Predicate<User> IS_HIGH_INCOME = new Predicate<User> () {
        @Override
        public boolean test(User user) {
            return user.income > 50000;
        }
    };

    public static final BiConsumer<Set<User>, User> SET_COLLECTOR = new BiConsumer<Set<User>, User>() {
        @Override
        public void accept(Set<User> users, User user) {
            users.add(user);
        }
    };

    public static final Callable<Set<User>> NEW_SET = new Callable<Set<User>>() {
        @Override
        public Set<User> call() {
            return new HashSet<User>();
        }
    };

    private static final Function<Integer, String> TO_STRING = new Function<Integer, String>() {
        @Override
        public String apply(Integer integer) {
            return integer + "";
        }
    };

    private static final Function<String, Integer> TO_INT = new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return Integer.parseInt(s);
        }
    };

    private static final Function4<String, String, Integer, Integer, User> TO_USER = new Function4<String, String, Integer, Integer, User>() {
        @Override
        public User apply(String name, String surname, Integer age, Integer income) {
            return new User(name, surname, age, income);
        }
    };

    private static final Function<Integer, Integer> IDENTITY = new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer integer) {
            return integer;
        }
    };

    private static final List<String> USER_NAMES = Arrays.asList("One", "Deux", "Pintru",
            "Maurition", "That weird guy", "Megamon");

    private static final BiFunction<Integer, Integer, Integer> BI_SUM = new BiFunction<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer integer, Integer integer2) throws Exception {
            return integer + integer2;
        }
    };
}
