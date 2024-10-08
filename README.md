# Hoover project

## Assumptions

### Project Structure

The solution is implemented as a maven project using Java 11 and Spring Boot 

No database is used due to the simplicity of the task required

DTOs have been used for the request and response objects


### Validations

The following validations have been added for the input:

roomSize:

`Mandatory, must be 2 NotNull numbers exactly with value >= 1`

coords:

`Mandatory, must be 2 NotNull numbers exactly with value >= 0 and smaller than the respective room dimension`
e.g., A room with size 10,10 has the top right corner with coordinates 9,9 (as they depict the bottom left corner) and as a result values > 9 are consider invalid or out of bounds

patches:

`Mandatory as input but can be an empty list, if a patch is given then it must be 2 NotNull numbers exactly with value >= 0`

instructions:

`Mandatory and not empty, with only the characters N, S, W, E allowed. Maximun length has been defined as 100 but it can be removed`

## Installation
1. **Clone the repository**:
    ```sh
    git clone
    ```

2. **Build the project using Maven**:
    ```sh
    mvn clean install
    ```

3. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```

## Execution

### 1st Approach

A sample postman collection has been added in the project under the name Hoover.postman_collection.json

After running the application and importing the collection to Postman, it is already configured to call the endpoint localhost:9876/hoover/execute with a sample body