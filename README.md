# Tasks_8-22-Spring
Task 10 - Models  
На основе диаграммы классов UML из предыдущей задачи, создайте новый проект Java и классы сущностей.  
Результат - в ветке https://github.com/v-malzam/University/tree/Models

Task 11 - DAO Layer (Spring JDBC, JdbcTemplate)  
Создайте DAO на основе Spring JDBC для своего приложения.  
Результат - в ветке https://github.com/v-malzam/University/tree/Spring_JDBC

Task 12 - Service Layer  
Создайте сервисный уровень и реализуйте бизнес-логику (добавьте / удалите сущности в другие сущности и сохраните их в БД и т.д.). Ментор может добавить дополнительные бизнес-правила. Вам следует использовать Spring IoC.  
Результат - в ветке https://github.com/v-malzam/University/tree/Service_Layer

Task 14 - User Interface-1  
Создайте статусные страницы (status pages, чтение данных из БД – отражение в HTML).  
Используйте Spring MVC и Thymeleaf, Bootstrap.  
Результат представлен в Task 15.

Task 15 - User Interface-2  
Добавить к Task 14 функционал передачи данных в базу (создание, редактирование, удаление).  
Результат - в ветке https://github.com/v-malzam/University/tree/UI

Task 16 - Data Source (JNDI)  
Создайте DataSource в файлах конфигурации веб-проекта. Переключите слой DAO на поиск источника данных по имени JNDI.  
Результат - в ветке https://github.com/v-malzam/University/tree/JNDI

Task 17 - Hibernate  
Перепишите слой DAO. Используйте Hibernate вместо Spring JDBC.  
Результат - в ветке https://github.com/v-malzam/University/tree/ORM

Task 18 - Spring Boot  
Преобразуйте приложение в Spring Boot.  
Результат - в ветке https://github.com/v-malzam/University/tree/BOOT

Task 19 - Spring Data JPA  
Перепишите слой DAO. Используйте Spring Data JPA вместо Hibernate.  
Результат - в ветке https://github.com/v-malzam/University/tree/JPA

Task 21 - REST  
Добавьте в проект REST Endpoints. Все функции пользовательского интерфейса должны быть доступны в REST Endpoints.  
Результат - в ветке https://github.com/v-malzam/University/tree/REST

Task 20 - Validation  
Добавьте валидацию к вашим моделям.  
Это может быть проверка имени, проверка времени, количество студентов в группах и т.д.  
Спросите своего ментора о проверках, которые следует реализовать в вашем коде.  
Результат - в ветке https://github.com/v-malzam/University/tree/Validation

Task 22 - Swagger  
Добавьте документацию Swagger в свой проект.  
Вы можете использовать 2 или 3 версии или спросить своего ментора.  
https://swagger.io/  
Результат - в ветке https://github.com/v-malzam/University/tree/Swagger

Task доп.1 - DTO modelMapper  
Добавить использование DTO в проекте.  
Результат - в ветке https://github.com/v-malzam/University/tree/DTO

Task доп.2 - Интернационализация, cookies  
Добавить в проект интернационализацию. Выбор языка должен запоминаться в cookies.  
Результат - в ветке https://github.com/v-malzam/University/tree/Intern

Task доп.3 - Spring Security  
Добавить в проект вход по логину и паролю. Разделить права доступа для студентов и администраторов.  
Результат - в ветке https://github.com/v-malzam/University/tree/Security

Task 13 - Exceptions and Logging  
Добавьте кастомные исключения и логирование.  
Используйте slf4j + logback.  
Результат: Логирование отрабатывалось на отдельном проекте, основная информация о результате доступна в итоговом файле logback.xml Настроен вывод в консоль, запись на диск, автоархивация записанных файлов в gz. (пока не определился, как эту информацию здесь разместить)
