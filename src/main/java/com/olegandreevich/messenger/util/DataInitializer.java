package com.olegandreevich.messenger.util;

import com.olegandreevich.messenger.entities.enums.FriendStatus;
import com.olegandreevich.messenger.entities.user.Friend;
import com.olegandreevich.messenger.entities.user.MyUser;
import com.olegandreevich.messenger.entities.user.Profile;
import com.olegandreevich.messenger.repositories.user.FriendRepository;
import com.olegandreevich.messenger.repositories.user.MyUserRepository;
import com.olegandreevich.messenger.repositories.user.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        checkIfDataExists()
                .filter(hasData -> !hasData)
                .switchIfEmpty(Mono.empty()) // Прекращаем выполнение, если данные уже созданы
                .thenMany(initUsers())
                .thenMany(initProfiles())
                .thenMany(initFriends())
                .subscribe();
    }

    /** * Проверка существования данных */
    private Mono<Boolean> checkIfDataExists() {
        return Flux.merge(
                        userRepository.count(),
                        profileRepository.count(),
                        friendRepository.count()
                )
                .all(count -> count.equals(0L)); // Возвращает true, если количество записей во всех коллекциях равно нулю
    }

    /** * Создание пользователей */
    private Flux<MyUser> initUsers() {
        return Flux.range(1, 10)
                .map(i -> {
                    MyUser user = new MyUser();
                    user.setId("" + i);
                    user.setUsername("user" + i);
                    user.setPassword(bCryptPasswordEncoder.encode("pass" + i));
                    user.setEmail("user" + i + "@example.com");
                    return user;
                })
                .flatMap(userRepository::save); // Асинхронное сохранение
    }

    /** * Создание профилей */
    private Flux<Profile> initProfiles() {
        return Flux.zip(
                        Flux.just(
                                new Profile("1", "Иван", "Иванов", "1985-07-15", null, "Люблю читать книги.", "1"),
                                new Profile("2", "Мария", "Петрова", "1990-03-21", null, "Обожаю путешествовать.", "2"),
                                new Profile("3", "Алексей", "Кузнецов", "1988-12-05", null, "Играю в футбол.", "3"),
                                new Profile("4", "Анна", "Сергеева", "1995-06-10", null, "Увлекаюсь фотографией.", "4"),
                                new Profile("5", "Сергей", "Антонов", "1983-02-14", null, "Занимаюсь спортом.", "5"),
                                new Profile("6", "Елена", "Николаева", "1992-11-28", null, "Работаю дизайнером.", "6"),
                                new Profile("7", "Дмитрий", "Федоров", "1987-09-03", null, "Интересуюсь технологиями.", "7"),
                                new Profile("8", "Ольга", "Васильева", "1991-04-17", null, "Преподаю английский.", "8"),
                                new Profile("9", "Павел", "Алексеев", "1986-08-23", null, "Раскрываюсь в музыке.", "9"),
                                new Profile("10", "Виктория", "Романова", "1994-01-09", null, "Гуляю по городу.", "10")
                        ),
                        getUserIds()
                )
                .map(tuple -> {
                    Profile profile = tuple.getT1();
                    profile.setUserId(tuple.getT2()); // Устанавливаем ID пользователя
                    return profile;
                })
                .flatMap(profileRepository::save); // Асинхронное сохранение профиля
    }

    /** * Получение списка идентификаторов пользователей */
    private Flux<String> getUserIds() {
        return Flux.range(1, 10)
                .map(i -> "user" + i)
                .flatMap(username -> userRepository.findByUsername(username)) // Получаем User по имени
                .map(MyUser::getId); // Берём Id пользователя
    }

    /** * Создание связей дружбы */
    private Flux<Friend> initFriends() {
        List<Tuple2<String,String>> pairs = Arrays.asList(
                Tuples.of("1","2"), // Первая пара: первый друг — второй
                Tuples.of("1","3"), // Вторая пара: первый друг — третий
                Tuples.of("1","4"), // Третья пара: первый друг — четвёртый
                Tuples.of("2","5"), // Четвёртая пара: второй друг — пятый
                Tuples.of("2","6")  // Пятая пара: второй друг — шестой
        );

        return Flux.fromIterable(pairs)
                .map(pair -> {
                    Friend friend = new Friend(); // Создаем новый объект Friend
                    friend.setUserId(pair.getT1());
                    friend.setFriendId(pair.getT2());
                    friend.setStatus(FriendStatus.ACCEPTED); // Укажите нужный статус здесь
                    friend.setRequestedAt(LocalDateTime.now());
                    return friend;
                })
                .flatMap(friendRepository::save); // Сохраняем дружбу асинхронно
    }
}

