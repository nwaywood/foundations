Start an sbt shell by running the command sbt in your terminal 


## Actions

- Compile - `exercises/compile`
- Run tests - `exercises/test`
- Run test files with regex - `exercises/testOnly *ValueFun*`


## Extra

All commands start with exercises because they apply to the exercises module where you will answer questions and write tests. There is also an answers module containing all the answers for the course. If you want to avoid to type exercises before every command, you can "move" to the exercises module using the following command in sbt shell:

`project exercises`

If you want to continuously run commands in the background, you can prepend the commands with "~". For  example, you can run all tests on every file save using:

`~ exercises/test`
