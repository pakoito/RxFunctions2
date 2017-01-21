/*
 * Copyright (c) pakoito 2016
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
import rx.Observable;
import rx.functions.*;

import java.util.*;

public class RxFunctionsTest {
    @Test
    public void testSame() throws Exception {
        final int start = 0;
        int separate = INCREMENT.call(INCREMENT.call(INCREMENT.call(INCREMENT.call(start))));
        int chain = RxFunctions.same(INCREMENT, INCREMENT, INCREMENT, INCREMENT).call(start);
        Assert.assertEquals(separate, chain);
    }

    @Test
    public void testChain() throws Exception {
        final int start = 0;
        int separate = TO_INT.call(TO_STRING.call(TO_INT.call(TO_STRING.call(start))));
        int chain = RxFunctions.chain(TO_STRING, TO_INT, TO_STRING, TO_INT).call(start);
        Assert.assertEquals(separate, chain);
    }

    @Test
    public void testCombine() throws Exception {
        User separate = TO_USER.call(TO_STRING.call(55), TO_STRING.call(55), IDENTITY.call(55), IDENTITY.call(55));
        User chain = RxFunctions.combine(TO_STRING, TO_STRING, IDENTITY, IDENTITY, TO_USER)
                .call(55);
        Assert.assertEquals(separate, chain);
    }

    @Test
    public void testReduce() throws Exception {
        for (int i = 0; i < ITERATIONS; i++) {
            Collections.shuffle(USER_NAMES);
            final Observable<User> userList = Observable.from(USER_NAMES).map(NAME_TO_USER).cache();
            List<User> separated = userList.filter(CORRECT_NAME).filter(IS_RETIRED)
                    .filter(IS_HIGH_INCOME).toList().toBlocking().first();
            final Func1<User, Boolean> mergeFilter = RxFunctions.reduce(true, AND, CORRECT_NAME,
                    IS_RETIRED, IS_HIGH_INCOME);
            List<User> chained = userList.filter(mergeFilter).toList().toBlocking().first();
            Assert.assertEquals(separated, chained);
        }
    }

    @Test
    public void testAnd() throws Exception {
        for (int i = 0; i < ITERATIONS; i++) {
            Collections.shuffle(USER_NAMES);
            final Observable<User> userList = Observable.from(USER_NAMES).map(NAME_TO_USER).cache();
            List<User> separated = userList.filter(CORRECT_NAME).filter(IS_RETIRED)
                    .filter(IS_HIGH_INCOME).toList().toBlocking().first();
            final Func1<User, Boolean> mergeFilter = RxFunctions.and(CORRECT_NAME, IS_RETIRED,
                    IS_HIGH_INCOME);
            List<User> chained = userList.filter(mergeFilter).toList().toBlocking().first();
            Assert.assertEquals(separated, chained);
        }
    }

    @Test
    public void testOr() throws Exception {
        for (int i = 0; i < ITERATIONS; i++) {
            Collections.shuffle(USER_NAMES);
            final Observable<User> userList = Observable.from(USER_NAMES).map(NAME_TO_USER).cache();
            Set<User> separated = userList.toList()
                    .flatMap(new Func1<List<User>, Observable<Set<User>>>() {
                        @Override
                        public Observable<Set<User>> call(List<User> users) {
                            final Observable<User> from = Observable.from(users);
                            return Observable
                                    .merge(from.filter(RxFunctions.not(CORRECT_NAME)),
                                            from.filter(IS_RETIRED), from.filter(IS_HIGH_INCOME))
                                    .collect(NEW_SET, SET_COLLECTOR);
                        }
                    }).toBlocking().first();
            final Func1<User, Boolean> mergeFilter =
                    RxFunctions.or(RxFunctions.not(CORRECT_NAME), IS_RETIRED, IS_HIGH_INCOME);
            Set<User> chained = userList.filter(mergeFilter).collect(NEW_SET, SET_COLLECTOR)
                    .toBlocking().first();
            Assert.assertEquals(separated, chained);
        }
    }

    @Test
    public void testNot() throws Exception {
        User retired = new User("bla", "bla", 90, 10);
        Assert.assertEquals(!IS_RETIRED.call(retired), RxFunctions.not(IS_RETIRED).call(retired));
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

    private static final Func1<Integer, Integer> INCREMENT = new Func1<Integer, Integer>() {
        @Override
        public Integer call(Integer integer) {
            return integer + 1;
        }
    };

    public static final Func1<String, User> NAME_TO_USER = new Func1<String, User>() {
        @Override
        public User call(String s) {
            return new User(s, s, (int)(Math.random() * 120), (float)Math.random() * 100000);
        }
    };

    private static final Func2<Boolean, Boolean, Boolean> AND = new Func2<Boolean, Boolean, Boolean>() {
        @Override
        public Boolean call(Boolean aBoolean, Boolean aBoolean2) {
            return aBoolean && aBoolean2;
        }
    };

    private static final Func1<User, Boolean> CORRECT_NAME = new Func1<User, Boolean>() {
        @Override
        public Boolean call(User user) {
            return user.name.length() < 200 && user.surname.length() < 200;
        }
    };

    private static final Func1<User, Boolean> IS_RETIRED = new Func1<User, Boolean>() {
        @Override
        public Boolean call(User user) {
            return user.age > 65;
        }
    };

    private static final Func1<User, Boolean> IS_HIGH_INCOME = new Func1<User, Boolean>() {
        @Override
        public Boolean call(User user) {
            return user.income > 50000;
        }
    };

    public static final Action2<Set<User>, User> SET_COLLECTOR = new Action2<Set<User>, User>() {
        @Override
        public void call(Set<User> users, User user) {
            users.add(user);
        }
    };

    public static final Func0<Set<User>> NEW_SET = new Func0<Set<User>>() {
        @Override
        public Set<User> call() {
            return new HashSet<User>();
        }
    };

    private static final Func1<Integer, String> TO_STRING = new Func1<Integer, String>() {
        @Override
        public String call(Integer integer) {
            return integer + "";
        }
    };

    private static final Func1<String, Integer> TO_INT = new Func1<String, Integer>() {
        @Override
        public Integer call(String s) {
            return Integer.parseInt(s);
        }
    };

    private static final Func4<String, String, Integer, Integer, User> TO_USER = new Func4<String, String, Integer, Integer, User>() {
        @Override
        public User call(String name, String surname, Integer age, Integer income) {
            return new User(name, surname, age, income);
        }
    };

    private static final Func1<Integer, Integer> IDENTITY = new Func1<Integer, Integer>() {
        @Override
        public Integer call(Integer integer) {
            return integer;
        }
    };

    private static final List<String> USER_NAMES = Arrays.asList("One", "Deux", "Pintru",
            "Maurition", "That weird guy", "Megamon");
}
