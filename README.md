

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/moonlightmoth/keyzz-auth/maven.yml)



<!-- PROJECT LOGO -->
<!--
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">Distant Mouse Server</h3>
</div>
-->
# Keyzz-auth
<!-- ABOUT THE PROJECT -->
## About The Project

Keyzz-auth is JWT authentification microservice for Keyzz application. This piece of code is responsible for logging in new users and authorising them to [keyzz-backend](https://github.com/moonlightmoth/keyzz-backend).

### Built with
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)


### API reference
For API reference check [here](https://api.moonlightmoth.ru/keyzz-auth/swagger-ui/index.html)

<!-- GETTING STARTED -->
## How to run
  
### Build
```
git clone https://github.com/moonlightmoth/keyzz-auth.git
cd keyzz-auth
mvn clean compile package
```
Compiled jar will be inside target directory.

### Docker
```
git clone https://github.com/moonlightmoth/keyzz-auth.git
docker build --tag <your_tag> .
```

### Usage

In order to work properly keyzz-auth needs to be connected to postgresql database named `main_db` with following schema: 

```
main_db=# \d user_details
                                       Table "public.user_details"
    Column     |         Type          | Collation | Nullable |                 Default
---------------+-----------------------+-----------+----------+------------------------------------------
 id            | integer               |           | not null | nextval('user_details_id_seq'::regclass)
 name          | character varying(30) |           | not null |
 surname       | character varying(30) |           | not null |
 role          | character varying(10) |           | not null |
 login         | character varying(30) |           | not null |
 password_hash | character varying(64) |           | not null |
Indexes:
    "user_details_pkey" PRIMARY KEY, btree (id)
    "user_details_login_key" UNIQUE CONSTRAINT, btree (login)
Check constraints:
    "user_details_role_check" CHECK (role::text = ANY (ARRAY['ADMIN'::character varying, 'USER'::character varying]::text[]))

```

Secrets management implemented using enviromental variables:
```
JWT_SECRET_SHA256=<sha_256 hash> # secret sha_256 hash for signing jwt
JWT_DATABASE_URL=jdbc:postgresql://<url> # database url to connect
JWT_DATABASE_USERNAME=<database user>
JWT_DATABASE_PASSWORD=<database password>
```


<!-- CONTACT -->
## Contact

* vasilev.iv.dev@mail.ru
* [Telegram](https://t.me/moonlightmoth)
<p align="right">(<a href="#readme-top">back to top</a>)</p>

