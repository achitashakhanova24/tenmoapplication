# Tenmo Application Apr 2023 - Apr 2023

**Short Description:** This is a Venmo-like money transfer application using the MVC pattern, Restful APIs, and the Spring framework on the server-side. PostgreSQL is utilized for efficient database management, providing customers with secure and seamless money transfer capabilities.

## Detailed Description
- **Controllers:** Handle the interaction between the user interface and the underlying logic. They are responsible for managing user input, authentication, and triggering the appropriate actions in response to user requests.

- **Models:** Represent the core entities in the system such as the user, account, and transfer. These models define the structure of the corresponding database tables, including fields like username, password, balance, etc.

- **DAOs (Data Access Objects):** Serve as the intermediaries between the controllers and the database, encapsulating the logic for retrieving, updating, and creating records. They contribute to the separation of concerns, ensuring that the database-related operations are distinct from the business logic.

- **Database Tables:** Store persistent data, capturing user details, account information, and transfer records. Each table is designed to maintain data integrity and support efficient retrieval and modification operations.

For user registration, a controller method interacts with a UserDAO to create a new user with an initial balance. The login functionality authenticates users and issues an authentication token for subsequent interactions.

To implement fund transfers, a TransferController handles the transfer process, ensuring the sender has sufficient funds, the recipient is valid, and updating the balances accordingly. Transfers will be stored in a TransferDAO, with each transfer having a unique identifier and an initial status of "Approved." Controllers for account balance, transaction history, and transfer details provide the necessary endpoints to fulfill these requirements.
