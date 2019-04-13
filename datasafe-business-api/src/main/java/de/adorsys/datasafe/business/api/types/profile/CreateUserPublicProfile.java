package de.adorsys.datasafe.business.api.types.profile;

import de.adorsys.datasafe.business.api.types.DFSAccess;
import de.adorsys.datasafe.business.api.types.UserID;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CreateUserPublicProfile implements PublicProfile<DFSAccess> {

    @NonNull
    private final UserID id;

    @NonNull
    private final DFSAccess publicKeys;

    @NonNull
    private final DFSAccess inbox;
}
