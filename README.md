## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

## Список выполненных задач:
1. Удалить социальные сети: vk, yandex.
2. Вынести чувствительную информацию в отдельный проперти файл. Переменные окружения: DATABASE_PASSWORD=maxim1100;DATABASE_USERNAME=maximyasn;MAIL_PASSWORD=zdfzsrqvgimldzyj;MAIL_USERNAME=jira4jr@gmail.com
3. Переделать тесты так, чтоб во время тестов использовалась in memory БД (H2), а не PostgreSQL **(ветка tasks)**.
4. Написать тесты для всех публичных методов контроллера ProfileRestController.
5. Сделать рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload чтоб он использовал современный подход для работы с файловой системмой.
6. Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе).
7. Написать Dockerfile для основного сервера
8. Написать docker-compose файл для запуска контейнера сервера вместе с БД и nginx.