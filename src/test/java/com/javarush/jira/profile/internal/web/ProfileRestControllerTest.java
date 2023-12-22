package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.internal.web.UserTestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProfileRestControllerTest extends AbstractControllerTest {

    private final String REST_URL = "/api/profile";
    private final String FIRST_ID = "1";
    private final String ID_STRING = "id";


    static AuthUser authUser = Mockito.mock(AuthUser.class);

    @BeforeAll
    public static void initAuthUser() {
        Mockito.when(authUser.id()).thenReturn(1L);
    }

    @Test
    public void getWithUnauthorizedUser() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(UserTestData.USER_MAIL)
    public void getWithAuthorizedUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param(ID_STRING, FIRST_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithUserDetails(UserTestData.USER_MAIL)
    public void updateTestWithNewTo() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(ProfileTestData.getNewTo())))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(UserTestData.USER_MAIL)
    public void updateTestWithInvalidTo() throws Exception{
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(ProfileTestData.getInvalidTo())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails(UserTestData.USER_MAIL)
    public void updateTestWithUnknownNotificationTo() throws Exception{
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(ProfileTestData.getWithUnknownNotificationTo())))
                .andExpect(status().is4xxClientError());
    }
}