# SETUP

## Backend

1. Let the project sync.
2. Copy the environment variables.
```bash
$ cd rest-service/src/main/resources
$ cp application.properties.sample application.properties
$ # Add your DB username and password in fields provided
```

> Optional: If you'd like to seed this DB, hop on to `LoadDatabase.java`, uncomment the lines mentioning `Configuration` and `Bean`, and start the application. Your DB will be seeded. Don't forget to comment those lines again afterwards.