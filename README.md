# RxFunctions2

RxFunctions2 is a library to smooth RxJava usage by functionally composing functions.

For the RxJava 1.X version please go to [RxFunctions](https://github.com/pakoito/RxFunctions)

## Rationale

Oftentimes you want to filter an `Observable` based off several criteria, i.e. get your users above a certain age that also live in a certain city; a string that should not be null or empty; or two objects being equal and both higher than 0.

Other times you have to chain several map operations in a row, like converting your network data from string to dto, and from dto to a viewmodel.

Where you could write 2-9 operations every time that chain surfaces, instead you can compose them a create a new, single, more meaningful function.

## Usage

### General Functions

These functions work for any type and situation.

#### Same

It allows you to chain `Function<T, T>` that have the same input and output types, like two `Function<String String>`, or six `Function<Integer, Integer>`.

The end result is a `Function<T, T>` that applies all of them sequentially and returns the result of the last one.

#### Chain

Chain allows composition of functions that change type, but maintain a logical sequence. This is useful in map operations, like transforming a string into an object `Function<String, User>`  and then retrieving one of the fields `Function<User, List<Locations>>` to finally compose our target class `Function<List<Locations>, LocationDirectory>`.

The end result is a `Function<T, R>` that applies all of them sequentially and returns a function that takes a parameter from the first one and returns the result of the last one.

#### Combine

Combine allows you to add up small operations into bigger ones. This case is common for object mutation, where you take one type parameter UserDto and have 4 functions `Function<UserDto, String>` to transform its fields into strings used by a constructor function `Function4<String, String, String, String, UserViewModel>`.

The end result is a function that applies several `Function<T, U>` `Function<T, V>` `Function<T, W>` parameters to the final aggregation function whose arity is one for each `Function3<U, V, W, R>`

#### Reduce

Reduce is the most complicated to understand, as it allows for incremental composition. You provide:

+ An initial value of type R

+ An aggregation function of type `BiFunction<R, R, R>` that takes the previous state, a new value obtained from the function being evaluated, and returns the new state

+ Any number of `Function<T, R>`.

Each function will be applied individually to the initial value by using the aggregation function until the last one gives the final result.

One example is the composition `and()` described below. It represents an operation of type `Function<T, Boolean>`, which takes an initial value of true, a set of functions returning booleans, and applies aggregation by doing `&&` between them.

The end result is a function `Function<T, R>` that has one input object, and returns the result of the internal aggregation.

### Boolean functions

The next composers work only for `Function<T, Boolean>`.

#### Not

Not negates the result of a `Function<T, Boolean>` to allow the creation of opposite functions. The most common case is testing if a filter does not fit a function defined previously.

#### And

And aggregates the result of any number of `Function<T, Boolean>` by means of `&&`. This helps composing more fine-grained filters into a single operation.

#### Or

And aggregates the result of any number of `Function<T, Boolean>` by means of `||`. This helps composing more fine-grained filters into a single operation.

## Examples

```java
Function<Integer, Integer> INCREMENT = num -> num + 1;
Function<Integer, Integer> SUM_FOUR =
                        RxFunctions.<Integer>same(INCREMENT, INCREMENT, INCREMENT, INCREMENT).call(0) // returns 4

Function<Integer, String> TO_STRING = num -> num + "";
Function<Integer, String> TO_INT = string -> Integer.parseInt(string);
Function<Integer, String> ROUND TRIPS =
                        RxFunctions.chain(TO_STRING, TO_INT, TO_STRING, TO_INT, TO_STRING).call(5); // returns "5"
```

Or more complex cases:

```java
Function<User, Boolean> BEST_USERS_FILTER =
                            RxFunctions.and(NOT_NULL,
                                            RxFunctions.not(IS_DEACTIVATED)
                                            RxFunctions.or(HAS_MANY_PURCHASES,
                                                            IS_HIGH_INCOME));
List<User> bestUsersList = getUserList().filter(BEST_USERS_FILTER).toList().toBlocking().first();
```

## Distribution

Add as a dependency to your `build.gradle`
```groovy
repositories {
    ...
    maven { url "https://jitpack.io" }
    ...
}
    
dependencies {
    ...
    compile 'com.github.pakoito:RxFunctions2:1.0.0'
    ...
}
```
or to your `pom.xml`
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.pakoito</groupId>
    <artifactId>RxFunctions2</artifactId>
    <version>1.0.0</version>
</dependency>
```

## License

Copyright (c) pakoito 2017

The Apache Software License, Version 2.0

See LICENSE.md
