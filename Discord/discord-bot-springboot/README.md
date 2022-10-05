# JDA Discord SpringBoot project

This is a small Discord bot project that utilizes Java SpringBoot framework.

## MVC Architecture example:

- This, below, is a simple visualisation of the MVC architecture used in the project.

<style>
    .center{
        text-align: center;
        margin: auto;
        border: 1px solid;
        padding: 10px:
    }
</style>
<div class="center">

```mermaid
    classDiagram
    class InsertUser{
        -UserService userService
        +void execute(SlashCommandEvent event)
        +List resolveSuccess(List members)
    }
    class User{
        -String username
        -String discriminator
        -String uid
        -List roles
        +get()
        +set()
    }
    class UserRepository{
        <<interface>>
        +findByID(Stirng id)
        +findByDisc(String disc)
    }
    class UserService{
        -UserRepository repo
        +get()
        +insert()
        +update()
        +delete()
    }

    SlashCommand <|-- InsertUser
    InsertUser --> UserService
    UserService --> UserRepository
    InsertUser --> User
    UserService --> User
    UserRepository --> User
    UserRepository --> UserService
    UserService --> InsertUser

```

</div>
