# **Kouhii ☕**

> Simple API server for Café website

## **Installation and Setup**

1. **Clone the repository**:

   ```sh
   git clone https://github.com/thiennbao/kouhii.git
   cd kouhii/server
   ```

2. **Create a `.env` file**:

   ```properties
   DATASOURCE_URL=jdbc:<your_database_url>
   DATASOURCE_USERNAME=<your_database_username>
   DATASOURCE_PASSWORD=<your_database_password>
   ```

3. **Load the `.env` file**: by exporting or configuring in your IDE.

   ```sh
   export $(grep -v '^#' .env | xargs) # Linux
   ```

4. **Run the Application**: use Maven to build and start the server:

   ```sh
   mvn spring-boot:run
   ```

5. **Access the API**: The API will be available at `http://localhost:8080`
