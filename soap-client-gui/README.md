# Design Prompt

You are a professional React developer. Please implement a web site.

## The functional requirements are following:

- let user upload one and only one excel file
- call an external http API which accept the uploaded excel file as multipart/form-data with parameter name "file"
- During waiting api response, show a component let user know that they have to wait
- The external api will response an top level array of objects, each object represent the processing result of each row in excel, with fields: is success, messages (may be the error message). show these information in a table
- if api response with code is not 200, means some exception occurred in backend service, and the caller (this web site) have to handle it. possible error status codes are 400, 404, and 500.
- If api does not response after timeout limit, terminate it and show alert to user.
- the api end point url and timeout limit should be configurable via environment variable.

## Non-functional requirements:

- use react (create project using vite) and ant design with typescript, component should be the function component style, avoid using React.FC.
- use fp-ts to implement logic as much as possible
- Explicit define interfaces and types instead of using type literal
- Provide test cases in Cypress, mock backend api using Mirage.

# Self Check

- Omitted import
- Initial state should be empty array instead of null or undefined