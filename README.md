# Tasks_8-22-Spring

#### v.18.04.2022 - GoF Patterns

Вынесен в паттерн Simple Factory громоздний блок кода по выбору Service-бина. 

#### v.31.10.2021 - CI/CD Jenkins

Настроен базовый Pipeline для Jenkins через Jenkins-file.  

В последующих коммитах настройка была удалена. Jenkins признан вызывающе некачественным - создает сложностей в разы больше, чем решает.

#### v.21.10.2021 - Docker

Написаны dockerfile и docker-compose, для запуска приложения совместно с контейнером Postgres.  
Настроен Debian сервер на отдельном ПК.   
Отлажена работа на:
 - Docker Desktop + WSL2 на Windows 10;
 - Debian-сервере.

#### Task 13 - Exceptions and Logging (01.09.2020)

Добавьте кастомные исключения и логирование.  
Используйте slf4j + logback.  
Результат: Логирование отрабатывалось на отдельном проекте, основная информация о результате доступна в итоговом файле
logback.xml Настроен вывод в консоль, запись на диск, автоархивация записанных файлов в gz. (пока не определился, как
эту информацию здесь разместить)

#### Task доп.3 - Spring Security (27.08.2020)

Добавить в проект вход по логину и паролю. Разделить права доступа для студентов и администраторов.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Security

#### Task доп.2 - Интернационализация, cookies (20.08.2020)

Добавить в проект интернационализацию. Выбор языка должен запоминаться в cookies.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Intern

#### Task доп.1 - DTO modelMapper (05.07.2020)

Добавить использование DTO в проекте.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/DTO

#### Task 22 - Swagger (03.07.2020)

Добавьте документацию Swagger в свой проект.  
Вы можете использовать 2 или 3 версии, или спросить своего ментора.  
https://swagger.io/  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Swagger

#### Task 20 - Validation (01.07.2020)

Добавьте валидацию к вашим моделям.  
Это может быть проверка имени, проверка времени, количество студентов в группах и т.д.  
Спросите своего ментора о проверках, которые следует реализовать в вашем коде.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Validation

#### Task 21 - REST (20.06.2020)

Добавьте в проект REST Endpoints. Все функции пользовательского интерфейса должны быть доступны в REST Endpoints.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/REST

#### Task 19 - Spring Data JPA (11.06.2020)

Перепишите слой DAO. Используйте Spring Data JPA вместо Hibernate.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/JPA

#### Task 18 - Spring Boot  (05.06.2020)

Преобразуйте приложение в Spring Boot.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/BOOT

#### Task 17 - Hibernate (31.05.2020)

Перепишите слой DAO. Используйте Hibernate вместо Spring JDBC.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/ORM

#### Task 16 - Data Source (JNDI) (16.05.2020)

Создайте DataSource в файлах конфигурации веб-проекта. Переключите слой DAO на поиск источника данных по имени JNDI.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/JNDI

#### Task 15 - User Interface-2 (15.05.2020)

Добавить к Task 14 функционал передачи данных в базу (создание, редактирование, удаление).  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/UI

#### Task 14 - User Interface-1

Создайте статусные страницы (status pages, чтение данных из БД – отражение в HTML).  
Используйте Spring MVC и Thymeleaf, Bootstrap.  
Результат представлен в Task 15.

#### Task 12 - Service Layer (29.04.2020)

Создайте сервисный уровень и реализуйте бизнес-логику (добавьте / удалите сущности в другие сущности и сохраните их в БД
и т.д.). Ментор может добавить дополнительные бизнес-правила. Вам следует использовать Spring IoC.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Service_Layer

#### Task 11 - DAO Layer (Spring JDBC, JdbcTemplate) (29.04.2020)

Создайте DAO на основе Spring JDBC для своего приложения.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Spring_JDBC

#### Task 10 - Models (21.04.2020)

На основе диаграммы классов UML из предыдущей задачи, создайте новый проект Java и классы сущностей.  
Результат - в ветке https://github.com/v-malzam/8-22-Spring_max-stack/tree/Models

#### Task 9 - Decompose university - UML

Разработать UML-модель приложения "Университетское расписание".
