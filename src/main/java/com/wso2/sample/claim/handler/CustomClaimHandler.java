package com.wso2.sample.claim.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.authentication.framework.config.model.StepConfig;
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;
import org.wso2.carbon.identity.application.authentication.framework.exception.FrameworkException;
import org.wso2.carbon.identity.application.authentication.framework.handler.claims.impl.DefaultClaimHandler;
import org.wso2.carbon.identity.application.authentication.framework.model.AuthenticatedUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides functionality to manipulate the claims returned by the authenticated framework.
 */
public class CustomClaimHandler extends DefaultClaimHandler {

    private static Log log = LogFactory.getLog(CustomClaimHandler.class);


    public Map<String, String> handleClaimMappings(StepConfig stepConfig,
                                                   AuthenticationContext context,
                                                   Map<String, String> remoteAttributes,
                                                   boolean isFederatedClaims) throws FrameworkException {

        String serviceProviderName = context.getServiceProviderName();
        AuthenticatedUser authenticatedUser;

        if (stepConfig != null) {
            //calling from StepBasedSequenceHandler
            authenticatedUser = stepConfig.getAuthenticatedUser();
        } else {
            //calling from RequestPathBasedSequenceHandler
            authenticatedUser = context.getSequenceConfig().getAuthenticatedUser();
        }

        // let the super class give us the default claims
        Map<String, String> claims = super.handleClaimMappings(stepConfig, context, remoteAttributes, isFederatedClaims);

        if (claims == null) {
            claims = new HashMap<>();
        }

        // get some claims from an external store.
        claims.putAll(fetchExternalClaims(serviceProviderName, authenticatedUser));

        return claims;
    }


    /**
     * Added method to retrieve claims from external sources. This results will be merged to the local claims when
     * returning final claim list, to be added to the SAML response, that is sent back to the SP.
     *
     * @param serviceProviderName : Service Provider name
     * @param authenticatedUser   : The user for whom we require claim values
     * @return
     */
    protected Map<String, String> fetchExternalClaims(String serviceProviderName,
                                                      AuthenticatedUser authenticatedUser) throws FrameworkException {

        // Call an external API an get the claims
        Map<String, String> externalClaims = new HashMap<String, String>();
        externalClaims.put("lucky_number", "734746475");
        externalClaims.put("status", "active");
        return externalClaims;
    }
}
