package de.adorsys.datasafe.directory.impl.profile.operations;

import de.adorsys.datasafe.directory.api.types.UserPrivateProfile;
import de.adorsys.datasafe.directory.api.types.UserPublicProfile;
import de.adorsys.datasafe.encrypiton.api.types.UserID;

import java.util.Map;

/**
 * User profile cache. Since users' profile is changed rarely it is quite safe to cache them.
 */
public interface UserProfileCache {

    Map<UserID, UserPublicProfile> getPublicProfile();
    Map<UserID, UserPrivateProfile> getPrivateProfile();
}
