package com.baioo.tech;

import com.atlassian.jira.bc.user.search.UserSearchService;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.customfields.converters.UserConverter;
import com.atlassian.jira.issue.customfields.searchers.UserPickerGroupSearcher;
import com.atlassian.jira.issue.customfields.searchers.transformer.CustomFieldInputHelper;
import com.atlassian.jira.jql.operand.JqlOperandResolver;
import com.atlassian.jira.jql.resolver.UserResolver;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.template.VelocityTemplatingEngine;
import com.atlassian.jira.user.UserFilterManager;
import com.atlassian.jira.user.UserHistoryManager;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.jira.util.EmailFormatter;
import com.atlassian.jira.util.velocity.VelocityRequestContextFactory;
import com.atlassian.jira.web.FieldVisibilityManager;
import org.springframework.beans.factory.annotation.Qualifier;

public class PeopleSelectSearcher extends UserPickerGroupSearcher{
    public PeopleSelectSearcher(@Qualifier("userConverter") UserConverter userConverter, JiraAuthenticationContext jiraAuthenticationContext, VelocityRequestContextFactory velocityRequestContextFactory, VelocityTemplatingEngine templatingEngine, ApplicationProperties applicationProperties, UserSearchService userSearchService, FieldVisibilityManager fieldVisibilityManager, JqlOperandResolver jqlOperandResolver, UserResolver userResolver, UserManager userManager, CustomFieldInputHelper customFieldInputHelper, GroupManager groupManager, PermissionManager permissionManager, UserHistoryManager userHistoryManager, UserFilterManager userFilterManager, EmailFormatter emailFormatter) {
        super(userConverter, jiraAuthenticationContext, velocityRequestContextFactory, templatingEngine, applicationProperties, userSearchService, fieldVisibilityManager, jqlOperandResolver, userResolver, userManager, customFieldInputHelper, groupManager, permissionManager, userHistoryManager, userFilterManager, emailFormatter);
    }
}
