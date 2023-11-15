
# Interview Screening Test

Welcome to the interview screening. 

Please read the instructions carefully before proceeding.
Excluding the bonus task, all other tasks are independent and can be tackled in any sequence.

Good Luck!

## Pre-requisites

- Java Development Kit (JDK) 17

Please ensure you have the above pre-requisites installed and configured before starting the tasks.

## Task 1: Improve Performance

Your primary objective is to improve the performance of the API in the `FirstProblemController` class. There are two main requirements:

1. The API should be able to save a list of 5 words in under 500ms.
2. If any word in the list contains the letter "a", the service should throw an `UnsupportedOperationException`.

There are already two unit tests provided:

- One checks the performance requirement.
- The other checks the validation requirement.

Your goal is to make changes to the code such that both unit tests pass successfully.

### Constraints:

- You **MUST NOT** modify the `saveWordToExternalApi` method.
- The API in the `FirstProblemController` should return a 201 status as soon as possible, but it should still throw an exception if the request is invalid.

### Tips:

- Run the tests in the `FirstProblemServiceTest` class instead of trying to use another tool (like Postman). We already implemented some request there.



## Task 2: Debugging the Book Service

Your primary objective is to debug and fix the issues present in the `BookService` class. There are a series of bugs related to the creation and handling of the `Book` entity:

1. The `BookService` is responsible for adding a book, which includes generating an SEO name and product code.
2. The product code is generated based on the SEO name, with specific conversion rules for characters.
3. There are multiple unit tests provided in the `BookServiceTest` class that validate the functionality. Some of these tests are currently failing due to bugs in the logic.

Your goal is to identify and fix the issues in the `BookService` and the `Book` entity such that all the unit tests in the `BookServiceTest` class pass successfully.

### Constraints:
- You **MUST NOT** modify the structure of the tests in the `BookServiceTest` class. Only the service and entity logic should be adjusted.
- Utilize a debugger to help identify the issues in the logic.

### Tips:
- Run the tests in the `BookServiceTest` class to identify the failing tests and the related bugs.
- Pay close attention to the logic for generating the product code based on the SEO name.


## Task 3: Customizable Greeting Message API

Your primary objective is to create a new API that manages customizable greeting messages based on the time of day and locale.

### Requirements:

1. **Greeting Configuration**: The application should support configurable greeting messages based on the time of day (morning, afternoon, evening) and locale (e.g., English, Spanish). The greetings should be stored in the `application.yaml` file.

2. **REST Endpoint**: Expose a single REST endpoint:
    - `GET /greet/{name}?locale=en` - which returns a greeting message based on the time of day and provided locale.

3. **Configurable Greetings**: The greetings in `application.yaml` should support multiple formats based on time of day and locale. The application should select the appropriate greeting based on the current time and the provided locale.

### Sample Configuration in `application.yaml`:

```yaml
greeting:
  morning:
    en: "Good morning, {name}!"
    es: "¡Buenos días, {name}!"
  afternoon:
    en: "Good afternoon, {name}!"
    es: "¡Buenas tardes, {name}!"
  evening:
    en: "Good evening, {name}!"
    es: "¡Buenas noches, {name}!"
```

### Expected Behavior:

- A request to `GET /greet/John?locale=en` at 9 AM should return: "Good morning, John!"
- A request to `GET /greet/John?locale=es` at 2 PM should return: "¡Buenas tardes, John!"


## Bonus Task: Implementing Security for the Greeting API

Your objective for this bonus task is to implement a security layer for the `/greet` API using Spring Security.

### Requirements:

1. **Spring Security Configuration**: Set up Spring Security in the application to protect the `/greet` endpoint.
2. **Header-Based Authentication**: Ensure that every request to the `/greet` endpoint must have a header `X-Auth-Token` with the value `such-secure-much-wow`.

### Expected Behavior:

- A request to `GET /greet/John?locale=en` without the correct `X-Auth-Token` header should return a `403 Forbidden` response.
- A request to `GET /greet/John?locale=en` with the header `X-Auth-Token: such-secure-much-wow` should proceed and return the appropriate greeting.

### Instructions:

1. Integrate Spring Security into the application.
2. Configure the security settings to require the specific header and value for the `/greet` endpoint.
3. Ensure other endpoints are not affected by this security configuration.
