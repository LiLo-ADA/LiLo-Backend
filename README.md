## Welcome to LiLo Backend Repository

This repository was developed using Ktor and currently hosted locally. So in case to run the app, you have to host this repository in your computer.

**Dont forget to change the branch to "main" first before run this repository**

## Documentation
Link: [https://documenter.getpostman.com/view/25211967/2s93JqSkYU#3ef6706d-6f51-4329-87ba-33bf749a585f](https://documenter.getpostman.com/view/17029751/2sA3BoaBRP)

## How to Run

1.  Install postgres in case you want to run this repository in your computer.
2.  Prepare a file named **application.conf** and put it in **resources** folder on your project.
3.  Type this code, and change all credentials with yours.

```plaintext
ktor{
	deployment{
		dev{
			HOST = "0.0.0.0"
			PORT = "8080"
			JDBC_URL = "{JDBC URL HERE}"
			DB_USERNAME = "postgres"
			DB_PASSWORD = "{YOUR DB PASSWORD}"
			JWT_SECRET = "{JWT SECRET HERE}"
			JWT_ISSUER = "0.0.0.0:8080/"
			JWT_AUDIENCE = "0.0.0.0:8080/audience"
			JWT_REALM = "0.0.0.0:8080/JWT_Realm"
			PW_SALT = "{PW SALT HERE}"
		}
	}
	application{
		modules=[com.example]
	}
}
```

4.  Run this sql script to insert lockers into the locker database.

```plaintext
INSERT INTO Locker (id, area, number, status, "reported") VALUES
(gen_random_uuid(), 'A', 1, 1, FALSE),
(gen_random_uuid(), 'A', 2, 2, TRUE),
(gen_random_uuid(), 'A', 3, 1, FALSE),
(gen_random_uuid(), 'A', 4, 2, FALSE),
(gen_random_uuid(), 'A', 5, 1, FALSE),
(gen_random_uuid(), 'A', 6, 1, FALSE),
(gen_random_uuid(), 'A', 7, 2, TRUE),
(gen_random_uuid(), 'A', 8, 1, FALSE),
(gen_random_uuid(), 'A', 9, 1, FALSE),
(gen_random_uuid(), 'A', 10, 3, FALSE),
(gen_random_uuid(), 'A', 11, 1, FALSE),
(gen_random_uuid(), 'A', 12, 1, FALSE),
(gen_random_uuid(), 'A', 13, 3, FALSE),
(gen_random_uuid(), 'A', 14, 1, FALSE),
(gen_random_uuid(), 'A', 15, 1, FALSE),
(gen_random_uuid(), 'A', 16, 2, TRUE),
(gen_random_uuid(), 'A', 17, 1, FALSE),
(gen_random_uuid(), 'A', 18, 1, FALSE),
(gen_random_uuid(), 'A', 19, 1, FALSE),
(gen_random_uuid(), 'A', 20, 2, TRUE),
(gen_random_uuid(), 'A', 21, 1, FALSE),
(gen_random_uuid(), 'A', 22, 1, FALSE),
(gen_random_uuid(), 'A', 23, 1, FALSE),
(gen_random_uuid(), 'A', 24, 3, FALSE),
(gen_random_uuid(), 'A', 25, 1, FALSE),
(gen_random_uuid(), 'A', 26, 1, FALSE),
(gen_random_uuid(), 'A', 27, 1, FALSE),
(gen_random_uuid(), 'B', 1, 2, TRUE),
(gen_random_uuid(), 'B', 2, 2, TRUE),
(gen_random_uuid(), 'B', 3, 2, TRUE),
(gen_random_uuid(), 'B', 4, 2, TRUE),
(gen_random_uuid(), 'B', 5, 2, TRUE),
(gen_random_uuid(), 'B', 6, 2, TRUE),
(gen_random_uuid(), 'B', 7, 2, TRUE),
(gen_random_uuid(), 'B', 8, 2, TRUE),
(gen_random_uuid(), 'B', 9, 2, TRUE),
(gen_random_uuid(), 'B', 10, 2, TRUE),
(gen_random_uuid(), 'B', 11, 2, TRUE),
(gen_random_uuid(), 'B', 12, 2, TRUE),
(gen_random_uuid(), 'B', 13, 2, TRUE),
(gen_random_uuid(), 'B', 14, 2, TRUE),
(gen_random_uuid(), 'B', 15, 2, TRUE),
(gen_random_uuid(), 'B', 16, 2, TRUE),
(gen_random_uuid(), 'B', 17, 2, TRUE),
(gen_random_uuid(), 'B', 18, 2, TRUE),
(gen_random_uuid(), 'B', 19, 2, TRUE),
(gen_random_uuid(), 'B', 20, 2, TRUE),
(gen_random_uuid(), 'B', 21, 2, TRUE),
(gen_random_uuid(), 'B', 22, 2, TRUE),
(gen_random_uuid(), 'B', 23, 2, TRUE),
(gen_random_uuid(), 'B', 24, 2, TRUE),
(gen_random_uuid(), 'B', 25, 2, TRUE),
(gen_random_uuid(), 'B', 26, 2, TRUE),
(gen_random_uuid(), 'B', 27, 2, TRUE),
(gen_random_uuid(), 'C', 1, 1, FALSE),
(gen_random_uuid(), 'C', 2, 1, FALSE),
(gen_random_uuid(), 'C', 3, 1, FALSE),
(gen_random_uuid(), 'C', 4, 1, FALSE),
(gen_random_uuid(), 'C', 5, 1, FALSE),
(gen_random_uuid(), 'C', 6, 1, FALSE),
(gen_random_uuid(), 'C', 7, 1, FALSE),
(gen_random_uuid(), 'C', 8, 1, FALSE),
(gen_random_uuid(), 'C', 9, 1, FALSE),
(gen_random_uuid(), 'C', 10, 1, FALSE),
(gen_random_uuid(), 'C', 11, 1, FALSE),
(gen_random_uuid(), 'C', 12, 1, FALSE),
(gen_random_uuid(), 'C', 13, 1, FALSE),
(gen_random_uuid(), 'C', 14, 1, FALSE),
(gen_random_uuid(), 'C', 15, 1, FALSE),
(gen_random_uuid(), 'C', 16, 1, FALSE),
(gen_random_uuid(), 'C', 17, 1, FALSE),
(gen_random_uuid(), 'C', 18, 1, FALSE),
(gen_random_uuid(), 'C', 19, 1, FALSE),
(gen_random_uuid(), 'C', 20, 1, FALSE),
(gen_random_uuid(), 'C', 21, 1, FALSE),
(gen_random_uuid(), 'C', 22, 1, FALSE),
(gen_random_uuid(), 'C', 23, 1, FALSE),
(gen_random_uuid(), 'C', 24, 1, FALSE),
(gen_random_uuid(), 'C', 25, 1, FALSE),
(gen_random_uuid(), 'C', 26, 1, FALSE),
(gen_random_uuid(), 'C', 27, 1, FALSE),
(gen_random_uuid(), 'C', 28, 1, FALSE),
(gen_random_uuid(), 'C', 29, 1, FALSE),
(gen_random_uuid(), 'C', 30, 1, FALSE),
(gen_random_uuid(), 'C', 31, 1, FALSE),
(gen_random_uuid(), 'C', 32, 1, FALSE),
(gen_random_uuid(), 'C', 33, 1, FALSE),
(gen_random_uuid(), 'C', 34, 1, FALSE),
(gen_random_uuid(), 'C', 35, 1, FALSE),
(gen_random_uuid(), 'C', 36, 1, FALSE),
(gen_random_uuid(), 'C', 37, 1, FALSE),
(gen_random_uuid(), 'C', 38, 1, FALSE),
(gen_random_uuid(), 'C', 39, 1, FALSE),
(gen_random_uuid(), 'C', 40, 1, FALSE),
(gen_random_uuid(), 'C', 41, 1, FALSE),
(gen_random_uuid(), 'C', 42, 1, FALSE),
(gen_random_uuid(), 'C', 43, 1, FALSE),
(gen_random_uuid(), 'C', 44, 1, FALSE),
(gen_random_uuid(), 'C', 45, 1, FALSE),
(gen_random_uuid(), 'C', 46, 1, FALSE),
(gen_random_uuid(), 'C', 47, 1, FALSE),
(gen_random_uuid(), 'C', 48, 1, FALSE),
(gen_random_uuid(), 'C', 49, 1, FALSE),
(gen_random_uuid(), 'C', 50, 1, FALSE),
(gen_random_uuid(), 'C', 51, 1, FALSE),
(gen_random_uuid(), 'C', 52, 1, FALSE),
(gen_random_uuid(), 'C', 53, 1, FALSE),
(gen_random_uuid(), 'C', 54, 1, FALSE),
(gen_random_uuid(), 'D', 1, 1, FALSE),
(gen_random_uuid(), 'D', 2, 1, FALSE),
(gen_random_uuid(), 'D', 3, 1, FALSE),
(gen_random_uuid(), 'D', 4, 1, FALSE),
(gen_random_uuid(), 'D', 5, 1, FALSE),
(gen_random_uuid(), 'D', 6, 1, FALSE),
(gen_random_uuid(), 'D', 7, 1, FALSE),
(gen_random_uuid(), 'D', 8, 1, FALSE),
(gen_random_uuid(), 'D', 9, 1, FALSE),
(gen_random_uuid(), 'D', 10, 1, FALSE),
(gen_random_uuid(), 'D', 11, 1, FALSE),
(gen_random_uuid(), 'D', 12, 1, FALSE),
(gen_random_uuid(), 'D', 13, 1, FALSE),
(gen_random_uuid(), 'D', 14, 1, FALSE),
(gen_random_uuid(), 'D', 15, 1, FALSE),
(gen_random_uuid(), 'D', 16, 1, FALSE),
(gen_random_uuid(), 'D', 17, 1, FALSE),
(gen_random_uuid(), 'D', 18, 1, FALSE),
(gen_random_uuid(), 'D', 19, 1, FALSE),
(gen_random_uuid(), 'D', 20, 1, FALSE),
(gen_random_uuid(), 'D', 21, 1, FALSE),
(gen_random_uuid(), 'D', 22, 1, FALSE),
(gen_random_uuid(), 'D', 23, 1, FALSE),
(gen_random_uuid(), 'D', 24, 1, FALSE),
(gen_random_uuid(), 'D', 25, 1, FALSE),
(gen_random_uuid(), 'D', 26, 1, FALSE),
(gen_random_uuid(), 'D', 27, 1, FALSE),
(gen_random_uuid(), 'D', 28, 1, FALSE),
(gen_random_uuid(), 'D', 29, 1, FALSE),
(gen_random_uuid(), 'D', 30, 1, FALSE),
(gen_random_uuid(), 'D', 31, 1, FALSE),
(gen_random_uuid(), 'D', 32, 1, FALSE),
(gen_random_uuid(), 'D', 33, 1, FALSE),
(gen_random_uuid(), 'D', 34, 1, FALSE),
(gen_random_uuid(), 'D', 35, 1, FALSE),
(gen_random_uuid(), 'D', 36, 1, FALSE),
(gen_random_uuid(), 'D', 37, 1, FALSE),
(gen_random_uuid(), 'D', 38, 1, FALSE),
(gen_random_uuid(), 'D', 39, 1, FALSE),
(gen_random_uuid(), 'D', 40, 1, FALSE),
(gen_random_uuid(), 'D', 41, 1, FALSE),
(gen_random_uuid(), 'D', 42, 1, FALSE),
(gen_random_uuid(), 'D', 43, 1, FALSE),
(gen_random_uuid(), 'D', 44, 1, FALSE),
(gen_random_uuid(), 'D', 45, 1, FALSE);
```

