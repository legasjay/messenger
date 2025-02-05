#для добавления пользователя заходим в терминал и вводим команды
mongosh -u root -p root --authenticationDatabase admin

use messenger_db

db.createUser({
user: "root",
pwd: "root",
roles: [
{ role: "readWrite", db: "messenger_db" }
]
});