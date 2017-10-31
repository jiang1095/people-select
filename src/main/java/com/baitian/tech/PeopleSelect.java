package com.baitian.tech;

import com.atlassian.jira.bc.user.search.UserSearchService;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.CustomFieldUtils;
import com.atlassian.jira.issue.customfields.config.item.UserFilterConfigItem;
import com.atlassian.jira.issue.customfields.converters.MultiUserConverter;
import com.atlassian.jira.issue.customfields.impl.MultiUserCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.rest.json.UserBeanFactory;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.template.soy.SoyTemplateRendererProvider;
import com.atlassian.jira.user.UserFilterManager;
import com.atlassian.jira.web.FieldVisibilityManager;
import com.atlassian.soy.renderer.SoyTemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

public class PeopleSelect extends MultiUserCFType {
    Logger logger = LoggerFactory.getLogger(PeopleSelect.class);

    private final GroupManager groupManager;
    private final ProjectRoleManager projectRoleManager;
    private final SoyTemplateRenderer soyTemplateRenderer;
    private final UserFilterManager userFilterManager;

    public PeopleSelect(CustomFieldValuePersister customFieldValuePersister, GenericConfigManager genericConfigManager, MultiUserConverter multiUserConverter, ApplicationProperties applicationProperties, JiraAuthenticationContext authenticationContext, UserSearchService searchService, FieldVisibilityManager fieldVisibilityManager, JiraBaseUrls jiraBaseUrls, UserBeanFactory userBeanFactory, GroupManager groupManager, ProjectRoleManager projectRoleManager, SoyTemplateRendererProvider soyTemplateRendererProvider, UserFilterManager userFilterManager) {
        super(customFieldValuePersister, genericConfigManager, multiUserConverter, applicationProperties, authenticationContext, searchService, fieldVisibilityManager, jiraBaseUrls, userBeanFactory);
        this.groupManager = groupManager;
        this.projectRoleManager = projectRoleManager;
        this.soyTemplateRenderer = soyTemplateRendererProvider.getRenderer();
        this.userFilterManager = userFilterManager;
    }

    @Override
    public Map<String, Object> getVelocityParameters(Issue issue, CustomField field, FieldLayoutItem fieldLayoutItem) {
        Map<String, Object> velocityParams = super.getVelocityParameters(issue, field, fieldLayoutItem);
        velocityParams.put(CustomFieldUtils.getParamKeyRequireProjectIds(), Boolean.TRUE);
        return velocityParams;
    }

    @Override
    public List<FieldConfigItemType> getConfigurationItemTypes() {
        List<FieldConfigItemType> types = super.getConfigurationItemTypes();
        types.add(new UserFilterConfigItem(this.groupManager, this.projectRoleManager, this.soyTemplateRenderer, this.userFilterManager));
        return types;
    }
}
