
Use Case
Build a simple application for the use case of book a show.
The application shall cater to the below users & their requirements;
Buyers – The users should be able to retrieve list of available seats for a show, select 1 or more seats, buy tickets
Admin – The users should be able to update the list of shows and seats. (Add or remove seat capacity)
Bonus
Build test cases for user to login and select 1 or more available seats and buy tickets and show on how it works.
Build a test case to simulate multiple users select same seats and try to book tickets at the same time and show on how the systems responds.
User can hold the seats for a time window of 30 seconds (configurable).
Those Selected seats are blocked for other users until the defined time window.
Requirements
Implement the solution as a Spring Boot Microservice (Java 8+ as backend services). The data shall be in-memory.
Implement the above use case considering object oriented principles and development best practices. The implementation should be a tested working executable.
The project codes to be upload to Github and shared back to us for offline review by Thursday, 26 May 2022


APIs
- retrieve list of available seats for a show
    - GET /seats/{showID}
- select 1 or more seats
    - POST /seats/select
    - params: seatIDs
    - return bookingID & seats
- buy tickets
    - POST /booking/{bookingID}
- Admin - update list of shows
    - POST /show/add
    - params: showID & show details
    - POST /show/update
    - params: showID & show details
- Admin - update list of seats for a show
    - POST /seats/add
    - params: showID, seatIDs

Entities
- Show
    showID, showName
- Seats
    seatID, showID, status
- Booking
    bookingID, userId, List<Seats>, Status

Repositories
- Shows
    List<Show>
- Seats
    List<Seat>
- Bookings
    List<Booking>

Use Case flow
1. Admin adds shows
    Input - {showID: 1, showName: TestShow1}
2. Admin adds seats
    Input - {showID: 1, seats:[A1,A2,A3]}
3. User retrieves seats
    Input - {showId:1}
    Output - {seats:[A1,A2,A3]}
4. User selects Seats
    Input - {seats:[A2,A3]}
    Output - {BookingID:1, seats:[A2,A3], Status:HOLD}
5. Users buys tickets
    Input - {BookingID: 1}
    Output - {BookingID:1, seats:[A2,A3], Status:BOOKED}
