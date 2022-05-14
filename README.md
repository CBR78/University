# Tasks_8-22-Spring

#### 14.05.22 - Нагрузочное тестирование JMeter

В JMeter создан тест производительности REST-контроллера. Настроен Maven-плагин, для тестирования и формирования
графического отчета.

#### 18.04.22 - GoF Patterns

Вынесен в паттерн Simple Factory громоздний блок кода по выбору Service-бина.

#### 31.10.21 - CI/CD Jenkins

Настроен базовый Pipeline для Jenkins через Jenkins-file.

В последующих коммитах настройка была удалена. Jenkins признан вызывающе некачественным - создает сложностей в разы
больше, чем решает.

#### 21.10.21 - Docker

Написаны dockerfile и docker-compose, для запуска приложения совместно с контейнером Postgres.  
Настроен Debian сервер на отдельном ПК.   
Отлажена работа на:

- Docker Desktop + WSL2 на Windows 10;
- Debian-сервере.

#### 01.09.20 - Exceptions and Logging

Добавьте кастомные исключения и логирование.  
Используйте slf4j + logback.  
Результат: Логирование отрабатывалось на отдельном проекте, основная информация о результате доступна в итоговом файле
logback.xml Настроен вывод в консоль, запись на диск, автоархивация записанных файлов в gz. (пока не определился, как
эту информацию здесь разместить)

#### 27.08.20 - Spring Security

Добавить в проект вход по логину и паролю. Разделить права доступа для студентов и администраторов.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Security

#### 20.08.20 - Интернационализация, cookies

Добавить в проект интернационализацию. Выбор языка должен запоминаться в cookies.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Intern

#### 05.07.20 - DTO modelMapper

Добавить использование DTO в проекте.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/DTO

#### 03.07.20 - Swagger

Добавьте документацию Swagger в свой проект.  
Вы можете использовать 2 или 3 версии, или спросить своего ментора.  
https://swagger.io/  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Swagger

#### 01.07.20 - Validation

Добавьте валидацию к вашим моделям.  
Это может быть проверка имени, проверка времени, количество студентов в группах и т.д.  
Спросите своего ментора о проверках, которые следует реализовать в вашем коде.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Validation

#### 20.06.20 - REST

Добавьте в проект REST Endpoints. Все функции пользовательского интерфейса должны быть доступны в REST Endpoints.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/REST

#### 11.06.20 - Spring Data JPA

Перепишите слой DAO. Используйте Spring Data JPA вместо Hibernate.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/JPA

#### 05.06.20 - Spring Boot

Преобразуйте приложение в Spring Boot.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/BOOT

#### 31.05.20 - Hibernate

Перепишите слой DAO. Используйте Hibernate вместо Spring JDBC.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/ORM

#### 16.05.20 - Data Source (JNDI)

Создайте DataSource в файлах конфигурации веб-проекта. Переключите слой DAO на поиск источника данных по имени JNDI.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/JNDI

#### 15.05.20 - User web-interface

Создайте статусные страницы (status pages, чтение данных из БД – отражение в HTML).  
Используйте Spring MVC и Thymeleaf, Bootstrap.  
Добавить функционал передачи данных в базу (создание, редактирование, удаление).  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/UI

#### 29.04.2020 - Service Layer

Создайте сервисный уровень и реализуйте бизнес-логику (добавьте / удалите сущности в другие сущности и сохраните их в БД
и т.д.). Ментор может добавить дополнительные бизнес-правила. Вам следует использовать Spring IoC.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Service_Layer

#### 29.04.2020 - DAO Layer (Spring JDBC, JdbcTemplate)

Создайте DAO на основе Spring JDBC для своего приложения.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Spring_JDBC

#### 21.04.2020 - Models

На основе диаграммы классов UML из предыдущей задачи, создайте новый проект Java и классы сущностей.  
Результат в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Models

#### Decompose university - UML

Разработать UML-модель приложения "Университетское расписание".
