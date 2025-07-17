package co.smarttechie.integrationtest

import co.smarttechie.model.ItemDto
import co.smarttechie.service.ItemService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = ["spring.jpa.hibernate.ddl-auto=create-drop"])
public class ItemControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ItemService itemService;

    Long createdId;
    String initialETag;
    ObjectMapper objectMapper;

    void setup() {
        objectMapper = new ObjectMapper()
        ItemDto created = itemService.createItem(new ItemDto("Test Item", "Initial Description"))
        createdId = created.id()
        initialETag = "\"${created.version()}\""
    }

    void "GET /items/{id} returns item with ETag"() {
        when: "The client requests the item by id"
                MvcResult response = mockMvc.perform(get("/items/${createdId}"))
                        .andExpect(status().isOk())
                        .andExpect(header().string("ETag", initialETag))
                        .andReturn();

        then: "The response contains the item data with ETag header"
                String json = response.response.contentAsString;
                json.contains("Test Item");
    }

    void "PUT /items/{id} with correct If-Match updates item successfully"() {
        given: "An updated item JSON with matching If-Match header"

                String updatedJson = objectMapper.writeValueAsString([
                        name       : "Updated Name",
                        description: "Initial Description"
                ]);

        when: "The client sends PUT request with correct If-Match header"
                MvcResult response = mockMvc.perform(put("/items/${createdId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("If-Match", initialETag)
                        .content(updatedJson))
                        .andExpect(status().isOk())
                        .andExpect(header().exists("ETag"))
                        .andReturn();

        then: "The response contains updated item data"
                String json = response.response.contentAsString;
                json.contains("Updated Name");
    }

    void "PUT /items/{id} with missing If-Match returns 412"() {
        given: "An updated item JSON without If-Match header"
                String updatedJson = objectMapper.writeValueAsString([
                        name       : "Updated Name",
                        description: "Updated Description",
                ]);

        when: "The client sends PUT request without If-Match header"
                ResultActions result = mockMvc.perform(put("/items/${createdId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson));

        then: "The server responds with 412 Precondition Failed"
               result.andExpect(status().isPreconditionFailed());
    }

    void "PUT /items/{id} with wrong If-Match returns 412"() {
        given: "An updated item JSON with wrong If-Match header"
                String updatedJson = objectMapper.writeValueAsString([
                        name       : "Updated Name",
                        description: "Updated Description",
                ]);

        when: "The client sends PUT request with incorrect If-Match header"
                ResultActions result = mockMvc.perform(put("/items/${createdId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("If-Match", "\"999\"")
                        .content(updatedJson));

        then: "The server responds with 412 Precondition Failed"
               result.andExpect(status().isPreconditionFailed());
    }

    void "PUT /items/{nonexistentId} returns 404"() {
        given: "An updated item JSON for non-existent id"
                String updatedJson = objectMapper.writeValueAsString([
                        name       : "Doesn't Exist",
                        description: "No desc",
                ]);

        when: "The client sends PUT request to non-existent id"
                ResultActions result = mockMvc.perform(put("/items/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("If-Match", initialETag)
                        .content(updatedJson));

        then: "The server responds with 404 Not Found"
                result.andExpect(status().isNotFound());
    }
}
