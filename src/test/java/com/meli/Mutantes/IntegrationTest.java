package com.meli.Mutantes;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class IntegrationTest extends MutantesApplicationTests{

    private MockMvc mockMvc;


    @Before
    public void init() {
        mockMvc = standaloneSetup(mutantesRestController, statsRestController).build();
    }

    @Test
    public void postMutantDnaAndGetStats() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/mutant")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mutant").value(true));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/mutant")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"dna\": [\"XXXX\",\"XXXX\",\"XXXX\",\"XXXX\"]}")
                )
                .andExpect(status().isForbidden());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/mutant")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/mutant")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"dna\": [\"ATGCGA\",\"TCACTG\"]}")
                )
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/stats")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countMutantDna").value(1))
                .andExpect(jsonPath("$.countHumanDna").value(1))
                .andExpect(jsonPath("$.ratio").value(0.5f));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/mutant/random")
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void main() {
        MutantesApplication.main(new String[]{});
    }

}
