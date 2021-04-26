# platform_ofd

Spring MVC, Spring Security, Apache Tiles 3, Tomcat 8, Java 8 минимальный личный кабинет пользователя.

В проекте используется БД MYSQL - инструкция по установке MAMP <a href="https://smarticle.ru/ustanovka-i-nastrojka-servera-mamp-instrukcija-po-perenosu-sajta-wordpress-na-mamp/"> ТУТ </a>.

Application.properties ждет от Вас БД с именем "ofd" на порту 8889 и данными для авторизации root/root.

В корне репозитория лежат два SQL файла для БД - structureSQL.sql (таблицы)  и dataSQL.sql (данные). Их необходимо импортировать один за другим.

Запустить проект.

Из интересного:

- все блоки html кода распилены на фрагменты для удобного переиспользования в других частях приложения (templates/block).
