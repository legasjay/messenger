db = db.getSiblingDB('admin');
db.createUser({
  user: "root",
  pwd: "example",
  roles: [{ role: "root", db: "admin" }]
});

db = db.getSiblingDB('messenger_db');
db.createUser({
  user: "messenger_user",
  pwd: "password",
  roles: [{ role: "root", db: "messenger_db" }]
});