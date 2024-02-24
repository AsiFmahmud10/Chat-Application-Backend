# Project: Chat Application api
This is a chat Apllication. React is used to devloped the frontend using and Spring boot for the Backend.

Feature :

- Registration
- Login by email
- Realtime chat
- Delete Message
- Log out

## End-point: Get Users
This is a GET request and it is used to get all users except the user who is requesting. This is used to find the other users whom logged in user can talk to.Here "user" contains logged in user information and "contact" contains other users informations. A successful GET response will have a `200 OK` status.
### Method: GET
>```
>http://localhost:8082/api/users
>```
### Response: 200
```json
{
    "user": {
        "id": 4453,
        "email": "tanvir1@gmail.com",
        "firstname": "Tanvir",
        "lastname": "Hasan"
    },
    "contacts": [
        {
            "id": 4352,
            "email": "nasif@gmail.com",
            "firstname": "Nasif",
            "lastname": "Rahman"
        },
        {
            "id": 4353,
            "email": "rahul@gmail.com",
            "firstname": "Rahul",
            "lastname": "Rahman"
        },
        {
            "id": 4354,
            "email": "tanvir@gmail.com",
            "firstname": "Tanvir",
            "lastname": "Rahman"
        },
        {
            "id": 4402,
            "email": "rita@gmail.com",
            "firstname": "Rita",
            "lastname": "Rahman"
        }
    ]
}
```

## End-point: Registration
This request is used for registration purpose. Do not need to provide Id.

A successful POST returns `201 Created` response code.
### Method: POST
>```
>http://localhost:8082/api/users
>```
### Body (**raw**)

```json
{
    "email": "sakib@gmail.com",
    "firstname": "sakib",
    "lastname": "Hasan"

}
```

### Response: 201
```json
{
    "id": 4552,
    "email": "sakib@gmail.com",
    "firstname": "sakib",
    "lastname": "Hasan"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Login By email
This request is used to log in by email.

A successful POST request returns a `200 OK` response code.
### Method: POST
>```
>http://localhost:8082/api/users/email
>```
### Body (**raw**)

```json
{
	"email": "nasif@gmail.com"
}
```

### Response: 200
```json
{
    "id": 4352,
    "email": "nasif@gmail.com",
    "firstname": "Nasif",
    "lastname": "Rahman"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Send Message
The post request is used to send a message to receiver. Do not need to provide any sender ID as we get it from session.

A successful POST returns `201 Created` response code.
### Method: POST
>```
>http://localhost:8082/message
>```
### Body (**raw**)

```json
{
    "receiverId":4353,
     "msg" : "how are you ? "
}
```

### Response: 201
```json
{
    "id": 6657,
    "name": null,
    "content": "how are you ? ",
    "senderId": 4352,
    "receiverId": 4353,
    "createdAt": "2024-02-22T03:36:03.614988"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Find coversasions
This post request is used to get the conversation between two users. We get sender Id from session and receiverId need to send within request.
### Method: POST
>```
>http://localhost:8082/message/reciever
>```
### Body (**raw**)

```json
{	
    "receiverId":4353
}
```

### Response: 200
```json
[
    {
        "id": 6652,
        "name": null,
        "content": "hi",
        "senderId": 4352,
        "receiverId": 4353,
        "createdAt": "2024-02-22T02:57:38.287092"
    },
    {
        "id": 6653,
        "name": null,
        "content": "hello",
        "senderId": 4353,
        "receiverId": 4352,
        "createdAt": "2024-02-22T02:57:46.638302"
    },
    {
        "id": 6654,
        "name": null,
        "content": "now",
        "senderId": 4353,
        "receiverId": 4352,
        "createdAt": "2024-02-22T02:57:49.432836"
    },
    {
        "id": 6655,
        "name": null,
        "content": "what",
        "senderId": 4352,
        "receiverId": 4353,
        "createdAt": "2024-02-22T02:57:52.243524"
    },
    {
        "id": 6656,
        "name": null,
        "content": "dsa",
        "senderId": 4352,
        "receiverId": 4353,
        "createdAt": "2024-02-22T02:58:04.927314"
    }
]

```


## End-point: Delete Message
This request is used to delete a Message using the message id. A successful Delete request response returns a massage with status code 200.
### Method: DELETE
>```
>http://localhost:8082/message/6656
>```

### Response: 200
```json
Message delete Successful
```

## Web Socket
>```
>app/notify-{userId}
>```
user send message at point app/notify-{userId}. We can extract the userId part and work with it. 
>```
>topic/sub
>```
Component subscribe to topic/sub can get message.  

## Setup :
### Bacend
- setup JDK and STS
- Clone the repo and open with STS
- Run the application

Application Default Port : 8082
Database : H2 database is used(so in case of test purpose no need to configure other database)
           you can open the database using http://localhost:8082/h2-console/
           JDBC url : jdbc:h2:~/test 
           username : sa
           password : password

### Frontend 
Forntend Part Link : https://github.com/AsiFmahmud10/Chat-Application/blob/master/README.md
Setup :

- Install Node.js version 18+. 20+
- Clone the Repository
- git clone https://github.com/AsiFmahmud10/Chat-Application.git
- cd /Chat-Application
- npm install
- npm run dev
[ It is required to open it at http://localhost:5174/ . By default it will open it at 5174port but if the port is busy then it will open in other port. Make sure 5174 is open ]



_________________________________________________
Powered By: [postman-to-markdown](https://github.com/bautistaj/postman-to-markdown/)












