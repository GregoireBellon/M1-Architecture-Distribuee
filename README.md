# Distributed architecture project
This repository host the 3 components of the project (the client, the internal CRM and the virtual CRM). 

It's kind of a monorepo : the thrift types are shared between every component, and you can run gradle commands from the root.

The application is fully tested using a dockerized environnment and some github pipeline. The dockerfiles and docker compose files are in the docker directory. The pipelines are deglared in the .github directory, and are executed on every push and merge on the dev branch.

## How to run it
For the project to work, evey component needs to be up. You need to have thrift installed on your computer (it is compatible with version 0.14).

### InternalCRM
You need to have Java 17 installed on your computer.

In the InternalCRM folder : 

Just run 
```
./gradlew run
```

You can also test the application, by running

```
./gradlew test
```

### VirtualCRM
You need to have Java 17 installed on your computer.

In the VirtualCRM folder :

Copy ex.env.properties to env.properties and complete it with your credentials

Then run 

```
./gradlew bootRun
```

You can also test the application, by running

```
./gradlew test
```
### SimpleClient
You need to have node 18 (or highter) installed on your computer, and a recent thrift version.
Into ./SimpleClient :
```
npm install
npm run build 
```
then, to get the command list : 
```
npm run start help
```

⚠️THE TWO CRMs MUST BE RUNNING IN ORDER FOR THE FOLLOWING TO WORK⚠️

to query the CRMs depending on the date :
```
npm run start date 20/02/2020 25/09/2023
```
to query the CRMs depending on the salary :
```
npm run start salary 20000 70000
```