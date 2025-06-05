# Getting Started

### Start the application
Compile: 
```
gradle compileJava
```
Run:
```
gradle bootRun
```
Test:
```
gradle test
```

### API documentation 
Go to [Swagger UI](http://localhost:8080/swagger-ui/index.html)

It was the first time I used and sat up swagger, but instead of put a postman collection file into the repo, I thought this was a more elegant solution.
But swagger auto generated documentation for more endpoint than I have created and I couldn't figure out how to stop that.
The relevant chapters are Account, Exchange.

### API Key

Add your https://app.exchangerate-api.com/ api token in exchange/ExchangeRateApiCom.java

This should be handled by an environment property or better a secret manager like Vault.
To set up Vault for this application would be a little out of scope for this task.
And I got into som problems with setting up custom environment variables, so not to use more than the 4 hours this was the solution

https://app.exchangerate-api.com/ needed a paid subscription for historic data, så I made an integration to https://manage.exchangeratesapi.io/ in exchange/ExchangeRateApiIo.java
I didn't test the solution for the 3.th task because I ran out of request and I didn't have time to implement 3.th api.
