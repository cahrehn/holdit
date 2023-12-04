# Hold It!
A reservation system

### Context
We are designing and building an online restaurant reservation system for a popular restaurant in Manhattan. Restaurants operate on thin margins and they rely on reservation system info to help accurately plan and optimize their food and ingredients purchases. Our service will help them do that.

* Create an API endpoint for creating reservations that allows the API consumer to specify the following attributes:
    1. party size
    1. reservation date
    1. reservation slot
    1. party name
    1. contact phone
    1. contact email address.
* Create an API endpoint for listing all reservations at the restaurant. The API consumer should be able to filter based on reservation attributes:
    1. party size
    1. reservation date
    1. reservation slot
    1. party name
    1. contact phone
    1. contact email address
* Provide documentation that describes how to build and run your code, and include sample API payloads.
* Your code is well-tested.
* The reservation creation endpoint should account for the constraint that table capacity in a restaurant is limited.

### Assumptions
* Reservation managers have a way to initialize and manage reservation availability.
    * Holiday schedules, openings, and closings.
    * Capacity changes on certain days.

To simplify the initial implementation:
* Reservations can only be made for the current day.
* Reservations can only be made on the hour.
* Restaurant customers always arrive on time.
* Restaurant customers always leave within an hour.
* Reservations are available 7 days a week between 5pm and 11pm, inclusive.
* Seating is communal or reconfigurable, so there's just a pool of available seats rather than a certain number of tables that can accomodate different party sizes.

In reality, we'd need to talk with our restaurant manager users to better understand:
* How do they think about reservation time slots?
    * Do they need 30-minute intervals? 15?
    * If they have e.g. 5 two-tops opening up at 7:00pm, do they want to let someone make a reservation at 6:45pm assuming one of them will leave early?
* How long do their customers typically spend at the restaurant and e.g. how does party size affects the length of time they spend at the restaurant?
* What range of party sizes do they want to allow? For example, even if they can physically accomodate two groups of 10 at the same time, maybe that creates too many headaches in the kitchen.

### Proposed Design
Spring Boot application that exposes the two endpoints.

#### Create
`POST /api/v1/reservation/create`
```
{
  "partyName": "Wyatt Rowan",
  "partySize": 2,
  "reservationDate": "2023-12-31",
  "reservationSlot": "6",
  "contactPhone": "253-761-5057",
  "contactEmail": "wrowan0@themeforest.net"
}
```

Validate that the general shape of the request is valid e.g. required fields: party size, reservation date, reservation slot, party name. We probably don't need both the phone and email address, but we should require one or the other and leave it up to the user and/or UX design to decide which to provide. I expect that somewhere in the application (maybe on the frontend) the reservation date will default to the current day and I considered doing that here rather than requiring it, but I can see that leading to customer service issues where people want to change reservations that they accidentally made for the current day. Better to return a validation error.

```
if (reservation is possible based on day, time, and capacity configuration) {
    if (reservation is available) {
        save and return confirmation message
    }
    return error
}
return error
```

#### List
`POST /api/v1/reservation/list`

I'm making this a `POST` so we can submit a request object that contains whatever combination of attributes we want without needing a bunch of different purely-RESTful paths and to be able to more easily add attributes as requirements change. It also means we don't need to worry about cached results that a `GET` request might return.

If all the attributes are empty i.e. the request is `{}`, we can show all the reservations for the current day.

In general, we probably want to sort the reservations by date and time. If a date isn't provided, we default to showing today's reservations.

