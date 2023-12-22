package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.internal.web.UserTestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProfileRestControllerTest extends AbstractControllerTest {

    private final String GET_PROFILE_URL = "/api/profile";
    @Mock
    private AuthUser user;

    @Test
    public void getWithUnauthorizedUser() throws Exception {
        perform(MockMvcRequestBuilders.get(GET_PROFILE_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    public void getWithAuthorizedUser() throws Exception {
        perform(MockMvcRequestBuilders.get(GET_PROFILE_URL)
                .content(String.valueOf(1L)))
                .andExpect(status().isOk());
    }
}