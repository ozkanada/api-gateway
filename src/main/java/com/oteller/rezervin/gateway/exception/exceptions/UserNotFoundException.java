package com.oteller.rezervin.gateway.exception.exceptions;

import com.oteller.rezervin.gateway.exception.enums.IFriendlyMessageCode;
import com.oteller.rezervin.gateway.exception.utils.FriendlyMessageUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class UserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8872841417774262485L;
	private final IFriendlyMessageCode friendlyMessageCode;

    public UserNotFoundException(IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode));
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[UserNotFoundException] -> message {} developer message {}",FriendlyMessageUtils.getFriendlyMessage(friendlyMessageCode), message);
    }
}
