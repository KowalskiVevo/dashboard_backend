# Тема для практической работы
Разработать систему информирования пользователей о днях рождениях. При этом необходимо предусмотреть возможность размещения фотографий и сведений о пользователях, 
а при приближении дня рождения необходимо выводить соответствующие сведения с фотографией на панель информирования.
Предусмотреть возможность отправки сообщения пользователю непосредственно в системе

# dashboard_backend
Back-end сервис, который реализует авторизацию и регистрацию, запись/редактирование пользователей, отправку уведомлений,
а также отправку сообщений в систему.

Архитектура сервиса - MVC

ER Диаграмма:

![ER Диаграмма](dashboard_urfu.jpg)

# Предварительные требования
- PostgresSQL
- JDK 17
- Maven 3.6.2 или новее (необязательно)
- IDE для разработки

## Стиль кодирования
* [Google Java Styleguide](https://google.github.io/styleguide/javaguide.html)
* [IDEA](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)

# Задачи для сервиса:
1. - [X] Разработать базу данных для хранения информации о пользователях, включая дату рождения и фотографии.
2. - [ ] Создать интерфейс для добавления новых пользователей в базу данных.
3. - [ ] Разработать систему уведомлений, которая будет проверять каждый день, есть ли пользователи с днем рождения в ближайшие несколько дней.
4. - [ ] Создать панель информирования, на которой будут отображаться фотографии и сведения о пользователях с днями рождения в ближайшие несколько дней.
5. - [ ] Разработать систему отправки сообщений пользователю непосредственно в системе, которая будет доступна из панели информирования или профиля пользователя.

### Подробности 5 пункта:
Для того чтобы пользователи могли обмениваться сообщениями, необходимо разработать систему отправки и приема сообщений. Система должна быть доступна из панели информирования и профиля пользователя.

При нажатии на кнопку "Написать сообщение" пользователь должен попасть на страницу отправки сообщения. На этой странице должны быть следующие поля:

- Получатель: поле для ввода имени пользователя, которому адресовано сообщение;
- Тема: поле для ввода темы сообщения;
- Сообщение: поле для ввода текста сообщения.

После заполнения всех полей пользователь может нажать кнопку "Отправить". Система должна проверить, что получатель существует в базе данных и отправить ему сообщение. Если получатель не найден, система должна вывести сообщение об ошибке.

Кроме того, все отправленные и полученные сообщения должны храниться в базе данных. Пользователь должен иметь возможность просмотреть все свои сообщения на странице своего профиля.

Также необходимо разработать систему уведомлений о новых сообщениях. При получении нового сообщения пользователь должен получить уведомление в панели информирования. При нажатии на уведомление пользователь должен попасть на страницу со списком своих сообщений, где он сможет прочитать новое сообщение.

## Используемые инструменты и зависимости
* [Maven](https://maven.apache.org/) - Dependency Management
* [Project Lombok](https://projectlombok.org/) - is a java library that automatically plugs into your editor and build tools, spicing up your java.
* [Spring](https://spring.io/projects/spring) - provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform
* [Spring Boot](https://spring.io/projects/spring-boot) - makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"

## Описание пакетов
