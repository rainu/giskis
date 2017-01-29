# giskis
GisKis stores the information from kismet in a database so that the user can generate graphs using SQL.

# How to build:

```
 cd <project-root>
 mvn clean install
 docker build -t rainu/giskis ./ 
```

# How to execute
After building the executable:
```
 $> cd <project-root>
 $project-root> docker-compose up
```
