# PSK 1 laboratorinis darbas

## Application Server configuration

1. In IntelliJ IDEA: register "JBoss Server" -> local:
    * Press "Fix", choose "exploded war"
2. Run the server, project should start successfully.


## Adding database
1. Choose H2 (local), embedded
2. jdbc:h2:~/h2database/StablesDB;AUTO_SERVER=TRUE
3. Use authentification (sa/sa)
4. Run schema.sql
