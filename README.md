## Welcome to LiLo Backend Repository

This repository was developed using Ktor and currently hosted locally. So in case to run the app, you have to host this repository in your computer.

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

**Dont forget to change the branch to "main" first before run this repository**

## Documentation
Link: [https://documenter.getpostman.com/view/25211967/2s93JqSkYU#3ef6706d-6f51-4329-87ba-33bf749a585f](https://documenter.getpostman.com/view/17029751/2sA3BoaBRP)
