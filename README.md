# OJDBC connection checker

Check connection strings of Oracle DBs

## Getting started
* build the project and image: `./gradlew copyLibs jar && docker-compose build`
* copy .env_template to .env and set the variables
* when using a TNS alias, make sure there is a tnsnames.ora in the `wallet` directory that contains
  the alias and TNS connection string
* run the test: `docker-compose run --rm tester` 

## Using an Oracle wallet
* set COMPOSE_FILE:
  ```bash
  export COMPOSE_FILE=docker-compose.yaml:docker-compose.wallet.yaml
  ```
* Copy the wallet files into the directory `wallet`
* make sure the connection string is identical to the one used in the wallet
* run the test: `docker-compose run --rm tester` 

## Testing the connection for LDAP urls
* set COMPOSE_FILE:
  ```bash
  export COMPOSE_FILE=docker-compose.yaml:docker-compose.ldap.yaml
  ```
* set the connection string to `@ldap://ldap_server:389/oracledb,cn=OracleContext,dc=example,dc=org`
* run the test: `docker-compose run --rm tester` 

Note: the test will fail the first time because the LDAP server will not be ready yet.

Note 2: To combine the LDAP test with a wallet, define 
```bash
export COMPOSE_FILE=docker-compose.yaml:docker-compose.wallet.yaml:docker-compose.ldap.yaml
```
and make sure you have done all the steps in the *Using an Oracle wallet* section


## Based on 
* [OJDBC driver](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)

