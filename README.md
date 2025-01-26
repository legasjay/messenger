Простой мессенджер с возможностью создавать приватные чаты, групповые чаты. С созданием сообществ, добавлением постов, 
комментариев, лайков и т.п.

Для запуска:
В начале подключаемся к базе данных mongodb, пишем в терминале:
mongosh --host localhost --port 27017 -u "root" -p "example" --authenticationDatabase "admin"
db.auth("root", "example")
use messenger_db
db.createUser({
...   user: "messenger_user",
...   pwd: "password",
...   roles: [
...     { role: "readWrite", db: "messenger_db" }
...   ]
... });

db.getUsers() проверяем.

После этого запускаем докер компоуз
docker-compose up -d
запускаем приложение в среде разработки.