# use this annotation to write Junit test for controller layer

1. @AutoConfigureMockMvc annotation. This annotation creates an instance of MockMvc
2. @WebMvcTest does not detect dependencies needed for the controller automatically, 
so we’ve to Mock them. While @SpringBootTest does it automatically
