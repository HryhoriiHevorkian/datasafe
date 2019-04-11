package de.adorsys.docusafe2.business.api.profile;

import de.adorsys.docusafe2.business.api.types.UserId;
import de.adorsys.docusafe2.business.api.types.UserIdAuth;
import de.adorsys.docusafe2.business.api.types.profile.UserPrivateProfile;
import de.adorsys.docusafe2.business.api.types.profile.UserPublicProfile;

public interface UserProfileService {

    /**
     * Resolves user's public meta-information like folder mapping, etc.
     * @param ofUser resolve request
     * @return resolved user's profile
     */
    UserPublicProfile publicProfile(UserId ofUser);

    /**
     * Resolves user's private meta-information like folder mapping, etc.
     * @param ofUser resolve request
     * @return resolved user's profile
     */
    UserPrivateProfile privateProfile(UserIdAuth ofUser);
}
