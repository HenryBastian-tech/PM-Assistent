# PM-Assistant

PM-Assistant is a web-based chat application designed to connect with an AI service for generating responses based on user input. This application is built with a Spring Boot backend, which connects to Azure OpenAI for AI-driven responses and optionally integrates with Atlassian Jira for project management functionalities.

## Features

**User Input with Dynamic Responses**: Users can type messages into a chat interface, which the application sends to an AI endpoint for response generation.
Loading Spinner: A spinner shows while the application fetches a response, providing a smooth user experience.

**Markdown Support**: AI-generated responses are formatted using Markdown for better readability.

**Atlassian Jira Integration**: Uses Atlassian Jira's API (optional) for fetching data or interacting with Jira projects if a valid token is provided.
Technologies Used

**Spring Boot**: Backend framework for RESTful API management and server logic.

**Azure OpenAI**: AI model provider for generating chat responses based on user messages.

**HTML/CSS/JavaScript**: Frontend technologies for the UI, with a loading spinner for smooth interactions.

**Marked.js**: JavaScript library for Markdown parsing and rendering.

## Prerequisites
Java 11 or newer
Node.js (optional, for managing frontend dependencies)
Access to Azure OpenAI with API key and endpoint.
(Optional) Atlassian Jira API token for Jira integration.

## Setup
1. Clone the Repository

```bash
git clone https://github.com/HenryBastian-tech/PM-Assistant.git
cd PM-Assistant
```

2. Configure the application.properties File

The application requires specific environment variables in `src/main/resources/application.properties` for connecting to Azure OpenAI and (optionally) Jira.

Fill out the properties file with your configuration:

```properties
spring.application.name=PmAssistent
spring.ai.chat.client.enabled=true
spring.ai.azure.openai.api-key=YOUR_AZURE_OPENAI_API_KEY
spring.ai.azure.openai.endpoint=YOUR_AZURE_OPENAI_ENDPOINT
spring.ai.azure.openai.chat.options.deployment-name=YOUR_DEPLOYMENT_NAME
spring.ai.azure.openai.chat.options.temperature=0.7
atlassian.jira.bearer-token=YOUR_JIRA_BEARER_TOKEN
```

3. Run the Application

In the project root directory, use Maven to run the Spring Boot application:

```bash
java -jar target/PmAssistent-0.0.1-SNAPSHOT.jar
```
The application will start on the default port, typically http://localhost:8080.


## Frontend Usage

- Open index.html in a web browser.
- Read the welcome message and self-introduction.
- In the input box, enter a message and press Enter.
- Your message will display on the screen, and the input box will disappear while the application fetches a response.
- The loading spinner indicates that the application is processing the message.
- Once the response is received, it is rendered in Markdown format beneath your message.

## Author
Created by Henry Bastian (TX-UL).