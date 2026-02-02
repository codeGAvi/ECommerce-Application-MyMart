 # use this annotation to write Junit test for controller layer

1. @AutoConfigureMockMvc annotation. This annotation creates an instance of MockMvc
2. @WebMvcTest does not detect dependencies needed for the controller automatically, 
so we’ve to Mock them. While @SpringBootTest does it automatically


// date 15/01/2026 
# add Pageable / Pagination in product : this is a request object , it says that which page and how many pages should come in page
..> use map: for mapping the Page object with Product response 
..>  calculate total pages required for hold the elemnts = ceil(totalElementsinDB/currentSize)

// date 18/01/2026
# Implement Sorting with Pagination 
  ... user Sort object , used Sort.by(same as ORDER BY in DB)
  .... sort based on all column of product with ascending as well as on descending 
  
// Implement filter : user can filter the product by category, price etc.

// Implemented dynamic price range filtering using Spring data JPA's derived queries, combined with pageable, 
  where user can select the price and then filter the product


// Transactional flow
🎯 Goal of Transactional Flow

Order place karte waqt ye sab ek hi unit me ho:

1️⃣ Product fetch
2️⃣ Stock check
3️⃣ Stock reduce  // stock validation
4️⃣ Order save
5️⃣ OrderItems save
6️⃣ Total amount calculate
7️⃣ (Fail ho to rollback everything)
##.
“I implemented multi-item order placement using @Transactional 
to ensure atomic stock updates and order persistence with rollback on failure.”

 
// ASYNC EMAIL NOTIFICATION
Integrated asynchronous email notifications to send order confirmations without blocking API responses, 
improving user experience and performance



//DOCKERIZATION 
--> Docker helps package my Spring Boot application along with its runtime environment into a container, so it runs consistently across different systems.
In my project, I containerized the backend to simplify deployment and avoid environment-specific issues.
This makes the application portable, easier to deploy, and production-ready.
