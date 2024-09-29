# Online Book Store Project Description

## 1. Technical framework used
- SpringBOOT
- H2 in-memory database
- Spring Data JPA
- Spring DOC OpenAPI 3/Swagger
- RESTful
- JUint
- Hamcrest

## 2. How to run the system
### 2.1 Pre-dependency environment
- JDK17, Used to provide the Java runtime environment
- Git, Used to download source code from Github
- IntelliJ IDEA, Used to browse source code and run test cases, etc.

### 2.2 Running the Online Book Store system
- Download the source code from [github/online-bookstore](https://github.com/henryzhengcn/online-bookstore) to a local directory
- Use IntelliJ IDEA to open the downloaded Online Book Store project
- Open the springboot startup file of OnlineBookStoreApplication and click the green triangle button to run the system
![Launch](https://github.com/henryzhengcn/online-bookstore/blob/main/images/0-launch.png)
- Add new books through [Book Management Module/Add Book Function](http://localhost:8080/onlinebookstore/swagger-ui/index.html#/Book%20Management%20Module%20API/add_1). Detailed function and data descriptions are available on the documentation page.
![AddBook](https://github.com/henryzhengcn/online-bookstore/blob/main/images/1-1AddBook.png)
- Query the added books through [Book Management Module/Query Book Function](http://localhost:8080/onlinebookstore/swagger-ui/index.html#/Book%20Management%20Module%20API/findAll_1)
![QueryBook](https://github.com/henryzhengcn/online-bookstore/blob/main/images/1-2QueryBook.png)
- Add your favorite books to the shopping cart through the [Shopping Cart Module/Add to Cart Function](http://localhost:8080/onlinebookstore/swagger-ui/index.html#/Shopping%20Cart%20Module%20API/add)
![AddCart](https://github.com/henryzhengcn/online-bookstore/blob/main/images/2-1AddCart.png)
- Query the books that have been added to the shopping cart through [Shopping Cart Module/Query Shopping Cart Function](http://localhost:8080/onlinebookstore/swagger-ui/index.html#/Shopping%20Cart%20Module%20API/findAll)
![QueryCart](https://github.com/henryzhengcn/online-bookstore/blob/main/images/2-2QueryCart.png)
- Settle the final selected goods through [Trading Module/Trading Settlement Function](http://localhost:8080/onlinebookstore/swagger-ui/index.html#/Trading%20Module%20API/checkout)
![Checkout](https://github.com/henryzhengcn/online-bookstore/blob/main/images/3-1Checkout.png)
![Checkout](https://github.com/henryzhengcn/online-bookstore/blob/main/images/3-2Checkout.png)

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "ShoppingCarts": [
            {
                "id": 8,
                "goodsId": 15,
                "goodsQuantity": 5.0,
                "createTime": "2024-09-27T03:57:30.527+00:00",
                "updateTime": "2024-09-27T04:01:25.980+00:00"
            },
            {
                "id": 9,
                "goodsId": 16,
                "goodsQuantity": 3.0,
                "createTime": "2024-09-27T04:02:50.418+00:00",
                "updateTime": "2024-09-27T04:03:08.236+00:00"
            },
            {
                "id": 10,
                "goodsId": 17,
                "goodsQuantity": 2.0,
                "createTime": "2024-09-27T04:03:23.745+00:00",
                "updateTime": "2024-09-27T04:03:40.014+00:00"
            }
        ],
        "Checkout": {
            "totalAmount": 313.6,
            "totalQuantity": 10.0
        },
        "InvalidShoppingCarts": [],
        "BoughtGoods": [
            {
                "id": 15,
                "title": "The World Is Flat",
                "author": "Thomas L. Friedman",
                "price": 27.5,
                "category": "sci-tech",
                "createTime": "2024-09-27T02:45:36.391+00:00",
                "updateTime": null
            },
            {
                "id": 16,
                "title": "Mao: A Biography",
                "author": "Ross Terrill",
                "price": 36.7,
                "category": "history",
                "createTime": "2024-09-27T03:47:19.571+00:00",
                "updateTime": null
            },
            {
                "id": 17,
                "title": "1,000 Places to See Before You Die",
                "author": "Patricia Schultz",
                "price": 33.0,
                "category": "travel",
                "createTime": "2024-09-27T03:48:14.446+00:00",
                "updateTime": null
            }
        ]
    }
}
```

### 2.3 Running the Online Book Store Test Case
- Open the test case under the project source code test/java (such as TradingServiceImplTest), and click the green triangle button to run all test cases
![TestCase](https://github.com/henryzhengcn/online-bookstore/blob/main/images/4-1TestCase.png)

### 3. Related resources
- Source code location: https://github.com/henryzhengcn/online-bookstore
- Accessing URL and API description of the Online Book Store system: http://localhost:8080/onlinebookstore/swagger-ui/index.html
- Database backend address of the Online Book Store system: http://localhost:8080/onlinebookstore/h2/login.do
  - Database JDBC URL: jdbc:h2:~/onlinebookstore
  - User: root
  - Password: 123456
![DB](https://github.com/henryzhengcn/online-bookstore/blob/main/images/5-1DB.png)
![DB](https://github.com/henryzhengcn/online-bookstore/blob/main/images/5-2DB.png)
- The database initialization schema is located in resources/db. The system will automatically perform database initialization without manual execution.
