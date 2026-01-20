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
  wehere user can select the prince and then filter the product
